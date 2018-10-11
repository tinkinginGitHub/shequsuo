package cn.anyoufang.service;


import cn.anyoufang.entity.WeiXinVO;

import java.io.IOException;

public interface WxUserService {

    WeiXinVO getAndSaveUserInfoFromWx(String code,String nickname,String headurl,String sex)throws IOException;
    void saveWxUserBasicInfo(String accessToken,String openId ) throws Exception;
}
