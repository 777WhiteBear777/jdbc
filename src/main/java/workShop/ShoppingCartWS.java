package workShop;

import model.ShoppingCart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartWS {

    public static List<ShoppingCart> createAllShopCartRS(ResultSet resultSet) throws SQLException {

        List<ShoppingCart> list = new ArrayList<>();

        while (resultSet.next()) {
            ShoppingCart shopCart = new ShoppingCart();
            shopCart.setUserId(resultSet.getLong("user_id"));
            shopCart.setProductId(resultSet.getLong("product_id"));
            list.add(shopCart);
        }
        if (list.size()==0){
            return null;
        }
        return list;
    }

}
