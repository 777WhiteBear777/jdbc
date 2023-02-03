import connectivity.JDBC;
import dao.Impl.ProductJDBCDAO;
import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import workShop.ProductWS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductJDBCDAOTest {

    private final Logger LOGGER = LogManager.getLogger(JDBC.class.getName());
    private final ProductJDBCDAO productJDBCDAO = new ProductJDBCDAO();
    private Product product;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final String SELECT_BY_ID = "SELECT * FROM products WHERE id = ?";
    private final String SELECT = "SELECT * FROM products ORDER BY id DESC LIMIT 1";

    @BeforeEach
    public void getConnection(){
        try {
            connection = new JDBC().getConnection();
            preparedStatement = connection.prepareStatement(SELECT);
            resultSet = preparedStatement.executeQuery();
            product = ProductWS.createProductRS(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @Test
    public void testGetAll() {
        Assertions.assertNotNull(productJDBCDAO.getAll());
    }

    @Test
    public void addObj() {
        Product product = new Product();
        product.setName("Test");
        product.setCategory("Test");
        product.setPrice(0f);
        Assertions.assertNotNull(productJDBCDAO.addObj(product));
    }

    @Test
    public void getById() {
        Product product1 = productJDBCDAO.getById(product.getId());
        Assertions.assertEquals(product.getId(), product1.getId());
    }

    @Test
    public void update() {
        try {
            product.setPrice(30f);
            productJDBCDAO.update(product);

            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, product.getId());
            resultSet = preparedStatement.executeQuery();
            product = ProductWS.createProductRS(resultSet);

            Assertions.assertNotNull(product);

        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @Test
    public void delete(){
        try {
            productJDBCDAO.delete(product.getId() - 1);

            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, product.getId() - 1);
            resultSet = preparedStatement.executeQuery();
            product = ProductWS.createProductRS(resultSet);

            Assertions.assertNull(product.getId());

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

}
