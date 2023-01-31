package DAO.Impl;

import DAO.ShopCartDAO;
import Model.ShopCart;

import java.util.List;

public class ShopCartJDBCDAO implements ShopCartDAO {
    private final String SELECT_ALL = "SELECT * FROM product";
    private final String SELECT_BY_ID = "SELECT * FROM product WHERE id = ?";
    private final String INSERT = "INSERT INTO product (category, name, price) VALUES (?,?,?)";
    private final String UPDATE = "UPDATE product SET category = ?, name = ?, price = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM product WHERE id = ?";

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
