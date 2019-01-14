package mytest.day02._01_tx;

import mytest.day02._01_tx.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionTest {
    public void testTrans() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConn();
            conn.setAutoCommit(false); // 取消事务的自动提交机制
            String sql = "SELECT * FROM account WHERE name = ? AND balance >= ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "zwj");
            ps.setInt(2, 1000);
            rs = ps.executeQuery();

            if (!rs.next()) {
                throw new RuntimeException("余额不足10000");
            }

            sql = "UPDATE account SET balace = balance - ? WHERE name = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1000);
            ps.setString(2, "zwj");
            ps.executeUpdate();
            System.out.println(1 / 0); // 模拟停电
            sql = "UPDATE account SET balance = balance + ? WHERE name = ?";
            ps.setInt(1, 1000);
            ps.setString(2, "zm");
            ps.executeUpdate();
            conn.commit();// 提交事务
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback(); //回滚事务
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }
    }
}
