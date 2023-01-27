package WorkShop;

import Model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderWS {
    public static List<Order> createAllOrderRS(ResultSet resultSet) throws SQLException {
        List<Order> list = null;
        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setProduct(resultSet.getString("product"));
            order.setTotalPrice(resultSet.getDouble("total"));
            order.setUserId(resultSet.getInt("user_id"));
            list.add(order);
        }
        return list;
    }
}
