package mytest.day03._02.util;

public class StringUtil {
    public static boolean hasLength(String str){
        return str != null && !"".equals(str.trim());
    }
}
