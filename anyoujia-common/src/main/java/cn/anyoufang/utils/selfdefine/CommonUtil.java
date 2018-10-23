package cn.anyoufang.utils.selfdefine;

import cn.anyoufang.entity.selfdefined.Lock;
import cn.anyoufang.entity.selfdefined.LockInfo;
import cn.anyoufang.entity.selfdefined.LockRecord;
import cn.anyoufang.utils.JsonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * 锁硬件端接口响应处理通用工具类
 * @author daiping
 */
public class CommonUtil {


    /**
     * 提取重复代码，解析从硬件端返回的结果
     * @param res
     * @return
     */
    public static int paseResFromHardware(String res) {
        Map<String,Object> data = JsonUtils.jsonToMap(res);
        Object status = data.get("code");
        if(status != null) {
            return (Integer) status;
        }else {
            return 0;
        }
    }

    /**
     * 获取message
     * @param res
     * @return
     */
    public static String getMessage(String res) {
        Map<String,Object> data = JsonUtils.jsonToMap(res);
        Object message = data.get("message");
        return String.valueOf(message);
    }

    /**
     * 解析并封装返回给小程序端的门锁记录
     * @param data
     * @return
     */
    public static List<LockRecord> getRecords(String data) {
        Map<String,Object> map = JsonUtils.jsonToMap(data);
        Object objects = map.get("data");
        List<LockRecord> res = new ArrayList<>();
        if(objects != null) {
            JSONArray json = JSONArray.fromObject(objects);
            for(int index=0;index<json.size();index++) {
                JSONObject job = json.getJSONObject(index);
                LockRecord record = new LockRecord();
                int opentime = job.getInt("opentime");
                int pwdType = job.getInt("pwd_type");
                String nickname = job.getString("nickname");
                int isAlarm = job.getInt("isalarm");
                record.setIsAlarm(isAlarm);
                record.setOpentime(opentime);
                record.setPwdType(pwdType);
                record.setNickname(nickname);
                res.add(record);
            }
        }

        return res;
    }

    /**
     * 解析并封装返回给小程序端的门锁列表
     * @param list
     * @return
     */
    public static List<Lock> getAdminLockList(List<String> list) {
        List<Lock> res = new ArrayList<>();
        for(String data:list) {
            Map<String,Object> map = JsonUtils.jsonToMap(data);
            Object objects = map.get("data");
            if(objects !=null) {
                JSONArray json = JSONArray.fromObject(objects);
                int size = json.size();
                for(int index=0;index<size;index++) {
                    JSONObject job = json.getJSONObject(index);
                    Lock lock = new Lock();
                    lock.setLockCreatetime(job.getInt("lock_createtime"));
                    lock.setPower1(job.getInt("power1"));
                    lock.setPower2(job.getInt("power2"));
                    lock.setOnline(job.getInt("online"));
                    lock.setLocksn(job.getString("locksn"));
                    lock.setLockStatus(job.getInt("lock_status"));
                    lock.setAdmin(true);
                    lock.setLockfingerAuth(true);
                    lock.setLockpwdAuth(true);
                    res.add(lock);
                }
            }

        }

        return res;
    }

    /**
     * 解析并封装成员角色的锁信息返回给前台
     * @param list
     * @param pwdauths
     * @param fingerauths
     * @return
     */

    public static List<Lock> getMemberLockList(List<String> list,Map<String,Boolean> pwdauths,Map<String,Boolean> fingerauths) {

        List<Lock> res = new ArrayList<>();
        for(String data:list) {
            Map<String,Object> map = JsonUtils.jsonToMap(data);
            Object objects = map.get("data");
            if(objects != null) {
                JSONArray json = JSONArray.fromObject(objects);
                int size =  json.size();
                for(int index=0;index<size;index++) {
                    JSONObject job = json.getJSONObject(index);
                    Lock lock = new Lock();
                    lock.setLockCreatetime(job.getInt("lock_createtime"));
                    lock.setPower1(job.getInt("power1"));
                    lock.setPower2(job.getInt("power2"));
                    lock.setOnline(job.getInt("online"));
                    String locksn = job.getString("locksn");
                    lock.setLocksn(locksn);
                    lock.setLockStatus(job.getInt("lock_status"));
                    lock.setAdmin(false);
                    lock.setLockfingerAuth(pwdauths.get(locksn));
                    lock.setLockpwdAuth(fingerauths.get(locksn));
                    res.add(lock);
                }
            }

        }
        return res;
    }

    /**
     * 解析并封装返回给小程序端的数据
     * @param data
     * @return
     */

    public  static LockInfo getLockInfo(String data) {
        Map<String,Object> map = JsonUtils.jsonToMap(data);
        Object objects = map.get("data");
        JSONObject jsonObject = JSONObject.fromObject(objects);
        LockInfo lockInfo = new LockInfo();
        lockInfo.setLockCreatetime(jsonObject.getInt("lock_createtime"));
        lockInfo.setPower1(jsonObject.getInt("power1"));
        lockInfo.setPower2(jsonObject.getInt("power2"));
        lockInfo.setOnline(jsonObject.getInt("online"));
        lockInfo.setLockStatus(jsonObject.getInt("lock_status"));
        return lockInfo;
    }

    /**
     * 硬件响应ok
     * @param res
     * @return
     */
    public static boolean successResponse(String res) {
        if(paseResFromHardware(res) == 200) {
            return true;
        }
        return false;
    }

}
