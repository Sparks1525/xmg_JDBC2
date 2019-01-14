package mytest.day03._05.query;

import mytest.day03._05.util.StringUtil;

public class ProductDirQueryObject extends QueryObject {
    private String dirName;
    public void customizedQuery() {
        if (StringUtil.hasLength(dirName)) {
            super.addQuery("dirName LIKE ?", dirName);
        }
    }

}
