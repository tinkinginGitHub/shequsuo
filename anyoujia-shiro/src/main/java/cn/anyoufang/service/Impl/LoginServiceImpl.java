package cn.anyoufang.service.Impl;

import cn.anyoufang.entity.*;
import cn.anyoufang.entity.selfdefined.AnyoujiaResult;
import cn.anyoufang.entity.selfdefined.InitParam;
import cn.anyoufang.entity.selfdefined.LoginResult;
import cn.anyoufang.enumresource.HttpCodeEnum;
import cn.anyoufang.enumresource.UsertypeEnum;
import cn.anyoufang.mapper.*;
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
 * 处理用户登录注册相关
 *
 * @author daiping
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);
    /**
     * http状态码
     */
    private static final int T_H = HttpCodeEnum.TWO_HUNDRED.getCode();
    private static final int FOUR_H_1 = HttpCodeEnum.FOUR_HUNDRED1.getCode();
    private static final int FOUR_H = HttpCodeEnum.FOUR_HUNDRED.getCode();
    private static final int FIVE_H = HttpCodeEnum.FIVE_HUNDRED.getCode();

    @Autowired
    private SpMemberMapper memberMapper;

    @Autowired
    private SpMemberWxMapper wxMapper;

    @Autowired
    private SpMemberLoginMapper loginMapper;

    @Autowired
    private SpMemberRelationMapper relationMapper;

    @Autowired
    private SpLockAdminMapper adminLockMapper;

    /**
     * 阿里云短信模板id
     */
    @Value("${ANYOUJIACODE}")
    private String tempCode;
    /**
     * 登录验证码超时时间
     */
    @Value("${code_overtime}")
    private String overtime;
    /**
     * PHp接口加密参数
     */
    @Value("${php.url}")
    private String phpurl;
    @Value("${php.salt}")
    private String salt;
    @Value("${php.sso}")
    private String SSO;


    /**
     * 会员注册
     * @param account
     * @param pwd
     * @param openid
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public AnyoujiaResult memberRegister(String account, String pwd, String openid) throws Exception {
        SpMemberExample example = new SpMemberExample();
        SpMemberExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(account);
        List<SpMember> list = memberMapper.selectByExample(example);
        if (!list.isEmpty()) {
            return AnyoujiaResult.build(FOUR_H, "该用户已注册，请登录");
        }
        if (openid != null) {
            Map<String, Object> map = doRegister(account, Md5Utils.md5(pwd, "utf-8"));
            Integer status = (Integer) map.get("status");
            if (status == T_H) {
                SpMember member = new SpMember();
                int createdTime = DateUtil.generateTenTime();
                member.setRegisttime(createdTime);
                member.setPhone(account);
                member.setPassword(Md5Utils.md5(pwd, "utf-8"));
                //返回生成的自增主键
                memberMapper.insertSelective(member);
                SpMemberWx wxmember = new SpMemberWx();
                int genaratedid = member.getUid();
                wxmember.setUid(genaratedid);
                wxmember.setOpenid(openid);
                int active = wxMapper.insertSelective(wxmember);
                if (active == 1) {
                    return AnyoujiaResult.build(T_H, "注册成功");
                }
            } else {
                AnyoujiaResult.build(status, (String) map.get("msg"));
            }
        }
        return AnyoujiaResult.build(FOUR_H, "未获取到用户openid");
    }


    @Override
    public Map<String, Object> memberLoginByPwd(String account, String pwd, String ip) throws Exception {
        List<SpMember> list = memberMapper.selectByExample(createExample(account));
        if (list.isEmpty()) {
            return new Null();
        }
        Map<String, Object> data = new HashMap<>();
        SpMember user = list.get(0);
        String dbPwd = user.getPassword();
        String tempPwd = pwd;
        String md5Pwd;
        if (tempPwd.equals(dbPwd)) {
            md5Pwd = tempPwd;
        } else {
            md5Pwd = Md5Utils.md5(tempPwd, "UTF-8");
            if (!md5Pwd.equals(dbPwd)) {
                return new Null();
            }
        }
        Map<String, Object> res = doLogin(account, md5Pwd, ip);
        Integer status = (Integer) res.get("status");
        if (status == T_H) {
            String encrySession = (String) res.get("session");
            SpMember member = list.get(0);
            SpMemberLogin loginRecord = new SpMemberLogin();
            int current = (int) (System.currentTimeMillis() / 1000);
            loginRecord.setLogintime(current);
            loginRecord.setStatus(true);
            loginRecord.setType(true);
            loginRecord.setIp(ip);
            loginRecord.setUid(member.getUid());
            loginMapper.insertSelective(loginRecord);
            data.put("data", combineLoginResponseResult(member, encrySession));
            Map<String, Object> map = parseSession(encrySession);
            Object over = map.get("overtime");
            if (over == null) {
                return null;
            }
            String md5Phone = Md5Utils.md5((String) map.get("username"),"utf-8");
            int expire = DateUtil.getExpiretimeInten(over);
            RedisUtils.setex(md5Phone, "1", expire);
            return data;
        } else {
            data.put("msg", res.get("msg"));
            data.put("status", res.get("status"));
            return data;
        }

    }

    /**
     * 封装登录成功后返回给小程序端的结果
     * 抽取组合返回结果
     */
    private LoginResult combineLoginResponseResult(SpMember user, String token) {
        LoginResult loginRes = new LoginResult();
        loginRes.setAvatar(user.getAvatar());
        loginRes.setBname(user.getBname());
        loginRes.setPhone(user.getPhone());
        loginRes.setToken(token);
        return loginRes;
    }

    /**
     * 验证码登录
     * @param phone
     * @param ip
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> memberLoginByVerifyCode(String phone, String ip) throws Exception {
        List<SpMember> list = memberMapper.selectByExample(createExample(phone));
        if (list.isEmpty()) {
            return new Null();
        }
        SpMember user = list.get(0);
        String password = user.getPassword();
        Map<String, Object> res = doLogin(phone, password, ip);
        Integer status = (Integer) res.get("status");
        String encrySession = (String) res.get("session");
        Map<String, Object> map = parseSession(encrySession);
        Map<String, Object> data = new HashMap<>();
        if (status == T_H) {
            data.put("data", combineLoginResponseResult(user, encrySession));
            Object over = map.get("overtime");
            if (over == null) {
                return null;
            }
            String md5Phone = Md5Utils.md5((String) map.get("username"),"utf-8");
            int expire = DateUtil.getExpiretimeInten(over);
            RedisUtils.setex(md5Phone, "1", expire);
            return data;
        } else {
            data.put("status", status);
            data.put("msg", map.get("msg"));
            return data;
        }

    }


    /**
     * 登出
     * @param token
     * @throws Exception
     */
    @Override
    public void memberLogout(String token) throws Exception {
        if (token != null) {
            Map<String, Object> data = parseSession(token);
            String username = (String) data.get("username");
            String md5Encrypt = Md5Utils.md5(username, "utf-8");
            RedisUtils.del(md5Encrypt);
        }
    }

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @Override
    public String sendVerifCode(String phone) {
        String data = UUID.genarateCode();
        SendSmsResponse response = null;
        try {
            response = SendSmsUtil.sendSms(phone, tempCode, null, "{\"code\":\"" + data + "\"}");
            if (response.getCode() != null && response.getCode().equals("OK")) {
                int codeOvertime = Integer.valueOf(overtime);
                RedisUtils.setex(Md5Utils.md5(phone, "utf-8"), data, codeOvertime);
                return "true";
            } else {
                return response.getMessage();
            }
        } catch (ClientException e) {
            if (LOG.isInfoEnabled()) {
                if (response != null) {
                    LOG.info("code: " + response.getCode() + ", message: " + response.getMessage());
                }
            }
        }
        return "false";
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean resetPwd(String phone, String newPwd) throws Exception {
        String temp = Md5Utils.md5(newPwd, "utf-8");
        Map<String, Object> res = updateUserPassword(phone, temp);
        Integer status = (Integer) res.get("status");
        if (status == T_H) {
            SpMember member = new SpMember();
            member.setPassword(temp);
            int ok = memberMapper.updateByExampleSelective(member, createExample(phone));
            if (ok == 1) {
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
    public boolean setSecurityPwd(String password, int id, String question, String answer) {
        SpMember member = new SpMember();
        member.setSecuritypwd(password);
        member.setUid(id);
        member.setSecurityquestion(question);
        member.setSecurityanswer(answer);
        int num = memberMapper.updateByPrimaryKeySelective(member);
        if (num == 1) {
            return true;
        }
        return false;
    }

    private boolean setSecurityPwdInternal(String newPwd, int id) {
        SpMember member = new SpMember();
        member.setSecuritypwd(newPwd);
        member.setUid(id);
        int num = memberMapper.updateByPrimaryKeySelective(member);
        if (num == 1) {
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
        if (user != null) {
            String oldPassword = user.getSecuritypwd();
            if (oldPwd.equals(oldPassword)) {
                return setSecurityPwdInternal(newPwd, id);
            }
        }
        return false;
    }

    /**
     * 根据id查询用户
     */
    @Override
    public SpMember getUserById(int id) {
        return memberMapper.selectByPrimaryKey(id);
    }


    private SpMemberExample createExample(String phone) {
        SpMemberExample example = new SpMemberExample();
        SpMemberExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        return example;
    }


//    @Override
//    public boolean isExist(String phone) {
//        List<SpMember> user = memberMapper.selectByExample(createExample(phone));
//        if (!user.isEmpty()) {
//            return true;
//        }
//        return false;
//    }

    @Override
    public boolean checkByCode(String phone, String code) {
        String temp = Md5Utils.md5(phone, "utf-8");
        String cacheCode = RedisUtils.get(temp);
        if (cacheCode == null) {
            return false;
        }
        if (!cacheCode.equals(code.trim())) {
            return false;
        }
        //验证码只能使用一次
        RedisUtils.del(temp);
        return true;
    }

    /**
     * 关系为用户类型1家人，2老人儿童，3.租户
     */
    @Override
    public String getMemberRelation(String locksn, String phone) {

        SpMemberRelationExample example = new SpMemberRelationExample();
        SpMemberRelationExample.Criteria criteria = example.createCriteria();
        criteria.andLocksnEqualTo(locksn).andPhoneEqualTo(phone);
        List<SpMemberRelation> list = relationMapper.selectByExample(example);
        if (!list.isEmpty()) {
            SpMemberRelation relation = list.get(0);
            if (UsertypeEnum.ONE.getCode().equals(relation.getUsertype())) {
                return relation.getUserrelation();
            }
            return relation.getUsername();
        }
        return null;
    }

    @Override
    public List<SpLockAdmin> getLockAdmin(int memberid,String locksn) {

        SpLockAdminExample example = new SpLockAdminExample();
        SpLockAdminExample.Criteria criteria = example.createCriteria();
        criteria.andAdminidEqualTo(memberid).andLocksnEqualTo(locksn);
        return adminLockMapper.selectByExample(example);
    }

    @Override
    public boolean checkAccount1(String phone) {
        List<SpMember> list = memberMapper.selectByExample(createExample(phone));
        if (!list.isEmpty()) {
            return true;
        }

        return false;
    }

    @Override
    public SpMemberRelation getRelationMember(int relationid) {
       return relationMapper.selectByPrimaryKey(relationid);
    }


    /**
     * 安全密码找回
     */
    @Override
    public boolean checkAccountAndAnswer(String account, String code, String answer) {
        SpMemberExample example = new SpMemberExample();
        SpMemberExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(account).andSecurityanswerEqualTo(answer);
        List<SpMember> list = memberMapper.selectByExample(example);
        if (!list.isEmpty()) {
            return true;
        }
        return false;
    }


    /**
     * 获取用户已选择的安全问题
     */
    @Override
    public String getUserCheckedQuestion(int id) {

        SpMember user = memberMapper.selectByPrimaryKey(id);
        if (user != null) {
            return user.getSecurityquestion();
        }
        return "";
    }

    /**
     * 更新用户附加信息
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateAdditionalUserInfo(String avatar, String bname, String phone) {
        SpMember member = new SpMember();
        member.setAvatar(avatar);
        member.setBname(bname);
        int index = memberMapper.updateByExampleSelective(member, createExample(phone));
        if (index > 0) {
            return true;
        }
        return false;
    }
    //=>开始调用PHP鉴权中心进行登录注册相关

    /**
     * 注册
     */
    @Override
    public Map<String, Object> doRegister(String account, String password) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", account);
        map.put("password", password);
        map.put("type", "1");
        String json = JsonUtils.objectToJson(SortJsonAesc.sortMap(map));
        InitParam p = new InitParam();
        p.setMod("Sso");
        p.setFun("register");
        p.setSign(genarateSign(SSO, "register", json));
        p.setData(map);
        return parseResponse(doPhpRequest(p));
    }

    /**
     * type 1 表示小程序请求PHP鉴权中心
     * 登录
     *
     * @return Map
     */
    @Override
    public Map<String, Object> doLogin(String account, String password, String ip) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", account);
        map.put("password", password);
        map.put("type", "1");
        map.put("ip", ip);
        String json = JsonUtils.objectToJson(SortJsonAesc.sortMap(map));
        InitParam p = new InitParam();
        p.setMod(SSO);
        p.setFun("login");
        p.setSign(genarateSign(SSO, "login", json));
        p.setData(map);
        return parseResponse(doPhpRequest(p));
    }

    /**
     * 更新用户密码
     */
    @Override
    public Map<String, Object> updateUserPassword(String username, String password) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        String json = JsonUtils.objectToJson(SortJsonAesc.sortMap(map));
        InitParam p = new InitParam();
        p.setMod(SSO);
        p.setFun("updateuser");
        p.setSign(genarateSign(SSO, "updateuser", json));
        p.setData(map);
        return parseResponse(doPhpRequest(p));
    }

    /**
     * 维护登录状态
     */
    @Override
    public Map<String, Object> updateLogin(String username, String session) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("session", session);
        String json = JsonUtils.objectToJson(map);
        InitParam p = new InitParam();
        p.setMod(SSO);
        p.setFun("updatelogin");
        p.setSign(genarateSign(SSO, "updatelogin", json));
        p.setData(map);
        return parseResponse(doPhpRequest(p));
    }
    //=>结束调用PHP鉴权中心进行登录注册相关

    /**
     * 工具方法负责发送请求
     */
    private String doPhpRequest(InitParam initParam) throws Exception {
        String ss = JsonUtils.objectToJson(initParam);
        return SimulateGetAndPostUtil.sendPost(phpurl, genarateParamForPhpRequest(ss));
    }

    /**
     * 工具方法负责生成签名
     */
    private String genarateSign(String mod, String fun, String jsonData) {
        StringBuilder sb = new StringBuilder();
        String result = sb.append(mod).append(fun).append(jsonData).append(salt)
                .toString().replaceAll("\t|\n|\r", "");
        return Md5Utils.md5(result, "utf-8");
    }

    /**
     * 工具方法负责生成请求参数
     */
    private String genarateParamForPhpRequest(String s) throws Exception {
        return "sp=" + AesCBC.getInstance().encrypt(s).
                replaceAll("\r|\n", "").trim().replaceAll("\\+", "%2B");
    }

    /**
     * 根据手机号获取用户
     */
    @Override
    public SpMember getUserByAccount(String account) {
        List<SpMember> list = memberMapper.selectByExample(createExample(account));
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 删除用户安全密码
     */
    @Override
    public boolean delSecurityPassword(String phone) {
        SpMember user = new SpMember();
        user.setSecuritypwd("");
        int i = memberMapper.updateByExampleSelective(user, createExample(phone));
        if (i == 1) {
            return true;
        }
        return false;
    }

    /**
     * 检查账号是否存在
     */
    @Override
    public boolean checkAccount(String phone) {
        List<SpMember> list = memberMapper.selectByExample(createExample(phone));
        if (list.size() > 0 && !StringUtil.isEmpty(list.get(0).getPassword())) {
            return true;
        }

        return false;
    }


    /**
     * 解析PHP鉴权中心响应结果
     */
    public Map<String, Object> parseResponse(String response) {
        Map<String, Object> map = JsonUtils.jsonToMap(response);
        Integer status = (Integer) map.get("status");
        Object object = map.get("data");
        JSONObject json;
        Map<String, Object> data = new HashMap<>();
        if (object != null) {
            if (object instanceof String) {
                String msg = (String) object;
                data.put("msg", msg);
            } else {
                json = (JSONObject) map.get("data");
                data.putAll(JsonUtils.jsonToMap(json));
            }

        }
        data.put("status", status);
        return data;
    }

    /**
     * 解析PHP返回的加密session
     */

    public Map<String, Object> parseSession(String session) throws Exception {
        String result = AesCBC.getInstance().decrypt(session);
        try {
            JsonUtils.isJSONValid(result);
        } catch (Exception e) {
            if(LOG.isInfoEnabled()) {
                LOG.info("解析加密session发生异常: " + e.getMessage());
            }
            return new HashMap<>();
        }
        return JsonUtils.jsonToMap(result);
    }


}
