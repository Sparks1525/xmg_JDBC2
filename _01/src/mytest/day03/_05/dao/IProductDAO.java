package mytest.day03._05.dao;

import mytest.day03._05.domain.Product;

import java.util.List;

public interface IProductDAO {
    void save(Product product);
    void update(Product product);
    void delete(Long id);
    Product get(Long id);
    List<Product> list();
}
