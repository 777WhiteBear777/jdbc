import dao.Impl.OrderHibernateDAO;
import model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class OrderHibernateDAOTest {

    OrderHibernateDAO orderHibernateDAO = new OrderHibernateDAO();

    @Test
    public void getAllOrder() {
        Assertions.assertNotNull(orderHibernateDAO.getAllOrder());
    }

    @Test
    public void getAllOrderByUser() {
        Assertions.assertNotNull(orderHibernateDAO.getAllOrderByUser(1L));
    }

    @Test
    public void addOrder() throws SQLException {
        Order order = new Order();
        order.setProduct("TEST, TEST, TEST");
        order.setUserId(20L);
        order.setTotalPrice(9999F);
        Assertions.assertNotNull(orderHibernateDAO.addOrder(order));
    }

}
