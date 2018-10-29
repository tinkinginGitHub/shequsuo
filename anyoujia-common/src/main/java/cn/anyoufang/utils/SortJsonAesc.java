package cn.anyoufang.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * json字符串升序排序
 * 缺点：只能比较最外层的字段，如果json里面还有第二层第三层，则里面不能排序，
 * 因为这个方法主要是利用treemap里面的key来排序的
 * @author daiping
 */
public class SortJsonAesc {


    public static Map<String, String> sortMap(Map<String, String> map) {
	        if (map == null || map.isEmpty()) {
	            return null;
	        }
	        Map<String, String> sortMap = new TreeMap<>(new MapComparator());
	        sortMap.putAll(map);
	        return sortMap;
    }
}

class MapComparator implements Comparator<String>{

    @Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }

}

