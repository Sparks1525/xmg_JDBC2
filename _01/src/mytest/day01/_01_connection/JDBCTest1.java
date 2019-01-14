package mytest.day01._01_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest1 {

    @org.junit.Test
    public void testJDBC1() throws Exception {
        String sql = "CREATE TABLE `student` (`id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT," +
                "`name` VARCHAR(20) NOT NULL, `age` INT(20) UNIQUE)";
        String url = "jdbc:mysql://localhost:3306/xmg";
        String username = "root";
        String password = "123456";

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement st = conn.createStatement();
        st.executeUpdate(sql);
        st.close();
        conn.close();
    }

    @org.junit.Test
    public void testJDBC2() {
        String sql = "CREATE TABLE `student` (`id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT," +
                "`name` VARCHAR(20) NOT NULL, `age` INT(20) NOT NULL)";
        String url = "jdbc:mysql://localhost:3306/xmg";
        String username = "root";
        String password = "123456";

        Connection conn = null;
        Statement st = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @org.junit.Test
    public void testJDBC3() {

        String sql = "CREATE TABLE `student` (`id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT," +
                "`name` VARCHAR(20) NOT NULL)";
        String url = "com.mysql.jdbc.Driver";
        String username = "root";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement st = conn.createStatement();) {
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @org.junit.Test
    public void testJDBC4() {
        String url = "jdbc:mysql://localhost:3306/xmg";
        String sql = "INSERT INTO student(name,age) VALUE ('ximen',34)";
        String username = "root";
        String password = "123456";

        Connection conn = null;
        Statement st = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @org.junit.Test
    public void testJDBC5() {
        String sql = "UPDATE student SET name = 'qiaofeng',age = '30' WHERE id = 2";
        String url = "jdbc:mysql://localhost:3306/xmg";
        String username = "root";
        String password = "123456";

        Connection conn = null;
        Statement st = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @org.junit.Test
    public void testJDBC6() {

        String sql = "DELETE FROM student WHERE id = 2";
        String url = "jdbc:mysql://localhost:3306/xmg";
        String username = "root";
        String password = "123456";

        Connection conn = null;
        Statement st = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @org.junit.Test
    public void testJDBC7() {

        String sql = "SELECT count(id) num FROM product";
        String url = "jdbc:mysql://localhost:3306/xmg";
        String username = "root";
        String password = "123456";

        Connection conn = null;
        Statement st = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            st = conn.createStatement();
            resultSet = st.executeQuery(sql);
            if (resultSet.next()) {
                int num = resultSet.getInt("num");
                System.out.println("num:" + num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @org.junit.Test
    public void testJDBC8() {

        String sql = "SELECT * FROM product WHERE id = 10";
        String url = "jdbc:mysql://localhost:3306/xmg";
        String username = "root";
        String password = "123456";

        Connection conn = null;
        Statement st = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            st = conn.createStatement();
            resultSet = st.executeQuery(sql);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("productName");
                System.out.println("id:" + id);
                System.out.println("productName:" + productName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @org.junit.Test
    public void testJDBC9() {

        String sql = "SELECT * FROM product";
        String url = "jdbc:mysql://localhost:3306/xmg";
        String username = "root";
        String password = "123456";

        Connection conn = null;
        Statement st = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            st = conn.createStatement();
            resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("productName");
                System.out.println("id:" + id + ",productName:" + productName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if(resultSet != null){
                    resultSet.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
