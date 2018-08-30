package cn.anyoufang.util;

import java.util.UUID;

/**
 * @author
 * @Function
 * @DATE 2017/11/18 14:17
 */
public class UUIDUtils {
    public  static String uuid(){
        String s = UUID.randomUUID().toString();
        String uuid = s.replace("-", "");
        return uuid;
    }

    public static void main(String[] args) {

        System.out.println(UUIDUtils.uuid());
    }
}
