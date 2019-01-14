package mytest.day02._05_datasource._02_c3p0;

import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;
import java.sql.Connection;

public class C3p0Util {

    private C3p0Util(){}
    private static DataSource dataSource = null;
    /*
        1):配置文件名称,必须叫做:c3p0.properties或者c3p0-config.xml.
        2):c3p0.properties文件必须存放与classpath的根路径.
        3):要求c3p0.properties文件中所有的key必须以c3p0.作为前缀,如:c3p0.jdbcUrl.
        4):要求c3p0.properties文件中的key,必须是ComboPooledDataSource对象中的属性名.
     */

    static {
        try {
            // c3p0内部:知道从classpath的根路径去读取c3p0.properties文件
            DataSource ds_unpooled = DataSources.unpooledDataSource();
            dataSource = DataSources.pooledDataSource(ds_unpooled);

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
