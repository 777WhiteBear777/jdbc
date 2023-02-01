package DAO.Impl;

import Connectivity.JDBC;
import DAO.OrderDAO;
import Model.Order;
import WorkShop.OrderWS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderJDBCDAO implements OrderDAO {
    private final static Logger LOGGER = LogManager.getLogger(JDBC.class.getName());

    private final String SELECT_ALL = "SELECT * FROM shop.Order";
    private final String SELECT_ALL_BY_ID = "SELECT * FROM shop.Order WHERE id = ?";
    private final String INSERT = "INSERT INTO shop.Order (product, total_price, user_id) VALUES (?,?,?)";

    @Test
    @Override
    public List<Order> getAllOrder() {
        List<Order> list = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            list = OrderWS.createAllOrderRS(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return list;
    }

    @Test
    @Override
    public List<Order> getAllOrderByUser(Long userId) {
        List<Order> list = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = OrderWS.createAllOrderRS(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return list;
    }

    @Test
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
                LOGGER.error(e);
            }


        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return id;
    }
}
