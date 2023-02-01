package Sevice;

import DAO.Impl.*;
import Model.Order;
import Model.Product;
import Model.ShopCart;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    public void createOrderJDBC(Long id) {

        Order order = new Order();
        OrderJDBCDAO orderJDBCDAO = new OrderJDBCDAO();
        ShopCartJDBCDAO shopCartJDBCDAO = new ShopCartJDBCDAO();
        ProductJDBCDAO productJDBCDAO = new ProductJDBCDAO();
        List<ShopCart> shopCartList;
        List<Product> productList = new ArrayList<>();

        try {
            shopCartList = shopCartJDBCDAO.getAllProductByUser(id);
            for (ShopCart shop : shopCartList
            ) {
                productList.add(productJDBCDAO.getById(shop.getProductId()));
            }

            for (int i = 0; i < productList.size(); i++) {
                if (i != 0 && i != productList.size() - 1) {
                    order.setProduct(", " + productList.get(i).getName());
                    order.setTotalPrice(productList.get(i).getPrice());
                }if (i == 0) {
                    order.setProduct(productList.get(i).getName());
                    order.setTotalPrice(productList.get(i).getPrice());
                } else {
                    order.setProduct(", " + productList.get(i).getName());
                    order.setTotalPrice(productList.get(i).getPrice());
                }

            }
            order.setUserId(id);
             orderJDBCDAO.addOrder(order);
            shopCartJDBCDAO.deleteAllProductByUser(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createOrderHibernate(Long id){
        Order order = new Order();
        OrderHibernateDAO orderHibernateDAO = new OrderHibernateDAO();
        ShopCartHibernateDAO shopCartHibernateDAO = new ShopCartHibernateDAO();
        ProductHibernateDAO productHibernateDAO = new ProductHibernateDAO();
        List<ShopCart> shopCartList;
        List<Product> productList = new ArrayList<>();
        try {
            shopCartList = shopCartHibernateDAO.getAllProductByUser(id);
            for (ShopCart shop : shopCartList
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
            shopCartHibernateDAO.deleteAllProductByUser(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
