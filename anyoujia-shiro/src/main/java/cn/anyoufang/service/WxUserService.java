package cn.anyoufang.service;


import cn.anyoufang.entity.selfdefined.ResultWx;

import java.io.IOException;

public interface WxUserService {

    ResultWx CheckUserwXInfoBand(String code)throws IOException;

    String getOpenId(String code)throws IOException;
    void saveWxUserBasicInfo(String accessToken, String openId) throws Exception;
}
