package cn.anyoufang.service;

/**
 * @author daiping
 */
public interface CommonService {

    boolean saveComments(String comment,String url,String phone);

    boolean checkSecurityPassword(String dbpassword, String password);

    boolean saveActionRecords(String locksn,int memberid,String action,int when);
}
