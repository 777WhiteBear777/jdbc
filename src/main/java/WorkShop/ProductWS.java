package WorkShop;

import Model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductWS {

    public static Product createProductRS(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        if (resultSet.next()) {
            product.setId(resultSet.getInt("id"));
            product.setCategory(resultSet.getString("category"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getFloat("price"));
        }
        return product;
    }

    public static List<Product> createAllProductRS(ResultSet resultSet) throws SQLException {
        List<Product> list = null;
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setCategory(resultSet.getString("category"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getFloat("price"));
            list.add(product);
        }
        return list;
    }
}
