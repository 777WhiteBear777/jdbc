import connectivity.JDBC;
import dao.Impl.ShoppingCartJDBCDAO;
import model.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import workShop.ShoppingCartWS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopCartJDBCDAOTest {

    private final ShoppingCartJDBCDAO shoppingCartJDBCDAO = new ShoppingCartJDBCDAO();
    private final Logger LOGGER = LogManager.getLogger(JDBC.class.getName());
    private ShoppingCart shoppingCart = new ShoppingCart();
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final String SELECT = "SELECT * FROM shopping_cart ORDER BY user_id DESC LIMIT 1";
    private final String SELECT_BY_ID = "SELECT * FROM shopping_cart WHERE user_id = ? LIMIT 1";
    private final String SELECT_ALL_BY_ID = "SELECT * FROM shopping_cart WHERE user_id = ?";

    @BeforeEach
    public void getConnection() {
        try {
            connection = new JDBC().getConnection();
            preparedStatement = connection.prepareStatement(SELECT);
            resultSet = preparedStatement.executeQuery();
            shoppingCart = createShoppingCartRS(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Test
    public void getAllProductByUser() {
        Assertions.assertNotNull(shoppingCartJDBCDAO.getAllProductByUser(1L));
    }

    @Test
    public void deleteAllProductByUser() {
        try {
            shoppingCartJDBCDAO.deleteAllProductByUser(shoppingCart.getUserId());

            preparedStatement = connection.prepareStatement(SELECT_ALL_BY_ID);
            preparedStatement.setLong(1, shoppingCart.getUserId());
            resultSet = preparedStatement.executeQuery();

            Assertions.assertNull(ShoppingCartWS.createAllShopCartRS(resultSet));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Test
    public void deleteShopCart() {
        try {
            shoppingCartJDBCDAO.deleteShopCart(shoppingCart.getUserId(), shoppingCart.getProductId());

            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, shoppingCart.getUserId());
            resultSet = preparedStatement.executeQuery();
            shoppingCart = createShoppingCartRS(resultSet);

            Assertions.assertNull(shoppingCart.getUserId());
        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @Test
    public void addShopCart() {

        try {
            shoppingCart.setUserId(14L);
            shoppingCart.setProductId(8L);
            shoppingCartJDBCDAO.addShopCart(shoppingCart);

            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, shoppingCart.getUserId());
            resultSet = preparedStatement.executeQuery();
            shoppingCart = createShoppingCartRS(resultSet);

            Assertions.assertNotNull(shoppingCart);
        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @AfterEach
    public void closeConnection() {
        try {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    public static ShoppingCart createShoppingCartRS(ResultSet resultSet) throws SQLException {

        ShoppingCart shoppingCart1 = new ShoppingCart();

        if (resultSet.next()) {
            shoppingCart1.setUserId(resultSet.getLong(1));
            shoppingCart1.setProductId(resultSet.getLong(2));
        }
        return shoppingCart1;
    }

}
