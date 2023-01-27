package DAO.Impl;

import DAO.CommonDAO;
import DAO.ShopCartDAO;
import Model.ShopCart;

import java.util.List;

public class ShopCartJDBCDAO implements ShopCartDAO {
    @Override
    public List<ShopCart> getAllProductByUser(int userId) {
        return null;
    }

    @Override
    public void deleteAllProductByUser(int userId) {

    }

    @Override
    public void deleteProductByUser(int userId, int productId) {

    }

    @Override
    public ShopCart addProductByUser(ShopCart shopCart) {
        return null;
    }
}
