package mytest.day04._02.query;

import mytest.day04._02.util.StringUtil;

public class ProductDirQueryObject extends QueryObject {
    private String dirName;
    public void customizedQuery() {
        if (StringUtil.hasLength(dirName)) {
            super.addQuery("dirName LIKE ?", dirName);
        }
    }

}
