package DAO.Impl;

import Connectivity.JDBC;
import DAO.CommonDAO;
import Model.Product;
import WorkShop.ProductWS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductJDBCDAO implements CommonDAO<Product> {

    private final String SELECT_ALL = "SELECT * FROM Product";
    private final String SELECT_BY_ID = "SELECT * FROM Product WHERE id = ?";
    private final String INSERT = "INSERT INTO Product (category, name, price) VALUES (?,?,?)";
    private final String UPDATE = "UPDATE Product SET category = ?, name = ?, price = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM Product WHERE id = ?";


    @Override
    public List<Product> getAll() {
        List<Product> list;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            list = ProductWS.createAllProductRS(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Integer addObj(Product obj) {
        int id = 0;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, obj.getCategory());
            preparedStatement.setString(2, obj.getName());
            preparedStatement.setFloat(3, obj.getPrice());
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

    @Override
    public Product getById(int id) {
        Product product;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            product = ProductWS.createProductRS(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            preparedStatement.setInt(4, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
