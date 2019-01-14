package mytest.day03._04.dao;

import mytest.day03._04.domain.ProductDir;

import java.util.List;

public interface IProductDirDAO {
    void save(ProductDir obj);
    void update(ProductDir obj);
    void delete(Long id);
    ProductDir get(Long id);
    List<ProductDir> list();

}
