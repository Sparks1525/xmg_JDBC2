package mytest.day03._03.query;

import mytest.day03._03.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductDirQueryObject implements IQuery{

    private String dirName;

    private List<String> conditions = new ArrayList<>();
    private List<Object> parameters = new ArrayList<>();

    @Override
    public String getQuery() {
        StringBuilder sql = new StringBuilder(200);
        this.customizedQuery();
        for (int i = 0; i < conditions.size(); i++) {
            if (i == 0) { // 第一个条件
                sql.append(" WHERE ");
            } else { // 不是第一个条件
                sql.append(" AND ");
            }
            sql.append(conditions.get(i));
        }
        return sql.toString();
    }

    @Override
    public List<Object> getParameters() {
        return this.parameters;
    }


    //自身对象的定义查询
    public void customizedQuery() {
        if (StringUtil.hasLength(dirName)) {
            conditions.add("dirName LIKE ?");
            parameters.add("%" + dirName + "%");
        }
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }
}
