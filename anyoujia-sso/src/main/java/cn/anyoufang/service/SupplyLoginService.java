package cn.anyoufang.service;

public interface SupplyLoginService {

    String login(String username, String password);
    boolean logout();

}
