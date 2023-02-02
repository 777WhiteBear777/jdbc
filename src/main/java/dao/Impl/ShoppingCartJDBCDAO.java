package dao.Impl;

import connectivity.JDBC;
import dao.ShopCartDAO;
import model.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import workShop.ShoppingCartWS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ShoppingCartJDBCDAO implements ShopCartDAO {

    private final Logger LOGGER = LogManager.getLogger(JDBC.class.getName());

    private final String SELECT_ALL_BY_USER_ID = "SELECT * FROM shopping_cart WHERE user_id = ?";
    private final String INSERT = "INSERT INTO shopping_cart (user_id, product_id) VALUES (?,?)";
    private final String DELETE_ALL_PRODUCT_BY_USER_ID = "DELETE FROM shopping_cart WHERE user_id = ?";
    private final String DELETE_PRODUCT_BY_USER_ID = "DELETE FROM shopping_cart WHERE user_id = ? AND product_id = ?";

    @Override
    public List<ShoppingCart> getAllProductByUser(Long userId) {
        List<ShoppingCart> list = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            list = ShoppingCartWS.createAllShopCartRS(preparedStatement.executeQuery());

        } catch (SQLException e) {
            LOGGER.error(e);
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
            LOGGER.error(e);
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
            LOGGER.error(e);
        }

    }

    @Override
    public void addShopCart(ShoppingCart shopCart) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setLong(1, shopCart.getUserId());
            preparedStatement.setLong(2, shopCart.getProductId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

}
