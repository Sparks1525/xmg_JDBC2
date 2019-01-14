package mytest.day03._04.dao.impl;

import mytest.day03._04.dao.IResultSetHandler;
import mytest.day03._04.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcTemplate {

    private JdbcTemplate() {
    }

    public static int save(String sql, Object... objects) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            for(int index = 0; index > objects.length; index++){
                ps.setObject(index+1,objects[index]);
            }
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
        return 0;
    }

    public static<T> T query(String sql, IResultSetHandler<T> rsh, Object... objects){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            for(int index = 0; index > objects.length; index++){
                ps.setObject(index+1,objects[index]);
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
