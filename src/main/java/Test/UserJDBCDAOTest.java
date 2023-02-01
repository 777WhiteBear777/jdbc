package Test;

import DAO.Impl.UserJDBCDAO;
import Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserJDBCDAOTest {
    UserJDBCDAO userJDBCDAO = new UserJDBCDAO();

    @Test
    public void testGetAll() {
        Assertions.assertNotNull(userJDBCDAO.getAll());
    }

    @Test
    public void addObj() {
        User user = new User();
        user.setFirstname("Test");
        user.setLastname("Test");
        Assertions.assertNotNull(userJDBCDAO.addObj(user));
    }

    @Test
    public void getById() {
        User user = userJDBCDAO.getById(1L);
        Assertions.assertEquals(1, user.getId());
    }

}
