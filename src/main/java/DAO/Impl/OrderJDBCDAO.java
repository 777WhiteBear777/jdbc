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
    private final String INSERT = "INSERT INTO Order (product, total_price, user_id) VALUES (?,?,?)";

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
    public List<Order> getAllOrderByUser(Long userId) {
        List<Order> list;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = OrderWS.createAllOrderRS(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Long addOrder(Order order) {
        Long id = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, order.getProduct());
            preparedStatement.setFloat(2, order.getTotalPrice());
            preparedStatement.setLong(3, order.getUserId());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                id = resultSet.next() ? resultSet.getLong(1) : null;
            } catch (SQLException e) {
                System.out.println(e + "addObj exception ....");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
