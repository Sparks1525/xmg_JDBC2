package mytest.day03._05.dao.impl;

import mytest.day03._05.dao.IProductDAO;
import mytest.day03._05.dao.IProductDirDAO;
import mytest.day03._05.dao.IResultSetHandler;
import mytest.day03._05.domain.Product;
import mytest.day03._05.domain.ProductDir;
import mytest.day03._05.query.ProductQueryObject;
import mytest.day03._05.util.StringUtil;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAOImpl implements IProductDAO {


    /**
     * 高级查询
     *
     * @param name         商品名称模糊查询  productName LIKE '%name@'
     * @param minSalePrice 查询商品零售价大于等于最低价格 salePrice >= minSalePrice
     * @param maxSalePrice 查询商品零售价小于等于最高价格 salePrice >= maxSalePrice
     * @return 符合条件的结果集
     */
    public List<Product> query(String name, BigDecimal minSalePrice,
                               BigDecimal maxSalePrice) {
        StringBuilder sql = new StringBuilder("SELECT * FROM product WHERE 1=1");
        //使用List来封装查询的参数(List允许元素重复并且记录添加顺序)
        List<Object> params = new ArrayList<>();
        //商品的名称
        if (StringUtil.hasLength(name)) {
            sql.append(" AND productName LIKE ?");
            params.add("%" + name + "%");
        }
        //最低零售价
        if (minSalePrice != null) {
            sql.append(" AND salePrice >= ?");
            params.add(minSalePrice);
        }
        //最高零售价
        if (maxSalePrice != null) {
            sql.append(" AND salePrice <= ?");
            params.add(maxSalePrice);
        }
        return JdbcTemplate.query(sql.toString(), new ProductResultSetHandler(), params.toArray());
    }


    public List<Product> query2(String name, BigDecimal minSalePrice,
                                BigDecimal maxSalePrice) {
        StringBuilder sql = new StringBuilder();
        //使用List来封装查询的参数(List允许元素重复并且记录添加顺序)
        List<Object> params = new ArrayList<>();
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
            params.add("%" + name + "%");
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
            params.add(minSalePrice);
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
            params.add(maxSalePrice);
        }
        return JdbcTemplate.query(sql.toString(), new ProductResultSetHandler(), params.toArray());

    }


    public List<Product> query4(ProductQueryObject qo){
        String sql = "SELECT * FROM product" + qo.getQuery();
        return JdbcTemplate.query(sql,new ProductResultSetHandler(), qo.getParameters().toArray());
    }


    @Override
    public void save(Product obj) {
        String sql = "INSERT INTO product(productName,brand,supplier,salePrice,costPrice,cutoff,dir_id) VALUES(?,?,?,?,?,?,?)";
        Object[] params = {obj.getProductName(), obj.getBrand(),
                obj.getSupplier(), obj.getSalePrice(), obj.getCostPrice(),
                obj.getCutoff(), obj.getDir().getId()};
        JdbcTemplate.save(sql, params);
    }

    @Override
    public void update(Product obj) {
        String sql = "UPDATE product SET productName = ?,brand= ?, supplier = ?, salePrice = ?,costPrice = ?, cutoff = ?, dir_id = ? WHERE id = ?";
        Object[] params = {obj.getProductName(), obj.getBrand(),
                obj.getSupplier(), obj.getSalePrice(), obj.getCostPrice(),
                obj.getCutoff(), obj.getDir().getId(), obj.getId()};
        JdbcTemplate.save(sql, params);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        JdbcTemplate.save(sql, id);
    }

    @Override
    public Product get(Long id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        List<Product> list = JdbcTemplate.query(sql, new ProductResultSetHandler(), id);
        return list.size() == 1 ? list.get(0) : null;
    }

    @Override
    public List<Product> list() {
        String sql = "SELECT * FROM product";
        return JdbcTemplate.query(sql, new ProductResultSetHandler());
    }


    class ProductResultSetHandler implements IResultSetHandler<List<Product>> {
        private IProductDirDAO dirDAO = new ProductDirDAOImpl();

        //缓存商品的分类对象
        //key:商品分类的id
        //value:商品分类对象
        private Map<Long, ProductDir> cache = new HashMap<>();

        @Override
        public List<Product> handler(ResultSet rs) throws SQLException {
            List<Product> list = new ArrayList<>();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setProductName(rs.getString("productName"));
                p.setBrand(rs.getString("brand"));
                p.setSupplier(rs.getString("supplier"));
                p.setSalePrice(rs.getBigDecimal("salePrice"));
                p.setCostPrice(rs.getBigDecimal("costPrice"));
                p.setCutoff(rs.getDouble("cutoff"));
                Long dirId = rs.getLong("dir_id");// 商品分类的id
                //判断缓存中是否有dirId对应的对象
                ProductDir dir = cache.get(dirId);
                if (dir == null) {
                    // 缓存中没有,第一次查询该分类
                    dir = dirDAO.get(dirId);
                    cache.put(dirId, dir);
                }
                p.setDir(dir);
                list.add(p);
            }
            return list;
        }
    }
}
