package DAO.Impl;

import Connectivity.JDBC;
import DAO.CommonDAO;
import Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserJDBCDAO implements CommonDAO<User> {
    private final Logger LOGGER = LogManager.getLogger(JDBC.class.getName());

    private final String SELECT_ALL = "SELECT * FROM User";
    private final String SELECT_BY_ID = "SELECT * FROM User WHERE id = ?";
    private final String INSERT = "INSERT INTO User ( firstname, lastname) VALUES (?,?)";
    private final String UPDATE = "UPDATE User SET firstname = ?, lastname = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM User WHERE id = ?";

    @Test
    @Override
    public List<User> getAll() {
        List<User> list = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            list = WorkShop.UserWS.createAllUserRS(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return list;
    }

    @Test
    @Override
    public Long addObj(User obj) {
        Long id = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, obj.getFirstname());
            preparedStatement.setString(2, obj.getLastname());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                id = resultSet.next() ? resultSet.getLong(1) : null;
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return id;
    }

    @Test
    @Override
    public User getById(Long id) {
        User user = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            user = WorkShop.UserWS.createUserRS(preparedStatement.executeQuery());

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public void update(User obj) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, obj.getFirstname());
            preparedStatement.setString(2, obj.getLastname());
            preparedStatement.setLong(3, obj.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @Override
    public void delete(Long id) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
