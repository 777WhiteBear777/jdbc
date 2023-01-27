package DAO;

import Model.ShopCart;

import java.util.List;

public interface ShopCartDAO {
    List<ShopCart> getAllProductByUser(int userId);
    void deleteAllProductByUser(int userId);
    void deleteProductByUser(int userId ,int productId);
    ShopCart addProductByUser(ShopCart shopCart);

}
