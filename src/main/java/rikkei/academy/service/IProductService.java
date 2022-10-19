package rikkei.academy.service;

import rikkei.academy.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    void save(Product product);
    void delete(int id);
    List<Product> findByName(String name);
    void update(int id, Product product);
    Product findById(int id);
}
