package mytest.day02._07_hibernate;


import mytest.day02._07_hibernate.dao.impl.JdbcTemplate;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class HibernateTest {
    // 模拟保存操作
    // 内省机制:可以获取和操作JavaBean对象的属性
    public static void save(Object obj){
        /*
         获取表名
         拼sql
         获取beanInfo
         获取pds
         for pds
         获取属性名
         拼 属性名sb和占位符sb
         获取属性值并加入list
         删除,
         拼sql
         调用JdbcTemplate
         */
        try{
            // 获取对象对应的表名
            String tableName = obj.getClass().getSimpleName();
            Table table = obj.getClass().getAnnotation(Table.class);
            if(table != null){
                tableName = table.value();
            }
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ").append(tableName).append("(");
            StringBuilder columnSql = new StringBuilder(); //拼需要插入哪一列的SQL：name,age
            StringBuilder placeHolderSql = new StringBuilder();//拼占位符的SQL:?,?
            List<Object> params = new ArrayList<>();//装参数
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(),
                    Object.class); //Object.class 不从父类中获取父类的属性
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for(PropertyDescriptor pd : pds){
                // 对象中的属性名
                String propertyName = pd.getName();
                if(!"id".equals(propertyName)){
                    columnSql.append(propertyName).append(",");
                    placeHolderSql.append("?").append(",");
                    //获取属性的值,调用属性的getter方法
                    Object val = pd.getReadMethod().invoke(obj);
                    params.add(val);
                }
            }
            //删除最后一个
            columnSql.deleteCharAt(columnSql.length() - 1); //age,id,name
            placeHolderSql.deleteCharAt(placeHolderSql.length() - 1);
            sql.append(columnSql);
            sql.append(") VALUES (");
            sql.append(placeHolderSql);
            sql.append(")");
            System.out.println("SQL= "+sql);
            System.out.println("parmas=" + params);
            JdbcTemplate.update(sql.toString(), params.toArray());

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
