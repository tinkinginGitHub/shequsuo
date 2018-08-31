package cn.anyoufang.service;

import com.aliyuncs.exceptions.ClientException;

public interface MemberService {

    boolean memberRegister(String account,String pwd);
    boolean memberLogin(String account,String pwd);
    void memeberLogout();
    String getVerifCode(String phone) throws ClientException;
}
