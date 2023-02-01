package WorkShop;

import Model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderWS {
    public static List<Order> createAllOrderRS(ResultSet resultSet) throws SQLException {
        List<Order> list = new ArrayList<>();
        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getLong("id"));
            order.setProduct(resultSet.getString("product"));
            order.setTotalPrice(resultSet.getFloat("total"));
            order.setUserId(resultSet.getLong("user_id"));
            list.add(order);
        }
        return list;
    }
}
