package mytest.day04._02.util;

public class StringUtil {
    public static boolean hasLength(String str){
        return str != null && !"".equals(str.trim());
    }
}
