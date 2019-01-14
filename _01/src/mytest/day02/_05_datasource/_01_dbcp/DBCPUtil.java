package mytest.day02._05_datasource._01_dbcp;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DBCPUtil {

    private DBCPUtil(){}

    // key名必须等于BasicDataSource中的属性名(由setter方法决定)

    private static Properties p = new Properties();
    private static DataSource dataSource = null;

    static {
        try {
            // 从classpath的根路径去寻找dbcp.properties文件
            InputStream inputStream = Thread.currentThread().
                    getContextClassLoader().
                    getResourceAsStream("dbcp.properties");
            p.load(inputStream);
            dataSource = BasicDataSourceFactory.createDataSource(p);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConn(){
        try{
            return dataSource.getConnection();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
