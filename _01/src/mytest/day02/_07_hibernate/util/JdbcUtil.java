package mytest.day02._07_hibernate.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
    private JdbcUtil(){}
    private static Properties p = new Properties();
    private static DataSource dataSource = null;

    static {
        try {
            InputStream inputStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("druid.properties");
            p.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(p);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConn(){
        try {
            return dataSource.getConnection();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection conn, Statement st, ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(st != null){
                    st.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
