package cn.anyoufang.service;

import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.SpMemberRelation;

import java.util.List;

/**
 * @author daiping
 */
public interface MemberService {

    boolean addUser(String usertype,
                             String username,
                             String phone,
                             String userrelation,
                             String locksn,
                             int finger,
                             int pwd,int adminId);

     boolean delUser(String locksn,int userid);

     SpMember updateUser(SpMember user);

    List<SpMemberRelation> getMembersForByAdminId(int adminId,String locksn);

    boolean updateMemberLockPwdOrFinger(int id,String locksn,int status,String type) throws Exception;

    String delLockPwd(String locksn,int seqid);

    boolean updateExpireDateForRenter(int userid,String locksn);
}
