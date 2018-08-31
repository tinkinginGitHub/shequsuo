package cn.anyoufang.service.Impl;

import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.SpMemberExample;
import cn.anyoufang.mapper.SpMemberMapper;
import cn.anyoufang.service.MemberService;
import cn.anyoufang.utils.Md5Utils;
import cn.anyoufang.utils.SendSmsUtil;
import cn.anyoufang.utils.ShiroUtils;
import cn.anyoufang.utils.UUID;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private SpMemberMapper memberMapper;

    @Value("{signName}")
    private String signName;

    @Value("${templcateCodebespeak}")
    private String tempCode;



    @Override
    public boolean memberRegister(String account, String pwd) {
        SpMemberExample example = new SpMemberExample();
        SpMemberExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(account);
        List<SpMember> list =  memberMapper.selectByExample(example);
        if(list !=null &&!"".equals(list.get(0))) {
            return false;
        }
        SpMember member = new SpMember();
        member.setPhone(account);
        member.setPassword(Md5Utils.md5(pwd,"utf-8"));
       int active =  memberMapper.insertSelective(member);
       if(active == 1) {
           return true;
       }
        return false;
    }

    @Override
    public boolean memberLogin(String account, String pwd) {

       Subject currentUser =  ShiroUtils.getSubject();
       if(!currentUser.isAuthenticated()) {
           String password = Md5Utils.md5(pwd,"utf-8");
           UsernamePasswordToken token = new UsernamePasswordToken(account, password);
           token.setRememberMe(true);
           currentUser.login(token);
           return true;
       }
        return false;
    }

    @Override
    public void memeberLogout() {
        ShiroUtils.logout();
    }

    @Override
    public String getVerifCode(String phone) throws ClientException {

        String data = UUID.genarateCode();
        SendSmsResponse sendSmsResponse = SendSmsUtil.sendSms(phone,signName,tempCode,null,"{\"code\":\""+data+"\"}");
        return sendSmsResponse.getCode();
    }
}
