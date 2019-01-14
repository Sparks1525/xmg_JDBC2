package mytest.day04._02.dao;

import mytest.day04._02.domain.ProductDir;

import java.util.List;

public interface IProductDirDAO {
    void save(ProductDir obj);
    void update(ProductDir obj);
    void delete(Long id);
    ProductDir get(Long id);
    List<ProductDir> list();

}
