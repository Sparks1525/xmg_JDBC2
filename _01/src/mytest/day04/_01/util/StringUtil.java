package mytest.day04._01.util;

public class StringUtil {
    public static boolean hasLength(String str){
        return str != null && !"".equals(str.trim());
    }
}
