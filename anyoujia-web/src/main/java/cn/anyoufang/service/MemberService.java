package cn.anyoufang.service;

import cn.anyoufang.entity.SpMember;

/**
 * @author daiping
 */
public interface MemberService {

    SpMember addUser(SpMember user);

     boolean delUser(SpMember user);

     SpMember updateUser(SpMember user);
}
