package cn.anyoufang.service.impl;

import cn.anyoufang.entity.SpMemberRelation;
import cn.anyoufang.entity.SpMemberRelationExample;
import cn.anyoufang.exception.LockException;
import cn.anyoufang.mapper.SpMemberRelationMapper;
import cn.anyoufang.service.LockMemberService;
import cn.anyoufang.service.LoginService;
import cn.anyoufang.utils.DateUtil;
import cn.anyoufang.utils.JsonUtils;
import cn.anyoufang.utils.Md5Utils;
import cn.anyoufang.utils.SimulateGetAndPostUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

/**
 * @author daiping
 */

@Service
public class LockMemberServiceImpl implements LockMemberService {

    @Autowired
    private SpMemberRelationMapper relationMapper;

    @Autowired
    private LoginService  loginService;

    @Value("${lock.salt}")
    private String lockSalt;
    @Value("${pageSize}")
    private String pagesize;
    @Value("${lock.url}")
    private String url;

    private int deadline = 30;


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
        SpMemberRelation user = new SpMemberRelation();
        user.setUsertype(usertype);
        user.setUsername(username);
        if(!"-1".equals(phone)){
            user.setPhone(phone);
        }
        if(!"-1".equals(endtime)) {
            user.setEndtime(formatTimeString(endtime));
        }
        if(!"-1".equals(userrelation)){
            user.setUserrelation(userrelation);
        }

        if("2".equals(usertype)){
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
        int addTime = (int) (System.currentTimeMillis() /1000);
        user.setAddtime(addTime);
       int index =  relationMapper.insertSelective(user);
        if(index == 1) {
            return true;
        }
        return false;
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
            int index =  relationMapper.deleteByPrimaryKey(userid);
            if(index == 1) {
                String res =  delLockPwd(locksn,userid);
                Map<String,Object> data = JsonUtils.jsonToMap(res);
                Integer status = (Integer)data.get("code");
                if(status == 200) {
                    return true;
                }
            }
        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return false;
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
     * 更新租客密码及指纹权限
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
            //usertype 3表示租户，只有租户有过期时间，当租户30天内未注册系统时，则过期并删除
            if(end != 0 &&!loginService.checkAccount(phone)) {
                if ("3".equals(user.getUsertype())) {
                    if (DateUtil.isNotExpired(end,deadline)) {
                        validMembers.add(user);
                    } else {
                        invalidMembers.add(user);
                    }
                }
            }
        }
        if(invalidMembers.size()>0) {
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
        if("finger".equals(type)) {
            if(status == 0) {
                user.setFingerpwdauth(false);
            }else {
                user.setFingerpwdauth(true);
            }

        }else {
            if(status == 0) {
                user.setFingerpwdauth(false);
            }else {
                user.setFingerpwdauth(true);
            }
        }

        int index = relationMapper.updateByExampleSelective(user,example);
        if(index == 1) {
            return true;
        }
        return false;
    }
}
