package mytest.day01._02_statement1.dao.impl;

import mytest.day01._02_statement1.dao.IStudentDAO;
import mytest.day01._02_statement1.domain.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {

    private String driverClassName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/xmg";
    private String username = "root";
    private String password = "123456";


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
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, username, password);
            st = conn.createStatement();
            st.executeUpdate(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM student WHERE id = " + id;

        Connection conn = null;
        Statement st = null;

        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url,username,password);
            st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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
        try{
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url,username,password);
            st = conn.createStatement();
            st.executeUpdate(sb.toString());
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(st != null){
                    st.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Student get(int id) {

        String sql = "SELECT * FROM student WHERE id = " + id;

        Connection conn = null;
        Statement st = null;
        ResultSet resultSet = null;
        try{
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url,username,password);
            st = conn.createStatement();
            resultSet = st.executeQuery(sql);

            if(resultSet.next()){
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setAge(age);
                return student;
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    if(st != null){
                        st.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    try{
                        if(conn != null){
                            conn.close();
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
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

        try{
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url,username,password);
            st = conn.createStatement();
            resultSet = st.executeQuery(sql);
            while (resultSet.next()){

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setAge(age);
                list.add(student);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    if(st != null){
                        st.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    try {
                        if(conn != null){
                            conn.close();
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return list;
    }
}
