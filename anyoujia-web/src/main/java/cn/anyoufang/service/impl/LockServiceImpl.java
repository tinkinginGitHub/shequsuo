package cn.anyoufang.service.impl;

import cn.anyoufang.entity.SpLock;
import cn.anyoufang.entity.SpLockAdmin;
import cn.anyoufang.entity.SpLockAdminExample;
import cn.anyoufang.entity.SpLockFinger;
import cn.anyoufang.entity.SpLockFingerExample;
import cn.anyoufang.entity.SpLockPassword;
import cn.anyoufang.entity.SpLockPasswordExample;
import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.SpMemberRelation;
import cn.anyoufang.entity.SpMemberRelationExample;
import cn.anyoufang.entity.selfdefined.AnyoujiaResult;
import cn.anyoufang.entity.selfdefined.InitParam;
import cn.anyoufang.entity.selfdefined.Lock;
import cn.anyoufang.entity.selfdefined.LockCombineInfo;
import cn.anyoufang.entity.selfdefined.LockInfo;
import cn.anyoufang.entity.selfdefined.LockRecord;
import cn.anyoufang.entity.selfdefined.Temppwd;
import cn.anyoufang.enumresource.HttpCodeEnum;
import cn.anyoufang.enumresource.PwdTypeEnum;
import cn.anyoufang.enumresource.StateEnum;
import cn.anyoufang.exception.LockException;
import cn.anyoufang.mapper.SpLockAdminMapper;
import cn.anyoufang.mapper.SpLockFingerMapper;
import cn.anyoufang.mapper.SpLockMapper;
import cn.anyoufang.mapper.SpLockPasswordMapper;
import cn.anyoufang.mapper.SpMemberMapper;
import cn.anyoufang.mapper.SpMemberRelationMapper;
import cn.anyoufang.service.LockService;
import cn.anyoufang.utils.DateUtil;
import cn.anyoufang.utils.JsonUtils;
import cn.anyoufang.utils.Md5Utils;
import cn.anyoufang.utils.SimulateGetAndPostUtil;
import cn.anyoufang.utils.SortJsonAesc;
import cn.anyoufang.utils.pagehelper.PageHelper;
import cn.anyoufang.utils.selfdefine.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static cn.anyoufang.utils.HandlePhpRequestUtil.doPhpRequest;
import static cn.anyoufang.utils.HandlePhpRequestUtil.genarateSign;
import static cn.anyoufang.utils.HandlePhpRequestUtil.parseResponse;

/**
 * @author daiping
 */
@Service
public class LockServiceImpl implements LockService {

    private static final Logger log = LoggerFactory.getLogger(LockServiceImpl.class);
    private static final int T_H = HttpCodeEnum.TWO_HUNDRED.getCode();
    private static final int FOUR_H = HttpCodeEnum.FOUR_HUNDRED.getCode();
    private static final int FIVE_H = HttpCodeEnum.FIVE_HUNDRED.getCode();
    private static final int T_H_1 = HttpCodeEnum.TWO_HUNDRED1.getCode();
    private static final String T_H_1_MSG = HttpCodeEnum.TWO_HUNDRED1.getValue();
    /**
     * 魔法数字，临时使用来区分指纹id和密码id,指纹，密码两张表都是使用的id自增长，最终会有相同的时候。
     * 已有替代方案是通过返回的usertype来区分是指纹还是密码记录
     */
    private static final int delimeterForFingerAndPwdId = 470101011;

    @Value("${lock.salt}")
    private String lockSalt;
    @Value("${pageSize}")
    private int pagesize;
    @Value("${lock.url}")
    private String url;

    @Value("${deadline}")
    private int deadline;

    @Value("${php.Project.url}")
    private String phpProjectUrl;
    @Resource
    private SpMemberRelationMapper relationMapper;

    @Autowired
    private SpLockAdminMapper lockMapper;

    @Autowired
    private SpMemberMapper memberMapper;

    @Autowired
    private SpLockPasswordMapper passwordMapper;

    @Autowired
    private SpLockFingerMapper fingerMapper;

    @Autowired
    private SpLockMapper lockinfoMapper;


