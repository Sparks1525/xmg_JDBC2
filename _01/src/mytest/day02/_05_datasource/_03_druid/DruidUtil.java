package mytest.day02._05_datasource._03_druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidUtil {
    private DruidUtil(){}
    private static Properties p = new Properties();
    private static DataSource dataSource = null;
    static {
        try {
            // 从classpath的根路径去寻找druid.properties文件
            InputStream inputStream = Thread.currentThread().
                    getContextClassLoader().
                    getResourceAsStream("druid.properties");
            p.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(p);
        }catch (Exception e){
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
