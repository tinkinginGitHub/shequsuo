package cn.anyoufang.service;

import cn.anyoufang.entity.SpLockAdmin;
import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.SpMemberRelation;
import cn.anyoufang.entity.selfdefined.AnyoujiaResult;
import com.aliyuncs.exceptions.ClientException;

import java.util.List;
import java.util.Map;

/**
 * 登录相关业务
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
     * @param
     * @return
     */
    Map<String,Object>  memberLoginByVerifyCode(String phone,String ip) throws Exception;

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
     * 重设安全密码之前检查
     * @param account
     * @param code
     * @param answer
     * @return
     */
    boolean checkAccountAndAnswer(String account,String code,String answer);

    /**
     *获取用户已经选择的安全问题
     * @param id
     * @return
     */
    String getUserCheckedQuestion(int id);

    /**
     *用户空缺信息
     * @param avatar
     * @param bname
     * @param phone
     * @return
     */
    boolean updateAdditionalUserInfo(String avatar,String bname,String phone,int gender);

    // boolean isExist(String phone);

    /**
     * 调用PHP鉴权中心注册
     * @param account
     * @param password
     * @return
     * @throws Exception
     */

    Map<String,Object> doRegister(String account,String password) throws Exception;

    /**
     * 调用PHP鉴权中心接口登录
     * @param account
     * @param password
     * @param ip
     * @return
     * @throws Exception
     */

    Map<String,Object> doLogin(String account,String password,String ip) throws Exception;

    /**
     * 更新用户密码
     * @param username
     * @param password
     * @return
     * @throws Exception
     */

    Map<String,Object> updateUserPassword(String username,String password) throws Exception;

    /**
     * 更新登录状态
     * @param username
     * @param session
     * @return
     * @throws Exception
     */

    Map<String,Object> updateLogin(String username,String session) throws Exception;

    /**
     * 判断用户是否注册
     * @param account
     * @return
     */
    SpMember getUserByAccount(String account);

    /**
     * 删除安全密码
     * @param phone
     * @return
     */
    boolean delSecurityPassword(String phone);

    /**
     * 检查账户
     * @param phone
     * @return
     */
    boolean checkAccount(String phone);

    /**
     * 根据验证码验证账户
     * @param phone
     * @param code
     * @return
     */
    boolean checkByCode(String phone,String code);


    /**
     * 获取用户关系
     * @param locksn
     * @param phone
     * @return
     */
    String getMemberRelation(String locksn,String phone);

    /**
     * 一个人可以是多把锁的管理员
     * @param memberid
     * @return
     */
    List<SpLockAdmin>  getLockAdmin(int memberid, String locksn);

    /**
     * 查询账户
     * @param phone
     * @return
     */
    boolean checkAccount1(String phone);

    SpMemberRelation getRelationMember(int relationid);

}
