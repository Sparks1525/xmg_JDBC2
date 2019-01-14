package mytest.day03._04.query;

import mytest.day03._04.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductQueryObject extends QueryObject {
    private String name;
    private BigDecimal minSalePrice;
    private BigDecimal maxSalePrice;

    //自身对象的定义查询
    public  void customizedQuery(){
        if(StringUtil.hasLength(name)){
            super.addQuery("productName LIKE ?","%" + name + "%");
        }
        if(minSalePrice != null){
            super.addQuery("salePrice >= ?",minSalePrice);
        }

        if(maxSalePrice != null){
            super.addQuery("salePrice <= ?",maxSalePrice);
        }
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
