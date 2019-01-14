package mytest.day02._03_blob;


import mytest.day02._03_blob.util.JdbcUtil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BigDataTest {

    // 需求:把C:/outman.png的数据存储到数据库表中
    public void testWrite() throws Exception {
        String sql = "INSERT INTO image (headImg) VALUE(?)";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBlob(1, new FileInputStream("C:/outman.png"));
        ps.executeUpdate();
        JdbcUtil.close(conn, ps);
    }


    // 需求:把数据库表中的二进制数据读取出来-程序->存储
    public void testRead() throws Exception {
        String sql = "SELECT * FROM image WHERE id = ?";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, 1L);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Blob image = rs.getBlob("headImg");
            InputStream in = image.getBinaryStream();
            Files.copy(in, Paths.get("C:/123.png"));
        }
        JdbcUtil.close(conn, ps, rs);
    }

}
