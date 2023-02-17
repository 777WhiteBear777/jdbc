import dao.Impl.OrderHibernateDAO;
import dao.Impl.OrderJDBCDAO;
import model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class OrderDAOTest {

    OrderHibernateDAO orderHibernateDAO = new OrderHibernateDAO();
    OrderJDBCDAO orderJDBCDAO = new OrderJDBCDAO();

    @Test
    public void getAllOrder() {
        Assertions.assertNotNull(orderHibernateDAO.getAllOrder());
        Assertions.assertNotNull(orderJDBCDAO.getAllOrder());
    }

    @Test
    public void getAllOrderByUser() {
        Assertions.assertNotNull(orderHibernateDAO.getAllOrderByUser(1L));
        Assertions.assertNotNull(orderJDBCDAO.getAllOrderByUser(1L));
    }

    @Test
    public void addOrder() throws SQLException {
        Order order = new Order();
        order.setProduct("TEST, TEST, TEST");
        order.setUserId(5L);
        order.setTotalPrice(9999F);
        Assertions.assertNotNull(orderHibernateDAO.addOrder(order));
        Assertions.assertNotNull(orderJDBCDAO.addOrder(order));
    }

}
