package Test;

import DAO.Impl.ShopCartJDBCDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopCartJDBCDAOTest {
    ShopCartJDBCDAO shopCartJDBCDAO = new ShopCartJDBCDAO();

    @Test
    public void getAllProductByUser() {
        Assertions.assertNotNull(shopCartJDBCDAO.getAllProductByUser(1L));
    }
}
