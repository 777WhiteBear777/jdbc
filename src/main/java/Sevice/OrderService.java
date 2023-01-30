package Sevice;

import DAO.Impl.OrderJDBCDAO;
import DAO.Impl.ProductJDBCDAO;
import DAO.Impl.ShopCartJDBCDAO;
import Model.Order;
import Model.Product;
import Model.ShopCart;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    public void createOrder(int id) {
        Order order = new Order();
        OrderJDBCDAO orderJDBCDAO = new OrderJDBCDAO();
        ShopCartJDBCDAO shopCartJDBCDAO = new ShopCartJDBCDAO();
        ProductJDBCDAO productJDBCDAO = new ProductJDBCDAO();
        List<ShopCart> shopCartList ;
        List<Product> productList = new ArrayList<>();
        try {
            shopCartList = shopCartJDBCDAO.getAllProductByUser(id);
            for ( ShopCart shop:shopCartList
                 ) {
                productList.add(productJDBCDAO.getById(shop.getProductId()));
            }
            for (Product product: productList) {
                order.setProduct(product.getName()+", ");
                order.setTotalPrice(product.getPrice());
            }
            orderJDBCDAO.addOrder(order);
            shopCartJDBCDAO.deleteAllProductByUser(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
