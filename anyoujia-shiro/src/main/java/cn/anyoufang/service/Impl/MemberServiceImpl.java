package cn.anyoufang.service.Impl;

import cn.anyoufang.entity.*;
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

    @Value("${signName}")
    private String signName;

    @Value("${templcateCodebespeak}")
    private String tempCode;


    @Override
    public boolean memberRegister(String account, String pwd, WeiXinVO weiXinVO) {
        SpMemberExample example = new SpMemberExample();
        SpMemberExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(account);
        List<SpMember> list = memberMapper.selectByExample(example);
        if (list != null && !"".equals(list.get(0))) {
            return false;
        }
        SpMember member = new SpMember();
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
            return true;
        }
        return false;
    }


    @Override
    public SpMember memberLoginByPwd(String account, String pwd, String ip) {

        Subject currentUser = ShiroUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            String password = Md5Utils.md5(pwd, "utf-8");
            UsernamePasswordToken token = new UsernamePasswordToken(account, password);
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
        SendSmsUtil.sendSms(phone, signName, tempCode, null, "{\"code\":\"" + data + "\"}");
        RedisUtils.setex(phone, data, 600);
    }

    @Override
    public void resetPwd(String phone, String newPwd) {
        SpMember member = new SpMember();
        member.setPassword(Md5Utils.md5(newPwd, "utf-8"));
        memberMapper.updateByExampleSelective(member, createExample(phone));
    }

    @Override
    public boolean resetPasswordLogined(String oldPassword, String newPassword) {
        SpMember user = ShiroUtils.getUserEntity();
        String newPwd = Md5Utils.md5(newPassword, "utf-8");
        String oldPwd = Md5Utils.md5(oldPassword, "utf-8");
        if (!oldPwd.equals(user.getPassword())) {
            return false;
        }
        resetPwd(user.getPhone(), newPwd);
        return true;
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
        if (list == null) {
            return "";
        }
        String password = list.get(0).getPassword();
        return password;
    }
}
