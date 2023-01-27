package WorkShop;

import Model.ShopCart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ShopCartWS {
    public static List<ShopCart> createAllShopCartRS(ResultSet resultSet) throws SQLException {
        List<ShopCart> list = null;
        while (resultSet.next()) {
            ShopCart shopCart = new ShopCart();
            shopCart.setProductId(resultSet.getInt("product_id"));
            shopCart.setUserId(resultSet.getInt("user_id"));
            list.add(shopCart);
        }
        return list;
    }
}
