package cn.anyoufang.service.impl;

import cn.anyoufang.entity.*;
import cn.anyoufang.entity.selfdefined.AnyoujiaResult;
import cn.anyoufang.entity.selfdefined.Lock;
import cn.anyoufang.enumresource.HttpCodeEnum;
import cn.anyoufang.enumresource.ParamEnum;
import cn.anyoufang.exception.LockException;
import cn.anyoufang.mapper.SpAdminLockMapper;
import cn.anyoufang.mapper.SpLockFingerMapper;
import cn.anyoufang.mapper.SpLockPasswordMapper;
import cn.anyoufang.mapper.SpMemberRelationMapper;
import cn.anyoufang.service.LockService;
import cn.anyoufang.utils.DateUtil;
import cn.anyoufang.utils.JsonUtils;
import cn.anyoufang.utils.Md5Utils;
import cn.anyoufang.utils.SimulateGetAndPostUtil;
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
    @Value("${lock.salt}")
    private String lockSalt;
    @Value("${pageSize}")
    private String pagesize;
    @Value("${lock.url}")
    private String url;

    @Value("${deadline}")
    private int deadline;
    @Resource
    private SpMemberRelationMapper relationMapper;

    @Autowired
    private SpAdminLockMapper lockMapper;

    @Autowired
    private SpLockPasswordMapper passwordMapper;

    @Autowired
    private SpLockFingerMapper fingerMapper;



    /**
     *设置用户锁密码
     * @param ptype 类型 1: 永久 2：一次 3：限时
     * @param memberid 会员id
     * @param locksn
     * @param endtime
     * @param pwds
     * @param nickname
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
                                         boolean isAdmin) {

        //保存记录并生成seqid
        SpLockPassword lockPassword = new SpLockPassword();
        lockPassword.setLocksn(locksn);
        lockPassword.setMemberid(memberid);
        lockPassword.setPtype(ptype);
        lockPassword.setAddtime(DateUtil.generateTenTime());
        passwordMapper.insertSelective(lockPassword);
        int seqid = lockPassword.getPwdid();
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String param = sb.append(endtime)
                .append(locksn)
                .append(nickname).append(ptype).append(pwds).append(timestamp).append(lockSalt).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam = "method=set.lock.pwd&ptype=1&temptime="+timestamp+"&sign="+sign+"&seqid="+seqid +
                "&locksn="+locksn+"&endtime="+endtime + "&pwds="+pwds + "&nickname="+nickname;
        String res = SimulateGetAndPostUtil.sendPost(url,combineParam);
         try {
            Map<String,Object> data =  JsonUtils.jsonToMap(res);
            Map<String,String> parsedMap = new HashMap<>();
            String code =  String.valueOf(data.get("code"));
            parsedMap.put("code",code);
            parsedMap.put("msg",String.valueOf(data.get("message")));
            if(Integer.valueOf(code) ==T_H) {
                int updated;
                if(isAdmin) {
                    SpAdminLock lock = new SpAdminLock();
                    //只有永久密码被设置后这个标示才能设置为true
                    if(ParamEnum.ONE.getCode().equals(ptype)) {
                        lock.setSetedlockpwd(true);
                    }
                    SpAdminLockExample example = new SpAdminLockExample();
                    SpAdminLockExample.Criteria criteria = example.createCriteria();
                    criteria.andAdminidEqualTo(memberid);
                    updated = lockMapper.updateByExampleSelective(lock,example);

                }else {
                    SpMemberRelation user = new SpMemberRelation();
                    //只有永久密码被设置后这个标示才能设置为true
                    if(ParamEnum.ONE.getCode().equals(ptype)) {
                        user.setSetedlockpwd(true);
                    }
                    updated = relationMapper.updateByExampleSelective(user,getExample(phone,locksn));
                }
                if(updated == 1) {
                    return parsedMap;
                }
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
                                                    String phone,String fingerdesc,String fingerid,boolean isAdmin) {

        //添加指纹记录
        // ptype 类型 1: 永久 2：一次 3：限时(暂时指纹只有永久类型)
        int ptype = 1;
        SpLockFinger lockFinger = new SpLockFinger();
        lockFinger.setFingerdesc(fingerdesc);
        lockFinger.setFingerid(fingerid);
        lockFinger.setLocksn(locksn);
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
        String combineParam = "method=set.lock.user&ptype=1&temptime="+timestamp+"&sign="+sign+"&seqid="+seqid +
                "&locksn="+locksn+"&endtime="+endtime + "&ptype="+ptype + "&nickname="+nickname+"&usertype="+usertype;
        String res = SimulateGetAndPostUtil.sendPost(url,combineParam);
        Map<String,Object> data =  JsonUtils.jsonToMap(res);
        String code =  String.valueOf(data.get("code"));
       int state =  Integer.valueOf(code);
       int updated;
        if(state == T_H) {
            if(isAdmin) {
                SpAdminLock lock = new SpAdminLock();
                lock.setSetedlockfinger(true);
                SpAdminLockExample example = new SpAdminLockExample();
                SpAdminLockExample.Criteria criteria = example.createCriteria();
                criteria.andAdminidEqualTo(memberid);
                updated = lockMapper.updateByExampleSelective(lock,example);

            }else {
                SpMemberRelation user = new SpMemberRelation();
                //只有永久密码被设置后这个标示才能设置为true
                if(ParamEnum.ONE.getCode().equals(ptype)) {
                    user.setSetedlockfinger(true);
                }
                updated = relationMapper.updateByExampleSelective(user,getExample(phone,locksn));
            }

            if(updated == 1) {
                return AnyoujiaResult.build(T_H,String.valueOf(data.get("message")));
            }
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
        SpAdminLock lock = new SpAdminLock();
        lock.setAdminid(userid);
        lock.setLocksn(locksn);
        lock.setCreatetime((int)time);

        try {
            lockMapper.insertSelective(lock);
        }catch (Exception e) {
            if(log.isInfoEnabled()) {
                log.info(e.getMessage());
            }
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
            return AnyoujiaResult.build(FIVE_H,"系统异常");
        }
        String state = String.valueOf(data.get("code"));
        Integer status = Integer.valueOf(state);
        return AnyoujiaResult.build(status,String.valueOf(data.get("message")));
    }

    private boolean checkLockRegisted(String locksn) {
        SpAdminLockExample example = new SpAdminLockExample();
        SpAdminLockExample.Criteria criteria = example.createCriteria();
        criteria.andLocksnEqualTo(locksn);
        List<SpAdminLock> list = lockMapper.selectByExample(example);
        if(list.size() >0) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public AnyoujiaResult getAllLockList(SpMember user) {

        int userid = user.getUid();
        String phone = user.getPhone();
        List<String> adminLockResList = new ArrayList<>();
        List<SpAdminLock> list1 =  getAdminLocks(userid);
        //作为管理员的锁
            Iterator<SpAdminLock> itr =  list1.iterator();
            while(itr.hasNext()) {
                SpAdminLock adminLock =  itr.next();
                int id = adminLock.getId();
                StringBuilder sb = new StringBuilder();
                long timestamp = System.currentTimeMillis()/1000;
                String param = sb.append(timestamp).append(id).append(lockSalt).toString();
                String sign = Md5Utils.md5(param,"UTF-8");
                String combineParam = "method=get.lock.list&userid="+id+"&temptime="+timestamp+"&sign="+sign;
                String res =  SimulateGetAndPostUtil.sendPost(url,combineParam);
                adminLockResList.add(res);
            }
        //根据phone查询关系表
        List<SpMemberRelation> list =  relationMapper.selectByExample(combineExampleByPhone(phone));
        List<SpMemberRelation> valid = new ArrayList<>();
        List<SpMemberRelation> invalid = new ArrayList<>();
        Iterator<SpMemberRelation> it = list.iterator();
        while(it.hasNext()) {
            SpMemberRelation mr = it.next();
            if(DateUtil.isNotExpired(mr.getEndtime(),deadline)) {
                valid.add(mr);
            }else {
                invalid.add(mr);
            }
        }
        if(invalid.size() >0) {
            relationMapper.deleteByExample(combineExampleByPhone(phone));
        }
        List<Integer> adminIds = new ArrayList<>();
        Map<String,Boolean> pwdauths = new HashMap<>();
        Map<String,Boolean> fingerauths = new HashMap<>();
        if(valid.size() >0){
            for(SpMemberRelation u:valid) {
                Integer parentid = u.getParentid();
                String locksn = u.getLocksn();
                pwdauths.put(locksn,u.getLockpwdauth());
                fingerauths.put(locksn,u.getFingerpwdauth());
                adminIds.add(parentid);
            }
        }
        List<String> memberLockResList = new ArrayList<>();
        Iterator<Integer> iterator =  adminIds.iterator();
        while(iterator.hasNext()) {
            Integer id =  iterator.next();
            StringBuilder sb = new StringBuilder();
            long timestamp = System.currentTimeMillis()/1000;
            String param = sb.append(timestamp).append(id).append(lockSalt).toString();
            String sign = Md5Utils.md5(param,"UTF-8");
            String combineParam = "method=get.lock.list&userid="+id+"&temptime="+timestamp+"&sign="+sign;
            String res =  SimulateGetAndPostUtil.sendPost(url,combineParam);
            memberLockResList.add(res);
        }
        //将自己作为管理员的和作为成员的锁分开
        List<Lock> admin =  CommonUtil.getAdminLockList(adminLockResList);
        List<Lock> member = CommonUtil.getMemberLockList(memberLockResList,pwdauths,fingerauths);
        List<Lock> res = new LinkedList<>(admin);
        res.addAll(member);
        return AnyoujiaResult.ok(res);
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

    private List<SpAdminLock> getAdminLocks(int adminid) {
        SpAdminLockExample example = new SpAdminLockExample();
        SpAdminLockExample.Criteria criteria = example.createCriteria();
        criteria.andAdminidEqualTo(adminid);
        List<SpAdminLock> list = lockMapper.selectByExample(example);
        return list;
    }

    private SpMemberRelationExample combineExampleByPhone(String phone) {
        SpMemberRelationExample example = new SpMemberRelationExample();
        SpMemberRelationExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        return example;
    }
}
