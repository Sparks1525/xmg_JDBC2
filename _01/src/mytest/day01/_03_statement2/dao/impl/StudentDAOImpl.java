package mytest.day01._03_statement2.dao.impl;

import mytest.day01._03_statement2.dao.IStudentDAO;
import mytest.day01._03_statement2.domain.Student;
import mytest.day01._03_statement2.util.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {


    @Override
    public void save(Student stu) {

        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO student(name,age) VALUE(");
        sb.append("'").append(stu.getName()).append("'").append(",");
        sb.append(stu.getAge());
        sb.append(")");

        Connection conn = null;
        Statement st = null;
        try {
            conn = JdbcUtil.getConn();
            st = conn.createStatement();
            st.executeUpdate(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, st, null);
        }
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM student WHERE id = " + id;
        Connection conn = null;
        Statement st = null;
        try {
            conn = JdbcUtil.getConn();
            st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, st, null);
        }

    }

    @Override
    public void update(Student stu) {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE student SET name = ");
        sb.append("'").append(stu.getName()).append("'");
        sb.append(",age = ").append(stu.getAge());
        sb.append(" WHERE id = ").append(stu.getId());

        Connection conn = null;
        Statement st = null;
        try {
            conn = JdbcUtil.getConn();
            st = conn.createStatement();
            st.executeUpdate(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, st, null);
        }
    }

    @Override
    public Student get(int id) {
        String sql = "SELECT * FROM student WHERE id = " + id;
        Connection conn = null;
        Statement st = null;
        ResultSet resultSet = null;
        try {
            conn = JdbcUtil.getConn();
            st = conn.createStatement();
            resultSet = st.executeQuery(sql);

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
            JdbcUtil.close(conn, st, resultSet);
        }
        return null;
    }

    @Override
    public List<Student> list() {
        String sql = "SELECT * FROM student";
        Connection conn = null;
        Statement st = null;
        ResultSet resultSet = null;
        List<Student> list = new ArrayList<>();
        try {
            conn = JdbcUtil.getConn();
            st = conn.createStatement();
            resultSet = st.executeQuery(sql);
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
            JdbcUtil.close(conn, st, resultSet);
        }
        return list;
    }
}
