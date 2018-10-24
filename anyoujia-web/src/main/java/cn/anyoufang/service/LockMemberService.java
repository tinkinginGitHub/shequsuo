package cn.anyoufang.service;

import cn.anyoufang.entity.SpLockFinger;
import cn.anyoufang.entity.SpMemberRelation;

import java.util.List;

/**
 * @author daiping
 */
public interface LockMemberService {

    /**
     * 锁管理员添加成员
     * @param usertype
     * @param username
     * @param phone
     * @param userrelation
     * @param locksn
     * @param finger
     * @param pwd
     * @param adminId
     * @param endtime
     * @return
     */
    boolean addUser(String usertype,
                             String username,
                             String phone,
                             String userrelation,
                             String locksn,
                             int finger,
                             int pwd,int adminId,String endtime);

    /**
     * 管理员删除锁成员的所有权限（指纹以及密码）
     * @param locksn
     * @param userid
     * @return
     */
     boolean delUser(String locksn,int userid);

    /**
     * 获取锁成员
     * @param adminId
     * @param locksn
     * @return
     */
    List<SpMemberRelation> getMembersForByAdminId(int adminId,String locksn);

    /**
     * 更改锁成员的密码和指纹权限
     * @param id
     * @param locksn
     * @param status
     * @param type
     * @return
     * @throws Exception
     */
    boolean updateMemberLockPwdOrFinger(int id,String locksn,int status,String type) throws Exception;

    /**
     * 调用硬件云提供接口 删除锁密码/指纹/IC卡用户信息
     * @param locksn
     * @param seqid
     * @return
     */
    String delLockPwd(String locksn,int seqid);

    /**
     * 更新租客过期时间
     * @param userid
     * @param locksn
     * @return
     */
    boolean updateExpireDateForRenter(int userid,String locksn,String endtime);

    /**
     * 用户删除指纹
     * @param seqid
     * @param locksn
     * @return
     */
    boolean deleteFingerAccordent(int seqid,String locksn);

    /**
     * 获取用户指纹列表
     * @param memberid
     * @param locksn
     * @return
     */
    List<SpLockFinger> getFingerList(int memberid,String locksn);

    /**
     * 判断是否已经设置永久密码
     * @param locksn
     * @param phone
     * @return boolean
     */
    boolean isSetLockPwdForever(String locksn,String phone,int memberid);

    /**
     * 删除锁密码用户相关信息
     * @param memberid
     * @param locksn
     * @return
     */
    boolean deletePermentPwd(int memberid,String locksn,String phone);
}
