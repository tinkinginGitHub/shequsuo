package cn.anyoufang.controller;

import cn.anyoufang.entity.AnyoujiaResult;
import cn.anyoufang.util.SimulateGetAndPostUtil;
import cn.anyoufang.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author daiping
 */
@RestController
@RequestMapping("/api")
public class LockController {

    @Value("${lock.salt}")
    private String lockSalt;
    @Value("${pageSize}")
    private String pagesize;
    @Value("${lock.url}")
    private String url;

    /**
     * 获取锁开锁记录或者报警记录
     * @param locksn
     * @param isalarm
     * @param page
     * @return
     */
    @RequestMapping("/lockrecords")
    public String getOpenLockRecords(@RequestParam String locksn,
                                     @RequestParam int isalarm ,
                                     @RequestParam int page) {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        //0表示开锁记录，1表示报警记录
        String ss = sb.append(isalarm).append(locksn).append(page).append(pagesize).append(timestamp).append(lockSalt).toString();
        String sss = Md5Utils.md5(ss,"UTF-8");
        String param = "method=get.lock.openlist&page="+page+"&pagesize="+pagesize+"&locksn="+locksn+"&temptime="+timestamp+"&sign="+sss + "&isalarm="+isalarm;
        return SimulateGetAndPostUtil.sendPost(url,param);

    }

    /**
     *获取锁密码/指纹/IC卡/临时密码用户列表
     * @param locksn
     * @param pwdtype
     * @param usertype
     * @return
     */
    @RequestMapping("/lockusers")
    public String getLockUserList(@RequestParam String locksn,
                                  @RequestParam(required = false,defaultValue = "-1") int pwdtype,
                                  @RequestParam int usertype) {


        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String ss;
        if(pwdtype == -1){
            ss=sb.append(locksn).append(timestamp).append(usertype).append(lockSalt).toString();
        }else{
            ss=sb.append(locksn).append(pwdtype).append(timestamp).append(usertype).append(lockSalt).toString();
        }
        String sss = Md5Utils.md5(ss,"UTF-8");
        String param;
        if(pwdtype != -1){
            param = "method=get.lock.userlist&locksn="+locksn+"&temptime="+timestamp+"&sign="+sss + "&usertype="+usertype+"&pwdtype="+pwdtype;
        }else {
            param = "method=get.lock.userlist&locksn="+locksn+"&temptime="+timestamp+"&sign="+sss + "&usertype="+usertype;
        }
        return  SimulateGetAndPostUtil.sendPost(url,param);
    }

    /**
     * 删除锁密码/指纹/IC卡用户信息
     * @param locksn
     * @param seqid
     * @return
     */
    @RequestMapping("/dellockpwd")
    public String delLockPwd(@RequestParam String locksn,
                             @RequestParam(defaultValue = "-1") int seqid) {

        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String ss = sb.append(locksn).append(seqid).append(timestamp).append(lockSalt).toString();
        String sss = Md5Utils.md5(ss,"UTF-8");
        String param = "method=del.lock.pwd&locksn="+locksn+"&seqid="+seqid+"&temptime="+timestamp+"&sign="+sss;
        return SimulateGetAndPostUtil.sendPost(url,param);
    }

    /**
     * 添加指纹/IC卡用户信息
     * @return
     */
    @RequestMapping("/setlockuser")
    public AnyoujiaResult setLockUser() {
        return null;
    }
    /**
     * 添加锁密码用户
     */

    @RequestMapping("/setlockpwd")
    public String setLockPwd(@RequestParam int ptype,
                             @RequestParam int seqid,
                             @RequestParam String locksn,
                             @RequestParam(required = false) int endtime,
                             @RequestParam String pwds,
                             @RequestParam String nickname) {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String ss = sb.append(endtime).append(locksn).append(nickname).append(ptype).append(pwds).append(timestamp).append(lockSalt).toString();
        String sss = Md5Utils.md5(ss,"UTF-8");
        String param = "method=set.lock.pwd&ptype=1&temptime="+timestamp+"&sign="+sss+"&seqid="+seqid +
                "&locksn="+locksn+"&endtime="+endtime + "&pwds="+pwds + "&nickname="+nickname;
      return  SimulateGetAndPostUtil.sendPost(url,param);
    }

    /**
     * 获取APP用户注册锁列表
     * @param userid
     * @return
     */
    @RequestMapping("/locklist")
    public String getLockList(@PathVariable int userid) {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String ss = sb.append(timestamp).append(userid).append(lockSalt).toString();
        String sss = Md5Utils.md5(ss,"UTF-8");
        String param = "method=get.lock.list&userid=1&temptime="+timestamp+"&sign="+sss;
        return SimulateGetAndPostUtil.sendPost(url,param);
    }

    /**
     * 获取单个门锁详情
     * @param locksn
     * @return
     */
    @RequestMapping("/lockinfo")
    public String getLockInfo(@PathVariable String locksn) {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String ss = sb.append(locksn).append(timestamp).append(lockSalt).toString();
        String sss = Md5Utils.md5(ss,"UTF-8");
        String param = "method=get.lock.info&locksn="+locksn+"&temptime="+timestamp+"&sign="+sss;
       return SimulateGetAndPostUtil.sendPost(url,param);
    }

    @RequestMapping("/registerlock")
    public String registerLockInfo(@RequestParam String locksn,
                                   @RequestParam int userid) {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String ss = sb.append(locksn).append(timestamp).append(userid).append(lockSalt).toString();
        String sss = Md5Utils.md5(ss,"UTF-8");
        String param = "method=register.lock.info&userid="+userid+"&locksn="+locksn+"&temptime="+timestamp+"&sign="+sss;
        return SimulateGetAndPostUtil.sendPost(url,param);
    }


}
