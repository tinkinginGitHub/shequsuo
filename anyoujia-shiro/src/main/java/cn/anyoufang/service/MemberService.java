package cn.anyoufang.service;

import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.WeiXinVO;
import com.aliyuncs.exceptions.ClientException;

/**
 * @author daiping
 */
public interface MemberService {
    /**
     * 用户注册
     * @param account
     * @param pwd
     * @param weiXinVO
     * @return
     */
    boolean memberRegister(String account,String pwd,WeiXinVO weiXinVO);

    /**
     * 用户使用密码登录
     * @param account
     * @param pwd
     * @param ip
     * @return
     */
    SpMember memberLoginByPwd(String account, String pwd,String ip);

    /**
     * 用户使用验证码登录
     * @param code
     * @return
     */
    SpMember memberLoginByVerifyCode(String phone,String code,String ip);

    /**
     * 用户登出系统
     */
    void memberLogout();

    /**
     * 获取验证码
     * @param phone
     * @throws ClientException
     */
    void getVerifCode(String phone) throws ClientException;

    /**
     * 重置密码
     * @param phone
     * @param newPwd
     */
    void resetPwd(String phone,String newPwd);

}
