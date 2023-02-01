package DAO.Impl;

import Connectivity.JDBC;
import DAO.ShopCartDAO;
import Model.ShopCart;
import WorkShop.ShopCartWS;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ShopCartJDBCDAO implements ShopCartDAO {
    private final String SELECT_ALL_BY_USER_ID = "SELECT * FROM Shop_cart WHERE user_id = ?";
    private final String INSERT = "INSERT INTO Shop_cart (user_id, product_id) VALUES (?,?)";
    private final String DELETE_ALL_PRODUCT_BY_USER_ID = "DELETE FROM Shop_cart WHERE user_id = ?";
    private final String DELETE_PRODUCT_BY_USER_ID = "DELETE FROM Shop_cart WHERE user_id = ? AND product_id = ?";

    @Test
    @Override
    public List<ShopCart> getAllProductByUser(Long userId) {
        List<ShopCart> list;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            list = ShopCartWS.createAllShopCartRS(preparedStatement.executeQuery());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    @Override
    public void deleteAllProductByUser(Long userId) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_PRODUCT_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteShopCart(Long userId, Long productId) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, productId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addShopCart(ShopCart shopCart) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setLong(1, shopCart.getUserId());
            preparedStatement.setLong(2, shopCart.getProductId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
