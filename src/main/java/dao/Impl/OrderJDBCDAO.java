package dao.Impl;

import connectivity.JDBC;
import dao.OrderDAO;
import model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import workShop.OrderWS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderJDBCDAO implements OrderDAO {
    private final static Logger LOGGER = LogManager.getLogger(JDBC.class.getName());

    private final String SELECT_ALL = "SELECT * FROM orders";
    private final String SELECT_ALL_BY_ID = "SELECT * FROM orders WHERE id = ?";
    private final String INSERT = "INSERT INTO orders (product, total_price, user_id) VALUES (?,?,?)";

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
