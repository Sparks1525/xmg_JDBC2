package mytest.day04._02.dao.impl;

import mytest.day04._02.dao.IProductDirDAO;
import mytest.day04._02.dao.IResultSetHandler;
import mytest.day04._02.domain.ProductDir;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDirDAOImpl implements IProductDirDAO {
    @Override
    public void save(ProductDir obj) {
        String sql = "INSERT INTO productdir(dirName,parent_id) VALUES(?,?)";
        Object[] objects = {obj.getDirName(),obj.getParent_id()};
        mytest.day03._01.dao.impl.JdbcTemplate.save(sql,objects);
    }

    @Override
    public void update(ProductDir obj) {
        String sql = "UPDATE productdir SET dirName = ?,parent_id = ? WHERE id = ?";
        Object[] params = {obj.getDirName(), obj.getParent_id(), obj.getId()};
        mytest.day03._01.dao.impl.JdbcTemplate.save(sql, params);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM productdir WHERE id = ?";
        mytest.day03._01.dao.impl.JdbcTemplate.save(sql, id);
    }

    @Override
    public ProductDir get(Long id) {
        String sql = "SELECT * FROM productdir WHERE id = ?";
        List<ProductDir> list = JdbcTemplate.query(sql, new ProductDirResultSetHandler(), id);
        return list.size() == 1 ? list.get(0) : null;
    }

    @Override
    public List<ProductDir> list() {
        String sql = "SELECT * FROM productdir";
        return JdbcTemplate.query(sql, new ProductDirResultSetHandler());
    }

    class ProductDirResultSetHandler implements IResultSetHandler<List<ProductDir>> {

        @Override
        public List<ProductDir> handler(ResultSet rs) throws SQLException {
            List<ProductDir> list = new ArrayList<>();
            while (rs.next()){
                ProductDir dir = new ProductDir();
                dir.setId(rs.getLong("id"));
                dir.setDirName(rs.getString("dirName"));
                dir.setParent_id(rs.getLong("parent_id"));
                list.add(dir);
            }
            return list;
        }
    }
}
