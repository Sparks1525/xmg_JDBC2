package mytest.day02._06_template.dao.impl;

import mytest.day02._06_template.dao.IResultSetHandler;
import mytest.day02._06_template.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcTemplate {

    // 命令设计模式

    // DML
    public static int update(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            // 设置占位符参数
            for(int index = 0; index < params.length; index++){
                ps.setObject(index + 1, params[index]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
        return 0;
    }

    // DQL
    public static <T> T query(String sql, IResultSetHandler<T> rsh, Object... params){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            // 设置占位符参数
            for(int index = 0; index < params.length; index++){
                ps.setObject(index + 1, params[index]);
            }
            rs = ps.executeQuery();
            // 处理结果集
            return rsh.handle(rs);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }
}
