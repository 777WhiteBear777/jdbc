package sevice;

import connectivity.JDBC;
import dao.Impl.*;
import model.Order;
import model.Product;
import model.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final Logger LOGGER = LogManager.getLogger(JDBC.class.getName());

    public void createOrderJDBC(Long id) {

        Order order = new Order();
        OrderJDBCDAO orderJDBCDAO = new OrderJDBCDAO();
        ShoppingCartJDBCDAO shoppingCartJDBCDAO = new ShoppingCartJDBCDAO();
        ProductJDBCDAO productJDBCDAO = new ProductJDBCDAO();
        List<ShoppingCart> shopCartList;
        List<Product> productList = new ArrayList<>();

        try {
            shopCartList = shoppingCartJDBCDAO.getAllProductByUser(id);
            for (ShoppingCart shop : shopCartList
            ) {
                productList.add(productJDBCDAO.getById(shop.getProductId()));
            }

            for (int i = 0; i < productList.size(); i++) {
                if (i != 0 && i != productList.size() - 1) {
                    order.setProduct(", " + productList.get(i).getName());
                    order.setTotalPrice(productList.get(i).getPrice());
                }else if (i == 0) {
                    order.setProduct(productList.get(i).getName());
                    order.setTotalPrice(productList.get(i).getPrice());
                } else {
                    order.setProduct(", " + productList.get(i).getName());
                    order.setTotalPrice(productList.get(i).getPrice());
                }

            }
            order.setUserId(id);
             orderJDBCDAO.addOrder(order);
            shoppingCartJDBCDAO.deleteAllProductByUser(id);
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    public void createOrderHibernate(Long id){

        Order order = new Order();
        OrderHibernateDAO orderHibernateDAO = new OrderHibernateDAO();
        ShoppingCartHibernateDAO shoppingCartHibernateDAO = new ShoppingCartHibernateDAO();
        ProductHibernateDAO productHibernateDAO = new ProductHibernateDAO();
        List<ShoppingCart> shopCartList;
        List<Product> productList = new ArrayList<>();

        try {
            shopCartList = shoppingCartHibernateDAO.getAllProductByUser(id);
            for (ShoppingCart shop : shopCartList
            ) {
                productList.add(productHibernateDAO.getById(shop.getProductId()));
            }

            for (int i = 0; i < productList.size(); i++) {
                if (i != 0 && i != productList.size() - 1) {
                    order.setProduct(", " + productList.get(i).getName());
                    order.setTotalPrice(productList.get(i).getPrice());
                }else if (i == 0) {
                    order.setProduct(productList.get(i).getName());
                    order.setTotalPrice(productList.get(i).getPrice());
                } else {
                    order.setProduct(", " + productList.get(i).getName());
                    order.setTotalPrice(productList.get(i).getPrice());
                }

            }
            order.setUserId(id);
            orderHibernateDAO.addOrder(order);
            shoppingCartHibernateDAO.deleteAllProductByUser(id);
        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

}
