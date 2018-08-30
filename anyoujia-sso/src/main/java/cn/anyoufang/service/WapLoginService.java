package cn.anyoufang.service;

import cn.anyoufang.entity.AnyoufangResult;
import cn.anyoufang.entity.TbRenter;
import cn.anyoufang.entity.WeiXinVO;
import com.aliyuncs.exceptions.ClientException;
import io.jsonwebtoken.Claims;

public interface WapLoginService {

    String getVerifyCode(String phoneNumber) throws ClientException;
    AnyoufangResult login(String json,boolean ischangePhone, WeiXinVO weiXinVO);
    AnyoufangResult logout(String token);
    AnyoufangResult getUserByToken(String token);
    AnyoufangResult changePhone(String json,String token);
    AnyoufangResult doVerify(String json,String token);
    TbRenter getUserInfoByToken(String token);
    String isLogin(String auth,String openId);
    TbRenter getUser(String  auth);
    String getToken(String auth);
    boolean isPhoneExist(String phone);
    String logincheckByOpenId(String openId);

}
