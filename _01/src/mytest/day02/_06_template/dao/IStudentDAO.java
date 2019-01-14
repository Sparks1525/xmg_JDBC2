package mytest.day02._06_template.dao;

import mytest.day02._06_template.domain.Student;

import java.util.List;

public interface IStudentDAO {
    void save(Student stu);
    void delete(Long id);
    void update(Student newStu);
    Student get(Long id);
    List<Student> list();
}