    /**
     * 设置用户锁密码
     *
     * @param ptype    类型 1: 永久 2：一次 3：限时
     * @param memberid 会员id
     * @param motive   临时密码 动机
     */
    @Override
    public Map<String, String> setLockPwd(int ptype,
                                          int memberid,
                                          String locksn,
                                          int endtime,
                                          String pwds,
                                          String nickname,
                                          String phone,
                                          boolean isAdmin, String motive, int relationid) {

        if(isAdmin && relationid != 0){
            SpMemberRelation relation = relationMapper.selectByPrimaryKey(relationid);
            if(relation != null){
                String username = relation.getUsername();
                nickname = username;
            }
        }
        int seqid;
        if (ptype == 1) {
            SpLockPasswordExample example = new SpLockPasswordExample();
            SpLockPasswordExample.Criteria criteria = example.createCriteria();
            criteria.andMemberidEqualTo(memberid).andLocksnEqualTo(locksn).andExpiredEqualTo(false);
            List<SpLockPassword> lockPasswords = passwordMapper.selectByExample(example);
            if(!lockPasswords.isEmpty()){
                SpLockPassword lockPassword = lockPasswords.get(0);
                seqid = lockPassword.getPwdid();
            }else{
                Map<String,String> res = new HashMap<>(2);
                res.put("status","201");
                res.put("msg","暂无数据");
                return res;
            }
        }else {
            //保存记录并生成seqid
            SpLockPassword lockPassword = new SpLockPassword();
            lockPassword.setLocksn(locksn);
            lockPassword.setMemberid(memberid);
            lockPassword.setPtype(ptype);
            lockPassword.setExpired(false);
            lockPassword.setMotive(motive);
            if (relationid != 0) {
                lockPassword.setRelationid(relationid);
            }
            //临时密码的过期时间，永久密码的删除时间,这里不做判断，因为在删除永久密码的时候会更新删除时间
            lockPassword.setDeltime(endtime);
            lockPassword.setAddtime(DateUtil.generateTenTime());
            passwordMapper.insertSelective(lockPassword);
            seqid = lockPassword.getPwdid();
        }
        long timestamp = DateUtil.generateTenTime();
        StringBuilder sb = new StringBuilder();
        //永久密码不传时间
        if (endtime != 0) {
            sb = sb.append(endtime);
        }
        String param = sb.append(locksn).append(nickname).append(ptype).append(pwds).append(timestamp).append(lockSalt).toString();
        String sign = Md5Utils.md5(param, "UTF-8");
        StringBuilder toCombine = new StringBuilder("method=set.lock.pwd&ptype=").append(ptype);
         toCombine.append("&temptime=").append(timestamp).
                  append("&sign=").append(sign).
                  append("&seqid=").append(seqid).
                  append("&locksn=").append(locksn).
                  append("&pwds=").append(pwds).
                  append("&nickname=").append(nickname);
        //永久密码不传时间参数
        if (endtime != 0) {
           toCombine.append("&endtime=").append(endtime);
        }
        String combineParam = toCombine.toString();
        String res = SimulateGetAndPostUtil.sendPost(url, combineParam);
        try {
            Map<String, Object> data = JsonUtils.jsonToMap(res);
            Map<String, String> parsedMap = new HashMap<>(2);
            String code = String.valueOf(data.get("code"));
            parsedMap.put("code", code);
            parsedMap.put("msg", String.valueOf(data.get("message")));
            if (Integer.valueOf(code) == T_H) {
                if (isAdmin) {
                    //只有永久密码被设置后这个标示才能设置为true
                    if (PwdTypeEnum.ONE.getCode() == ptype) {
                        //不为0，则表示管理员给老人和儿童设置密码
                        if (relationid != 0) {
                            SpMemberRelation oldChild = new SpMemberRelation();
                            oldChild.setSetedlockpwd(true);
                            oldChild.setId(relationid);
                            relationMapper.updateByPrimaryKeySelective(oldChild);

                        } else {
                            SpLockAdmin lock = new SpLockAdmin();
                            lock.setSetedlockpwd(true);
                            lock.setUpdatetime(DateUtil.generateTenTime());
                            SpLockAdminExample example = new SpLockAdminExample();
                            SpLockAdminExample.Criteria criteria = example.createCriteria();
                            criteria.andAdminidEqualTo(memberid).andLocksnEqualTo(locksn);
                            lockMapper.updateByExampleSelective(lock, example);
                        }

                    }

                } else {
                    //只有永久密码被设置后这个标示才能设置为true
                    if (PwdTypeEnum.ONE.getCode() == ptype) {
                        SpMemberRelation user = new SpMemberRelation();
                        user.setSetedlockpwd(true);
                        relationMapper.updateByExampleSelective(user, getExample(phone, locksn));
                    }
                }
                return parsedMap;
            }
            //不成功，删除无用记录
            passwordMapper.deleteByPrimaryKey(seqid);
            return null;
        } catch (Exception e) {
            if (log.isInfoEnabled()) {
                log.info("设置锁密码失败:" + e.getMessage());
            }
            return null;
        }
    }

