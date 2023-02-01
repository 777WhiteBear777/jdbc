package Test;

import DAO.Impl.OrderHibernateDAO;
import Model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class OrderHibernateDAOTest {
    OrderHibernateDAO orderHibernateDAO = new OrderHibernateDAO();

    @Test
    public void getAllOrderTest() {
        Assertions.assertNotNull(orderHibernateDAO.getAllOrder());
    }

    @Test
    public void getAllOrderByUserTest() {
        Assertions.assertNotNull(orderHibernateDAO.getAllOrderByUser(1L));
    }

    @Test
    public void addOrderTest() throws SQLException {
        Order order = new Order();
        order.setProduct("TEST, TEST, TEST");
        order.setUserId(20L);
        order.setTotalPrice(9999F);
        Assertions.assertNotNull(orderHibernateDAO.addOrder(order));
    }
}
