package mytest.day02._05_datasource._02_c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;

public class C3P0Test {
    public static DataSource getDataSource() throws Exception{
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/xmg");
        cpds.setUser("root");
        cpds.setPassword("123456");
        cpds.setMaxPoolSize(10); // 设置最大连接数
        return cpds;
    }
}
