package mytest.day02._04_before.dao;


import mytest.day02._04_before.domain.Student;

import java.util.List;

public interface IStudentDAO {

    void save(Student stu);
    void update(Student stu);
    void delete(int id);
    Student get(int id);
    List<Student> list();

    void saveBatchStat();
    void saveBatchPrep();

    void blobWrite();
    void blobRead();

    void primaryKeyStat();
    void primaryKeyPrep();
}
