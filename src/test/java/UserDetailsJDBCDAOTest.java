import connectivity.JDBC;
import dao.Impl.UserDetailsJDBCDAO;
import model.UserDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import workShop.UserDetailsWS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsJDBCDAOTest {

    private final UserDetailsJDBCDAO userDetailsJDBCDAO = new UserDetailsJDBCDAO();
    private final Logger LOGGER = LogManager.getLogger(JDBC.class.getName());
    private UserDetails userDetails;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private final String SELECT_BY_ID = "SELECT * FROM user_details WHERE id = ?";
    private final String SELECT = "SELECT * FROM user_details ORDER BY id DESC LIMIT 1";

    @BeforeEach
    public void getConnection(){
        try {
            connection = new JDBC().getConnection();
            preparedStatement = connection.prepareStatement(SELECT);
            resultSet = preparedStatement.executeQuery();
            userDetails = UserDetailsWS.createUserDetailsRS(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @Test
    public void testGetAll() {
        Assertions.assertNotNull(userDetailsJDBCDAO.getAll());
    }

    @Test
    public void addObj() {
        UserDetails user = new UserDetails();
        user.setAge((byte) 5);
        user.setGender("Test");
        user.setUserId(5L);
        Assertions.assertNotNull(userDetailsJDBCDAO.addObj(user));
    }

    @Test
    public void getById() {
        UserDetails user = userDetailsJDBCDAO.getById(1L);
        Assertions.assertEquals(1, user.getId());
    }

    @Test
    public void update() {
        try {
            userDetails.setAge((byte) 30);
            userDetailsJDBCDAO.update(userDetails);

            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, userDetails.getId());
            resultSet = preparedStatement.executeQuery();
            userDetails = UserDetailsWS.createUserDetailsRS(resultSet);

            Assertions.assertNotNull(userDetails);

        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @Test
    public void delete(){
        try {
            userDetailsJDBCDAO.delete(userDetails.getId() - 1);

            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, userDetails.getId() - 1);
            resultSet = preparedStatement.executeQuery();
            userDetails = UserDetailsWS.createUserDetailsRS(resultSet);

            Assertions.assertNull(userDetails.getId());

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
