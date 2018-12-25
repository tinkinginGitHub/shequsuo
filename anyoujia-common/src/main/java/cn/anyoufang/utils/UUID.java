package cn.anyoufang.utils;

import java.util.Random;


public class UUID {
    public static final String[] CHARS = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };
    public static final String[] NUMBERS = new String[] {   "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9" };

    public static String getUrl() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = java.util.UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(CHARS[x % 0x3E]);
        }
        return shortBuffer.toString();

    }
    public static String getCode(int limit) {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = java.util.UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < limit; i++) {
            String str = uuid.substring(i * 2, i * 2 + 2);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(CHARS[x % 0x3E]);
        }
        return shortBuffer.toString();


    }
    
    /**
     * @author chenyi
     * @Description 随机数字
     * @param
     * @date date2017/11/7
     */
    public static int getNumber(int limit) {
        String max="1";
        for (int i=1;i<limit;i++){
            max+="0";
        }
        return (int)((Math.random()*9+1)*Integer.parseInt(max));

    }

    public static long genItemId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = Long.parseLong(str);
        return id;
    }

    /**
     * 生成券序列号
     * @return 序列码
     */
    public static String getCode() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = java.util.UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 10; i++) {
            String str = uuid.substring(i * 2, i * 2 + 2);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(CHARS[x % 0x3E]);
        }
        return shortBuffer.toString();


    }

    /**
     * 生成六位手机登录短信验证码
     * @return
     */
    public static String genarateCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0;i<6;i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 生成uuid
     * @return uuid
     */
    public static String getUUID() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 根据一个数获取伪随机数
     * @param x
     * @return
     */
    public static int xorShift(int x){
        int y=x;
        y ^= x >>6;
        y ^= y <<21;
        y ^= y >>7;
        return y;
    }

    public static void main(String[] args) {
        System.out.println(xorShift(470101137));
    }
}
