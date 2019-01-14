package mytest.day03._01.dao.impl;

import mytest.day03._01.dao.IProductDAO;
import mytest.day03._01.dao.IProductDirDAO;
import mytest.day03._01.dao.IResultSetHandler;
import mytest.day03._01.domain.Product;
import mytest.day03._01.domain.ProductDir;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAOImpl implements IProductDAO {
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
