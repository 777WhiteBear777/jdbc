package Runner;

import DAO.Impl.OrderJDBCDAO;
import DAO.Impl.UserDetailsHibernateDAO;
import DAO.Impl.UserHibernateDAO;
import DAO.Impl.UserJDBCDAO;
import Model.Order;
import Model.User;
import Model.UserDetails;
import Sevice.OrderService;

import java.util.ArrayList;
import java.util.List;

public class Main {


//    private static PreparedStatement statement ;
//    private static ResultSet resultSet;

    public static void main(String[] args) {
//        Order order = new Order();
//        OrderJDBCDAO orderJDBCDAO = new OrderJDBCDAO();
//        order.setTotalPrice(10);
//        order.setUserId(3);
//        order.setProduct("Milk");
//
//        orderJDBCDAO.addOrder(order);

//        OrderService orderService = new OrderService();
//        orderService.createOrder(2);
        List<User> list = new ArrayList<>();
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO();
        list = userHibernateDAO.getAllUser();
        System.out.println(list);

        List<UserDetails> list1 = new ArrayList<>();
        UserDetailsHibernateDAO userDetailsHibernateDAO = new UserDetailsHibernateDAO();
       list1 = userDetailsHibernateDAO.getAllUserDetails();
        System.out.println(list);
    }
}
