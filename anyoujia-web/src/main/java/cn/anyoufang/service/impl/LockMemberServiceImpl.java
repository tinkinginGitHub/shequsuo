package cn.anyoufang.service.impl;

import cn.anyoufang.entity.*;
import cn.anyoufang.enumresource.ParamEnum;
import cn.anyoufang.enumresource.PtypeAuthEnum;
import cn.anyoufang.enumresource.PwdTypeEnum;
import cn.anyoufang.enumresource.UsertypeEnum;
import cn.anyoufang.exception.LockException;
import cn.anyoufang.mapper.SpAdminLockMapper;
import cn.anyoufang.mapper.SpLockFingerMapper;
import cn.anyoufang.mapper.SpLockPasswordMapper;
import cn.anyoufang.mapper.SpMemberRelationMapper;
import cn.anyoufang.service.LockMemberService;
import cn.anyoufang.service.LoginService;
import cn.anyoufang.utils.DateUtil;
import cn.anyoufang.utils.Md5Utils;
import cn.anyoufang.utils.SimulateGetAndPostUtil;
import cn.anyoufang.utils.selfdefine.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 锁相关的人相关业务处理
 * @author daiping
 */

@Service
public class LockMemberServiceImpl implements LockMemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LockMemberServiceImpl.class);

    @Autowired
    private SpMemberRelationMapper relationMapper;

    @Autowired
    private SpAdminLockMapper adminLockMapper;

    @Autowired
    private SpLockPasswordMapper passwordMapper;

    @Autowired
    private SpLockFingerMapper fingerMapper;

    @Autowired
    private LoginService  loginService;

    @Value("${lock.salt}")
    private String lockSalt;
    @Value("${pageSize}")
    private String pagesize;
    @Value("${lock.url}")
    private String url;
    /**
     * 被添加成员有效注册天数
     */
    @Value("${deadline}")
    private int deadline;


    /**
     *管理员添加锁成员
     * 老人儿童的密码和指纹权限默认开启
     * @param usertype 用户类型1家人，2老人儿童，3.租户
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
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean addUser(String usertype,
                                    String username,
                                    String phone,
                                    String userrelation,
                                    String locksn,
                                    int finger,
                                    int pwd,int adminId,String endtime) {

        //不能重复添加同一个手机号在同一把锁上
        if(!memberadded(locksn,phone)){
            return false;
        }
        SpMemberRelation user = new SpMemberRelation();
        user.setUsertype(usertype);
        user.setUsername(username);
        if(!ParamEnum.MINUS_ONE.getCode().equals(phone)){
            user.setPhone(phone);
        }
        if(!ParamEnum.MINUS_ONE.getCode().equals(endtime)) {
            user.setEndtime(formatTimeString(endtime));
        }
        if(!ParamEnum.MINUS_ONE.getCode().equals(userrelation)){
            user.setUserrelation(userrelation);
        }

        if(UsertypeEnum.TWO.equals(usertype)){
            user.setFingerpwdauth(true);
            user.setLockpwdauth(true);
        }else {
            if(finger == 0) {
                user.setFingerpwdauth(false);
            }else {
                user.setFingerpwdauth(true);
            }

            if(pwd == 0) {
                user.setLockpwdauth(false);
            }else {
                user.setLockpwdauth(true);
            }
        }

        user.setParentid(adminId);
        user.setLocksn(locksn);
        user.setAddtime(DateUtil.generateTenTime());
       int index =  relationMapper.insertSelective(user);
        if(index == 1) {
            return true;
        }
        return false;
    }

    /**
     * 成员是否已经被添加
     * @param locksn
     * @param phone
     * @return
     */
    private boolean memberadded(String locksn,String phone) {
        SpMemberRelationExample example = new SpMemberRelationExample();
        SpMemberRelationExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone).andLocksnEqualTo(locksn);
        List<SpMemberRelation> list = relationMapper.selectByExample(example);
        return list.isEmpty();
    }

    /**
     * 管理员删除锁成员的所有权限（指纹以及密码）
     * @param locksn
     * @param userid
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean delUser(String locksn,int userid) {
        try{
               SpMemberRelation relationUser =  relationMapper.selectByPrimaryKey(userid);
               boolean isDelete = true;
               if(relationUser != null) {
                   String phone = relationUser.getPhone();
                   SpMember member =  loginService.getUserByAccount(phone);
                   int memberId =  member.getUid();
                   if(relationUser.getLockpwdauth()){
                       if(relationUser.getSetedlockpwd()) {
                           SpLockPasswordExample example = new SpLockPasswordExample();
                           SpLockPasswordExample.Criteria criteria =  example.createCriteria();
                           criteria.andLocksnEqualTo(locksn).andMemberidEqualTo(memberId);
                           List<SpLockPassword> lockPasswords =  passwordMapper.selectByExample(example);
                          if(!lockPasswords.isEmpty()) {
                             SpLockPassword lockPassword =  lockPasswords.get(0);
                             int seqPwdid = lockPassword.getPwdid();
                              String res = delLockPwd(locksn,seqPwdid);
                              if(CommonUtil.successResponse(res)) {
                                  SpLockPassword lPwd = new SpLockPassword();
                                  lPwd.setExpired(true);
                                  lPwd.setPwdid(seqPwdid);
                                  passwordMapper.updateByPrimaryKeySelective(lPwd);
                              }else {
                                  isDelete =false;
                              }
                          }

                       }
                   }
                   if(relationUser.getFingerpwdauth()) {
                           List<SpLockFinger> fingers =  getFingerListInternal(locksn,memberId);
                           if(!fingers.isEmpty()) {
                               SpLockFinger finger = fingers.get(0);
                               int seqFingerid = finger.getId();
                               String res = delLockPwd(locksn,seqFingerid);
                               if(CommonUtil.successResponse(res)) {
                                   SpLockFinger finger1 = new SpLockFinger();
                                   finger1.setExpired(true);
                                   finger1.setId(seqFingerid);
                                   fingerMapper.updateByPrimaryKeySelective(finger1);
                               }else {
                                   isDelete = false;
                               }
                           }

                   }
               }

                if(isDelete) {
                    relationMapper.deleteByPrimaryKey(userid);
                    return true;
                }
        }catch (Exception e) {
            if(LOGGER.isInfoEnabled()) {
                LOGGER.info("删除锁成员发生异常: " + e.getMessage());
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return false;
    }

    /**
     * 提取重复代码，查询获取用户指纹列表
     * @param locksn
     * @param memberId
     * @return
     */
    private List<SpLockFinger> getFingerListInternal(String locksn,int memberId) {
        SpLockFingerExample example = new SpLockFingerExample();
        SpLockFingerExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(memberId).andLocksnEqualTo(locksn).andExpiredEqualTo(false);
        return fingerMapper.selectByExample(example);
    }


    /**
     * 删除锁密码/指纹/IC卡用户信息
     * @param locksn
     * @param seqid
     * @return
     */
    @Override
    public String delLockPwd(String locksn,int seqid) {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String param = sb.append(locksn).append(seqid).append(timestamp).append(lockSalt).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam = "method=del.lock.pwd&locksn="+locksn+"&seqid="+seqid+"&temptime="+timestamp+"&sign="+sign;
        return SimulateGetAndPostUtil.sendPost(url,combineParam);
    }

    /**
     * 更新租客过期时间
     * @param userid 成员id
     * @param locksn
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateExpireDateForRenter(int userid, String locksn,String endtime) throws LockException{
        SpMemberRelation user = new SpMemberRelation();
        user.setId(userid);
        user.setEndtime(formatTimeString(endtime));
        int updatedLine = relationMapper.updateByPrimaryKeySelective(user);
        if(updatedLine == 1) {
            return true;
        }
        return false;
    }

    /**
     *
     * TODO 分布式事务
     * 分别删除单个手指指纹
     * @param seqid
     * @param locksn
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean deleteFingerAccordent(int seqid, String locksn) {
        String delResult = delLockPwd(locksn,seqid);
        boolean res = CommonUtil.successResponse(delResult);
        if(res) {
             SpLockFinger finger = new SpLockFinger();
             finger.setId(seqid);
             finger.setDeltime(DateUtil.generateTenTime());
             finger.setExpired(true);
             fingerMapper.updateByPrimaryKeySelective(finger);
             return true;
             }
        return false;
    }

    /**
     * 移除永久密码
     * TODO 分布式事务
     * @param memberid
     * @param locksn
     * @return
     */
    @Override
    public boolean deletePermentPwd(int memberid,String locksn,String phone){
        SpLockPasswordExample example = new SpLockPasswordExample();
        SpLockPasswordExample.Criteria criteria =  example.createCriteria();
        criteria.andLocksnEqualTo(locksn).andMemberidEqualTo(memberid).andPtypeEqualTo(PwdTypeEnum.ONE.getCode());
        List<SpLockPassword> lockPasswords =  passwordMapper.selectByExample(example);
        if(lockPasswords.isEmpty()) {
            return false;
        }
        SpLockPassword lockPassword = lockPasswords.get(0);
        int seqid = lockPassword.getPwdid();
        String delResult = delLockPwd(locksn,seqid);
        boolean res = CommonUtil.successResponse(delResult);
        if(res) {
            //管理员删除密码或者用户删除密码
           SpAdminLock adminLock =  isNotAdminLock(memberid,locksn);
           if(adminLock == null) {
               SpMemberRelation memberRelation =  isMemberRelation(phone,locksn);
               if(memberRelation != null) {
                   SpMemberRelationExample example1 = new SpMemberRelationExample();
                   SpMemberRelationExample.Criteria criteria1 = example1.createCriteria();
                   criteria1.andPhoneEqualTo(phone).andLocksnEqualTo(locksn);
                   SpMemberRelation user = new SpMemberRelation();
                   user.setSetedlockpwd(false);
                   user.setUpdatetime(DateUtil.generateTenTime());
                   relationMapper.updateByExampleSelective(user,example1);
               }
           }else {
               SpAdminLock lock = new SpAdminLock();
               lock.setSetedlockpwd(false);
               lock.setId(adminLock.getId());
               lock.setUpdatetime(DateUtil.generateTenTime());
               adminLockMapper.updateByPrimaryKeySelective(lock);
           }
            SpLockPassword password = new SpLockPassword();
            password.setDeltime(DateUtil.generateTenTime());
            password.setExpired(true);
            passwordMapper.updateByPrimaryKeySelective(password);
            return true;
        }
        return false;
    }

    private SpMemberRelation isMemberRelation(String phone,String locksn) {
        SpMemberRelationExample example1 = new SpMemberRelationExample();
        SpMemberRelationExample.Criteria criteria1 =  example1.createCriteria();
        criteria1.andPhoneEqualTo(phone).andLocksnEqualTo(locksn);
        List<SpMemberRelation> list1 = relationMapper.selectByExample(example1);
        if(list1.isEmpty()) {
            return null;
        }
        return list1.get(0);
    }

    private SpAdminLock isNotAdminLock(int memberid,String locksn) {
        SpAdminLockExample example = new SpAdminLockExample();
        SpAdminLockExample.Criteria criteria = example.createCriteria();
        criteria.andAdminidEqualTo(memberid).andLocksnEqualTo(locksn);
        List<SpAdminLock> list = adminLockMapper.selectByExample(example);
        if(list.isEmpty()) {
             return null;
        }
        return list.get(0) ;
    }

    /**
     * 获取用户指纹列表
     * @param memberid
     * @param locksn
     * @return
     */
    @Override
    public List<SpLockFinger> getFingerList(int memberid, String locksn) {
      return getFingerListInternal(locksn,memberid);
    }

    /**
     * 用于前台判断是否已经设置锁永久密码
     * @param locksn
     * @param phone
     * @param memberid
     * @return
     */
    @Override
    public boolean isSetLockPwdForever(String locksn, String phone,int memberid) {
        SpAdminLock adminLock = isNotAdminLock(memberid,locksn);
        if(adminLock == null) {
           SpMemberRelation memberRelation =  isMemberRelation(phone,locksn);
            if( memberRelation == null) {
               return false;
            }else {
                Boolean isSeted = memberRelation.getSetedlockpwd();
                if(isSeted) {
                    return true;
                }else {
                    return false;
                }
            }
        }
        boolean isseted =  adminLock.getSetedlockpwd();
        if(isseted) {
            return true;
        }
        return false;
    }

    /**
     * 负责格式时间字符串
     * @param s
     * @return
     */
    private int formatTimeString(String s) {
        long endTimestamp = Long.parseLong(s);
        int end = (int) (endTimestamp /1000);
        return end;
    }

    /**
     * 被添加为锁成员的租户需要在30天内注册app并设置密码等
     * @param adminId
     * @param locksn
     * @return
     */
    @Override
    public List<SpMemberRelation> getMembersForByAdminId(int adminId,String locksn) {
        List<SpMemberRelation> members = relationMapper.selectByExample(extractDuplicatCode(adminId,locksn));
        List<SpMemberRelation> validMembers = new ArrayList<>();
        List<SpMemberRelation> invalidMembers = new ArrayList<>();
       Iterator<SpMemberRelation> iterator =  members.iterator();
        while(iterator.hasNext()) {
            SpMemberRelation user = iterator.next();
            String phone =  user.getPhone();
            int end = user.getEndtime();
            String usertype = user.getUsertype();
            //usertype 3表示租户，只有租户有过期时间，当租户30天内未注册系统时，则过期并删除
            if(UsertypeEnum.THREE.getCode().equals(usertype)) {
                if (!loginService.checkAccount1(phone)) {
                    if (DateUtil.isNotExpired(end,deadline)) {
                        validMembers.add(user);
                    } else {
                        invalidMembers.add(user);
                    }
                }

            }else {
                validMembers.add(user);
            }

        }
        if(!invalidMembers.isEmpty()) {
            SpMemberRelationExample example = new SpMemberRelationExample();
            SpMemberRelationExample.Criteria criteria =  example.createCriteria();
            criteria.andParentidEqualTo(invalidMembers.get(0).getParentid());
            relationMapper.deleteByExample(example);
        }
        return validMembers;
    }

    /**
     * 抽取重复代码
     * @param id
     * @param locksn
     * @return
     */
    private SpMemberRelationExample extractDuplicatCode(int id,String locksn){
        SpMemberRelationExample example = new SpMemberRelationExample();
        SpMemberRelationExample.Criteria criteria = example.createCriteria();
        criteria.andParentidEqualTo(id).andLocksnEqualTo(locksn);
        return example;
    }

    /**
     *管理员更新锁成员的指纹和密码权限
     * @param id relationId关系用户id
     * @param locksn
     * @param status 0,关闭，1开启
     * @param type finger,pwd(String 类型)
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateMemberLockPwdOrFinger(int id, String locksn, int status,String type) {
        SpMemberRelationExample example = new SpMemberRelationExample();
        SpMemberRelationExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id).andLocksnEqualTo(locksn);
        SpMemberRelation user = new SpMemberRelation();
        if(PtypeAuthEnum.FINGER.getCode().equals(type)) {
            if(status == 0) {
                user.setFingerpwdauth(false);
            }else {
                user.setFingerpwdauth(true);
            }

        }else {
            if(status == 0) {
                user.setLockpwdauth(false);
            }else {
                user.setLockpwdauth(true);
            }
        }

        int index = relationMapper.updateByExampleSelective(user,example);
        if(index == 1) {
            return true;
        }
        return false;
    }
}
