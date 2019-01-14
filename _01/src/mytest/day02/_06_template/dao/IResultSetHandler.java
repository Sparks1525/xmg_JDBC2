package mytest.day02._06_template.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//处理结果集的规范
public interface IResultSetHandler<T> {
    // 处理结果集
    T handler(ResultSet rs) throws SQLException;
}
