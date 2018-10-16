package cn.anyoufang.service.Impl;

import cn.anyoufang.entity.*;
import cn.anyoufang.mapper.SpMemberLoginMapper;
import cn.anyoufang.mapper.SpMemberMapper;
import cn.anyoufang.mapper.SpMemberWxMapper;
import cn.anyoufang.service.LoginService;
import cn.anyoufang.utils.*;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daiping
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    private SpMemberMapper memberMapper;

    @Autowired
    private SpMemberWxMapper wxMapper;

    @Autowired
    private SpMemberLoginMapper loginMapper;

    @Value("${ANYOUJIACODE}")
    private String tempCode;
    @Value("${code_overtime}")
    private String overtime;
    private static final String salt = "575gh5rr556Dfhr67Ohrt8";


    @Override
    @Transactional
    public AnyoujiaResult memberRegister(String account, String pwd, WeiXinVO weiXinVO) throws Exception {
        SpMemberExample example = new SpMemberExample();
        SpMemberExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(account);
        List<SpMember> list = memberMapper.selectByExample(example);
        if (list.size() > 0) {
            return AnyoujiaResult.build(400,"该用户已注册，请登录");
        }
        SpMember member = new SpMember();
        if(weiXinVO !=null) {
            int wxid = weiXinVO.getWxid();
            member.setPhone(account);
            member.setPassword(Md5Utils.md5(pwd, "utf-8"));
            member.setWx(wxid);
            member.setBname(weiXinVO.getNickname());
            int uid = memberMapper.insertSelective(member);
            SpMemberWx wxmember = new SpMemberWx();
            wxmember.setUid(uid);
            wxmember.setWx(wxid);
            //根据id更新数据库
            int active = wxMapper.updateByPrimaryKeySelective(wxmember);
            if (uid != 0 && active == 1) {
                return AnyoujiaResult.build(200,"注册成功");
            }
            return AnyoujiaResult.build(400,"注册失败");
        }else {
            Map<String,Object> map = doRegister(account, Md5Utils.md5(pwd,"utf-8"));
            Integer status = (Integer) map.get("status");
            if(status == 200) {
                member.setPhone(account);
                member.setPassword(Md5Utils.md5(pwd, "utf-8"));
                int uid = memberMapper.insertSelective(member);
                if (uid != 0) {
                    return AnyoujiaResult.build(200, "注册成功");
                }
            }else {
                AnyoujiaResult.build(status,(String)map.get("msg"));
            }
        }
        return AnyoujiaResult.build(400,"注册失败");

    }


    @Override
    public Map<String,Object> memberLoginByPwd(String account, String pwd, String ip) throws Exception {
        List<SpMember> list = memberMapper.selectByExample(createExample(account));
        if(list.size() == 0) {
            return new Null();
        }
        Map<String,Object> data = new HashMap<>();
        SpMember user = list.get(0);
        String tempPwd = pwd;
        String md5Pwd = Md5Utils.md5(tempPwd,"UTF-8");
        if(!md5Pwd.equals(user.getPassword())) {
            return null;
        }
           Map<String,Object> res =  doLogin(account,md5Pwd,ip);
           Integer status = (Integer) res.get("status");
            if(status == 200) {
                String encrySession = (String) res.get("session");
                Map<String,Object> map =  parseSession(encrySession);
                LOG.info("登陆成功时的sessionid: "+encrySession );
                SpMember member = list.get(0);
                SpMemberLogin loginRecord = new SpMemberLogin();
                int current = (int) (System.currentTimeMillis() / 1000);
                loginRecord.setLogintime(current);
                loginRecord.setStatus(true);
                loginRecord.setType(true);
                loginRecord.setIp(ip);
                loginRecord.setUid(member.getUid());
                loginMapper.insertSelective(loginRecord);
                data.put("member",member);
                data.put("token",encrySession);
                data.put("status",status);
                Object over =  map.get("overtime");
                if(over == null) {
                    return null;
                }
                String overtime = String.valueOf(over);
                long endtime = new Long(overtime) * 1000;
                long now = System.currentTimeMillis();
                int expire = (int) ((endtime - now)/1000);
                RedisUtils.setex(Md5Utils.md5((String) map.get("username"),"utf-8"),"1",expire);
                return data;
            }else {
                data.put("msg",res.get("msg"));
                data.put("status",res.get("status"));
                return data;
            }

    }


    @Override
    public Map<String,Object>  memberLoginByVerifyCode(String phone, String code, String ip) throws Exception {
        if(phone ==  null || code == null) {
            return new Null();
        }
        List<SpMember> list = memberMapper.selectByExample(createExample(phone));
        if(list.size() == 0) {
            return new Null();
        }
        String password = getPassword(phone, code);
        if (!"".equals(password)) {
            Map<String,Object> res = doLogin(phone,password,ip);
            Integer status = (Integer) res.get("status");
            Map<String,Object> data = new HashMap<>();
            String encrySession = (String) res.get("session");
            Map<String,Object> map =  parseSession(encrySession);;
            if(status == 200) {
                data.put("token",encrySession);
                data.put("data",list.get(0));
               Object over =  map.get("overtime");
               if(over == null) {
                   return null;
               }
                String overtime = String.valueOf(over);
                long endtime = new Long(overtime) * 1000;
                long now = System.currentTimeMillis();
                int expire = (int) ((endtime - now)/1000);
                RedisUtils.setex(Md5Utils.md5((String) map.get("username"),"utf-8"),"1",expire);
                return data;
            }else {
                data.put("status",status);
                data.put("msg",map.get("msg"));
                return data;
            }
        }

        return null;
    }


    @Override
    public void memberLogout(String token) throws Exception {
        if(token != null) {
            Map<String,Object> data =  parseSession(token);
            String username = (String) data.get("username");
            RedisUtils.del(username);
        }
    }

    @Override
    public void getVerifCode(String phone) throws ClientException {
        String data = UUID.genarateCode();
        SendSmsResponse response = SendSmsUtil.sendSms(phone, tempCode, null, "{\"code\":\"" + data + "\"}");
        if(LOG.isInfoEnabled()) {
            LOG.info("code: "+response.getCode() + ", message: " + response.getMessage());
        }
        int codeOvertime = Integer.valueOf(overtime);
        RedisUtils.setex(Md5Utils.md5(phone,"utf-8"), data, codeOvertime);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean resetPwd(String phone, String newPwd) throws Exception {
        String temp = Md5Utils.md5(newPwd,"utf-8");
        Map<String,Object> res =  updateUserPassword(phone,temp);
        Integer status = (Integer) res.get("status");
        if(status == 200) {
            SpMember member = new SpMember();
            member.setPassword(temp);
            int ok =  memberMapper.updateByExampleSelective(member, createExample(phone));
            if(ok == 1) {
                return true;
            }
        }

       return false;
    }

    @Override
    public boolean resetPasswordLogined(String phone, String newPassword) throws Exception {
     return resetPwd(phone, newPassword);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean setSecurityPwd(String password,int id,String question,String answer) {
        SpMember member = new SpMember();
        member.setSecuritypwd(password);
        member.setUid(id);
        member.setSecurityquestion(question);
        member.setSecurityanswer(answer);
       int num =  memberMapper.updateByPrimaryKeySelective(member);
       if(num == 1) {
           return true;
       }
        return false;
    }

    private boolean setSecurityPwdInternal( String newPwd, int id) {
        SpMember member = new SpMember();
        member.setSecuritypwd(newPwd);
        member.setUid(id);
        int num =  memberMapper.updateByPrimaryKeySelective(member);
        if(num == 1) {
            return true;
        }
        return false;
    }

    /**
     * 更改安全密码
     */
    @Override
    public boolean updateSecurityPwd(String oldPwd, String newPwd, int id) {
        SpMember user = memberMapper.selectByPrimaryKey(id);
        if(user != null) {
           String oldPassword =  user.getSecuritypwd();
           if(StringUtil.isNotEmpty(oldPassword) && oldPassword.equals(oldPwd)) {
              return  setSecurityPwdInternal(newPwd,id);
           }
        }
        return false;
    }

    /**
     * 根据id查询用户
     */
    @Override
    public SpMember getUserById(int id) {
        return  memberMapper.selectByPrimaryKey(id);
    }


    private SpMemberExample createExample(String phone) {
        SpMemberExample example = new SpMemberExample();
        SpMemberExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        return example;
    }

    @Override
    public boolean isExist(String phone) {
        List<SpMember> user = memberMapper.selectByExample(createExample(phone));
        if(user.size() > 0) {
            return true;
        }
        return false;
    }


    /**
     * 抽取重复代码
     *
     * @return 密码
     */
    private String getPassword(String phone, String code) {
        String cacheCode = RedisUtils.get(phone);
        if (cacheCode == null) {
            return "";
        }
        if (!cacheCode.equals(code.trim())) {
            return "";
        }
        List<SpMember> list = memberMapper.selectByExample(createExample(phone));
        if (list.size() == 0) {
            return "";
        }
        String password = list.get(0).getPassword();
        return password;
    }

    @Override
    public boolean checkByCode(String phone,String code) {
      String temp =   Md5Utils.md5(phone,"utf-8");
        String cacheCode = RedisUtils.get(temp);
        if (cacheCode == null) {
            return false;
        }
        if (!cacheCode.equals(code.trim())) {
            return false;
        }
        return true;
    }

    /**
     * 密码找回
     * @param account
     * @param code
     * @return
     */
    @Override
    public boolean checkAccount(String account,String code) {
        List<SpMember> list = memberMapper.selectByExample(createExample(account));
        if(list.size() == 0) {
            return false;
        }
        if(StringUtil.isEmpty(getPassword(account,code))) {
            return false;
        }
        return true;
    }

    /**
     * 安全密码找回
     * @param account
     * @param code
     * @param answer
     * @return
     */
    @Override
    public boolean checkAccountAndAnswer(String account, String code, String answer) {
        if(StringUtil.isEmpty(answer)) {
            return false;
        }
        SpMemberExample example = new SpMemberExample();
        SpMemberExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(account).andSecurityanswerEqualTo(answer);
        List<SpMember> list = memberMapper.selectByExample(example);
        if(list.size() > 0) {
            return true;
        }
        return false;
    }



    @Override
    public String getUserCheckedQuestion(int id) {

        SpMember user = memberMapper.selectByPrimaryKey(id);
        if(user != null) {
           return user.getSecurityquestion();
        }
        return "";
    }

    @Override
    public boolean updateAdditionalUserInfo(String avatar, String bname, String phone) {
        SpMember member = new SpMember();
        member.setAvatar(avatar);
        member.setBname(bname);
       int index =  memberMapper.updateByExampleSelective(member,createExample(phone));
        if(index >0) {
            return true;
        }
        return false;
    }

    @Override
    public Map<String,Object> doRegister(String account, String password) throws Exception {
        StringBuilder sb = new StringBuilder();
        Data data1 = new Data();
        Map<String,String> map = new HashMap<>();
        map.put("username",account);
        map.put("password",password);
        map.put("type","1");
        data1.setData(map);
        String json = JsonUtils.objectToJson(data1.getData());
        System.out.println(json);
        String result =  sb.append("Sso").append("register").append(json).append(salt).toString().replaceAll("\t|\n|\r","");
        String sign = Md5Utils.md5(result,"utf-8");
        InitParam p = new InitParam();
        p.setMod("Sso");
        p.setFun("register");
        p.setSign(sign);
        Map<String,String> data = new HashMap<>();
        data.put("username",account);
        data.put("password",password);
        data.put("type","1");
        p.setData(data);
        String ss =  JsonUtils.objectToJson(p);
        // 加密
        String enString = AesCBC.getInstance().encrypt(ss).replaceAll("\r|\n", "").trim().replaceAll("\\+","%2B");
        String param = "sp=" + enString;
        String response =  SimulateGetAndPostUtil.sendPost("http://144.anyoujia.com/Sso/Api/index/",param);
        return parseResponse(response);
    }

    @Override
    public Map<String,Object> doLogin(String account, String password, String ip) throws Exception {
        StringBuilder sb = new StringBuilder();
        Data data1 = new Data();
        Map<String,String> map = new HashMap<>();
        map.put("username",account);
        map.put("password",password);
        //type 为1表示wx小程序用户
        map.put("type","1");
        map.put("ip",ip);
        data1.setData(map);
        String json = JsonUtils.objectToJson(data1.getData());
        System.out.println(json);
        String result =  sb.append("Sso").append("login").append(json).append(salt).toString().replaceAll("\t|\n|\r","");
        String sign = Md5Utils.md5(result,"utf-8");
        InitParam p = new InitParam();
        p.setMod("Sso");
        p.setFun("login");
        p.setSign(sign);
        Map<String,String> data = new HashMap<>();
        data.put("username",account);
        data.put("password",password);
        data.put("ip",ip);
        data.put("type","1");
        p.setData(data);
        String ss =  JsonUtils.objectToJson(p);
        // 加密
        String enString = AesCBC.getInstance().encrypt(ss).replaceAll("\r|\n", "").trim().replaceAll("\\+","%2B");
        String param = "sp=" + enString;
        String response =  SimulateGetAndPostUtil.sendPost("http://144.anyoujia.com/Sso/Api/index/",param);
        System.out.println(response);
        return parseResponse(response);
    }

    @Override
    public Map<String,Object> updateUserPassword(String username, String password) throws Exception {
        StringBuilder sb = new StringBuilder();
        Data data1 = new Data();
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        data1.setData(map);
        String json = JsonUtils.objectToJson(data1.getData());
        System.out.println(json);
        String result =  sb.append("Sso").append("updateuser").append(json).append(salt).toString().replaceAll("\t|\n|\r","");
        String sign = Md5Utils.md5(result,"utf-8");
        InitParam p = new InitParam();
        p.setMod("Sso");
        p.setFun("updateuser");
        p.setSign(sign);
        Map<String,String> data = new HashMap<>();
        data.put("username",username);
        data.put("password",password);
        p.setData(data);
        String ss =  JsonUtils.objectToJson(p);
        // 加密
        String enString = AesCBC.getInstance().encrypt(ss).replaceAll("\r|\n", "").trim().replaceAll("\\+","%2B");
        String param = "sp=" + enString;
        String response =  SimulateGetAndPostUtil.sendPost("http://144.anyoujia.com/Sso/Api/index/",param);
        return parseResponse(response);
    }

    @Override
    public Map<String,Object> updateLogin(String username, String session) throws Exception {
        StringBuilder sb = new StringBuilder();
        Data data1 = new Data();
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("session",session);
        data1.setData(map);
        String json = JsonUtils.objectToJson(data1.getData());
        System.out.println(json);
        String result =  sb.append("Sso").append("updatelogin").append(json).append(salt).toString().replaceAll("\t|\n|\r","");
        String sign = Md5Utils.md5(result,"utf-8");
        System.out.println(sign);
        InitParam p = new InitParam();
        p.setMod("Sso");
        p.setFun("updatelogin");
        p.setSign(sign);
        Map<String,String> data = new HashMap<>();
        data.put("username",username);
        data.put("session",session);
        p.setData(data);
        String ss =  JsonUtils.objectToJson(p);
        // 加密
        String enString = AesCBC.getInstance().encrypt(ss).replaceAll("\r|\n", "").trim().replaceAll("\\+","%2B");
        String param = "sp=" + enString;
        String response =  SimulateGetAndPostUtil.sendPost("http://144.anyoujia.com/Sso/Api/index/",param);
        System.out.println(response);
        Map<String,Object> result1 = parseResponse(response);
        return result1;
    }

    @Override
    public SpMember getUserByAccount(String account) {
       List<SpMember> list =  memberMapper.selectByExample(createExample(account));
        if(list.size() >0) {
             return list.get(0);
        }
        return null;
    }

    @Override
    public boolean delSecurityPassword(String phone) {
        SpMember user = new SpMember();
        user.setSecuritypwd("");
       int i =  memberMapper.updateByExampleSelective(user,createExample(phone));
       if(i == 1) {
           return true;
       }
        return false;
    }

    @Override
    public boolean checkAccount(String phone) {
        List<SpMember> list =  memberMapper.selectByExample(createExample(phone));
        if(list.size() >0) {
            return true;
        }
        return false;
    }


    public Map<String,Object> parseResponse(String response) {
        Map<String,Object> map = JsonUtils.jsonToMap(response);
        Integer status = (Integer) map.get("status");
        Object object =  map.get("data");
        JSONObject json;
        Map<String,Object> data =new HashMap<>();
        if(object != null ) {
            if(object instanceof String) {
                String msg =  (String)object;
                data.put("msg",msg);
            }else {
                json = (JSONObject) map.get("data");
                data.putAll(JsonUtils.jsonToMap(json));
            }

        }
        data.put("status",status);
        return data;
    }

    public Map<String,Object> parseSession(String session) throws Exception{
        String result= AesCBC.getInstance().decrypt(session);
       try {
           JsonUtils.isJSONValid(result);
       }catch (Exception e) {
           return new HashMap<>();
       }
        return JsonUtils.jsonToMap(result);
    }




}
