package DAO;

import Model.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getAllOrder();
    List<Order> getAllOrderByUser(int userId);
    Order addOrder(Order order);
    }
