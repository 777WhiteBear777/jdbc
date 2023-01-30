package DAO.Impl;

import Connectivity.JDBC;
import DAO.CommonDAO;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserJDBCDAO implements CommonDAO<User> {

    @Override
    public List<User> getAll() {
        List<User> list = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            list = WorkShop.UserWS.createAllUserRS(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Integer addObj(User obj) {
        int id = 0;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user ( firstname, lastname) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, obj.getFirstname());
            preparedStatement.setString(2, obj.getLastname());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                id = resultSet.next() ? resultSet.getInt(1) : null;
            } catch (SQLException e) {
                System.out.println(e + "addObj exception ....");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public User getById(int id) {
        User user;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            user = WorkShop.UserWS.createUserRS(preparedStatement.executeQuery());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void update(User obj) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE shop.user SET firstname = ?, lastname = ? WHERE id = ?")) {
            preparedStatement.setString(1, obj.getFirstname());
            preparedStatement.setString(2, obj.getLastname());
            preparedStatement.setInt(3, obj.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
