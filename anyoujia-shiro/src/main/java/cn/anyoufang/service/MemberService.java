package cn.anyoufang.service;

import cn.anyoufang.entity.AnyoujiaResult;
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
    AnyoujiaResult memberRegister(String account, String pwd, WeiXinVO weiXinVO);

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
     * 未登录状态下重置密码
     * @param phone
     * @param newPwd
     */
    boolean resetPwd(String phone,String newPwd);

    /**
     * 已经登录状态下重置密码
     * @param oldPassword
     * @param newPassword
     * @return
     */
    boolean resetPasswordLogined(String oldPassword,String newPassword);

    /**
     * 设置安全密码
     * @param password
     * @param id
     * @return
     */
    boolean setSecurityPwd(String password,int id,String question,String answer);

    /**
     * 更改安全密码
     * @param oldPwd
     * @param newPwd
     * @param id
     * @return
     */
    boolean updateSecurityPwd(String oldPwd,String newPwd,int id,String answer,String question);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    SpMember getUserById(int id);

    /**
     * 检查账户
     * @param account
     * @param code
     * @return
     */
    boolean checkAccount(String account,String code);

    /**
     * 重设安全密码之前检查
     * @param account
     * @param code
     * @param answer
     * @return
     */
    boolean checkAccountAndAnswer(String account,String code,String answer);

    /**
     *
     * @param id
     * @return
     */
    String getUserCheckedQuestion(int id);

    /**
     *
     * @param avatar
     * @param bname
     * @param phone
     * @return
     */
    boolean updateAdditionalUserInfo(String avatar,String bname,String phone);

}
