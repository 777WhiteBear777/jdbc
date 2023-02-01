package Test;

import DAO.Impl.UserHibernateDAO;
import Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserHibernateDAOTest {
    UserHibernateDAO userHibernateDAO = new UserHibernateDAO();

    @Test
    public void testGetAll() {
        Assertions.assertNotNull(userHibernateDAO.getAll());
    }

    @Test
    public void addObj() {
        User user = new User();
        user.setFirstname("Test");
        user.setLastname("Test");
        Assertions.assertNotNull(userHibernateDAO.addObj(user));
    }

    @Test
    public void getById() {
        User user = userHibernateDAO.getById(1L);
        Assertions.assertEquals(1, user.getId());
    }
}
