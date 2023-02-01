package DAO.Impl;

import Connectivity.JDBC;
import DAO.CommonDAO;
import Model.UserDetails;
import WorkShop.UserDetailsWS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDetailsJDBCDAO implements CommonDAO<UserDetails> {
    private final Logger LOGGER = LogManager.getLogger(JDBC.class.getName());

    private final String SELECT_ALL = "SELECT * FROM User_details";
    private final String SELECT_BY_ID = "SELECT * FROM User_details WHERE id = ?";
    private final String INSERT = "INSERT INTO User_details(gender, age, user_id) VALUES (?,?,?)";
    private final String UPDATE = "UPDATE User_details SET gender = ?, age = ?, user_id = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM User_details WHERE id = ?";

    @Test
    @Override
    public List<UserDetails> getAll() {
        List<UserDetails> list = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            list = UserDetailsWS.createAllUserDetailsRS(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return list;
    }

    @Test
    @Override
    public Long addObj(UserDetails obj) {
        Long id = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, obj.getGender());
            preparedStatement.setByte(2, obj.getAge());
            preparedStatement.setLong(3, obj.getUserId());
            preparedStatement.executeUpdate();
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
    public UserDetails getById(Long id) {
        UserDetails userDetails = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setDouble(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            userDetails = UserDetailsWS.createUserDetailsRS(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return userDetails;
    }

    @Override
    public void update(UserDetails obj) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, obj.getGender());
            preparedStatement.setByte(2, obj.getAge());
            preparedStatement.setLong(3, obj.getUserId());
            preparedStatement.setLong(4, obj.getId());
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
