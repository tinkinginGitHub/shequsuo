package cn.anyoufang.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import cn.anyoufang.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

import cn.anyoufang.controller.WapLoginController;
import cn.anyoufang.entity.AnyoufangResult;
import cn.anyoufang.entity.TbRenter;
import cn.anyoufang.entity.TbRenterExample;
import cn.anyoufang.entity.WeiXinVO;
import cn.anyoufang.mapper.TbRenterMapper;
import cn.anyoufang.service.WapLoginService;
import io.jsonwebtoken.Claims;

@Service
public class WapLoginServiceImpl implements WapLoginService {

    private static final Logger logger = LoggerFactory.getLogger(WapLoginController.class);
    @Autowired
    private TbRenterMapper renterMapper;

    @Value("${USER_SESSION}")
    private String USER_SESSION;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Value("${templcateCodelogin}")
    private String templcateCode;

    @Value("${signName}")
    private String signName;

    @Value("${USER_LOGIN_TIMES}")
    private String loginTimes;

    @Value("${USER_LOGIN_INTERVAL}")
    private String interval;

    @Override
    public AnyoufangResult login(String json,boolean ischangePhone, WeiXinVO weiXinVO) {

        Map<String,Object> params = JsonUtils.jsonToMap(json);
        String phoneNumber = (String)params.get("phone");
        String code = (String)params.get("code");
        String key = USER_SESSION + ":" + phoneNumber;
        String verifyCode =  RedisUtils.get(key);
        if( code.equals(verifyCode)) {
            //登录成功将原数据失效
            TbRenter renter = null;
              if(weiXinVO != null) {
                  renter =  doInsertOpenId(weiXinVO,phoneNumber);
              }else {
                  renter = doInsertNewPhone(phoneNumber);
              }
            RedisUtils.setExpire(USER_SESSION + ":" + phoneNumber,0);
            String token = UUID.getUUID();
            //保存用户信息，并传回token
            RedisUtils.set(USER_SESSION + ":" + token, JsonUtils.objectToJson(renter));
            //重置过期时间
            RedisUtils.setExpire(USER_SESSION + ":" +token,SESSION_EXPIRE);
            String uuid = renter.getId();
            long times = 0l;
           if(!ischangePhone) {
               String uud = RedisUtils.get(uuid);
               if(uud != null) {
                   times = Long.valueOf(uud);
               }else {
                   times = 0;
               }
               AtomicLong al = new AtomicLong(times);
               al.getAndAdd(1);
               //保存用户登录次数
               RedisUtils.set(uuid,String.valueOf(al));
               //在指定时间之后，可再次登录
               RedisUtils.setExpire(uuid,Integer.valueOf(interval));
              // data.put("times",times);//登录次数
           }

            return AnyoufangResult.ok(token);
        }
        return AnyoufangResult.build(400,"验证码错误");
    }

    @Override
    public AnyoufangResult logout(String token) {
        RedisUtils.setExpire(USER_SESSION + ":" + token,0);
        return AnyoufangResult.ok();
    }

    @Override
    public AnyoufangResult getUserByToken(String token) {
        String json = RedisUtils.get(USER_SESSION + ":" + token);
        if (StringUtils.isBlank(json)) {
            return AnyoufangResult.build(400, "用户登录已经过期");
        }
        //重置Session的过期时间
        RedisUtils.setExpire(USER_SESSION + ":" + token, SESSION_EXPIRE);
        //把json转换成User对象
        TbRenter user = JsonUtils.jsonToPojo(json, TbRenter.class);
        return AnyoufangResult.ok(user);
    }
    @Override
    public TbRenter getUserInfoByToken(String token) {
        String json = RedisUtils.get(USER_SESSION + ":" + token);
        if (StringUtils.isBlank(json)) {
            return null;
        }
        //重置Session的过期时间
        RedisUtils.setExpire(USER_SESSION + ":" + token, SESSION_EXPIRE);
        //把json转换成User对象
        TbRenter user = JsonUtils.jsonToPojo(json, TbRenter.class);
        return user;
    }

    @Override
    public AnyoufangResult changePhone(String json,String token) {
        Map<String,Object> map = JsonUtils.jsonToMap(json);
        String newPhone = (String) map.get("phone");
        String newcode = (String)map.get("code");
       String code =  RedisUtils.get(USER_SESSION + ":" + newPhone);
       TbRenter renter = getUserInfoByToken(token);
       renter.setPhone(newPhone);
       if(code !=null&& code.equals(newcode)) {
           renterMapper.updateByPrimaryKeySelective(renter);
           RedisUtils.setExpire(USER_SESSION + ":" + token,0);//将原来的登录信息处理掉
           return login(json,true,null);
       }
       return AnyoufangResult.build(400,"更改手机失败");

    }

