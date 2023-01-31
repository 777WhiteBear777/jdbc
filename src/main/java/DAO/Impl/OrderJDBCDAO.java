package DAO.Impl;

import Connectivity.JDBC;
import DAO.OrderDAO;
import Model.Order;
import WorkShop.OrderWS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderJDBCDAO implements OrderDAO {
    private final String SELECT_ALL = "SELECT * FROM Order";
    private final String SELECT_ALL_BY_ID = "SELECT * FROM Order WHERE id = ?";
    private final String INSERT = "INSERT INTO shop.Order (product, total_price, user_id) VALUES (?,?,?)";

    @Override
    public List<Order> getAllOrder() {
        List<Order> list;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            list = OrderWS.createAllOrderRS(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Order> getAllOrderByUser(int userId) {
        List<Order> list;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_ID)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = OrderWS.createAllOrderRS(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Integer addOrder(Order order) {
        int id = 0;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, order.getProduct());
            preparedStatement.setDouble(2, order.getTotalPrice());
            preparedStatement.setInt(3, order.getUserId());
            preparedStatement.executeUpdate();
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
}
