package mytest.day02._06_template.dao.impl;

import mytest.day02._06_template.dao.IResultSetHandler;
import mytest.day02._06_template.dao.IStudentDAO;
import mytest.day02._06_template.domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    @Override
    public void save(Student stu) {
        String sql = "INSERT INTO student(name,age) VALUES(?,?)";
        Object[] params = {stu.getName(), stu.getAge()};
        JdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM student WHERE id = ?";
        JdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Student newStu) {
        String sql = "UPDATE student SET name = ?, age = ? WHERE id = ?";
        Object[] params = {newStu.getName(), newStu.getAge(), newStu.getId()};
        JdbcTemplate.update(sql, params);
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
            while (rs.next()) {
                Student stu = new Student();
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                Integer age = rs.getInt("age");
                stu.setId(id);
                stu.setName(name);
                stu.setAge(age);
                list.add(stu);
            }
            return list;
        }
    }
}
