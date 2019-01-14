package mytest.day02._07_hibernate.dao.impl;

import mytest.day02._07_hibernate.dao.IResultSetHandler;
import mytest.day02._07_hibernate.dao.IStudentDAO;
import mytest.day02._07_hibernate.domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    @Override
    public void save(Student stu) {
        String sql = "INSERT INTO student(name,age) VALUES(?,?)";
        Object[] obj = {stu.getName(),stu.getAge()};
        JdbcTemplate.update(sql,obj);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM student WHERE id = ?";
        JdbcTemplate.update(sql,id);
    }

    @Override
    public void update(Student stu) {
        String sql = "UPDATE student SET name = ?,age = ? WHERE id = ?";
        Object[] obj = {stu.getName(), stu.getAge(), stu.getId()};
        JdbcTemplate.update(sql,obj);
    }

    @Override
    public Student get(Long id) {
        String sql = "SELECT * FROM student WHERE id = ?";
        List<Student> list = JdbcTemplate.query(sql, new StudentResultSetHandler(), id);
        return list.size() == 1 ? list.get(0) : null;
    }

    @Override
    public List<Student> list() {
        String sql = "SELECT * FROM student";
        return JdbcTemplate.query(sql, new StudentResultSetHandler());
    }

    class StudentResultSetHandler implements IResultSetHandler<List<Student>> {
        @Override
        public List<Student> handler(ResultSet rs) throws SQLException {
            List<Student> list = new ArrayList<>();
            while (rs.next()){
                Student stu = new Student();
                stu.setId(rs.getLong("id"));
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("int"));
                list.add(stu);
            }
            return list;
        }
    }
}
