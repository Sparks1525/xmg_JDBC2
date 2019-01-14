package mytest.day02._04_before.dao.impl;


import mytest.day02._04_before.JdbcUtil.JdbcUtil;
import mytest.day02._04_before.dao.IStudentDAO;
import mytest.day02._04_before.domain.Student;
import org.junit.runner.Result;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    @Override
    public void save(Student stu) {
        String sql = "INSERT INTO student(name,age) VALUES (?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, stu.getName());
            ps.setInt(2, stu.getAge());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
    }

    @Override
    public void update(Student stu) {
        String sql = "UPDATE student SET name = ?,age = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, stu.getName());
            ps.setInt(2, stu.getAge());
            ps.setInt(3, stu.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM student WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
    }

    @Override
    public Student get(int id) {
        String sql = "SELECT * FROM student WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConn();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Student stu = new Student();
                stu.setId(rs.getInt("id"));
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("age"));
                return stu;
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public List<Student> list() {
        String sql = "SELECT * FROM student";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();
        try {
            conn = JdbcUtil.getConn();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student stu = new Student();
                stu.setId(rs.getInt("id"));
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("age"));
                list.add(stu);
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public void saveBatchStat() {
        Connection conn = null;
        Statement st = null;
        try {
            conn = JdbcUtil.getConn();
            st = conn.createStatement();

            for (int i = 0; i < 1000; i++) {
                String sql = "INSERT INTO student(name) VALUES (" + i + ")";
                st.addBatch(sql);
                if (i % 200 == 0) {
                    st.executeBatch();
                    st.clearBatch();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, st, null);
        }
    }

    @Override
    public void saveBatchPrep() {
        String sql = "INSERT INTO student (age) VALUES (?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < 1000; i++) {
                ps.setInt(1, i);
                ps.addBatch();
                if (i % 200 == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                    ps.clearParameters();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }

    }

    @Override
    public void blobWrite() {
        String sql = "INSERT INTO image(headImp) VALUES (?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setBlob(1, new FileInputStream("C:/xxx.png"));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
    }

    @Override
    public void blobRead() {
        String sql = "SELECT * FROM image WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, 1L);
            rs = ps.executeQuery();
            Blob image = rs.getBlob("headImp");
            InputStream in = image.getBinaryStream();
            Files.copy(in, Paths.get("C:/xxx.png"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }
    }
    
    @Override
    public void primaryKeyStat() {
        String sql = "INSERT INTO student(age) VALUES (" + 1 + ")";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConn();
            st = conn.createStatement();
            st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            rs = st.getGeneratedKeys();
            if(rs.next()){
                Long key = rs.getLong(1);
                System.out.println(key);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,st,rs);
        }
    }

    @Override
    public void primaryKeyPrep() {
        String sql = "INSERT INTO student(age) VALUES (?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,1);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                Long key = rs.getLong(1);
                System.out.println(key);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps ,rs);
        }

    }
}
