package cn.anyoufang.service.impl;

import cn.anyoufang.entity.*;
import cn.anyoufang.entity.selfdefined.AnyoujiaResult;
import cn.anyoufang.entity.selfdefined.Lock;
import cn.anyoufang.entity.selfdefined.LockCombineInfo;
import cn.anyoufang.entity.selfdefined.Temppwd;
import cn.anyoufang.enumresource.HttpCodeEnum;
import cn.anyoufang.enumresource.PwdTypeEnum;
import cn.anyoufang.exception.LockException;
import cn.anyoufang.mapper.*;
import cn.anyoufang.service.LockService;
import cn.anyoufang.utils.DateUtil;
import cn.anyoufang.utils.JsonUtils;
import cn.anyoufang.utils.Md5Utils;
import cn.anyoufang.utils.SimulateGetAndPostUtil;
import cn.anyoufang.utils.pagehelper.PageHelper;
import cn.anyoufang.utils.selfdefine.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author daiping
 */
@Service
public class LockServiceImpl implements LockService{

    private static final Logger log = LoggerFactory.getLogger(LockServiceImpl.class);
    private static final int T_H = HttpCodeEnum.TWO_HUNDRED.getCode();
    private static final int FOUR_H_1 = HttpCodeEnum.FOUR_HUNDRED1.getCode();
    private static final int FIVE_H = HttpCodeEnum.FIVE_HUNDRED.getCode();
    private static final int T_H_1 = HttpCodeEnum.TWO_HUNDRED1.getCode();
    private static final String T_H_1_MSG = HttpCodeEnum.TWO_HUNDRED1.getValue();
    @Value("${lock.salt}")
    private String lockSalt;
    @Value("${pageSize}")
    private int pagesize;
    @Value("${lock.url}")
    private String url;

    @Value("${deadline}")
    private int deadline;
    @Resource
    private SpMemberRelationMapper relationMapper;

    @Autowired
    private SpLockAdminMapper lockMapper;

    @Autowired
    private SpLockPasswordMapper passwordMapper;

    @Autowired
    private SpLockFingerMapper fingerMapper;

    @Autowired
    private SpLockMapper lockinfoMapper;



    /**
     *设置用户锁密码
     * @param ptype 类型 1: 永久 2：一次 3：限时
     * @param memberid 会员id
     * @param locksn
     * @param endtime
     * @param pwds
     * @param nickname
     * @param motive 临时密码 动机
     * @return
     */
    @Override
    public Map<String,String> setLockPwd(int ptype,
                                         int memberid,
                                         String locksn,
                                         int endtime,
                                         String pwds,
                                         String nickname,
                                         String phone,
                                         boolean isAdmin,String motive,int relationid) {

        //保存记录并生成seqid
        SpLockPassword lockPassword = new SpLockPassword();
        lockPassword.setLocksn(locksn);
        lockPassword.setMemberid(memberid);
        lockPassword.setPtype(ptype);
        lockPassword.setMotive(motive);
        if(relationid !=0){
            lockPassword.setRelationid(relationid);
        }
        //临时密码的过期时间，永久密码的删除时间,这里不做判断，因为在删除永久密码的时候会更新删除时间
        lockPassword.setDeltime(endtime);
        lockPassword.setDeltime(endtime);
        lockPassword.setAddtime(DateUtil.generateTenTime());
        passwordMapper.insertSelective(lockPassword);
        int seqid = lockPassword.getPwdid();
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        //永久密码不传时间
        if(endtime != 0) {
            sb = sb.append(endtime);
        }
        String param = sb.append(locksn).append(nickname).append(ptype).append(pwds).append(timestamp).append(lockSalt).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam = "method=set.lock.pwd&ptype="+ptype+"&temptime="+timestamp+"&sign="+sign+"&seqid="+seqid +
                "&locksn="+locksn + "&pwds="+pwds + "&nickname="+nickname;
        //永久密码不传时间参数
        if(endtime !=0){
            combineParam = combineParam+"&endtime="+endtime;
        }
        String res = SimulateGetAndPostUtil.sendPost(url,combineParam);
         try {
            Map<String,Object> data =  JsonUtils.jsonToMap(res);
            Map<String,String> parsedMap = new HashMap<>();
            String code =  String.valueOf(data.get("code"));
            parsedMap.put("code",code);
            parsedMap.put("msg",String.valueOf(data.get("message")));
            if(Integer.valueOf(code) ==T_H) {
                if(isAdmin) {
                    //只有永久密码被设置后这个标示才能设置为true
                    if(PwdTypeEnum.ONE.getCode()==ptype) {
                        //不为0，则表示管理员给老人和儿童设置密码
                        if(relationid !=0){
                            SpMemberRelation oldChild = new SpMemberRelation();
                            oldChild.setSetedlockpwd(true);
                            oldChild.setId(relationid);
                            relationMapper.updateByPrimaryKeySelective(oldChild);

                        }else {
                            SpLockAdmin lock = new SpLockAdmin();
                            lock.setSetedlockpwd(true);
                            lock.setUpdatetime(DateUtil.generateTenTime());
                            SpLockAdminExample example = new SpLockAdminExample();
                            SpLockAdminExample.Criteria criteria = example.createCriteria();
                            criteria.andAdminidEqualTo(memberid).andLocksnEqualTo(locksn);
                            lockMapper.updateByExampleSelective(lock,example);
                        }

                    }

                }else {
                    //只有永久密码被设置后这个标示才能设置为true
                    if(PwdTypeEnum.ONE.getCode()==ptype) {
                        SpMemberRelation user = new SpMemberRelation();
                        user.setSetedlockpwd(true);
                        relationMapper.updateByExampleSelective(user,getExample(phone,locksn));
                    }
                }
                return parsedMap;
            }
             //不成功，删除无用记录
             passwordMapper.deleteByPrimaryKey(seqid);
             return null;
         }catch (Exception e) {
             if(log.isInfoEnabled()) {
                 log.info("设置锁密码失败:" + e.getMessage());
             }
             return null;
         }
    }

