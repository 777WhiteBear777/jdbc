package Test;

import DAO.Impl.UserDetailsHibernateDAO;
import Model.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserDetailsHibernateDAOTest {
    UserDetailsHibernateDAO userDetailsHibernateDAO = new UserDetailsHibernateDAO();

    @Test
    public void testGetAll() {
        Assertions.assertNotNull(userDetailsHibernateDAO.getAll());
    }

    @Test
    public void addObj() {
        UserDetails user = new UserDetails();
        user.setAge((byte) 5);
        user.setGender("Test");
        user.setUserId(20L);
        Assertions.assertNotNull(userDetailsHibernateDAO.addObj(user));
    }

    @Test
    public void getById() {
        UserDetails user = userDetailsHibernateDAO.getById(1L);
        Assertions.assertEquals(1, user.getId());
    }
}