    @Override
    public String getVerifyCode(String phoneNumber) throws ClientException {

        //TbRenter renter = doInsertNewPhone(phoneNumber);//执行插入逻辑
//        TbRenterExample example = new TbRenterExample();
//        TbRenterExample.Criteria criteria = example.createCriteria();
//        criteria.andPhoneEqualTo(phoneNumber);
//        List<TbRenter> list =  renterMapper.selectByExample(example);

//        String uuid = renter.getId();
//        String times = RedisUtil.getValues(uuid);
//        if(times == null) {
//            times = "0";
//        }
//        long time = Long.valueOf(times);
//        long logintimes = Long.valueOf(loginTimes);
//        //超过登录次数，不再获取验证码
//        if(time > logintimes) {
//            return null;
//        }
        String data = UUID.genarateCode();
        //把验证码信息保存到redis，key就是token，value就是验证码
        String key = USER_SESSION + ":" + phoneNumber;
        RedisUtils.set(key, data);
        //设置过期时间
        RedisUtils.setExpire(USER_SESSION + ":" + phoneNumber, SESSION_EXPIRE);
        //发送验证码
        SendSmsResponse sendSmsResponse =
                SendSmsUtil.sendSms(phoneNumber,signName,templcateCode,null,"{\"code\":\""+data+"\"}");
        logger.info(sendSmsResponse.getMessage()+": "+sendSmsResponse.getCode());
        return data;
    }

    @Override
    public boolean isPhoneExist(String phone) {
        TbRenterExample example = new TbRenterExample();
        TbRenterExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<TbRenter> list =  renterMapper.selectByExample(example);
        return list.size() == 0 ? false : true;
    }

    private TbRenter doInsertNewPhone(String phoneNumber ) {

        TbRenterExample example = new TbRenterExample();
        TbRenterExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phoneNumber);
        List<TbRenter> list =  renterMapper.selectByExample(example);
        if(list == null || list.size() == 0) {
            TbRenter renter = new TbRenter();
            renter.setId(UUID.getUUID());
            renter.setPhone(phoneNumber);
            renter.setCreated(new Date());
            //状态1.正常，0.冻结
            renter.setStatus((byte)1);
            renterMapper.insert(renter);
            return renter;
        }
        return list.get(0);
    }

    private TbRenter doInsertOpenId(WeiXinVO weiXinVO,String phone ) {

        TbRenterExample example = new TbRenterExample();
        TbRenterExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<TbRenter> list =  renterMapper.selectByExample(example);
        if(list == null || list.size() == 0) {
            TbRenter renter = new TbRenter();
            renter.setId(UUID.getUUID());
            renter.setPhone(phone);
            renter.setNickName(weiXinVO.getNickname());
            renter.setHeaderUrl(weiXinVO.getHeadimgurl());
            renter.setOpenId(weiXinVO.getOpenid());
            Integer sex = weiXinVO.getSex();
            int sexs =  sex!= null?sex : 0;
            renter.setGender(String.valueOf(sexs));
            renter.setCreated(new Date());
            //状态1.正常，0.冻结
            renter.setStatus((byte)1);
            renterMapper.insert(renter);
            return renter;
        }
        TbRenter user = list.get(0);
        user.setHeaderUrl(weiXinVO.getHeadimgurl());
        user.setNickName(weiXinVO.getNickname());
        user.setOpenId(weiXinVO.getOpenid());
        Integer sex = weiXinVO.getSex();
        int sexs =  sex!= null?sex : 0;
        user.setGender(String.valueOf(sexs));
        renterMapper.updateByPrimaryKeySelective(user);
        return user;
    }



    @Override
    public AnyoufangResult doVerify(String json,String token) {
        Map<String,Object> map = JsonUtils.jsonToMap(json);
        String code = (String)map.get("code");
        TbRenter renter = getUserInfoByToken(token);
        String verifycode = null;
        if(renter !=null) {
            verifycode = RedisUtils.get(USER_SESSION + ":" + renter.getPhone());
        }

        if(verifycode !=null && verifycode.equals(code)) {
            return AnyoufangResult.ok();
        }
        return AnyoufangResult.build(400,"验证码错误");
    }

    @Override
    public String isLogin(String auth,String openId) {
        if(auth != null) {
            Claims claims = JwtHelper.parseJWT(auth);
            String token = "";
            if(claims != null) {
                token = (String) claims.get("token");
                TbRenter user = getUserInfoByToken(token);
                if(user != null) {
                    return "true";
                }
            }
        }else if(openId != null) {
            String key = openId + ":" + USER_SESSION;
           if(RedisUtils.get(key) !=null) {
               return JwtHelper.createJWT(openId,"user","user");
           }

        }
        return "";
    }

    @Override
    public TbRenter getUser(String auth) {
        if (auth != null) {
            Claims claims = JwtHelper.parseJWT(auth);
            String token = "";
            if (claims != null) {
                token = (String) claims.get("token");
                TbRenter user = getUserInfoByToken(token);
                if (user != null) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public String getToken(String auth) {
        if (auth != null) {
            Claims claims = JwtHelper.parseJWT(auth);
            String token = "";
            if (claims != null) {
                token = (String) claims.get("token");
                if(token != null) {
                    return token;
                }
            }
        }
        return null;
    }

    @Override
    public String logincheckByOpenId(String openId) {
        TbRenterExample example = new TbRenterExample();
        TbRenterExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<TbRenter> users = renterMapper.selectByExample(example);
        if(users != null && users.size() >0) {
            TbRenter user = users.get(0);
            if(user.getOpenId() != null) {
                RedisUtils.set(USER_SESSION + ":" + openId, JsonUtils.objectToJson(user));
                return JwtHelper.createJWT(openId,"user","user");
            }
            return "";
        }
        return "";
    }
}
