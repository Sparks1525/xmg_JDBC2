package mytest.day04._02.test;

import mytest.day04._02.dao.IResultSetHandler;
import mytest.day04._02.dao.impl.JdbcTemplate;
import mytest.day04._02.dao.impl.ProductDAOImpl;
import mytest.day04._02.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Test {


//    public void testPage1() throws Exception{
//        //用户传入的
//        Integer currentPage = 1;
//        Integer pageSize = 10;
//
//
//        //查结果集 SELECT * FROM 表名 LIMIT beginIndex ,pageSize;
//        String baseSql = "SELECT * FROM product LIMIT ?,?";
//        List<Product> list = JdbcTemplate.query(
//                baseSql, //查询结果集的SQL
//                new ProductResultSetHandler(), // 结果集处理器
//                (currentPage - 1) * pageSize,//开始索引
//                pageSize); //每页最多条数
//
//
//        // 查结果总数:
//        String  countSql = "SELECT COUNT(*) FROM product";
//        Integer totalCount = JdbcTemplate.query(
//                countSql, //查询结果总数SQL
//                new IResultSetHandler<Long>() {
//                    @Override
//                    public Long handler(ResultSet rs) throws SQLException {
//                        if(rs.next()){
//                            return rs.getLong(1);
//                        }
//                        return 0L;
//                    }
//                }
//        )
//
//
//        System.out.println(totalCount);
//        for(Product p : list){
//            System.out.println(p);
//        }
//    }
}
