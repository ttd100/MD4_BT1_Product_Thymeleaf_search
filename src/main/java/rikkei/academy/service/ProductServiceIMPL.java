package rikkei.academy.service;

import org.springframework.stereotype.Service;
import rikkei.academy.model.Product;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceIMPL implements IProductService {
    public static  List<Product> productList = new ArrayList<>();
    private static int lastId = 0;
    static {
        productList.add(new Product(1, "one", "so 1", 200000));
        lastId++;
        productList.add(new Product(2, "two", "so2", 220000));
        lastId++;
        productList.add(new Product(3, "three", "so 3", 250000));
        lastId++;
        productList.add(new Product(4, "for", "so 4", 260000));
        lastId++;

    }
    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        lastId++;
        product.setId(lastId);
        productList.add(product);
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                productList.remove(i);
            }
        }
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().toLowerCase().contains(name.toLowerCase())){
                products.add(productList.get(i));
            }
        }
        return products;
    }

    @Override
    public void update(int id, Product product) {
        productList.set(product.getId()-1,product);
    }


    @Override
    public Product findById(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                return productList.get(i);
            }
        }
        return null;
    }
}
