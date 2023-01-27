package DAO.Impl;

import Connectivity.JDBC;
import DAO.CommonDAO;
import Connectivity.JDBC;
import Model.UserDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDetailsJDBCDAO implements CommonDAO<UserDetails> {
    @Override
    public List<UserDetails> getAll() {
        List<UserDetails> list;
        try(Connection connection = new JDBC().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_details");
            ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()){

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Integer addObj(UserDetails obj) {
        return null;
    }

    @Override
    public UserDetails getById(int id) {
        return null;
    }

    @Override
    public void update(UserDetails obj) {

    }

    @Override
    public void delete(int id) {

    }
}
