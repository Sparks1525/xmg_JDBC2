package mytest.day02._07_hibernate.dao.impl;

import mytest.day02._07_hibernate.dao.IResultSetHandler;
import mytest.day02._07_hibernate.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcTemplate {
    private JdbcTemplate() {
    }

    public static int update(String sql, Object... obj) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            for (int index = 0; index < obj.length; index++) {
                ps.setObject(index + 1, obj[index]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
        return 0;
    }

    public static <T> T query(String sql, IResultSetHandler<T> rsh, Object... obj){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            for(int index = 0; index< obj.length; index++){
                ps.setObject(index+1, obj[index]);
            }
            rs = ps.executeQuery();
            return rsh.handler(rs);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
        }
        return null;
    }

}
