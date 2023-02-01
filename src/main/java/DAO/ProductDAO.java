package DAO;

import Model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAllProduct();

    Product addProduct(Product product);

    Product getProduct(Long id);

    void updateProduct(Product product);

    void deleteProduct(Long id);
}
