package cn.anyoufang.test;

import static org.apache.shiro.util.StringUtils.hasLength;

/**
 * @author daiping
 */
public class StringUtilTest {

    public static void main(String[] args) {
        System.out.println(hasText(""));
        System.out.println(hasText(" "));
        System.out.println(hasText("test"));
        System.out.println(hasText(null));
    }

    public static boolean hasText(String str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
