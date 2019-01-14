package mytest.day01._04_preparedstatement.test;


import mytest.day01._04_preparedstatement.dao.IStudentDAO;

public class Test {
    public static void main(String[] args) {
        IStudentDAO dao = new StudentDAOImpl();
        System.out.println(dao.get(1));

    }
}
