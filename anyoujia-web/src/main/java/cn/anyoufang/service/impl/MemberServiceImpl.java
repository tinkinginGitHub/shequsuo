package cn.anyoufang.service.impl;

import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.SpMemberRelation;
import cn.anyoufang.entity.SpMemberRelationExample;
import cn.anyoufang.mapper.SpMemberRelationMapper;
import cn.anyoufang.service.MemberService;
import cn.anyoufang.utils.JsonUtils;
import cn.anyoufang.utils.Md5Utils;
import cn.anyoufang.utils.SimulateGetAndPostUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

/**
 * @author daiping
 */

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private SpMemberRelationMapper relationMapper;

    @Value("${lock.salt}")
    private String lockSalt;
    @Value("${pageSize}")
    private String pagesize;
    @Value("${lock.url}")
    private String url;




    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean addUser(String usertype,
                                    String username,
                                    String phone,
                                    String userrelation,
                                    String locksn,
                                    int finger,
                                    int pwd,int adminId) {
        SpMemberRelation user = new SpMemberRelation();
        user.setUsertype(usertype);
        user.setUsername(username);
        user.setPhone(phone);
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
        user.setParentid(adminId);
        user.setUserrelation(userrelation);
        user.setLocksn(locksn);
       int index =  relationMapper.insertSelective(user);
        if(index == 1) {
            return true;
        }
        return false;
    }

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

    @Override
    /**
     * 删除锁密码/指纹/IC卡用户信息
     */
    public String delLockPwd(String locksn,int seqid) {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String param = sb.append(locksn).append(seqid).append(timestamp).append(lockSalt).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam = "method=del.lock.pwd&locksn="+locksn+"&seqid="+seqid+"&temptime="+timestamp+"&sign="+sign;
        return SimulateGetAndPostUtil.sendPost(url,combineParam);
    }

    @Override
    public boolean updateExpireDateForRenter(int userid, String locksn) {
        return false;
    }

    @Override
    public SpMember updateUser(SpMember user) {
        return null;
    }

    @Override
    public List<SpMemberRelation> getMembersForByAdminId(int adminId,String locksn) {
        List<SpMemberRelation> members = relationMapper.selectByExample(extractDuplicatCode(adminId,locksn));
        return members;
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

    @Override
    /**
     * type: finger,pwd(String 类型)
     */
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
