package cn.anyoufang.controller.lock;

import cn.anyoufang.controller.AbstractController;
import cn.anyoufang.entity.SpLockFinger;
import cn.anyoufang.entity.selfdefined.AnyoujiaResult;
import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.SpMemberRelation;
import cn.anyoufang.exception.LockException;
import cn.anyoufang.service.LoginService;
import cn.anyoufang.service.LockMemberService;
import cn.anyoufang.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 锁成员管理控制器
 * @author daiping
 */

@RestController
@RequestMapping("/api")
public class LockMemberController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(LockMemberController.class);
    @Autowired
    private LockMemberService memberService;

    @Autowired
    private LoginService loginService;

    /**
     * usertype 1家人，2老人儿童，3.租户',
     * @param request
     * @param locksn
     * @return
     */
    @RequestMapping("/admin/memlist")
    public AnyoujiaResult getMembersForByAdminId(HttpServletRequest request, @RequestParam String locksn) {

        if(StringUtil.isEmpty(locksn)) {
            return AnyoujiaResult.build(FOUR_H_4,"锁SN不存在");
        }
        SpMember admin = getUser(request,loginService);
        if(admin == null) {
            return AnyoujiaResult.build(FOUR_H_1,"登陆超时");
        }
        int id = admin.getUid();
        List<SpMemberRelation> list =  memberService.getMembersForByAdminId(id,locksn);
        Map<String,List<Map<String,Object>>> res = new HashMap<>();
        List<Map<String,Object>> family = new ArrayList<>();
        List<Map<String,Object>> oldChild = new ArrayList<>();
        List<Map<String,Object>> renters = new ArrayList<>();
        for(SpMemberRelation user:list) {
            Map data = new HashMap();
            String type =  user.getUsertype();
            data.put("fingerpwdauth",user.getFingerpwdauth());
            data.put("lockpwdauth",user.getLockpwdauth());
            data.put("phone",user.getPhone());
            data.put("usertype",type);
            data.put("relation",user.getUserrelation());
            data.put("username",user.getUsername());
            data.put("setedlockfinger",user.getSetedlockfinger());
            data.put("setedlockpwd",user.getSetedlockpwd());
            data.put("endtime",user.getEndtime());
            data.put("relationid",user.getId());
           if("1".equals(type)) {
               family.add(data);
               res.put(type,family);
           }else if ("2".equals(type)) {
               oldChild.add(data);
               res.put(type,oldChild);
           }else {
               renters.add(data);
               res.put(type,renters);
           }
        }
        return AnyoujiaResult.ok(res);
      }


    /**
     *
     * @param id
     * @param locksn
     * @param type 0 指纹，1 密码
     * @param status 0 关闭，1开启
     * @param request
     * @return
     */
      @RequestMapping("/admin/onoff")
      public AnyoujiaResult updateMemberLockPwd(@RequestParam int id,
                                                @RequestParam String locksn,
                                                @RequestParam String type,
                                                @RequestParam int status,HttpServletRequest request ) {
        String token = request.getHeader("token");
        if(StringUtil.isEmpty(locksn)) {
            return AnyoujiaResult.build(FOUR_H,"参数异常");
        }
          if(!isLogin(token)) {
              return AnyoujiaResult.build(FOUR_H_1,"登陆超时");
          }

          boolean ok;
          try {
               ok = memberService.updateMemberLockPwdOrFinger(id, locksn, status,type);
          } catch (Exception e) {
              if(log.isInfoEnabled()) {
                  log.info(e.getMessage());
              }
              return AnyoujiaResult.build(FIVE_H,"系统异常");
          }
          if(ok) {
              return AnyoujiaResult.ok();
          }
          return AnyoujiaResult.build(FIVE_H,"sorry...");
      }

    /**
     * 管理员添加用户
     * @param usertype
     * @param username
     * @param phone 老人和小孩不需要添加手机号
     * @param finger
     * @param pwd
     * @param request
     * @param endtime 只有租客需要设置过期时间
     * @return
     */
      @RequestMapping("/admin/addmem")
      public AnyoujiaResult addNewMember(@RequestParam String usertype,
                                         @RequestParam String username,
                                         @RequestParam(required = false,defaultValue = "-1") String phone,
                                         @RequestParam(required = false,defaultValue = "-1") String relation,
                                         @RequestParam String locksn,
                                         @RequestParam(required = false,defaultValue = "-1") String endtime,
                                         @RequestParam int finger,
                                         @RequestParam int pwd, HttpServletRequest request) {

        if(StringUtil.isEmpty(username)
                || StringUtil.isEmpty(usertype)
                || StringUtil.isEmpty(phone)
                || StringUtil.isEmpty(relation)
                || StringUtil.isEmpty(locksn)) {

            return AnyoujiaResult.build(FOUR_H,"参数异常");
        }

          SpMember admin = getUser(request,loginService);
          if(admin == null) {
              return AnyoujiaResult.build(FOUR_H_1,"登陆超时");
          }
        int adminId = admin.getUid();

          try {
              boolean ok = memberService.addUser(usertype,username,phone,relation,locksn,finger,pwd,adminId,endtime);
              if(ok) {
                  return AnyoujiaResult.ok();
              }
          }catch (Exception e) {
              if(log.isInfoEnabled()) {
                  log.info(e.getMessage());
              }
              return AnyoujiaResult.build(FIVE_H,"系统异常");
          }
          return AnyoujiaResult.build(FIVE_H,"系统异常");
      }

    /**
     * 锁管理员删除成员
     * @param locksn
     * @param userid 成员id
     * @return
     */
      @RequestMapping("/admin/delmem")
      public AnyoujiaResult adminRemoveMember(@RequestParam  String locksn,@RequestParam int userid) {

          try {
              boolean ok = memberService.delUser(locksn,userid);
              if(ok) {
                  return AnyoujiaResult.ok();
              }else {
                  return AnyoujiaResult.build(FIVE_H,"系统异常");
              }
          }catch (Exception e) {
              if(log.isInfoEnabled()) {
                  log.info(e.getMessage());
              }
              return AnyoujiaResult.build(FIVE_H,"系统异常");
          }
      }

    /**
     *
     * @param endtime 租客过期时间
     * @param id 租客id
     * @return
     */
      @RequestMapping("/admin/update/rentexpire")
      public AnyoujiaResult updateExpireDateForRenter(@RequestParam String endtime,
                                                      @RequestParam int id,
                                                      @RequestParam String locksn) {
          if(StringUtil.isEmpty(endtime) || StringUtil.isEmpty(locksn)) {
              return AnyoujiaResult.build(FOUR_H,"参数异常");
          }

         boolean updateTimeSeccuss;
          try{
              updateTimeSeccuss = memberService.updateExpireDateForRenter(id,locksn,endtime);
              if(updateTimeSeccuss) {
                  return AnyoujiaResult.ok();
              }
          }catch (LockException e) {
              if(log.isInfoEnabled()) {
                  log.info(e.getMessage());
              }
              return AnyoujiaResult.build(FIVE_H,"系统错误");
          }
          return AnyoujiaResult.build(FIVE_H,"系统错误");
      }

    /**
     * 获取用户指纹id列表
     * @param locksn
     * @param request
     * @return
     */
      @RequestMapping("/admin/fingerlist")
      public  AnyoujiaResult getFingerList(@RequestParam String locksn,HttpServletRequest request) {
          if(StringUtil.isEmpty(locksn)) {
              return AnyoujiaResult.build(FOUR_H,"参数异常");
          }
          SpMember user = getUser(request,loginService);
          if(user == null) {
              return AnyoujiaResult.build(FOUR_H_1,"登陆超时");
          }
          List<SpLockFinger> res = memberService.getFingerList(user.getUid(),locksn);
          return AnyoujiaResult.ok(res);
      }


     /**
     * 移除指纹
     * @param seqid
     * @param locksn
     * @return
     */
       @RequestMapping("/admin/removefinger")
      public AnyoujiaResult removeFingerBySeqId(@RequestParam int seqid,
                                                @RequestParam String locksn) {
          if(StringUtil.isEmpty(locksn)) {
              return AnyoujiaResult.build(FOUR_H,"参数异常");
          }

          if(memberService.deleteFingerAccordent(seqid,locksn)) {
              return AnyoujiaResult.ok();
          }
          return AnyoujiaResult.build(FOUR_H,"删除超时,请重试");
      }

      @RequestMapping("/admin/checksetedpwd")
      public AnyoujiaResult isSetLockPwdForever(@RequestParam String locksn,HttpServletRequest request) {
          if(StringUtil.isEmpty(locksn)) {
              return AnyoujiaResult.build(FOUR_H,"参数异常");
          }
          SpMember user = getUser(request,loginService);
          if(user == null) {
              return AnyoujiaResult.build(FOUR_H_1,"登陆超时");
          }
         boolean ok =  memberService.isSetLockPwdForever(locksn,user.getPhone(),user.getUid());
          if(ok) {
              return AnyoujiaResult.ok();
          }
          return AnyoujiaResult.build(FIVE_H,"系统错误");
      }
}
