package cn.anyoufang.service;

import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.selfdefined.AnyoujiaResult;
import cn.anyoufang.entity.selfdefined.Temppwd;

import java.util.List;
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
     * @param relationid
     * @return
     */
    Map<String,String> setLockPwd(int ptype,
                                  int seqid,
                                  String locksn,
                                  int endtime,
                                  String pwds,
                                  String nickname,
                                  String phone,boolean isAdmin,String motive,int relationid);

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
     * @param relationid
     * @return
     */

    AnyoujiaResult setLockUserFingerPassword( int seqid,
                                              String locksn,
                                              int endtime,
                                              int usertype,
                                              String nickname,
                                              String phone,String fingerdesc,String fingerid,boolean isAdmin, int relationid);


    /**
     * 注册锁管理员
     * @param locksn
     * @param userid
     * @return
     */
    AnyoujiaResult registerLockInfo(String locksn, int userid) throws Exception;


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
    AnyoujiaResult getLockRecords(String locksn, int isalarm , int page,int begintime);

    /**
     * 获取单个门锁详情
     * @param locksn
     * @return
     */
    AnyoujiaResult getLockInfo(String locksn);

    /**
     * 获取临时密码列表
     * @param locksn
     * @param pwdtype
     * @param usertype
     * @return
     */
    List<Temppwd> getLockTempPwdList(String locksn, int pwdtype, int usertype,int memberid,int begintime,int page);

    /**
     * 获取锁激活状态和地址
     * @param locksn
     * @param prokey
     * @return
     */
    AnyoujiaResult getLockActiveAndAddress(String locksn,String prokey);

    /**
     * 调用PHP比对锁地址和小区地址是否匹配
     * @param locksn
     * @param uid
     * @return
     */
    AnyoujiaResult callPhpToActiveLock(String locksn,int uid) throws Exception;

    /**
     * 从PHP获取工程码
     * @param locksn
     * @return
     * @throws Exception
     */
    AnyoujiaResult getProuctNum(String locksn) throws Exception;

    /**
     * 从PHP获取临时密码
     * @param locksn
     * @return
     */
    AnyoujiaResult getTempPwdFromPhp(String locksn) throws Exception;

    /**
     * 更新设置永久密码状态
     * @param memberid
     * @param locksn
     * @return
     */
    AnyoujiaResult updateSetedPwdState(int memberid,String locksn,boolean isAdmin,String phone,int relationid);

}
