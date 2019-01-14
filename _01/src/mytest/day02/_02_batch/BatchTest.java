package mytest.day02._02_batch;

import mytest.day02._02_batch.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class BatchTest {

    public void testInsertByStatementBatch() throws Exception{
        Connection conn = JdbcUtil.getConn();
        Statement st = conn.createStatement();
        long begin = System.currentTimeMillis();
        for(int i = 1; i <= 1000; i++){
            String sql = "INSERT INTO student(age) VALUE(" + i + ")";
            st.addBatch(sql); // 添加到批量处理中去
            if(i % 200 == 0){
                st.executeBatch(); //执行批量操作
                st.clearBatch(); // 清理批处理中信息
            }
        }
        System.out.println(System.currentTimeMillis() - begin);
        JdbcUtil.close(conn,st);
    }

    public void testInsertByPreparedStatementBatch() throws Exception{

        String sql = "INSERT INTO student (age) VALUE(?)";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        long begin = System.currentTimeMillis();
        for(int i = 1; i <= 1000; i++){
            ps.setInt(1, i);
            ps.addBatch(); // 添加到批量处理中去
            if(i % 200 == 0){
                ps.executeBatch(); // 执行批量操作
                ps.clearBatch(); // 清除批量处理中的缓存
                ps.clearParameters(); // 清除批量处理中的参数
            }
        }
        System.out.println(System.currentTimeMillis() - begin);
        JdbcUtil.close(conn,ps);
    }
}
