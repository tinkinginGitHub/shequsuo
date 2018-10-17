package cn.anyoufang.controller;

import cn.anyoufang.entity.AnyoujiaResult;
import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.SpMemberRelation;
import cn.anyoufang.service.LoginService;
import cn.anyoufang.service.MemberService;
import cn.anyoufang.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author daiping
 */

@RestController
@RequestMapping("/api")
public class MemberController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private MemberService memberService;

    @Autowired
    private LoginService loginService;


    @RequestMapping("/admin/memlist")
      public AnyoujiaResult getMembersForByAdminId(HttpServletRequest request, @RequestParam String locksn) {

        if(StringUtil.isEmpty(locksn)) {
            return AnyoujiaResult.build(404,"锁SN不存在");
        }
        SpMember admin = getUser(request,loginService);
        if(admin == null) {
            return AnyoujiaResult.build(401,"登陆超时");
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
            return AnyoujiaResult.build(400,"参数异常");
        }
          if(!isLogin(token)) {
              return AnyoujiaResult.build(401,"登陆超时");
          }

          boolean ok;
          try {
               ok = memberService.updateMemberLockPwdOrFinger(id, locksn, status,type);
          } catch (Exception e) {
              if(log.isInfoEnabled()) {
                  log.info(e.getMessage());
              }
              return AnyoujiaResult.build(500,"系统异常");
          }
          if(ok) {
              return AnyoujiaResult.ok();
          }
          return AnyoujiaResult.build(500,"sorry...");
      }

    /**
     * 管理员添加用户
     * @param usertype
     * @param username
     * @param phone
     * @param finger
     * @param pwd
     * @param request
     * @return
     */
      @RequestMapping("/admin/addmem")
      public AnyoujiaResult addNewMember(@RequestParam String usertype,
                                         @RequestParam String username,
                                         @RequestParam String phone,
                                         @RequestParam String relation,
                                         @RequestParam String locksn,
                                         @RequestParam int finger,
                                         @RequestParam int pwd, HttpServletRequest request) {

        if(StringUtil.isEmpty(username)
                || StringUtil.isEmpty(usertype)
                || StringUtil.isEmpty(phone)
                || StringUtil.isEmpty(relation)) {

            return AnyoujiaResult.build(400,"参数异常");
        }

          SpMember admin = getUser(request,loginService);
          if(admin == null) {
              return AnyoujiaResult.build(401,"登陆超时");
          }
        int adminId = admin.getUid();

          try {
              boolean ok = memberService.addUser(usertype,username,phone,relation,locksn,finger,pwd,adminId);
              if(ok) {
                  return AnyoujiaResult.ok();
              }
          }catch (Exception e) {
              if(log.isInfoEnabled()) {
                  log.info(e.getMessage());
              }
              return AnyoujiaResult.build(500,"系统异常");
          }
          return AnyoujiaResult.build(500,"系统异常");
      }

    /**
     * 管理员删除成员
     * @param locksn
     * @param userid
     * @return
     */
      @RequestMapping("/admin/delmem")
      public AnyoujiaResult adminRemoveMember(@RequestParam  String locksn,@RequestParam int userid) {

          try {
              boolean ok = memberService.delUser(locksn,userid);
              if(ok) {
                  return AnyoujiaResult.ok();
              }else {
                  return AnyoujiaResult.build(500,"系统异常");
              }
          }catch (Exception e) {
              if(log.isInfoEnabled()) {
                  log.info(e.getMessage());
              }
              return AnyoujiaResult.build(500,"系统异常");
          }
      }
}
