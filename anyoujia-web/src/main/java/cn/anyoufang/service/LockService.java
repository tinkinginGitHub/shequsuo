package cn.anyoufang.service;

import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.selfdefined.AnyoujiaResult;

import java.util.Map;

/**
 * @author daiping
 */
public interface LockService {


    /**
     * 设置用户锁密码
     * @param ptype
     * @param seqid
     * @param locksn
     * @param endtime
     * @param pwds
     * @param nickname
     * @param phone
     * @return
     */
    Map<String,String> setLockPwd(int ptype,
                                  int seqid,
                                  String locksn,
                                  int endtime,
                                  String pwds,
                                  String nickname,
                                  String phone,boolean isAdmin,String motive);

    /**
     * 设置用户指纹密码
     * @param seqid
     * @param locksn
     * @param endtime
     * @param usertype
     * @param nickname
     * @param phone
     * @param fingerdesc
     * @param fingerid
     * @return
     */

    AnyoujiaResult setLockUserFingerPassword( int seqid,
                                              String locksn,
                                              int endtime,
                                              int usertype,
                                              String nickname,
                                              String phone,String fingerdesc,String fingerid,boolean isAdmin);


    /**
     * 注册锁管理员
     * @param locksn
     * @param userid
     * @return
     */
    AnyoujiaResult registerLockInfo(String locksn, int userid);


    /**
     * 获取注册用户的锁列表
     * @param user
     * @return
     */

    AnyoujiaResult getAllLockList(SpMember user);

    /**
     * 获取门锁的所有记录
     * @param locksn
     * @param isalarm
     * @param page
     * @return
     */
    AnyoujiaResult getLockRecords(String locksn, int isalarm , int page);

    /**
     * 获取单个门锁详情
     * @param locksn
     * @return
     */
    AnyoujiaResult getLockInfo(String locksn);


}
