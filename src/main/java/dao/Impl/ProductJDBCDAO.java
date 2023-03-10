package dao.Impl;

import connectivity.JDBC;
import dao.CommonDAO;
import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import workShop.ProductWS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductJDBCDAO implements CommonDAO<Product> {
    private final Logger LOGGER = LogManager.getLogger(JDBC.class.getName());


    private final String SELECT_ALL = "SELECT * FROM products";
    private final String SELECT_BY_ID = "SELECT * FROM products WHERE id = ?";
    private final String INSERT = "INSERT INTO products (category, name, price) VALUES (?,?,?)";
    private final String UPDATE = "UPDATE products SET category = ?, name = ?, price = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM products WHERE id = ?";

    @Override
    public List<Product> getAll() {
        List<Product> list = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            list = ProductWS.createAllProductRS(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return list;
    }

    @Override
    public Long addObj(Product obj) {
        Long id = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, obj.getCategory());
            preparedStatement.setString(2, obj.getName());
            preparedStatement.setFloat(3, obj.getPrice());
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

    @Override
    public Product getById(Long id) {
        Product product = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            product = ProductWS.createProductRS(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return product;
    }

    @Override
    public void update(Product obj) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, obj.getCategory());
            preparedStatement.setString(2, obj.getName());
            preparedStatement.setFloat(3, obj.getPrice());
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
