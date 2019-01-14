package mytest.day03._05.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//查询的基类
public class QueryObject implements IQuery {

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

    //专门用来暴露给子类,添加自身对象的定义查询
    protected void customizedQuery() {
    }

    //专门暴露给子类,让子类用于添加自身的查询条件和参数
    protected void addQuery(String condition, Object... param) {
        this.conditions.add("(" + condition + ")"); // 解决优先级
        this.parameters.addAll(Arrays.asList(param));
    }
}
