package mytest.day03._04.query;

import mytest.day03._04.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductDirQueryObject extends QueryObject {
    private String dirName;
    public void customizedQuery() {
        if (StringUtil.hasLength(dirName)) {
            super.addQuery("dirName LIKE ?", dirName);
        }
    }


    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }
}
