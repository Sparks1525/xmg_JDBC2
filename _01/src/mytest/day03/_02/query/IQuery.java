package mytest.day03._02.query;

import java.util.List;

// 表示查询对象的规范
public interface IQuery {
    //返回拼接好查询条件的SQL;
    String getQuery();

    //返回getQuery方法拼接查询SQL中占位符对应的参数
    List<Object> getParameters();

}
