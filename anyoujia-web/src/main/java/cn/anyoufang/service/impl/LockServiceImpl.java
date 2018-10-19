package cn.anyoufang.service.impl;

import cn.anyoufang.entity.SpAdminLock;
import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.SpMemberRelation;
import cn.anyoufang.entity.SpMemberRelationExample;
import cn.anyoufang.entity.selfdefined.AnyoujiaResult;
import cn.anyoufang.exception.LockException;
import cn.anyoufang.mapper.SpAdminLockMapper;
import cn.anyoufang.mapper.SpMemberRelationMapper;
import cn.anyoufang.service.LockService;
import cn.anyoufang.utils.DateUtil;
import cn.anyoufang.utils.JsonUtils;
import cn.anyoufang.utils.Md5Utils;
import cn.anyoufang.utils.SimulateGetAndPostUtil;
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
    @Value("${lock.salt}")
    private String lockSalt;
    @Value("${pageSize}")
    private String pagesize;
    @Value("${lock.url}")
    private String url;

    @Resource
    private SpMemberRelationMapper relationMapper;

    @Autowired
    private SpAdminLockMapper lockMapper;



    /**
     *设置用户锁密码
     * @param ptype 类型 1: 永久 2：一次 3：限时
     * @param seqid 会员id
     * @param locksn
     * @param endtime
     * @param pwds
     * @param nickname
     * @return
     */
    @Override
    public Map<String,String> setLockPwd(int ptype, int seqid, String locksn, int endtime, String pwds,String nickname,String phone) {
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
            if(Integer.valueOf(code) ==200) {
                SpMemberRelation user = new SpMemberRelation();
                user.setSetedlockpwd(true);
                SpMemberRelationExample example = new SpMemberRelationExample();
                SpMemberRelationExample.Criteria criteria = example.createCriteria();
                criteria.andPhoneEqualTo(phone).andLocksnEqualTo(locksn);
                int updated = relationMapper.updateByExampleSelective(user,example);
                if(updated == 1) {
                    return parsedMap;
                }
            }
            return null;
         }catch (Exception e) {
             if(log.isInfoEnabled()) {
                 log.info(e.getMessage());
             }
             return null;
         }
    }

    /**
     * 设置用户指纹密码
     * @param seqid 用户id
     * @param locksn
     * @param ptype 类型 1: 永久 2：一次 3：限时
     * @param endtime
     * @param usertype  2:指纹 3：IC卡
     * @param nickname
     * @return
     */
    @Override
    public AnyoujiaResult setLockUserFingerPassword(int seqid,
                                                    String locksn,
                                                    int ptype,
                                                    int endtime,
                                                    int usertype,
                                                    String nickname,
                                                    String phone) {

        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String param = sb.append(endtime)
                .append(locksn)
                .append(nickname)
                .append(ptype)
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
        if(state ==200) {
            SpMemberRelation user = new SpMemberRelation();
            user.setSetedlockfinger(true);
            SpMemberRelationExample example = new SpMemberRelationExample();
            SpMemberRelationExample.Criteria criteria = example.createCriteria();
            criteria.andPhoneEqualTo(phone).andLocksnEqualTo(locksn);
            int updated = relationMapper.updateByExampleSelective(user,example);
            if(updated == 1) {
                return AnyoujiaResult.build(200,String.valueOf(data.get("message")));
            }
        }
        return AnyoujiaResult.build(state,String.valueOf(data.get("msg")));
    }

    /**
     * 注册锁管理员
     * @param locksn
     * @param userid
     * @return
     */
    @Override
    public AnyoujiaResult registerLockInfo(String locksn, int userid) {

        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String param = sb.append(locksn).append(timestamp).append(userid).append(lockSalt).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam = "method=register.lock.info&userid="+userid+"&locksn="+locksn+"&temptime="+timestamp+"&sign="+sign;
        String res = SimulateGetAndPostUtil.sendPost(url,combineParam);
        Map<String,Object> data;
        try {
            data  =  JsonUtils.jsonToMap(res);
        }catch (LockException e) {
            if(log.isInfoEnabled()) {
                log.info(e.getMessage());
            }
            return AnyoujiaResult.build(500,"系统异常");
        }
        String state = String.valueOf(data.get("code"));
        Integer status = Integer.valueOf(state);
        if(status == 200) {
            SpAdminLock adminLock = lockMapper.selectByPrimaryKey(userid);
            if(adminLock == null) {
                SpAdminLock lock = new SpAdminLock();
                lock.setAdminid(userid);
                lock.setLocksn(locksn);
                lock.setCreatetime((int)timestamp);
              try {
                  int insert =  lockMapper.insertSelective(lock);
                  if(insert == 1) {
                      return AnyoujiaResult.build(status,String.valueOf(data.get("msg")));
                  }
              }catch (Exception e) {
                  if(log.isInfoEnabled()) {
                      log.info(e.getMessage());
                  }
              }
            }
            return AnyoujiaResult.build(500,"系统异常");
        }
        return AnyoujiaResult.build(status,String.valueOf(data.get("msg")));
    }

    @Override
    public AnyoujiaResult getAllLockList(SpMember user) {

        int userid = user.getUid();
        String phone = user.getPhone();
        //根据phone查询关系表
        List<SpMemberRelation> list =  relationMapper.selectByExample(combineExampleByPhone(phone));
        List<SpMemberRelation> valid = new ArrayList<>();
        List<SpMemberRelation> invalid = new ArrayList<>();
        Iterator<SpMemberRelation> it = list.iterator();
        while(it.hasNext()) {
            SpMemberRelation mr = it.next();
            if(DateUtil.isNotExpired(mr.getEndtime(),30)) {
                valid.add(mr);
            }else {
                invalid.add(mr);
            }
        }
        if(invalid.size() >0) {
            relationMapper.deleteByExample(combineExampleByPhone(phone));
        }
        List<Integer> adminIds = new ArrayList<>();
        if(valid.size() >0){
            for(SpMemberRelation u:valid) {
                adminIds.add(u.getParentid());
            }
        }
         //将自己为锁管理员的id添加进去
        adminIds.add(userid);
        List<String> result = new ArrayList<>();
       Iterator<Integer> iterator =  adminIds.iterator();
        while(iterator.hasNext()) {
           Integer id =  iterator.next();
            StringBuilder sb = new StringBuilder();
            long timestamp = System.currentTimeMillis()/1000;
            String param = sb.append(timestamp).append(id).append(lockSalt).toString();
            String sign = Md5Utils.md5(param,"UTF-8");
            String combineParam = "method=get.lock.list&userid="+id+"&temptime="+timestamp+"&sign="+sign;
            String res =  SimulateGetAndPostUtil.sendPost(url,combineParam);
            result.add(res);
        }
        //TODO 将自己作为管理员的和作为成员的锁分开
        Map<String,List<String>> res = new HashMap<>();
        return AnyoujiaResult.ok(res);
    }

//    @Override
//    public AnyoujiaResult getProctedDays(SpMember user, String sn) {
//        SpMemberRelationExample example = new SpMemberRelationExample();
//        SpMemberRelationExample.Criteria criteria = example.createCriteria();
//        criteria.andPhoneEqualTo(user.getPhone()).andLocksnEqualTo(sn);
//       List<SpMemberRelation> list = relationMapper.selectByExample(example);
//        return null;
//    }

    private SpMemberRelationExample combineExampleByPhone(String phone) {
        SpMemberRelationExample example = new SpMemberRelationExample();
        SpMemberRelationExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        return example;
    }
}
