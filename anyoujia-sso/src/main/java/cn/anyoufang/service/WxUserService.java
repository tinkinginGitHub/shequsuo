package cn.anyoufang.service;


import cn.anyoufang.entity.WeiXinVO;

import java.io.IOException;

public interface WxUserService {

    WeiXinVO getAndSaveUserInfoFromWx(String code)throws IOException;
}
