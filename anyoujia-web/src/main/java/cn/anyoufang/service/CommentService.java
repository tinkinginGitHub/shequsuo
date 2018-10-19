package cn.anyoufang.service;

/**
 * @author daiping
 */
public interface CommentService {

    boolean saveComments(String comment,String url,String phone);

    boolean checkSecurityPassword(String dbpassword, String password);
}
