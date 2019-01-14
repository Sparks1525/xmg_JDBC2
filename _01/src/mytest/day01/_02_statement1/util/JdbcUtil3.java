package mytest.day01._02_statement1.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtil3 {
    private JdbcUtil3(){}

    private static String driverClassName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/xmg";
    private static String username = "root";
    private static String password = "123456";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConn(){
        try {
            return DriverManager.getConnection(url,username,password);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
