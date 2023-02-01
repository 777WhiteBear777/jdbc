package Test;

import DAO.Impl.ShopCartHibernateDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopCartHibernateDAOTest {
    ShopCartHibernateDAO shopCartHibernateDAO = new ShopCartHibernateDAO();

    @Test
    public void getAllProductByUser() {
        Assertions.assertNotNull(shopCartHibernateDAO.getAllProductByUser(1L));
    }
}
