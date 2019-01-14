package mytest.day01._02_statement1.test;


import mytest.day01._02_statement1.dao.IStudentDAO;
import mytest.day01._02_statement1.dao.impl.StudentDAOImpl;

public class Test {
    public static void main(String[] args) {
        IStudentDAO dao = new StudentDAOImpl();
        System.out.println(dao.get(1));

    }
}
