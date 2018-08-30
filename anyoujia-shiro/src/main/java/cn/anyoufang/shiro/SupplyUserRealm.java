package cn.anyoufang.shiro;


import cn.anyoufang.entity.TbSupplier;
import cn.anyoufang.entity.TbSupplierExample;
import cn.anyoufang.mapper.TbSupplierMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SupplyUserRealm extends AuthorizingRealm {
    private Logger log = LoggerFactory.getLogger(SupplyUserRealm.class);

    @Autowired
    private TbSupplierMapper supplierMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        TbSupplierExample example = new TbSupplierExample();
        TbSupplierExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(username);
        //查询用户信息
        List<TbSupplier> users = supplierMapper.selectByExample(example);
        TbSupplier user = users != null ? users.get(0):null;

        //账号不存在
        if(user == null) {
            log.info("账号或密码不正确");
            throw new UnknownAccountException("账号或密码不正确");
        }

        //密码错误
        if(!password.equals(user.getPwd())) {
            log.info("账号或密码不正确");
            throw new IncorrectCredentialsException("账号或密码不正确");
        }

        //账号锁定
        if(user.getStatus() == (byte)0){//1.表示正常，0表示锁定
            log.info("账号已被锁定,请联系管理员");
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;

    }
}
