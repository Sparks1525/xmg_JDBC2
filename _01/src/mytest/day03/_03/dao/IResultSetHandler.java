package mytest.day03._03.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultSetHandler<T> {
    T handler(ResultSet rs) throws SQLException;
}