    /**
     * 设置用户指纹密码
     * @param memberid 用户id
     * @param locksn
     * @param endtime
     * @param usertype  2:指纹 3：IC卡
     * @param nickname
     * @param fingerdesc 指纹描述
     * @return
     */
    @Override
    public AnyoujiaResult setLockUserFingerPassword(int memberid,
                                                    String locksn,
                                                    int endtime,
                                                    int usertype,
                                                    String nickname,
                                                    String phone,String fingerdesc,String fingerid,boolean isAdmin,int relationid) {

        //添加指纹记录
        // ptype 类型 1: 永久 2：一次 3：限时(暂时指纹只有永久类型)
        int ptype = 1;
        SpLockFinger lockFinger = new SpLockFinger();
        lockFinger.setFingerdesc(fingerdesc);
        lockFinger.setFingerid(fingerid);
        lockFinger.setLocksn(locksn);
        if(relationid != 0) {
            lockFinger.setRelationid(relationid);
        }
        //临时指纹的过期时间，永久指纹的删除时间,这里不做判断，因为在删除永久指纹的时候会更新删除时间
        lockFinger.setDeltime(endtime);
        lockFinger.setMemberid(memberid);
        lockFinger.setPtype(ptype);
        lockFinger.setAddtime(DateUtil.generateTenTime());
        fingerMapper.insertSelective(lockFinger);
        int seqid = lockFinger.getId();
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String param = sb.append(endtime)
                .append(locksn)
                .append(nickname)
                .append(ptype)
                .append(seqid)
                .append(timestamp)
                .append(usertype)
                .append(lockSalt).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam = "method=set.lock.user&ptype="+ptype+"&temptime="+timestamp+"&sign="+sign+"&seqid="+seqid +
                "&locksn="+locksn+"&endtime="+endtime + "&ptype="+ptype + "&nickname="+nickname+"&usertype="+usertype;
        String res = SimulateGetAndPostUtil.sendPost(url,combineParam);
        Map<String,Object> data =  JsonUtils.jsonToMap(res);
        String code =  String.valueOf(data.get("code"));
       int state =  Integer.valueOf(code);

        if(state == T_H) {
            return AnyoujiaResult.build(T_H,String.valueOf(data.get("message")));
        }
        //不成功则删除无用记录
        fingerMapper.deleteByPrimaryKey(seqid);
        return AnyoujiaResult.build(state,String.valueOf(data.get("message")));
    }

