package DAO.Impl;

import Connectivity.JDBC;
import DAO.ShopCartDAO;
import Model.ShopCart;
import WorkShop.ShopCartWS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ShopCartJDBCDAO implements ShopCartDAO {
    private final String SELECT_ALL_BY_USER_ID = "SELECT * FROM Shop_cart WHERE user_id = ?";
    private final String INSERT_BY_USER_ID = "INSERT INTO Shop_cart (user_id, product_id) VALUES (?,?)";
    private final String DELETE_ALL_PRODUCT_BY_USER_ID = "DELETE FROM Shop_cart WHERE user_id = ?";
    private final String DELETE_PRODUCT_BY_USER_ID = "DELETE FROM Shop_cart WHERE user_id = ? AND product_id = ?";

    @Override
    public List<ShopCart> getAllProductByUser(int userId) {
        List<ShopCart> list;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);
            list = ShopCartWS.createAllShopCartRS(preparedStatement.executeQuery());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void deleteAllProductByUser(int userId) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_PRODUCT_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteProductByUser(int userId, int productId) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addProductByUser(ShopCart shopCart) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BY_USER_ID)) {
            preparedStatement.setInt(1, shopCart.getUserId());
            preparedStatement.setInt(2, shopCart.getProductId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
