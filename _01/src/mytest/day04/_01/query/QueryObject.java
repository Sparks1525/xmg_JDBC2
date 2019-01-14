package mytest.day04._01.query;

import java.util.*;

//查询的基类
public class QueryObject implements IQuery {

    // 封装查询的条件 如:productName LIKE ?
    private List<String> conditions = new ArrayList<>();
    // 使用List来封装查询的参数(List允许元素重复并且记录添加顺序)
    private List<Object> parameters = new ArrayList<>();

    //封装排序的列和对应的规则:dir_id ASC或者salePrice DESC
    //LinkedHashMap可以包装key和value的添加顺序
    private Map<String,String> orderByMap = new LinkedHashMap<>();

    //返回拼接好的查询条件: WHERE 条件1 AND 条件2 ....
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

        // 拼接排序规则
        if(orderByMap.size() > 0){
            Set<Map.Entry<String,String>> entrySet = orderByMap.entrySet();
            sql.append(" ORDER BY ");
            for(Map.Entry<String,String> entry : entrySet){
                String colomnName=  entry.getKey();
                String orderByType = entry.getValue();
                sql.append(colomnName).append(" ").append(orderByType).append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
        }

        return sql.toString();
    }



    //专门暴露给子类,让子类专门用于添加排序规则的方法
    protected void setOrderBy(String columnName,OrderBy ascOrDesc){
        orderByMap.put(columnName,ascOrDesc.name());
    }
    //排序的规则 ASC/DESC
    enum OrderBy{
        ASC, DESC;
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