    /**
     * 设置用户指纹密码
     *
     * @param usertype   2:指纹 3：IC卡
     * @param fingerdesc 指纹描述
     */
    @Override
    public AnyoujiaResult setLockUserFingerPassword(String locksn,
                                                    int endtime,
                                                    int usertype,
                                                    String nickname,
                                                    String phone, String fingerdesc, int fingerid, boolean isAdmin, int relationid) {
        //管理员给其他老人儿童添加指纹
        if(isAdmin && relationid != 0){
            SpMemberRelation relation = relationMapper.selectByPrimaryKey(relationid);
            if(relation != null){
                String username = relation.getUsername();
                nickname = username;
            }
        }
        int ptype = 1;
        long timestamp = DateUtil.generateTenTime();
        StringBuilder sb = new StringBuilder();
        String param = sb.append(endtime)
                .append(locksn)
                .append(nickname)
                .append(ptype)
                .append(fingerid)
                .append(timestamp)
                .append(usertype)
                .append(lockSalt).toString();
        String sign = Md5Utils.md5(param, "UTF-8");
        StringBuilder toCombine = new StringBuilder("method=set.lock.user&ptype=").append(ptype);
        String combineParam = toCombine.append("&temptime=").append(timestamp).
                append("&sign=").append(sign).
                append("&seqid=").append(fingerid).
                append("&locksn=").append(locksn).
                append("&endtime=").append(endtime).
                append("&ptype=").append(ptype).append("&nickname=").append(nickname).append("&usertype=").append(usertype).toString();
        String res = SimulateGetAndPostUtil.sendPost(url, combineParam);
        Map<String, Object> data = JsonUtils.jsonToMap(res);
        String code = String.valueOf(data.get("code"));
        int state = Integer.valueOf(code);

        if (state == T_H) {
            SpLockFinger finger = new SpLockFinger();
            finger.setId(fingerid);
            finger.setFingerdesc(fingerdesc);
            finger.setExpired(false);
            if(relationid !=0){
                finger.setRelationid(relationid);
            }
            fingerMapper.updateByPrimaryKeySelective(finger);
            return AnyoujiaResult.build(T_H, String.valueOf(data.get("message")));
        }
        //不成功则删除无用记录
        fingerMapper.deleteByPrimaryKey(fingerid);
        return AnyoujiaResult.build(state, String.valueOf(data.get("message")));
    }

