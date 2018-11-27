package cn.anyoufang.service.impl;

import cn.anyoufang.entity.SpActionRecords;
import cn.anyoufang.entity.SpActionRecordsExample;
import cn.anyoufang.entity.SpLockAdmin;
import cn.anyoufang.entity.SpLockAdminExample;
import cn.anyoufang.entity.SpLockFinger;
import cn.anyoufang.entity.SpLockFingerExample;
import cn.anyoufang.entity.SpLockPassword;
import cn.anyoufang.entity.SpLockPasswordExample;
import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.SpMemberExample;
import cn.anyoufang.entity.SpMemberRelation;
import cn.anyoufang.entity.SpMemberRelationExample;
import cn.anyoufang.entity.selfdefined.SetRecord;
import cn.anyoufang.entity.selfdefined.SqlStatus;
import cn.anyoufang.enumresource.ParamEnum;
import cn.anyoufang.enumresource.PtypeAuthEnum;
import cn.anyoufang.enumresource.UsertypeEnum;
import cn.anyoufang.exception.LockException;
import cn.anyoufang.mapper.SpActionRecordsMapper;
import cn.anyoufang.mapper.SpLockAdminMapper;
import cn.anyoufang.mapper.SpLockFingerMapper;
import cn.anyoufang.mapper.SpLockPasswordMapper;
import cn.anyoufang.mapper.SpMemberMapper;
import cn.anyoufang.mapper.SpMemberRelationMapper;
import cn.anyoufang.service.LockMemberService;
import cn.anyoufang.service.LoginService;
import cn.anyoufang.utils.DateUtil;
import cn.anyoufang.utils.Md5Utils;
import cn.anyoufang.utils.SimulateGetAndPostUtil;
import cn.anyoufang.utils.pagehelper.PageHelper;
import cn.anyoufang.utils.selfdefine.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 锁相关的人相关业务处理
 *
 * @author daiping
 */

