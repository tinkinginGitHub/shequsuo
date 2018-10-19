package cn.anyoufang.service;

import cn.anyoufang.entity.selfdefined.AnyoujiaResult;
import cn.anyoufang.entity.SpMember;
import com.aliyuncs.exceptions.ClientException;

import java.util.Map;

/**
 * @author daiping
 */
public interface LoginService {
    /**
     * 用户注册
     * @param account
     * @param pwd
     * @param openId
     * @return
     */
    AnyoujiaResult memberRegister(String account, String pwd, String openId) throws Exception;

    /**
     * 用户使用密码登录
     * @param account
     * @param pwd
     * @param ip
     * @return
     */
    Map<String,Object> memberLoginByPwd(String account, String pwd, String ip) throws Exception;

    /**
     * 用户使用验证码登录
     * @param code
     * @return
     */
    Map<String,Object>  memberLoginByVerifyCode(String phone,String code,String ip) throws Exception;

    /**
     * 用户登出系统
     */
    void memberLogout(String token) throws Exception;

    /**
     * 获取验证码
     * @param phone
     * @throws ClientException
     */
    String sendVerifCode(String phone) throws ClientException;

    /**
     * 未登录状态下重置密码
     * @param phone
     * @param newPwd
     */
    boolean resetPwd(String phone,String newPwd) throws Exception;

    /**
     * 已经登录状态下重置密码
     * @param oldPassword
     * @param newPassword
     * @return
     */
    boolean resetPasswordLogined(String oldPassword,String newPassword) throws Exception;

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
    boolean updateSecurityPwd(String oldPwd,String newPwd,int id);

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

     boolean isExist(String phone);

    Map<String,Object> doRegister(String account,String password) throws Exception;

    Map<String,Object> doLogin(String account,String password,String ip) throws Exception;

    Map<String,Object> updateUserPassword(String username,String password) throws Exception;

    Map<String,Object> updateLogin(String username,String session) throws Exception;
    SpMember getUserByAccount(String account);

    boolean delSecurityPassword(String phone);

    boolean checkAccount(String phone);

    boolean checkByCode(String phone,String code);

}
