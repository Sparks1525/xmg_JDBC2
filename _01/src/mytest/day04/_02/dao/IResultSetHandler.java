package mytest.day04._02.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultSetHandler<T> {
    T handler(ResultSet rs) throws SQLException;
}