@Service
public class LockMemberServiceImpl implements LockMemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LockMemberServiceImpl.class);

    @Autowired
    private SpMemberRelationMapper relationMapper;

    @Autowired
    private SpLockAdminMapper adminLockMapper;

    @Autowired
    private SpLockPasswordMapper passwordMapper;

    @Autowired
    private SpLockFingerMapper fingerMapper;

    @Autowired
    private SpActionRecordsMapper recordsMapper;

    @Autowired
    private SpMemberMapper memberMapper;


    @Autowired
    private LoginService loginService;

    @Value("${lock.salt}")
    private String lockSalt;
    @Value("${pageSize}")
    private int pagesize;
    @Value("${lock.url}")
    private String url;
    /**
     * 被添加成员有效注册天数
     */
    @Value("${deadline}")
    private int deadline;


    /**
     * 管理员添加锁成员
     * 老人儿童的密码和指纹权限默认开启
     *
     * @param usertype 用户类型1家人，2老人儿童，3.租户
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean addUser(String usertype,
                           String username,
                           String phone,
                           String userrelation,
                           String locksn,
                           int finger,
                           int pwd, int adminId, int endtime) {

        //不能重复添加同一个手机号在同一把锁上
        if (!memberadded(locksn, phone)) {
            return false;
        }
        SpMemberRelation user = new SpMemberRelation();
        user.setUsertype(usertype);
        user.setUsername(username);
        if (!ParamEnum.MINUS_ONE.getCode().equals(phone)) {
            user.setPhone(phone);
        }
        if (-1 != endtime) {
            user.setEndtime(endtime);
        }
        if (!ParamEnum.MINUS_ONE.getCode().equals(userrelation)) {
            user.setUserrelation(userrelation);
        }

        if (UsertypeEnum.TWO.equals(usertype)) {
            user.setFingerpwdauth(true);
            user.setLockpwdauth(true);
        } else {
            if (finger == SqlStatus.ZERO) {
                user.setFingerpwdauth(false);
            } else {
                user.setFingerpwdauth(true);
            }

            if (pwd == SqlStatus.ZERO) {
                user.setLockpwdauth(false);
            } else {
                user.setLockpwdauth(true);
            }
        }

        user.setParentid(adminId);
        user.setLocksn(locksn);
        user.setAddtime(DateUtil.generateTenTime());
        int index = relationMapper.insertSelective(user);
        if (index == SqlStatus.ONE) {
            return true;
        }
        return false;
    }

    /**
     * 成员是否已经被添加
     */
    private boolean memberadded(String locksn, String phone) {
        SpMemberRelationExample example = new SpMemberRelationExample();
        SpMemberRelationExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone).andLocksnEqualTo(locksn);
        List<SpMemberRelation> list = relationMapper.selectByExample(example);
        return list.isEmpty();
    }

    /**
     * 管理员删除锁成员的所有权限（指纹以及密码）
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean deleteUser(String locksn, int userid) {
        try {
            SpMemberRelation relationUser = relationMapper.selectByPrimaryKey(userid);
            if (relationUser == null) {
                return false;
            }
            boolean isDelete = true;
            String phone = relationUser.getPhone();
            String usertype = relationUser.getUsertype();

            if (UsertypeEnum.TWO.getCode().equals(usertype)) {
                //老人儿童默认开启指纹和密码权限
                int realtionid = relationUser.getId();
                SpLockPasswordExample example = new SpLockPasswordExample();
                SpLockPasswordExample.Criteria criteria = example.createCriteria();
                criteria.andLocksnEqualTo(locksn).andRelationidEqualTo(realtionid).andExpiredEqualTo(false);
                List<SpLockPassword> lockPasswords = passwordMapper.selectByExample(example);
                handleSetedPwd(lockPasswords, locksn, isDelete);
                handleSetedFinger(getOldChildFingerListInternal(locksn, realtionid), locksn, isDelete);
                if (isDelete) {
                    relationMapper.deleteByPrimaryKey(userid);
                    return true;
                }

            } else {
                //非老人和儿童类别则进行如下逻辑
                SpMember member = loginService.getUserByAccount(phone);
                //临时添加了，但暂未注册被直接删除
                if (member == null) {
                    int deleteRes = relationMapper.deleteByPrimaryKey(userid);
                    if (deleteRes == SqlStatus.ONE) {
                        return true;
                    }
                    return false;
                }
                int memberId = member.getUid();
                if (relationUser.getLockpwdauth()) {
                    if (relationUser.getSetedlockpwd()) {
                        SpLockPasswordExample example = new SpLockPasswordExample();
                        SpLockPasswordExample.Criteria criteria = example.createCriteria();
                        criteria.andLocksnEqualTo(locksn).andMemberidEqualTo(memberId).andExpiredEqualTo(false);
                        List<SpLockPassword> lockPasswords = passwordMapper.selectByExample(example);
                        handleSetedPwd(lockPasswords, locksn, isDelete);

                    }
                }
                if (relationUser.getFingerpwdauth()) {
                    List<SpLockFinger> fingers = getFingerListInternal(locksn, memberId);
                    handleSetedFinger(fingers, locksn, isDelete);
                }
            }
            if (isDelete) {
                relationMapper.deleteByPrimaryKey(userid);
                return true;
            }
        } catch (Exception e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除锁成员发生异常: " + e.getMessage());
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return false;
    }

    private void handleSetedPwd(List<SpLockPassword> lockPasswords, String locksn, boolean isDelete) {
        if (!lockPasswords.isEmpty()) {
            SpLockPassword lockPassword = lockPasswords.get(0);
            int seqPwdid = lockPassword.getPwdid();
            String res = delLockPwd(locksn, seqPwdid);
            if (CommonUtil.successResponse(res)) {
                SpLockPassword lPwd = new SpLockPassword();
                lPwd.setExpired(true);
                lPwd.setPwdid(seqPwdid);
                passwordMapper.updateByPrimaryKeySelective(lPwd);
            } else {
                isDelete = false;
            }
        }
    }

    private void handleSetedFinger(List<SpLockFinger> fingers, String locksn, boolean isDelete) {
        if (!fingers.isEmpty()) {
            SpLockFinger finger = fingers.get(0);
            int seqFingerid = finger.getId();
            String res = delLockPwd(locksn, seqFingerid);
            if (CommonUtil.successResponse(res)) {
                SpLockFinger finger1 = new SpLockFinger();
                finger1.setExpired(true);
                finger1.setId(seqFingerid);
                fingerMapper.updateByPrimaryKeySelective(finger1);
            } else {
                isDelete = false;
            }
        }
    }

    /**
     * 提取重复代码，查询获取用户指纹列表
     */
    private List<SpLockFinger> getFingerListInternal(String locksn, int memberId) {
        SpLockFingerExample example = new SpLockFingerExample();
        SpLockFingerExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(memberId).andLocksnEqualTo(locksn).andExpiredEqualTo(false);
        return fingerMapper.selectByExample(example);
    }

    /**
     * 获取老人儿童指纹
     */
    private List<SpLockFinger> getOldChildFingerListInternal(String locksn, int relationid) {
        SpLockFingerExample example = new SpLockFingerExample();
        SpLockFingerExample.Criteria criteria = example.createCriteria();
        criteria.andRelationidEqualTo(relationid).andLocksnEqualTo(locksn).andExpiredEqualTo(false);
        return fingerMapper.selectByExample(example);
    }


    /**
     * 删除锁密码/指纹/IC卡用户信息
     */
    @Override
    public String delLockPwd(String locksn, int seqid) {
        long timestamp = System.currentTimeMillis() / 1000;
        StringBuilder sb = new StringBuilder();
        String param = sb.append(locksn).append(seqid).append(timestamp).append(lockSalt).toString();
        String sign = Md5Utils.md5(param, "UTF-8");
        String combineParam = "method=del.lock.pwd&locksn=" + locksn + "&seqid=" + seqid + "&temptime=" + timestamp + "&sign=" + sign;
        return SimulateGetAndPostUtil.sendPost(url, combineParam);
    }

    /**
     * 更新租客过期时间
     *
     * @param userid 成员id
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateExpireDateForRenter(int userid, String locksn, int endtime) throws LockException {
        SpMemberRelation user = new SpMemberRelation();
        user.setId(userid);
        user.setEndtime(endtime);
        int updatedLine = relationMapper.updateByPrimaryKeySelective(user);
        if (updatedLine == SqlStatus.ONE) {
            return true;
        }
        return false;
    }

    /**
     * TODO 分布式事务
     * 分别删除单个手指指纹
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean deleteFingerAccordent(int seqid, String locksn) {
        String delResult = delLockPwd(locksn, seqid);
        boolean res = CommonUtil.successResponse(delResult);
        if (res) {
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
     */
    @Override
    public boolean deletePermentPwd(int memberid, String locksn, String phone, int relationid,int pwdid) {

        SpLockPassword lockPasswords1 = passwordMapper.selectByPrimaryKey(pwdid);
        if (lockPasswords1== null) {
            return false;
        }
        if (relationid != -1) {
//            SpLockPasswordExample example = new SpLockPasswordExample();
//            SpLockPasswordExample.Criteria criteria = example.createCriteria();
//            criteria.andLocksnEqualTo(locksn).andRelationidEqualTo(relationid).andPtypeEqualTo(PwdTypeEnum.ONE.getCode());
            if (dodelInHardare(lockPasswords1, locksn)) {
                SpMemberRelation updateRe = new SpMemberRelation();
                updateRe.setId(relationid);
                updateRe.setSetedlockpwd(false);
                relationMapper.updateByPrimaryKeySelective(updateRe);
                SpLockPassword password = new SpLockPassword();
                password.setDeltime(DateUtil.generateTenTime());
                password.setExpired(true);
                password.setPwdid(lockPasswords1.getPwdid());
                passwordMapper.updateByPrimaryKeySelective(password);
                return true;
            } else {
                return false;
            }
        }
//        SpLockPasswordExample example = new SpLockPasswordExample();
//        SpLockPasswordExample.Criteria criteria = example.createCriteria();
//        criteria.andLocksnEqualTo(locksn).andMemberidEqualTo(memberid).andPtypeEqualTo(PwdTypeEnum.ONE.getCode()).andExpiredEqualTo(false);
//        List<SpLockPassword> lockPasswords = passwordMapper.selectByExample(example);
//        if (lockPasswords.isEmpty()) {
//            return false;
//        }

        if (dodelInHardare(lockPasswords1, locksn)) {
            //管理员删除密码或者用户删除密码
            SpLockAdmin adminLock = isNotAdminLock(memberid, locksn);
            if (adminLock == null) {
                SpMemberRelation memberRelation = isMemberRelation(phone, locksn);
                if (memberRelation != null) {
                    SpMemberRelationExample example1 = new SpMemberRelationExample();
                    SpMemberRelationExample.Criteria criteria1 = example1.createCriteria();
                    criteria1.andPhoneEqualTo(phone).andLocksnEqualTo(locksn);
                    SpMemberRelation user = new SpMemberRelation();
                    user.setSetedlockpwd(false);
                    user.setUpdatetime(DateUtil.generateTenTime());
                    relationMapper.updateByExampleSelective(user, example1);
                }
            } else {
                SpLockAdmin lock = new SpLockAdmin();
                lock.setSetedlockpwd(false);
                lock.setId(adminLock.getId());
                lock.setUpdatetime(DateUtil.generateTenTime());
                adminLockMapper.updateByPrimaryKeySelective(lock);
            }
            SpLockPassword password = new SpLockPassword();
            password.setDeltime(DateUtil.generateTenTime());
            password.setExpired(true);
            password.setPwdid(lockPasswords1.getPwdid());
            passwordMapper.updateByPrimaryKeySelective(password);
            return true;
        }
        return false;
    }

    private boolean dodelInHardare(SpLockPassword lockPassword, String locksn) { ;
        int seqid = lockPassword.getPwdid();
        String delResult = delLockPwd(locksn, seqid);
        return CommonUtil.successResponse(delResult);
    }

    private SpMemberRelation isMemberRelation(String phone, String locksn) {
        SpMemberRelationExample example1 = new SpMemberRelationExample();
        SpMemberRelationExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andPhoneEqualTo(phone).andLocksnEqualTo(locksn);
        List<SpMemberRelation> list1 = relationMapper.selectByExample(example1);
        if (list1.isEmpty()) {
            return null;
        }
        return list1.get(0);
    }

    private SpLockAdmin isNotAdminLock(int memberid, String locksn) {
        SpLockAdminExample example = new SpLockAdminExample();
        SpLockAdminExample.Criteria criteria = example.createCriteria();
        criteria.andAdminidEqualTo(memberid).andLocksnEqualTo(locksn);
        List<SpLockAdmin> list = adminLockMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 获取设置记录
     */
    @Override
    public List<SetRecord> getSetRecords(SpMember member, String locksn, int page, int begintime) {
        int memberid = member.getUid();
        SpActionRecordsExample example = new SpActionRecordsExample();
        SpActionRecordsExample.Criteria criteria = example.createCriteria();
        SpLockAdmin adminLock = isNotAdminLock(memberid, locksn);
        if (adminLock == null) {
            //SpMemberRelation memberRelation = isMemberRelation(member.getPhone(), locksn);
            criteria.andMemberidEqualTo(memberid).andLocksnEqualTo(locksn).andActiontimeGreaterThanOrEqualTo(begintime);
        } else {

            criteria.andLocksnEqualTo(locksn).andActiontimeGreaterThanOrEqualTo(begintime);
        }
        //按添加时间降序
        example.setOrderByClause("actiontime DESC");
        PageHelper.startPage(page, pagesize);
        List<SpActionRecords> records = recordsMapper.selectByExample(example);
        if (records.isEmpty()) {
            return null;
        }
        Set<Integer> memberids = new HashSet<>();
        Iterator<SpActionRecords> iterator = records.iterator();
        while (iterator.hasNext()) {
            SpActionRecords record = iterator.next();
            memberids.add(record.getMemberid());
        }
        List<Integer> ids = new ArrayList<>(memberids);
        SpMemberExample example1 = new SpMemberExample();
        SpMemberExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andUidIn(ids);
        List<SpMember> members = memberMapper.selectByExample(example1);
        List<SetRecord> res = new ArrayList<>();
        for (SpActionRecords record : records) {
            for (SpMember user : members) {
                int mid = record.getMemberid();
                int uid = user.getUid();
                String nickname = user.getBname();
                if (mid == uid) {
                    SetRecord r = new SetRecord();
                    r.setAction(record.getAction());
                    r.setNickname(nickname == null ? user.getName() : nickname);
                    r.setTime(record.getActiontime());
                    res.add(r);
                }
            }
        }
        return res;
    }


    /**
     * 获取用户指纹列表
     */
    @Override
    public List<SpLockFinger> getFingerList(int memberid, String locksn, int relationid) {

        if (relationid == 0) {
            return getFingerListInternal(locksn, memberid);
        }
        return getOldChildFingerListInternal(locksn, relationid);
    }

    /**
     * 用于前台判断是否已经设置锁永久密码
     * @Date 2018.11.15.16.42修改by 代平：若已经设置密码则返回密码id
     */
    @Override
    public Map isSetLockPwdForever(String locksn, String phone, int memberid, int relationid) {
        Map res = new HashMap(2);

        if (relationid != -1) {
            //老人和儿童
            SpMemberRelation relation = relationMapper.selectByPrimaryKey(relationid);
            if (relation != null) {
                Boolean setedlockpwd = relation.getSetedlockpwd();
                if (setedlockpwd) {
                    SpLockPasswordExample spexample = new SpLockPasswordExample();
                    SpLockPasswordExample.Criteria criteria = spexample.createCriteria();
                    criteria.andRelationidEqualTo(relationid).andLocksnEqualTo(locksn).andExpiredEqualTo(false);
                    List<SpLockPassword> lockPasswords = passwordMapper.selectByExample(spexample);
                    if (!lockPasswords.isEmpty()) {
                        SpLockPassword lockPassword = lockPasswords.get(0);
                        Integer pwdid = lockPassword.getPwdid();
                        res.put("pwdid", pwdid);
                        res.put("setedpwd", setedlockpwd);
                        return res;
                    }
                }{
                    return null;
                }
            }
        }

        SpLockAdmin adminLock = isNotAdminLock(memberid, locksn);
        SpMemberRelation memberRelation = null;
        if (adminLock == null && (memberRelation = isMemberRelation(phone, locksn)) == null) {
            return null;
        }

        if ((adminLock != null && adminLock.getSetedlockpwd() || (memberRelation != null && memberRelation.getSetedlockpwd()))) {
            SpLockPasswordExample spexample = new SpLockPasswordExample();
            SpLockPasswordExample.Criteria criteria = spexample.createCriteria();
            criteria.andMemberidEqualTo(memberid).andLocksnEqualTo(locksn).andExpiredEqualTo(false);
            List<SpLockPassword> lockPasswords = passwordMapper.selectByExample(spexample);
            if (!lockPasswords.isEmpty()) {
                SpLockPassword lockPassword = lockPasswords.get(0);
                Integer pwdid = lockPassword.getPwdid();
                res.put("pwdid", pwdid);
                res.put("setedpwd", true);
                return res;
            }
        }
        return null;

    }


    /**
     * 获取锁成员
     * 被添加为锁成员的租户需要在30天内注册app并设置密码
     * usertype 3表示租户，只有租户有过期时间，
     * 当租户30天内未注册系统时，则过期并删除
     */
    @Override
    public List<SpMemberRelation> getMembersForByAdminId(int adminId, String locksn) {
        List<SpMemberRelation> members = relationMapper.selectByExample(extractDuplicatCode(adminId, locksn));
        List<SpMemberRelation> validMembers = new ArrayList<>();
        List<SpMemberRelation> invalidMembers = new ArrayList<>();
        Iterator<SpMemberRelation> iterator = members.iterator();
        while (iterator.hasNext()) {
            SpMemberRelation user = iterator.next();
            String phone = user.getPhone();
            int end = user.getEndtime();
            String usertype = user.getUsertype();

            if (UsertypeEnum.THREE.getCode().equals(usertype)) {

                //检查被添加租户是否注册，未注册再判断是否过期
                if (!loginService.checkAccount1(phone)) {

                    if (DateUtil.isNotExpired(end, deadline)) {
                        validMembers.add(user);
                    } else {
                        invalidMembers.add(user);
                    }
                } else {
                    validMembers.add(user);
                }

            } else {
                validMembers.add(user);
            }

        }
        if (!invalidMembers.isEmpty()) {
            SpMemberRelationExample example = new SpMemberRelationExample();
            SpMemberRelationExample.Criteria criteria = example.createCriteria();
            criteria.andParentidEqualTo(invalidMembers.get(0).getParentid());
            relationMapper.deleteByExample(example);
        }
        return validMembers;
    }

    /**
     * 抽取重复代码
     */
    private SpMemberRelationExample extractDuplicatCode(int id, String locksn) {
        SpMemberRelationExample example = new SpMemberRelationExample();
        SpMemberRelationExample.Criteria criteria = example.createCriteria();
        criteria.andParentidEqualTo(id).andLocksnEqualTo(locksn);
        return example;
    }

    /**
     * 管理员更新锁成员的指纹和密码权限以及已经设置的密码和指纹
     *
     * @param id     relationId关系用户id
     * @param status 0,关闭，1开启
     * @param type   finger,pwd(String 类型)
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateMemberLockPwdOrFinger(int id, String locksn, int status, int type) {
        //2：启用 3：暂停
        int state;
        boolean expired;
        boolean notExpired;
        if (status == SqlStatus.ZERO) {
            state = 3;
            expired = true;
            notExpired = false;

        } else {
            state = 2;
            expired = false;
            notExpired = true;
        }
        SpMemberRelation relation = relationMapper.selectByPrimaryKey(id);
        SpMember registed = loginService.getUserByAccount(relation.getPhone());
        SpMemberRelation user = new SpMemberRelation();
        if (registed != null) {
            int uid = registed.getUid();
            if (PtypeAuthEnum.FINGER.getCode() == type) {
                List<SpLockFinger> fingers = getLockFingerPwdsRegisted(locksn, uid, notExpired);
                if (!iterateFingers(fingers, locksn, state, expired)) {
                    return false;
                }

            } else {
                List<SpLockPassword> lockpwds = getLockPwdListRegisted(notExpired, locksn, uid);
                if (!iteratePwds(lockpwds, locksn, state, expired)) {
                    return false;
                }
            }

        } else {
            if (UsertypeEnum.TWO.getCode().equals(relation.getUsertype())) {
                if (PtypeAuthEnum.FINGER.getCode() == type) {
                    List<SpLockFinger> fingers = getLockFingerPwdUnregisted(relation.getId(), locksn, notExpired);
                    if (!iterateFingers(fingers, locksn, state, expired)) {
                        return false;
                    }
                } else {
                    List<SpLockPassword> lockpwds = getLockPwdListUnregisted(notExpired, locksn, relation.getId());
                    if (!iteratePwds(lockpwds, locksn, state, expired)) {
                        return false;
                    }
                }
            }
        }
        //更新成员列表指纹或密码权限
        if (PtypeAuthEnum.FINGER.getCode() == type) {
            if (status == SqlStatus.ZERO) {
                user.setFingerpwdauth(false);
            } else {
                user.setFingerpwdauth(true);
            }

        } else {
            if (status == SqlStatus.ZERO) {
                user.setLockpwdauth(false);
            } else {
                user.setLockpwdauth(true);
            }
        }
        int index = relationMapper.updateByExampleSelective(user, dup(locksn, id));
        if (index == SqlStatus.ONE) {
            return true;
        }
        return false;
    }

    private List<SpLockFinger> getLockFingerPwdUnregisted(int relationid, String locksn, boolean notExpired) {
        SpLockFingerExample fingerExample = new SpLockFingerExample();
        SpLockFingerExample.Criteria criteria = fingerExample.createCriteria();
        criteria.andRelationidEqualTo(relationid).andLocksnEqualTo(locksn).andExpiredEqualTo(notExpired);
        return fingerMapper.selectByExample(fingerExample);
    }

    private List<SpLockFinger> getLockFingerPwdsRegisted(String locksn, int uid, boolean notExpired) {
        SpLockFingerExample fingerExample = new SpLockFingerExample();
        SpLockFingerExample.Criteria criteria = fingerExample.createCriteria();
        criteria.andLocksnEqualTo(locksn).andMemberidEqualTo(uid).andExpiredEqualTo(notExpired);
        return fingerMapper.selectByExample(fingerExample);
    }

    private List<SpLockPassword> getLockPwdListUnregisted(boolean notExpired, String locksn, int relationid) {
        SpLockPasswordExample passwordExample = new SpLockPasswordExample();
        SpLockPasswordExample.Criteria criteria = passwordExample.createCriteria();
        criteria.andExpiredEqualTo(notExpired).andLocksnEqualTo(locksn).andRelationidEqualTo(relationid);
        return passwordMapper.selectByExample(passwordExample);
    }

    private List<SpLockPassword> getLockPwdListRegisted(boolean notExpired, String locksn, int uid) {
        SpLockPasswordExample passwordExample = new SpLockPasswordExample();
        SpLockPasswordExample.Criteria criteria = passwordExample.createCriteria();
        criteria.andExpiredEqualTo(notExpired).andLocksnEqualTo(locksn).andMemberidEqualTo(uid);
        return passwordMapper.selectByExample(passwordExample);
    }

    private boolean iterateFingers(List<SpLockFinger> fingers, String locksn, int state, boolean expired) {
        Iterator<SpLockFinger> it = fingers.iterator();
        while (it.hasNext()) {
            SpLockFinger spLockFinger = it.next();
            int id1 = spLockFinger.getId();
            //可能会出现部分关闭成功
            if (!manageUser(locksn, id1, state)) {
                return false;
            }
            SpLockFinger sf = new SpLockFinger();
            sf.setExpired(expired);
            sf.setId(id1);
            fingerMapper.updateByPrimaryKeySelective(sf);

        }
        return true;
    }

    private boolean iteratePwds(List<SpLockPassword> list2, String locksn, int state, boolean expired) {
        Iterator<SpLockPassword> it = list2.iterator();
        while (it.hasNext()) {
            SpLockPassword spLockPassword = it.next();
            int pwdid = spLockPassword.getPwdid();
            int endtime = spLockPassword.getDeltime();
            if (DateUtil.notExpired(endtime)) {
                if (!manageUser(locksn, pwdid, state)) {
                    return false;
                }
                SpLockPassword sp = new SpLockPassword();
                sp.setPwdid(pwdid);
                sp.setExpired(expired);
                passwordMapper.updateByPrimaryKeySelective(sp);
            }
        }
        return true;
    }

    private SpMemberRelationExample dup(String locksn, int relationid) {
        SpMemberRelationExample example = new SpMemberRelationExample();
        SpMemberRelationExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(relationid).andLocksnEqualTo(locksn);
        return example;
    }

    /**
     * 管理用户信息(暂时用于移除临时密码)
     *
     * @param state 状态 1：删除 2：启用 3：暂停
     */
    @Override
    public boolean manageUser(String locksn, int seqid, int state) {
        long timestamp = DateUtil.generateTenTime();
        //locksn seqid state temptime
        StringBuilder sb = new StringBuilder();
        String ss = sb.append(locksn).append(seqid).append(state).append(timestamp).append(lockSalt).toString();
        String sign = Md5Utils.md5(ss, "UTF-8");
        StringBuilder toCombine = new StringBuilder("method=edit.lock.user&seqid=").append(seqid);
        String param =  toCombine.append("&temptime=").append(timestamp).
                        append("&state=").append(state).
                        append("&sign=").append(sign).
                        append("&locksn=").append(locksn).toString();
        String result = SimulateGetAndPostUtil.sendPost(url, param);
        if (CommonUtil.successResponse(result)) {
            //删除状态做更新
            if (state == 1) {
                setDbLockPwdExpired(seqid);
            }
            return true;
        }
        return false;
    }

    private void setDbLockPwdExpired(int seqid) {
        SpLockPassword password = new SpLockPassword();
        password.setPwdid(seqid);
        password.setExpired(true);
        password.setDeltime(DateUtil.generateTenTime());
        passwordMapper.updateByPrimaryKeySelective(password);
    }
}
