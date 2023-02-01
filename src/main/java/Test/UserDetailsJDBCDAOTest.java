package Test;

import DAO.Impl.UserDetailsJDBCDAO;
import Model.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserDetailsJDBCDAOTest {
    UserDetailsJDBCDAO userDetailsJDBCDAO = new UserDetailsJDBCDAO();

    @Test
    public void testGetAll() {
        Assertions.assertNotNull(userDetailsJDBCDAO.getAll());
    }

    @Test
    public void addObj() {
        UserDetails user = new UserDetails();
        user.setAge((byte) 5);
        user.setGender("Test");
        user.setUserId(20L);
        Assertions.assertNotNull(userDetailsJDBCDAO.addObj(user));
    }

    @Test
    public void getById() {
        UserDetails user = userDetailsJDBCDAO.getById(1L);
        Assertions.assertEquals(1, user.getId());
    }
}
