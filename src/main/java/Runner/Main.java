package Runner;

import DAO.Impl.OrderJDBCDAO;
import DAO.Impl.UserHibernateDAO;
import DAO.Impl.UserJDBCDAO;
import Model.Order;
import Model.User;
import Sevice.OrderService;

import java.util.ArrayList;
import java.util.List;

public class Main {


//    private static PreparedStatement statement ;
//    private static ResultSet resultSet;

    public static void main(String[] args) {
        User user = new User();
        user.setFirstname("Joe");
        user.setLastname("Baiden");

        final String insertUser = "INSERT INTO user (id, firstname, lastname) VALUES (?,?,?)";
        final String selectUserSql = "SELECT * FROM user";
       List<User> list = new ArrayList<>();
        UserJDBCDAO userJDBCDAO = new UserJDBCDAO();
//        list = userJDBCDAO.getAll();
//        System.out.println(list);
//
//
//        System.out.println(userJDBCDAO.getById(2));
//        userJDBCDAO.addObj(user);
////        user.setId(a );
//        list = userJDBCDAO.getAll();
//        System.out.println(list);
//
//        user.setLastname("Kilaf");
//        System.out.println(user.getId());
//        userJDBCDAO.update(user);
//        list = userJDBCDAO.getAll();
//        System.out.println(list);
//
//        userJDBCDAO.delete(3);
//        list = userJDBCDAO.getAll();
//        System.out.println(list);
//
//        UserHibernateDAO userDAO = new UserHibernateDAO();
////        userDAO.deleteUser(6);
//        list= userDAO.getAllUser();
//        System.out.println(list);
////
//        userDAO.addUser(user);

//        OrderService orderService = new OrderService();
//        orderService.createOrder(1);
        Order order = new Order();
        order.setUserId(1);
        order.setTotalPrice(10);
        order.setProduct("Bear");
        OrderJDBCDAO orderJDBCDAO = new OrderJDBCDAO();
        orderJDBCDAO.addOrder(order);
    }
}
