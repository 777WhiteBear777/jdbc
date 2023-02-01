package DAO;

import Model.ShopCart;

import java.util.List;

public interface ShopCartDAO {
    List<ShopCart> getAllProductByUser(Long userId);

    void deleteAllProductByUser(Long userId);

    void deleteShopCart(Long userId, Long productId);

    void addShopCart(ShopCart shopCart);

}