    /**
     * 提取重复代码
     * 根据手机号和锁sn获取
     * @param phone
     * @param locksn
     * @return
     */
    private SpMemberRelationExample getExample(String phone,String locksn) {
        SpMemberRelationExample example = new SpMemberRelationExample();
        SpMemberRelationExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone).andLocksnEqualTo(locksn);
        return example;
    }

    /**
     * 注册锁管理员使用sp_admin_lock 这张表的id;
     * @param locksn
     * @param userid
     * @return
     */
    @Override
    public AnyoujiaResult registerLockInfo(String locksn, int userid) {

        if(checkLockRegisted(locksn)) {
            return AnyoujiaResult.build(FOUR_H_1,"门锁已经注册");
        }
        long timestamp = System.currentTimeMillis()/1000;
        long time = timestamp;
        SpLockAdmin lock = new SpLockAdmin();
        lock.setAdminid(userid);
        lock.setLocksn(locksn);
        lock.setCreatetime((int)time);

        try {
            lockMapper.insertSelective(lock);
        }catch (Exception e) {
            if(log.isInfoEnabled()) {
                log.info(e.getMessage());
            }
            return AnyoujiaResult.build(FIVE_H,"系统异常添加锁失败");
        }
        StringBuilder sb = new StringBuilder();
        int id = lock.getId();
        String param = sb.append(locksn).append(timestamp).append(id).append(lockSalt).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam = "method=register.lock.info&userid="+id+"&locksn="+locksn+"&temptime="+timestamp+"&sign="+sign;
        String res = SimulateGetAndPostUtil.sendPost(url,combineParam);
        Map<String,Object> data;
        try {
            data  =  JsonUtils.jsonToMap(res);
        }catch (LockException e) {
            if(log.isInfoEnabled()) {
                log.info(e.getMessage());
            }
            return AnyoujiaResult.build(FIVE_H,"系统错误");
        }
        String state = String.valueOf(data.get("code"));
        Integer status = Integer.valueOf(state);
        return AnyoujiaResult.build(status,String.valueOf(data.get("message")));
    }

    /**
     * 检查锁已经被注册
     * @param locksn
     * @return
     */
    private boolean checkLockRegisted(String locksn) {
        SpLockAdminExample example = new SpLockAdminExample();
        SpLockAdminExample.Criteria criteria = example.createCriteria();
        criteria.andLocksnEqualTo(locksn);
        List<SpLockAdmin> list = lockMapper.selectByExample(example);
        if(list.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     *获取锁列表
     * @param user
     * @return
     */
    @Override
    public AnyoujiaResult getAllLockList(SpMember user) {

        String phone = user.getPhone();
        List<String> allLocksns = new ArrayList<>();
        List<SpLockAdmin> admins = getAdminLocks(user.getUid());
        List<SpMemberRelation> mR = relationMapper.selectByExample(combineExampleByPhone(phone));
        //如果用户既没有被添加过，也不是锁管理员，则直接返回
        if(admins.isEmpty() && mR.isEmpty()) {
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
        Map<String, Boolean> pwdauths = new HashMap<>();
        Map<String, Boolean> fingerauths = new HashMap<>();

        if (valid.isEmpty()) {
            return AnyoujiaResult.build(T_H_1, T_H_1_MSG);
        }

        Iterator<SpMemberRelation> iteratorVal = valid.iterator();
        while(iteratorVal.hasNext()) {
            SpMemberRelation u = iteratorVal.next();
            Integer parentid = u.getParentid();
            String locksn = u.getLocksn();
            pwdauths.put(locksn, u.getLockpwdauth());
            fingerauths.put(locksn, u.getFingerpwdauth());
            adminIds.add(parentid);
            locksns.add(locksn);
        }
        //获取seqid
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
        //将成员锁和管理员锁的sn放在一起去拿锁基本信息
        allLocksns.addAll(locksns);
        //获取锁相关基本信息
        List<LockCombineInfo> combineInfos = lockinfoMapper.selectLockInfoByCombinetable(allLocksns);

        //将自己作为管理员的和作为成员的锁分开
        List<Lock> admin = CommonUtil.getAdminLockList(adminLockResList);
        List<Lock> member = CommonUtil.getMemberLockList(memberLockResList, pwdauths, fingerauths);
        List<Lock> res = new LinkedList<>(admin);
        res.addAll(member);
        if (!combineInfos.isEmpty()){
            for (Lock lock : res) {
                for (LockCombineInfo l : combineInfos) {
                    if (lock.getLocksn().equals(l.getSn())) {
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
                        lock.setVcode(l.getCode2());
                        lock.setProducttime(l.getProducttime());
                    }
                }
            }
    }
        return AnyoujiaResult.ok(res);
    }

    private String getLockResFromHardare(SpLockAdmin adminLock) {
        int id = adminLock.getId();
        StringBuilder sb = new StringBuilder();
        long timestamp = System.currentTimeMillis()/1000;
        String param = sb.append(timestamp).append(id).append(lockSalt).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam = "method=get.lock.list&userid="+id+"&temptime="+timestamp+"&sign="+sign;
        return  SimulateGetAndPostUtil.sendPost(url,combineParam);
    }

    @Override
    public AnyoujiaResult getLockRecords(String locksn, int isalarm, int page) {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String param = sb.append(isalarm).append(locksn).append(page).append(pagesize).append(timestamp).append(lockSalt).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam = "method=get.lock.openlist&page="+page+"&pagesize="+pagesize+"&locksn="+locksn+"&temptime="+timestamp+"&sign="+sign + "&isalarm="+isalarm;
        String res =  SimulateGetAndPostUtil.sendPost(url,combineParam);
       if(CommonUtil.successResponse(res)) {
          return AnyoujiaResult.ok(CommonUtil.getRecords(res));
       }
        return AnyoujiaResult.build(CommonUtil.paseResFromHardware(res),CommonUtil.getMessage(res));
    }

    @Override
    public AnyoujiaResult getLockInfo(String locksn) {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String param = sb.append(locksn).append(timestamp).append(lockSalt).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam = "method=get.lock.info&locksn="+locksn+"&temptime="+timestamp+"&sign="+sign;
        String res = SimulateGetAndPostUtil.sendPost(url,combineParam);
        if(CommonUtil.successResponse(res)) {
            return AnyoujiaResult.ok(CommonUtil.getLockInfo(res));
        }
        return AnyoujiaResult.build(CommonUtil.paseResFromHardware(res),CommonUtil.getMessage(res));
    }

    /**
     * 获取临时密码列表
     * pwdtype [可选] 密码类型 99:全部 0: APP用户 1: 永久 2：一次 3：限时
     * usertype 用户类型 99:全部 0: APP 1:密码 2: 指纹 3:IC卡
     * @param locksn
     * @param pwdtype
     * @param usertype
     * @param memberid
     * @param begintime
     * @param page
     * @return
     */
    @Override
    public List<Temppwd> getLockTempPwdList(String locksn, int pwdtype, int usertype,int memberid,int begintime,int page) {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String param=sb.append(locksn).append(pwdtype).append(timestamp).append(usertype).append(lockSalt).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam = "method=get.lock.userlist&locksn="+locksn+"&temptime="+timestamp+"&sign="+sign + "&usertype="+usertype+"&pwdtype="+pwdtype;
        String result =  SimulateGetAndPostUtil.sendPost(url,combineParam);

        List<Temppwd> temppwds =  CommonUtil.exactTemppwd(result);
        if(temppwds == null) {
            return null;
        }
        List<Integer> seqids = new ArrayList<>();
        Iterator<Temppwd> it = temppwds.iterator();
        while(it.hasNext()) {
            Temppwd pwd = it.next();
            int seqid = pwd.getSeqid();
            seqids.add(seqid);
        }

        SpLockPasswordExample example = new SpLockPasswordExample();
        SpLockPasswordExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(memberid).andAddtimeGreaterThanOrEqualTo(begintime).andPwdidIn(seqids);
        example.setOrderByClause("addtime DESC");
        PageHelper.startPage(page,pagesize);
        List<SpLockPassword> lockPasswords =  passwordMapper.selectByExample(example);
        List<Temppwd> res = new LinkedList<>();
        if(!lockPasswords.isEmpty()){
            for(SpLockPassword lp:lockPasswords) {
                for(Temppwd p:temppwds) {
                    if(lp.getPwdid() == p.getSeqid()){
                        p.setMotive(lp.getMotive());
                        res.add(p);
                    }
                }
            }
        }
        return  res;
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
