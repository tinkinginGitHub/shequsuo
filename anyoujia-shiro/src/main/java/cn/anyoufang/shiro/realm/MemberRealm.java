package cn.anyoufang.shiro.realm;

import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.SpMemberExample;
import cn.anyoufang.mapper.SpMemberMapper;
import cn.anyoufang.utils.Md5Utils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MemberRealm extends AuthorizingRealm {

    @Autowired
    private SpMemberMapper memberMapper;



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        if(principals != null&& !principals.isEmpty()) {
            principals.getPrimaryPrincipal();
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        String phone = (String) token.getPrincipal();
        String password1 = new String((char[]) token.getCredentials());
        String password = Md5Utils.md5(password1, "utf-8");
        //查询用户信息
        SpMemberExample example = new SpMemberExample();
        SpMemberExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone).andPasswordEqualTo(password);
        List<SpMember> list = memberMapper.selectByExample(example);
        if(list.size() > 0) {
            SpMember member = list.get(0);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(member, password, getName());
            return info;
        }
        return null;
    }
}
