package mytest.day02._07_hibernate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultSetHandler<T> {
    T handler(ResultSet rs) throws SQLException;
}
