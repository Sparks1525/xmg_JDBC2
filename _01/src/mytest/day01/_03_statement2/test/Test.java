package mytest.day01._03_statement2.test;


import mytest.day01._03_statement2.dao.IStudentDAO;

public class Test {
    public static void main(String[] args) {
        IStudentDAO dao = new StudentDAOImpl();
        System.out.println(dao.get(1));

    }
}
