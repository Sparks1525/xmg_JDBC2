package mytest.day03._05.query;

import mytest.day03._05.util.StringUtil;

import java.math.BigDecimal;

//封装所有的商品的查询信息
public class ProductQueryObject extends QueryObject {
    private String name; //商品名称
    private BigDecimal minSalePrice; //最低零售价
    private BigDecimal maxSalePrice; //最高零售价
    private Long dirId; // 商品分类编号
    private String keyword; //关键字

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

        if(dirId != null && dirId != -1){
            super.addQuery("dir_id = ?", dirId);
        }

        if(StringUtil.hasLength(keyword)){
            super.addQuery("productName LIKE ? OR brand LIKE ?", "%" + keyword + "%","%" + keyword + "%");
        }
    }
}
