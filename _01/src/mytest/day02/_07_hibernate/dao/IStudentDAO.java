package mytest.day02._07_hibernate.dao;


import mytest.day02._07_hibernate.domain.Student;

import java.util.List;

public interface IStudentDAO {
    void save(Student stu);
    void delete(Long id);
    void update(Student stu);
    Student get(Long id);
    List<Student> list();
}
