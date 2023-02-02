package dao;

import model.ShoppingCart;

import java.util.List;

public interface ShopCartDAO {

    List<ShoppingCart> getAllProductByUser(Long userId);

    void deleteAllProductByUser(Long userId);

    void deleteShopCart(Long userId, Long productId);

    void addShopCart(ShoppingCart shopCart);

}
