package cn.anyoufang.controller.lock;

import cn.anyoufang.entity.AnyoujiaResult;
import cn.anyoufang.entity.SpMember;
import cn.anyoufang.exception.LockException;
import cn.anyoufang.service.LoginService;
import cn.anyoufang.utils.JsonUtils;
import cn.anyoufang.utils.Md5Utils;
import cn.anyoufang.utils.SimulateGetAndPostUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author daiping
 */
@RestController
@RequestMapping("/lock")
public class LockController {

    private static final Logger log = LoggerFactory.getLogger(LockController.class);

    @Value("${lock.salt}")
    private String lockSalt;
    @Value("${pageSize}")
    private String pagesize;
    @Value("${lock.url}")
    private String url;

    @Autowired
    private LoginService loginService;

    /**
     * 获取锁开锁记录或者报警记录
     * @param locksn
     * @param isalarm 0表示开锁记录，1表示报警记录
     * @param page
     * @return
     */
   // @RequestMapping("/records")
    public String getOpenLockRecords(@RequestParam String locksn,
                                     @RequestParam int isalarm ,
                                     @RequestParam int page) {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String param = sb.append(isalarm).append(locksn).append(page).append(pagesize).append(timestamp).append(lockSalt).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam = "method=get.lock.openlist&page="+page+"&pagesize="+pagesize+"&locksn="+locksn+"&temptime="+timestamp+"&sign="+sign + "&isalarm="+isalarm;
        return SimulateGetAndPostUtil.sendPost(url,combineParam);

    }

    /**
     *获取锁密码/指纹/IC卡/临时密码用户列表
     * @param locksn
     * @param pwdtype
     * @param usertype
     * @return
     */
   // @RequestMapping("/users")
    public String getLockUserList(@RequestParam String locksn,
                                  @RequestParam(required = false,defaultValue = "-1") int pwdtype,
                                  @RequestParam int usertype) {


        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String param;
        if(pwdtype == -1){
            param=sb.append(locksn).append(timestamp).append(usertype).append(lockSalt).toString();
        }else{
            param=sb.append(locksn).append(pwdtype).append(timestamp).append(usertype).append(lockSalt).toString();
        }
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam;
        String baseurl = "method=get.lock.userlist&locksn="+locksn+"&temptime="+timestamp+"&sign="+sign + "&usertype="+usertype;
        if(pwdtype != -1){
            combineParam = baseurl +"&pwdtype="+pwdtype;
        }else {
            combineParam = baseurl;
        }
        return  SimulateGetAndPostUtil.sendPost(url,combineParam);
    }

    /**
     * 删除锁密码/指纹/IC卡用户信息
     * @param locksn
     * @param seqid
     * @return
     */
   // @RequestMapping("/delpwd")
//    public String delLockPwd(@RequestParam String locksn,
//                             @RequestParam(defaultValue = "-1") int seqid) {
//
//        long timestamp = System.currentTimeMillis()/1000;
//        StringBuilder sb = new StringBuilder();
//        String param = sb.append(locksn).append(seqid).append(timestamp).append(lockSalt).toString();
//        String sign = Md5Utils.md5(param,"UTF-8");
//        String combineParam = "method=del.lock.pwd&locksn="+locksn+"&seqid="+seqid+"&temptime="+timestamp+"&sign="+sign;
//        return SimulateGetAndPostUtil.sendPost(url,combineParam);
//    }

    /**
     * 添加指纹/IC卡用户信息
     * @param seqid 用户ID，APP生成非重复的用户ID， 最大值：4294967295
     * @param locksn
     * @param ptype 类型 1: 永久 2：一次 3：限时
     * @param endtime
     * @param usertype 2:指纹 3：IC卡
     * @return
     */
   // @RequestMapping("/setuser")
    public String setLockUser(@RequestParam int seqid,
                              @RequestParam String locksn,
                              @RequestParam int ptype,
                              @RequestParam int endtime,
                              @RequestParam int usertype) {
        long timestamp = System.currentTimeMillis()/1000;
        SpMember user = loginService.getUserById(seqid);
        String nickname;
        if(user !=null){
            nickname = user.getBname();
        }else {
            nickname = "ct";
        }
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
        return  SimulateGetAndPostUtil.sendPost(url,combineParam);
    }

    /**
     * 添加锁密码用户
     */
    @RequestMapping("/setpwd")
    public String setLockPwd(@RequestParam int ptype,
                             @RequestParam int seqid,
                             @RequestParam String locksn,
                             @RequestParam(required = false) int endtime,
                             @RequestParam String pwds) {
        long timestamp = System.currentTimeMillis()/1000;
        SpMember user = loginService.getUserById(seqid);
        String nickname;
        if(user !=null){
            nickname = user.getBname();
        }else {
            nickname = "ct";
        }
        StringBuilder sb = new StringBuilder();
        String param = sb.append(endtime)
                         .append(locksn)
                         .append(nickname).append(ptype).append(pwds).append(timestamp).append(lockSalt).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam = "method=set.lock.pwd&ptype=1&temptime="+timestamp+"&sign="+sign+"&seqid="+seqid +
                "&locksn="+locksn+"&endtime="+endtime + "&pwds="+pwds + "&nickname="+nickname;
      return  SimulateGetAndPostUtil.sendPost(url,combineParam);
    }

    /**
     * 获取APP用户注册锁列表
     * @param userid
     * @return
     */
   // @RequestMapping("/list")
    public String getLockList(@PathVariable int userid) {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String param = sb.append(timestamp).append(userid).append(lockSalt).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam = "method=get.lock.list&userid=1&temptime="+timestamp+"&sign="+sign;
        return SimulateGetAndPostUtil.sendPost(url,combineParam);
    }

    /**
     * 获取单个门锁详情
     * @param locksn
     * @return
     */
   // @RequestMapping("/info")
    public String getLockInfo(@PathVariable String locksn) {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String param = sb.append(locksn).append(timestamp).append(lockSalt).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam = "method=get.lock.info&locksn="+locksn+"&temptime="+timestamp+"&sign="+sign;
       return SimulateGetAndPostUtil.sendPost(url,combineParam);
    }

    /**
     * 注册锁信息(添加锁管理员)
     * @param locksn
     * @param userid 注册会员的id
     * @return
     */
    @RequestMapping("/register")
    public AnyoujiaResult registerLockInfo(@RequestParam String locksn,
                                           @RequestParam int userid) {
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
        Integer status = (Integer) data.get("code");
        if(status == 200) {
            return AnyoujiaResult.ok();
        }
        return AnyoujiaResult.build(status,String.valueOf(data.get("msg")));
    }


}