    /**
     * 提取重复代码
     * 根据手机号和锁sn获取
     */
    private SpMemberRelationExample getExample(String phone, String locksn) {
        SpMemberRelationExample example = new SpMemberRelationExample();
        SpMemberRelationExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone).andLocksnEqualTo(locksn);
        return example;
    }

    /**
     * 注册锁管理员使用sp_admin_lock 这张表的id;
     */
    @Override
    public AnyoujiaResult registerLockInfo(String locksn, int userid) throws Exception {

        if (checkLockRegisted(locksn)) {
            return AnyoujiaResult.build(FOUR_H, "门锁已经注册");
        }
        //调用PHP接口判断锁位置是否正确
        AnyoujiaResult ar = callPhpToActiveLock(locksn, userid);
        if (ar != null) {
            return ar;
        }
        long timestamp = DateUtil.generateTenTime();
        long time = timestamp;
        SpLockAdmin lock = new SpLockAdmin();
        lock.setAdminid(userid);
        lock.setLocksn(locksn);
        lock.setCreatetime((int) time);
        try {
            lockMapper.insertSelective(lock);
        } catch (Exception e) {
            if (log.isInfoEnabled()) {
                log.info(e.getMessage());
            }
            return AnyoujiaResult.build(FIVE_H, "添加锁发生异常，请重新操作");
        }
        StringBuilder sb = new StringBuilder();
        final int id = lock.getId();
        String param = sb.append(locksn).append(timestamp).append(id).append(lockSalt).toString();
        String sign = Md5Utils.md5(param, "UTF-8");
        StringBuilder toCombine = new StringBuilder("method=register.lock.info&userid=").append(id);
        String combineParam = toCombine.append("&locksn=").append(locksn).
                append("&temptime=").append(timestamp).
                append("&sign=").append(sign).toString();
        String res = SimulateGetAndPostUtil.sendPost(url, combineParam);
        Map<String, Object> data;
        try {
            data = JsonUtils.jsonToMap(res);
        } catch (LockException e) {
            if (log.isInfoEnabled()) {
                log.info(e.getMessage());
            }
            return AnyoujiaResult.build(FIVE_H, "系统错误");
        }
        String state = String.valueOf(data.get("code"));
        Integer status = Integer.valueOf(state);
        if (status == T_H) {
            SpLock spLock = new SpLock();
            spLock.setSn(locksn);
            spLock.setActive(true);
            spLock.setActivetime(DateUtil.generateTenTime());
            lockinfoMapper.updateByPrimaryKeySelective(spLock);
        } else {
            //如果不成功，则删除无用数据，防止不能再次添加
            lockMapper.deleteByPrimaryKey(id);
        }
        return AnyoujiaResult.build(status, String.valueOf(data.get("message")));
    }


    /**
     * 检查锁已经被注册
     */
    private boolean checkLockRegisted(String locksn) {
        SpLockAdminExample example = new SpLockAdminExample();
        SpLockAdminExample.Criteria criteria = example.createCriteria();
        criteria.andLocksnEqualTo(locksn);
        List<SpLockAdmin> list = lockMapper.selectByExample(example);
        if (list.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 获取锁列表
     */
    @Override
    public AnyoujiaResult getAllLockList(SpMember user) {

        String phone = user.getPhone();
        List<String> allLocksns = new ArrayList<>();
        List<SpLockAdmin> admins = getAdminLocks(user.getUid());
        List<SpMemberRelation> mR = relationMapper.selectByExample(combineExampleByPhone(phone));
        //如果用户既没有被添加过，也不是锁管理员，则直接返回
        if (admins.isEmpty() && mR.isEmpty()) {
            return AnyoujiaResult.build(T_H_1, T_H_1_MSG);
        }
        //作为管理员的锁
        Iterator<SpLockAdmin> itr = admins.iterator();
        List<String> adminLockResList = new ArrayList<>();
        while (itr.hasNext()) {
            SpLockAdmin adminLock = itr.next();
            allLocksns.add(adminLock.getLocksn());
            adminLockResList.add(getLockResFromHardare(adminLock));
        }
        List<SpMemberRelation> valid = new ArrayList<>();
        List<SpMemberRelation> invalid = new ArrayList<>();
        Iterator<SpMemberRelation> it = mR.iterator();
        while (it.hasNext()) {
            SpMemberRelation mr = it.next();
            if (DateUtil.isNotExpired(mr.getEndtime(), deadline)) {
                valid.add(mr);
            } else {
                invalid.add(mr);
            }
        }
        //存在无效数据，执行删除
        if (!invalid.isEmpty()) {
            relationMapper.deleteByExample(combineExampleByPhone(phone));
        }
        List<Integer> adminIds = new ArrayList<>();
        List<String> locksns = new ArrayList<>();
        List<Lock> member = null;
        if (!valid.isEmpty()) {
            Map<String, Boolean> pwdauths = new HashMap<>();
            Map<String, Boolean> fingerauths = new HashMap<>();
            Iterator<SpMemberRelation> iteratorVal = valid.iterator();
            while (iteratorVal.hasNext()) {
                SpMemberRelation u = iteratorVal.next();
                Integer parentid = u.getParentid();
                String locksn = u.getLocksn();
                pwdauths.put(locksn, u.getLockpwdauth());
                fingerauths.put(locksn, u.getFingerpwdauth());
                adminIds.add(parentid);
                locksns.add(locksn);
            }
            //后期优化
            SpLockAdminExample e = new SpLockAdminExample();
            SpLockAdminExample.Criteria criteria = e.createCriteria();
            criteria.andAdminidIn(adminIds).andLocksnIn(locksns);
            List<SpLockAdmin> locks = lockMapper.selectByExample(e);
            List<String> memberLockResList = new ArrayList<>();
            Iterator<SpLockAdmin> iterator = locks.iterator();
            while (iterator.hasNext()) {
                SpLockAdmin adminLock = iterator.next();
                memberLockResList.add(getLockResFromHardare(adminLock));
            }
            member = CommonUtil.getMemberLockList(memberLockResList, pwdauths, fingerauths);
        }
        //将成员锁和管理员锁的sn放在一起去拿锁基本信息
        allLocksns.addAll(locksns);
        //获取锁相关基本信息
        List<LockCombineInfo> combineInfos = lockinfoMapper.selectLockInfoByCombinetable(allLocksns);
        //将自己作为管理员的和作为成员的锁分开
        List<Lock> admin = CommonUtil.getAdminLockList(adminLockResList);
        List<Lock> res = new LinkedList<>(admin);
        if (member != null && !member.isEmpty()) {
            res.addAll(member);
        }
        if (!combineInfos.isEmpty()) {
            String locksn;
            for (Lock lock : res) {
                for (LockCombineInfo l : combineInfos) {
                    if ((locksn = lock.getLocksn()).equals(l.getSn())) {
                        String color = l.getColor();
                        if (color != null) {
                            String[] c = color.split(",");
                            lock.setColor(c[1]);
                        }
                        lock.setModel(l.getModel());
                        lock.setOrigin(l.getOrigin());
                        StringBuilder sb = new StringBuilder(l.getCname());
                        String address = sb.append(l.getAddress()).toString();
                        lock.setAddress(address);
                        lock.setCode2(l.getCode2());
                        lock.setProducttime(l.getProducttime());
                        lock.setProkey(l.getProkey());
                        Map<String, String> nicknameAndPhone = memberMapper.selectBySn(locksn);
                        String adminNickname = nicknameAndPhone.get("bname");
                        if (adminNickname != null) {
                            lock.setNickname(adminNickname);
                        } else {
                            String adminPhone = nicknameAndPhone.get("phone");
                            lock.setNickname(adminPhone);
                        }

                    }
                }
            }
        }
        return AnyoujiaResult.ok(res);
    }

    private String getLockResFromHardare(SpLockAdmin adminLock) {
        int id = adminLock.getId();
        StringBuilder sb = new StringBuilder();
        long timestamp = DateUtil.generateTenTime();
        String param = sb.append(timestamp).append(id).append(lockSalt).toString();
        String sign = Md5Utils.md5(param, "UTF-8");
        StringBuilder toCombine = new StringBuilder("method=get.lock.list&userid=");
        String combineParam = toCombine.append(id).
                append("&temptime=").append(timestamp).
                append("&sign=").append(sign).toString();
        return SimulateGetAndPostUtil.sendPost(url, combineParam);
    }

    @Override
    public AnyoujiaResult getLockRecords(String locksn, int isalarm, int page, int begintime) {
        long timestamp = DateUtil.generateTenTime();
        StringBuilder sb = new StringBuilder();
        if (begintime != -1) {
            sb.append(begintime);
        }
        String param = sb.append(isalarm).append(locksn).append(page).append(pagesize).append(timestamp).append(lockSalt).toString();
        String sign = Md5Utils.md5(param, "UTF-8");
        StringBuilder p = new StringBuilder("method=get.lock.openlist&page=");
        p.append(page).
                append("&pagesize=").append(pagesize).
                append("&locksn=").append(locksn).
                append("&temptime=").append(timestamp).
                append("&sign=").append(sign).
                append("&isalarm=").append(isalarm);

        if (begintime != -1) {
            p.append("&begintime=").append(begintime).toString();
        }
        String combineParam = p.toString();
        String res = SimulateGetAndPostUtil.sendPost(url, combineParam);
        if (CommonUtil.successResponse(res)) {
            List<LockRecord> list = CommonUtil.getRecords(res);
            if (list.isEmpty()) {
                return AnyoujiaResult.build(T_H_1, T_H_1_MSG);
            }
            Iterator<LockRecord> iterator = list.iterator();
            while (iterator.hasNext()) {
                LockRecord lr = iterator.next();
                int userid = lr.getPwdRecordId();
                int usertype = lr.getUserType();
                int oldChildId;
                if (isUseridSmaller(userid, delimeterForFingerAndPwdId) || CommonUtil.checkIfPwdRecord(usertype)) {
                    SpLockPassword slp = passwordMapper.selectByPrimaryKey(userid);
                    if (slp != null) {
                        if ( slp.getRelationid() !=null&&((oldChildId =slp.getRelationid()) != 0)) {
                            //老人儿童
                            SpMemberRelation sr = relationMapper.selectByPrimaryKey(oldChildId);
                            lr.setRelation(sr.getUserrelation());
                        } else {
                            Integer memberid = slp.getMemberid();
                            Map<String, Object> sr = memberMapper.selectByIdJoinFind(memberid,locksn);
                            if (sr != null) {
                                lr.setHeadurl(String.valueOf(sr.get("avatar")));
                                lr.setRelation(String.valueOf(sr.get("userrelation")));
                                Integer gender = (Integer) sr.get("gender");
                                lr.setGender(gender);
                                lr.setRelationType(String.valueOf(sr.get("usertype")));
                            }else {
                                SpMember member = memberMapper.selectByPrimaryKey(memberid);
                                if(member !=null){
                                    lr.setHeadurl(member.getAvatar());
                                    lr.setRelation("自己");
                                    lr.setGender(member.getGender());
                                }
                            }

                        }
                    }
                } else if(CommonUtil.checkIfFingerRecord(usertype)) {
                    SpLockFinger slf = fingerMapper.selectByPrimaryKey(userid);
                    if (slf != null) {
                        if (slf.getRelationid() !=null&&((oldChildId = slf.getRelationid()) != 0)) {
                            //老人儿童
                            SpMemberRelation sr = relationMapper.selectByPrimaryKey(oldChildId);
                            lr.setRelation(sr.getUserrelation());
                        } else {
                            Integer memberid = slf.getMemberid();
                            Map<String, Object> sr = memberMapper.selectByIdJoinFind(memberid,locksn);
                            if (sr != null) {
                                lr.setHeadurl(String.valueOf(sr.get("avatar")));
                                lr.setRelation(String.valueOf(sr.get("userrelation")));
                                Integer gender = (Integer) sr.get("gender");
                                lr.setGender(gender);
                                lr.setRelationType(String.valueOf(sr.get("usertype")));
                            }else {
                                SpMember member = memberMapper.selectByPrimaryKey(memberid);
                                if(member !=null){
                                    lr.setHeadurl(member.getAvatar());
                                    lr.setRelation("自己");
                                    lr.setGender(member.getGender());
                                }
                            }
                        }
                    }
                }
            }
            return AnyoujiaResult.ok(list);
        }
        return AnyoujiaResult.build(CommonUtil.paseResFromHardware(res), CommonUtil.getMessage(res));
    }

    private boolean isUseridSmaller(int userid, int delimeterForFingerAndPwdId) {
        return delimeterForFingerAndPwdId > userid;
    }

    @Override
    public AnyoujiaResult getLockInfo(String locksn) {
        long timestamp = DateUtil.generateTenTime();
        StringBuilder sb = new StringBuilder();
        String param = sb.append(locksn).append(timestamp).append(lockSalt).toString();
        String sign = Md5Utils.md5(param, "UTF-8");
        StringBuilder combine = new StringBuilder("method=get.lock.info&locksn=");
        String combineParam = combine.append(locksn).
                append("&temptime=").append(timestamp).
                append("&sign=").append(sign).toString();
        String res = SimulateGetAndPostUtil.sendPost(url, combineParam);
        if (CommonUtil.successResponse(res)) {
            LockInfo lockInfo = CommonUtil.getLockInfo(res);
            if (lockInfo == null) {
                return AnyoujiaResult.build(T_H_1, T_H_1_MSG);
            }
            LockCombineInfo lockCombineInfo = lockinfoMapper.selectLockInfoByLocksn(locksn);
            if (lockCombineInfo == null) {
                return AnyoujiaResult.ok(lockInfo);
            }
            return AnyoujiaResult.ok(updateInfoOfLock(lockCombineInfo, lockInfo));
        }
        return AnyoujiaResult.build(CommonUtil.paseResFromHardware(res), CommonUtil.getMessage(res));
    }

    /**
     * 封装返回数据
     *
     * @return LockInfo
     */
    private LockInfo updateInfoOfLock(LockCombineInfo lockCombineInfo, LockInfo baseInfo) {
        LockInfo fullInfo = new LockInfo();
        String color = lockCombineInfo.getColor();
        if (color != null) {
            String[] c = color.split(",");
            fullInfo.setColor(c[1]);
        }
        fullInfo.setModel(lockCombineInfo.getModel());
        fullInfo.setOrigin(lockCombineInfo.getOrigin());
        StringBuilder sb = new StringBuilder(lockCombineInfo.getCname());
        String address = sb.append(lockCombineInfo.getAddress()).toString();
        fullInfo.setAddress(address);
        fullInfo.setCode2(lockCombineInfo.getCode2());
        fullInfo.setProducttime(lockCombineInfo.getProducttime());
        fullInfo.setLockCreatetime(baseInfo.getLockCreatetime());
        fullInfo.setPower1(baseInfo.getPower1());
        fullInfo.setPower2(baseInfo.getPower2());
        fullInfo.setOnline(baseInfo.getOnline());
        fullInfo.setLockStatus(baseInfo.getLockStatus());
        return fullInfo;
    }

    /**
     * 获取临时密码列表
     * pwdtype [可选] 密码类型 99:全部 0: APP用户 1: 永久 2：一次 3：限时
     * usertype 用户类型 99:全部 0: APP 1:密码 2: 指纹 3:IC卡
     */
    @Override
    public List<Temppwd> getLockTempPwdList(String locksn, int pwdtype, int usertype, int memberid, int begintime, int page) {
        long timestamp = DateUtil.generateTenTime();
        StringBuilder sb = new StringBuilder();
        String param = sb.append(locksn).append(pwdtype).append(timestamp).append(usertype).append(lockSalt).toString();
        String sign = Md5Utils.md5(param, "UTF-8");
        StringBuilder combine = new StringBuilder("method=get.lock.userlist&locksn=");
        String combineParam = combine.append(locksn).
                append("&temptime=").append(timestamp).
                append("&sign=").append(sign).
                append("&usertype=").append(usertype).
                append("&pwdtype=").append(pwdtype).toString();
        String result = SimulateGetAndPostUtil.sendPost(url, combineParam);

        List<Temppwd> temppwds = CommonUtil.exactTemppwd(result);
        if (temppwds == null) {
            return null;
        }
        List<Integer> seqids = new ArrayList<>();
        Iterator<Temppwd> it = temppwds.iterator();
        while (it.hasNext()) {
            Temppwd pwd = it.next();
            int seqid = pwd.getSeqid();
            seqids.add(seqid);
        }

        SpLockPasswordExample example = new SpLockPasswordExample();
        SpLockPasswordExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(memberid).andAddtimeGreaterThanOrEqualTo(begintime).andPwdidIn(seqids);
        example.setOrderByClause("addtime DESC");
        PageHelper.startPage(page, pagesize);
        List<SpLockPassword> lockPasswords = passwordMapper.selectByExample(example);
        List<Temppwd> res = new LinkedList<>();
        if (!lockPasswords.isEmpty()) {
            for (SpLockPassword lp : lockPasswords) {
                for (Temppwd p : temppwds) {
                    if (lp.getPwdid() == p.getSeqid()) {
                        p.setMotive(lp.getMotive());
                        res.add(p);
                    }
                }
            }
        }
        return res;
    }

    /**
     * 获取锁激活状态和地址
     * 0表示未激活状态,1表示已经激活
     */
    @Override
    public AnyoujiaResult getLockActiveAndAddress(String code2, String prokey) {
        final Map<String, String> params = new HashMap<>(2);
        params.put("prokey", prokey);
        params.put("code2", code2);
        Map<String,Object> data = lockinfoMapper.selectLockActiveByLocksnOrProkey(params);
        if (data == null || data.size() == 0) {
            return AnyoujiaResult.build(T_H_1, "暂未找到设备，请重试");
        }
        String active = StateEnum.NONACTIVE.getCode();
        Object o = data.get("active");
        String s = Boolean.toString((Boolean) o);
        if ("true".equalsIgnoreCase(s)) {
            active = StateEnum.ACTIVED.getCode();
        }
        Map<String, String> res = new HashMap<>();
        res.put("active", active);
        StringBuilder sb = new StringBuilder();
        String address = sb.append(data.get("cname")).append(data.get("address")).toString();
        res.put("address", address);
        res.put("prokey", String.valueOf(data.get("pro_key")));
        res.put("locksn", String.valueOf(data.get("sn")));
        return AnyoujiaResult.ok(res);
    }

    /**
     * 调用PHP接口比对锁地址和小区地址是否匹配
     *
     */
    @Override
    public AnyoujiaResult callPhpToActiveLock(String locksn, int uid) throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("sn", locksn);
        map.put("uid", String.valueOf(uid));
        String json = JsonUtils.objectToJson(SortJsonAesc.sortMap(map));
        InitParam p = new InitParam();
        p.setMod("Com");
        p.setFun("register");
        String sign = genarateSign("Com", "register", json);
        p.setSign(sign);
        p.setData(map);
        String s = doPhpRequest(p, phpProjectUrl);
        Map<String, Object> res = parseResponse(s);
        Integer status = (Integer) res.get("status");
        if (status != T_H) {
            return AnyoujiaResult.build(status, String.valueOf(res.get("data")));
        }
        return null;
    }

    @Override
    public AnyoujiaResult getProuctNum(String locksn) throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("sn", locksn);
        String json = JsonUtils.objectToJson(SortJsonAesc.sortMap(map));
        InitParam p = new InitParam();
        p.setMod("Com");
        p.setFun("code_gongcheng");
        String sign = genarateSign("Com", "code_gongcheng", json);
        p.setSign(sign);
        p.setData(map);
        String resString = doPhpRequest(p, phpProjectUrl);
        Map<String, Object> res = parseResponse(resString);
        Integer status = (Integer) res.get("status");
        if (status == T_H) {
            return AnyoujiaResult.ok(String.valueOf(res.get("msg")));
        }
        return AnyoujiaResult.build(status, String.valueOf(res.get("msg")));
    }

    @Override
    public AnyoujiaResult getTempPwdFromPhp(String locksn, String timestamp) throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("sn", locksn);
        if("-1" != timestamp){
            map.put("timstamp",String.valueOf(timestamp));
        }
        String json = JsonUtils.objectToJson(SortJsonAesc.sortMap(map));
        InitParam p = new InitParam();
        p.setMod("Com");
        p.setFun("code_linshi");
        String sign = genarateSign("Com", "code_linshi", json);
        p.setSign(sign);
        p.setData(map);
        String resString = doPhpRequest(p, phpProjectUrl);
        Map<String, Object> res = parseResponse(resString);
        Integer status = (Integer) res.get("status");
        if (status == T_H) {
            String msg = String.valueOf(res.get("msg"));
            if ("null".equalsIgnoreCase(msg)) {
                return AnyoujiaResult.build(FOUR_H, "获取临时密码失败");
            }
            return AnyoujiaResult.ok(msg);
        }
        return AnyoujiaResult.build(status, String.valueOf(res.get("msg")));
    }

    @Override
    public AnyoujiaResult updateSetedPwdState(int memberid, String locksn, boolean isAdmin, String phone, int relationid) {

        if (isAdmin) {
            //只有永久密码被设置后这个标示才能设置为true
            //不为0，则表示管理员给老人和儿童设置密码
            if (relationid != 0) {
                SpMemberRelation oldChild = new SpMemberRelation();
                oldChild.setSetedlockpwd(true);
                oldChild.setId(relationid);
                relationMapper.updateByPrimaryKeySelective(oldChild);

            } else {
                SpLockAdmin lock = new SpLockAdmin();
                lock.setSetedlockpwd(true);
                lock.setUpdatetime(DateUtil.generateTenTime());
                SpLockAdminExample example = new SpLockAdminExample();
                SpLockAdminExample.Criteria criteria = example.createCriteria();
                criteria.andAdminidEqualTo(memberid).andLocksnEqualTo(locksn);
                lockMapper.updateByExampleSelective(lock, example);
            }

        } else {
            //只有永久密码被设置后这个标示才能设置为true
            SpMemberRelation user = new SpMemberRelation();
            user.setSetedlockpwd(true);
            relationMapper.updateByExampleSelective(user, getExample(phone, locksn));
        }
        return AnyoujiaResult.ok();
    }

    @Override
    public AnyoujiaResult getFingerIdForFrontEnd(int memberid,int relationid,String locksn,String fingerdesc,int endtime) {

        //添加指纹记录
        // ptype 类型 1: 永久 2：一次 3：限时(暂时指纹只有永久类型)
        int ptype = 1;
        SpLockFinger lockFinger = new SpLockFinger();
        lockFinger.setFingerdesc(fingerdesc);
        lockFinger.setFingerid("");
        lockFinger.setLocksn(locksn);
        lockFinger.setExpired(true);
        if (relationid != 0) {
            lockFinger.setRelationid(relationid);
        }
        //临时指纹的过期时间，永久指纹的删除时间,这里不做判断，因为在删除永久指纹的时候会更新删除时间
        lockFinger.setDeltime(endtime);
        lockFinger.setMemberid(memberid);
        lockFinger.setPtype(ptype);
        lockFinger.setAddtime(DateUtil.generateTenTime());
        fingerMapper.insertSelective(lockFinger);
        int seqid = lockFinger.getId();
        return AnyoujiaResult.ok(seqid);
    }


    /**
     * 解绑锁
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public AnyoujiaResult unboundLock(String locksn, int userid) {

        StringBuilder sb = new StringBuilder();
        String ss = sb.append(locksn).append(userid).append(lockSalt).toString();
        String sign = Md5Utils.md5(ss,"UTF-8");
        String param = new StringBuilder("method=delete.lock.info&userid=").append(userid).
                                         append("&sign=").append(sign).
                                         append("&locksn=").append(locksn).toString();
        String result = SimulateGetAndPostUtil.sendPost(url,param);
        String code,message;
        int status;
       try{
           Map<String, Object> data = JsonUtils.jsonToMap(result);
           code = String.valueOf(data.get("code"));
           message = String.valueOf(data.get("message"));
           status =  Integer.valueOf(code);
       }catch (Exception e){
           return AnyoujiaResult.ok(result);
       }
       if(status == T_H){
           lockMapper.deleteByPrimaryKey(userid);
           SpLock lock = new SpLock();
           lock.setSn(locksn);
           lock.setActive(false);
           lock.setActivetime(0);
           lockinfoMapper.updateByPrimaryKeySelective(lock);
           updateFingers(locksn);
           updatePwds(locksn);
           removeMembers(locksn);
       }
        return AnyoujiaResult.build(status,message);
    }

    private void removeMembers(String locksn) {
        SpMemberRelationExample example = new SpMemberRelationExample();
        SpMemberRelationExample.Criteria criteria = example.createCriteria();
        criteria.andLocksnEqualTo(locksn);
        relationMapper.deleteByExample(example);
    }

    private void  updateFingers(String locksn){
        SpLockFingerExample example = new SpLockFingerExample();
        SpLockFingerExample.Criteria criteria = example.createCriteria();
        criteria.andExpiredEqualTo(false).andLocksnEqualTo(locksn);
        SpLockFinger finger = new SpLockFinger();
        finger.setLocksn(locksn);
        finger.setExpired(true);
        fingerMapper.updateByExampleSelective(finger,example);
    }

    private void updatePwds(String locksn){
        SpLockPasswordExample example = new SpLockPasswordExample();
        SpLockPasswordExample.Criteria criteria = example.createCriteria();
        criteria.andExpiredEqualTo(false).andLocksnEqualTo(locksn);
        SpLockPassword password = new SpLockPassword();
        password.setLocksn(locksn);
        password.setExpired(true);
        passwordMapper.updateByExampleSelective(password,example);
    }

    /**
     * 为前端获取密码id
     */
    @Override
    public AnyoujiaResult getPermPwdIdForFront(int memberid, int relationid, String locksn, int endtime,int ptype) {

        //保存记录并生成seqid
        SpLockPassword lockPassword = new SpLockPassword();
        lockPassword.setLocksn(locksn);
        lockPassword.setMemberid(memberid);
        lockPassword.setPtype(ptype);
        lockPassword.setExpired(false);
        lockPassword.setMotive("");
        if (relationid != 0) {
            lockPassword.setRelationid(relationid);
        }
        //临时密码的过期时间，永久密码的删除时间,这里不做判断，因为在删除永久密码的时候会更新删除时间
        lockPassword.setDeltime(endtime);
        lockPassword.setAddtime(DateUtil.generateTenTime());
        passwordMapper.insertSelective(lockPassword);
        int seqid = lockPassword.getPwdid();
        return AnyoujiaResult.ok(seqid);

    }

    private List<SpLockAdmin> getAdminLocks(int adminid) {
        SpLockAdminExample example = new SpLockAdminExample();
        SpLockAdminExample.Criteria criteria = example.createCriteria();
        criteria.andAdminidEqualTo(adminid);
        List<SpLockAdmin> list = lockMapper.selectByExample(example);
        return list;
    }

    private SpMemberRelationExample combineExampleByPhone(String phone) {
        SpMemberRelationExample example = new SpMemberRelationExample();
        SpMemberRelationExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        return example;
    }

}
