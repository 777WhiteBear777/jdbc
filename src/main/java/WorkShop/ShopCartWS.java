package WorkShop;

import Model.ShopCart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopCartWS {
    public static List<ShopCart> createAllShopCartRS(ResultSet resultSet) throws SQLException {
        List<ShopCart> list = new ArrayList<>();
        while (resultSet.next()) {
            ShopCart shopCart = new ShopCart();
            shopCart.setUserId(resultSet.getInt("user_id"));
            shopCart.setProductId(resultSet.getInt("product_id"));
            list.add(shopCart);
        }
        return list;
    }
}
