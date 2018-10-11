package cn.anyoufang.service.Impl;

import cn.anyoufang.entity.*;
import cn.anyoufang.exception.LockException;
import cn.anyoufang.mapper.SpMemberLoginMapper;
import cn.anyoufang.mapper.SpMemberMapper;
import cn.anyoufang.mapper.SpMemberWxMapper;
import cn.anyoufang.service.MemberService;
import cn.anyoufang.utils.*;
import com.aliyuncs.exceptions.ClientException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author daiping
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private SpMemberMapper memberMapper;

    @Autowired
    private SpMemberWxMapper wxMapper;

    @Autowired
    private SpMemberLoginMapper loginMapper;

    @Value("${ANYOUJIACODE}")
    private String tempCode;


    @Override
    public AnyoujiaResult memberRegister(String account, String pwd, WeiXinVO weiXinVO) {
        SpMemberExample example = new SpMemberExample();
        SpMemberExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(account);
        List<SpMember> list = memberMapper.selectByExample(example);
        if (list.size() > 0) {
            return AnyoujiaResult.build(400,"already registed.");
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
                return AnyoujiaResult.build(200,"success");
            }
            return AnyoujiaResult.build(400,"failed");
        }else {
            member.setPhone(account);
            member.setPassword(Md5Utils.md5(pwd, "utf-8"));
            int uid = memberMapper.insertSelective(member);
            if (uid != 0) {
                return AnyoujiaResult.build(200,"success");
            }
        }
        return AnyoujiaResult.build(400,"failed");

    }


    @Override
    public SpMember memberLoginByPwd(String account, String pwd, String ip) throws LockException {
        List<SpMember> list = memberMapper.selectByExample(createExample(account));
        if(list.size() == 0) {
            return new Null();
        }
        Subject currentUser = ShiroUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(account, pwd);
            token.setRememberMe(true);
            currentUser.login(token);
            SpMember member = ShiroUtils.getUserEntity();
            SpMemberLogin loginRecord = new SpMemberLogin();
            int current = (int) (System.currentTimeMillis() / 1000);
            loginRecord.setLogintime(current);
            loginRecord.setStatus(true);
            loginRecord.setType(true);
            loginRecord.setIp(ip);
            loginRecord.setUid(member.getUid());
            loginMapper.insertSelective(loginRecord);
            return member;
        }
        return null;
    }

    @Override
    public SpMember memberLoginByVerifyCode(String phone, String code, String ip) {
        List<SpMember> list = memberMapper.selectByExample(createExample(phone));
        if(list.size() == 0) {
            return new Null();
        }
        String password = getPassword(phone, code);
        if (!"".equals(password)) {
            return memberLoginByPwd(phone, password, ip);
        }
        return null;
    }

    @Override
    public void memberLogout() {
        ShiroUtils.logout();
    }

    @Override
    public void getVerifCode(String phone) throws ClientException {
        String data = UUID.genarateCode();
        SendSmsUtil.sendSms(phone, tempCode, null, "{\"code\":\"" + data + "\"}");
        RedisUtils.setex(phone, data, 600);
    }

    @Override
    public boolean resetPwd(String phone, String newPwd) {
        SpMember member = new SpMember();
        member.setPassword(Md5Utils.md5(newPwd, "utf-8"));
       int ok =  memberMapper.updateByExampleSelective(member, createExample(phone));
       if(ok == 1) {
           return true;
       }
       return false;
    }

    @Override
    public boolean resetPasswordLogined(String oldPassword, String newPassword) {
        SpMember user = ShiroUtils.getUserEntity();
        String newPwd = Md5Utils.md5(newPassword, "utf-8");
        String oldPwd = Md5Utils.md5(oldPassword, "utf-8");
        if (!oldPwd.equals(user.getPassword())) {
            return false;
        }
     return resetPwd(user.getPhone(), newPwd);
    }

    @Override
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

    /**
     * 更改安全密码
     */
    @Override
    public boolean updateSecurityPwd(String oldPwd, String newPwd, int id,String answer,String question) {
        SpMember user = memberMapper.selectByPrimaryKey(id);
        if(user != null) {
           String oldPassword =  user.getSecuritypwd();
           if(StringUtil.isNotEmpty(oldPassword) && oldPassword.equals(oldPwd)) {
              return  setSecurityPwd(newPwd,id,question,answer);
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
        String cacheCode = RedisUtils.get(account);
        if (cacheCode == null || !cacheCode.equals(code.trim())) {
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

}
