import connectivity.JDBC;
import dao.Impl.UserJDBCDAO;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import workShop.UserWS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class UserJDBCDAOTest {

    UserJDBCDAO userJDBCDAO = new UserJDBCDAO();
    private final Logger LOGGER = LogManager.getLogger(JDBC.class.getName());
    private User user;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final String SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";
    private final String SELECT = "SELECT * FROM users ORDER BY id DESC LIMIT 1";

    @BeforeEach
    public void getConnection() throws SQLException {
        try {
            connection = new JDBC().getConnection();
            preparedStatement = connection.prepareStatement(SELECT);
            resultSet = preparedStatement.executeQuery();
            user = UserWS.createUserRS(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @Test
    public void testGetAll() {
        Assertions.assertNotNull(userJDBCDAO.getAll());
    }

    @Test
    public void addObj() {
        User user = new User();
        user.setFirstname("Test");
        user.setLastname("Test");
        Assertions.assertNotNull(userJDBCDAO.addObj(user));
    }

    @Test
    public void getById() {
        User user = userJDBCDAO.getById(1L);
        Assertions.assertEquals(1, user.getId());
    }

    @Test
    public void update() {
        try {
            user.setLastname("TEST_TEST_TEST");
            userJDBCDAO.update(user);

            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, user.getId());
            resultSet = preparedStatement.executeQuery();
            user = UserWS.createUserRS(resultSet);

            Assertions.assertNotNull(user);

        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @Test
    public void delete() throws InterruptedException {
        try {
            userJDBCDAO.delete(user.getId() - 1);

            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, user.getId() - 1);
            resultSet = preparedStatement.executeQuery();
            user = UserWS.createUserRS(resultSet);

            Assertions.assertNull(user.getId());

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
