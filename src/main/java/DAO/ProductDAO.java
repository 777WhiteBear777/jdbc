package DAO;

import Model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAllProduct();

    Product addProduct(Product product);

    int getProduct(int id);

    void updateProduct(Product product);

    void deleteProduct(int id);
}
