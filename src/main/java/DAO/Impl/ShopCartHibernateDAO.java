package DAO.Impl;

import DAO.ShopCartDAO;
import Model.ShopCart;

import java.util.List;

public class ShopCartHibernateDAO implements ShopCartDAO {
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
    public void addProductByUser(ShopCart shopCart) {
    }
}
