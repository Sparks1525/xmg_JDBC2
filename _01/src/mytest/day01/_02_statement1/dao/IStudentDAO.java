package mytest.day01._02_statement1.dao;

import mytest.day01._02_statement1.domain.Student;

import java.util.List;

public interface IStudentDAO {

    void save(Student stu);

    void delete(int id);

    void update(Student stu);

    Student get(int id);

    List<Student> list();

}
