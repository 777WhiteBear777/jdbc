package Runner;

import DAO.Impl.OrderJDBCDAO;
import DAO.Impl.ShopCartHibernateDAO;
import DAO.Impl.UserJDBCDAO;
import Sevice.OrderService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {


//    private static PreparedStatement statement ;
//    private static ResultSet resultSet;

    public static void main(String[] args) throws FileNotFoundException {
//        Order order = new Order();
//        OrderJDBCDAO orderJDBCDAO = new OrderJDBCDAO();
//        order.setTotalPrice(10);
//        order.setUserId(3);
//        order.setProduct("Milk");
//
//        orderJDBCDAO.addOrder(order);


//
//        List<UserDetails> list1 = new ArrayList<>();
//        UserDetailsHibernateDAO userDetailsHibernateDAO = new UserDetailsHibernateDAO();
//       list1 = userDetailsHibernateDAO.getAll();
//        System.out.println(list1);
//        ShopCart shopCart = new ShopCart();
//        shopCart.setUserId(2);
//        shopCart.setProductId(2);
//        ShopCart shopCart1 = new ShopCart();
//        shopCart1.setUserId(2);
//        shopCart1.setProductId(5);
//        ShopCart shopCart2 = new ShopCart();
//        shopCart2.setUserId(2);
//        shopCart2.setProductId(3);
//        UserHibernateDAO userHibernateDAO = new UserHibernateDAO();
//        User user = new User();
//        user.setLastname("Los");
//        user.setFirstname("Bos");
//        userHibernateDAO.addObj(user);

        ShopCartHibernateDAO shopCartHibernateDAO = new ShopCartHibernateDAO();
//        shopCartHibernateDAO.addShopCart(shopCart);
//        shopCartHibernateDAO.addShopCart(shopCart1);
//        shopCartHibernateDAO.addShopCart(shopCart2);

        OrderService orderService = new OrderService();
        orderService.createOrderHibernate(1L);
//        orderService.createOrderJDBC(1);
//        ProductJDBCDAO productJDBCDAO = new ProductJDBCDAO();
//        System.out.println(productJDBCDAO.getAll());
//        OrderJDBCDAO orderJDBCDAO = new OrderJDBCDAO();
//        System.out.println(orderJDBCDAO.getAllOrder());
//        System.out.println(orderJDBCDAO.getAllOrderByUser(1L));
//
//        UserJDBCDAO userJDBCDAO = new UserJDBCDAO();
//        System.out.println(userJDBCDAO.getAll());

    }
}
