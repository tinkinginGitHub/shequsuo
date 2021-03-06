package cn.anyoufang.service;

import cn.anyoufang.entity.SpLockFinger;
import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.SpMemberRelation;
import cn.anyoufang.entity.selfdefined.SetRecord;

import java.util.List;
import java.util.Map;

/**
 *
 * 锁相关用户业务操作
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
    boolean addUser(String usertype, String username, String phone, String userrelation, String locksn, int finger, int pwd,int adminId,int endtime);

    /**
     * 管理员删除锁成员的所有权限（指纹以及密码）
     * @param locksn
     * @param userid
     * @return
     */
     boolean deleteUser(String locksn,int userid);

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
    boolean updateMemberLockPwdOrFinger(int id,String locksn,int status,int type) throws Exception;

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
    boolean updateExpireDateForRenter(int userid,String locksn,int endtime);

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
     * @param othermemberid
     * @return
     */
    List<SpLockFinger> getFingerList(int memberid,String locksn,int relationid,int othermemberid);

    /**
     * 判断是否已经设置永久密码
     * @param locksn
     * @param phone
     * @param memberid
     * @param relationid
     * @param othermemberid
     * @return
     */

    Map isSetLockPwdForever(String locksn, String phone, int memberid, int relationid, int othermemberid);

    /**
     * 删除锁密码用户相关信息
     * @param memberid
     * @param locksn
     * @return
     */
    boolean deletePermentPwd(int memberid,String locksn,String phone,int relationid,int pwdid);

    /**
     *管理用户信息
     * @param locksn
     * @param seqid
     * @param state 状态 1：删除 2：启用 3：暂停
     * @return
     */
    boolean manageUser(String locksn,int seqid,int state);

    /**
     * 获取所有设置记录
     * @param member
     * @param locksn
     * @param page
     * @param begintime
     * @return
     */
    List<SetRecord> getSetRecords(SpMember member, String locksn,int page,int begintime);
}
