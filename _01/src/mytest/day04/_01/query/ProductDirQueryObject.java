package mytest.day04._01.query;

import mytest.day04._01.util.StringUtil;

import java.beans.BeanInfo;
import java.beans.Introspector;

public class ProductDirQueryObject extends QueryObject {
    private String dirName;
    public void customizedQuery() {
        if (StringUtil.hasLength(dirName)) {
            super.addQuery("dirName LIKE ?", dirName);
        }
    }
}
