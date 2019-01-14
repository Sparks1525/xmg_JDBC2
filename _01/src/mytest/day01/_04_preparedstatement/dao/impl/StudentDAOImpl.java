package mytest.day01._04_preparedstatement.dao.impl;


import mytest.day01._04_preparedstatement.dao.IStudentDAO;
import mytest.day01._04_preparedstatement.domain.Student;
import mytest.day01._04_preparedstatement.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {


    @Override
    public void save(Student stu) {
        String sql = "INSERT INTO student(name,age) VALUE(?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,stu.getName());
            ps.setInt(2,stu.getAge());
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
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }

    }

    @Override
    public void update(Student stu) {
        String sql = "UPDATE student SET name = ?, age = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1,stu.getName());
            ps.setInt(2,stu.getAge());
            ps.setInt(3,stu.getId());
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
        ResultSet resultSet = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setAge(age);
                return student;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, resultSet);
        }
        return null;
    }

    @Override
    public List<Student> list() {
        String sql = "SELECT * FROM student";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Student> list = new ArrayList<>();
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setAge(age);
                list.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, resultSet);
        }
        return list;
    }
}
