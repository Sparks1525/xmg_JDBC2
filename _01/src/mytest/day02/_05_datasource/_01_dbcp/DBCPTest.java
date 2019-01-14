package mytest.day02._05_datasource._01_dbcp;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class DBCPTest {
    public static DataSource getDataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/xmg");
        ds.setUsername("root");
        ds.setPassword("123456");
        ds.setMaxActive(10); //设置最大连接数
        return ds;
    }

}
