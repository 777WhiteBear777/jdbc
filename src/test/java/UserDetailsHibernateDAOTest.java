import connectivity.HibernateSession;
import dao.Impl.UserDetailsHibernateDAO;
import model.UserDetails;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserDetailsHibernateDAOTest {

    private final UserDetailsHibernateDAO userDetailsHibernateDAO = new UserDetailsHibernateDAO();
    private UserDetails userDetails = new UserDetails();
    private Session session;

    @BeforeEach
    public void getProduct() {
        session = HibernateSession.getSessionFactory().openSession();
        userDetails = session.createQuery("from UserDetails order by id desc limit 1", UserDetails.class).getSingleResult();
    }

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

    @Test
    public void update() {
        userDetails.setAge((byte) 20);
        userDetailsHibernateDAO.update(userDetails);
        Assertions.assertNotNull(session.find(UserDetails.class, userDetails.getId()));
    }

    @Test
    public void delete() throws InterruptedException {
        userDetailsHibernateDAO.delete(userDetails.getId() - 1);
        Assertions.assertNull(session.find(UserDetails.class, userDetails.getId() - 1));
    }


    @AfterEach
    public void closeConnection() {
        session.close();
    }

}
