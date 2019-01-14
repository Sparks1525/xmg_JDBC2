package mytest.day01._04_preparedstatement.dao;

import mytest.day01._04_preparedstatement.domain.Student;

import java.util.List;

public interface IStudentDAO {

    void save(Student stu);

    void delete(int id);

    void update(Student stu);

    Student get(int id);

    List<Student> list();

}
