package mytest.day02._04_returnpk;


import mytest.day02._03_blob.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PrimaryKeyTest {

    public void testStatement() throws Exception {
        String sql = "INSERT INTO studnet (name) VALUES('dfgn')";
        Connection conn = JdbcUtil.getConn();
        Statement st = conn.createStatement();
        st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            Long id = rs.getLong(1); // 获取第一列的值
            System.out.println(id);
        }
        JdbcUtil.close(conn, st, rs);

    }

    public void testPreparedStatement() throws Exception {
        String sql = "INSERT INTO student(name) VALUE(?)";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, "lhc");
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            Long id = rs.getLong(1); // 获取第一列的值
            System.out.println(id);
        }
        JdbcUtil.close(conn, ps, rs);
    }
}
