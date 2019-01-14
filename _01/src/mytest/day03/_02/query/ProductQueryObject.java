package mytest.day03._02.query;

import mytest.day03._02.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductQueryObject implements IQuery{
    private String name;
    private BigDecimal minSalePrice;
    private BigDecimal maxSalePrice;

    //使用List来封装查询的参数(List允许元素重复并且记录添加顺序)
    public List<Object> parameters = new ArrayList<>();
    //返回拼接好的查询条件: WHERE 条件1 AND 条件2
    public String getQueryOld(){
        StringBuilder sql = new StringBuilder(200);
        //判断是否第一个条件:第一个条件使用WHERE ,其他条件使用AND
        boolean isFirst = true;
        // 商品名称
        if (StringUtil.hasLength(name)) {
            if (isFirst) {
                sql.append(" WHERE ");
                isFirst = false;
            } else {
                sql.append(" AND ");
            }
            sql.append(" AND productName LIKE ?");
            parameters.add("%" + name + "%");
        }
        //最低零售价
        if (minSalePrice != null) {
            if (isFirst) {
                sql.append(" WHERE ");
                isFirst = false;
            } else {
                sql.append(" AND ");
            }
            sql.append(" AND salePrice >= ?");
            parameters.add(minSalePrice);
        }
        //最高零售价
        if (maxSalePrice != null) {
            if (isFirst) {
                sql.append(" WHERE ");
                isFirst = false;
            } else {
                sql.append(" AND ");
            }
            sql.append(" AND salePrice <= ?");
            parameters.add(maxSalePrice);
        }
        return sql.toString();
    }

    //封装查询的条件:productName LIKE ?
    private List<String> conditions= new ArrayList<>();
    //返回拼接好的查询条件: WHERE 条件1 AND 条件2
    @Override
    public String getQuery(){
        StringBuilder sql = new StringBuilder(200);
        // 商品的名称
        if(StringUtil.hasLength(name)){
            conditions.add("productName LIKE ?");
            parameters.add("%" + name + "%");
        }
        //最低零售价
        if (minSalePrice != null) {
            conditions.add("salePrice >= ?");
            parameters.add(minSalePrice);
        }
        //最高零售价
        if (maxSalePrice != null) {
            conditions.add("salePrice <= ?");
            parameters.add(maxSalePrice);
        }
        for(int i = 0; i < conditions.size(); i++){
            if(i == 0){ // 第一个条件
                sql.append(" WHERE ");
            } else { // 不是第一个条件
                sql.append(" AND ");
            }
            sql.append(conditions.get(i));
        }

        return sql.toString();
    }

    // 返回查询条件
    @Override
    public List<Object> getParameters(){
        return this.parameters;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMinSalePrice() {
        return minSalePrice;
    }

    public void setMinSalePrice(BigDecimal minSalePrice) {
        this.minSalePrice = minSalePrice;
    }

    public BigDecimal getMaxSalePrice() {
        return maxSalePrice;
    }

    public void setMaxSalePrice(BigDecimal maxSalePrice) {
        this.maxSalePrice = maxSalePrice;
    }
}
