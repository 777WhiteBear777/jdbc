package Test;

import DAO.Impl.ProductHibernateDAO;
import Model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductHibernateDAOTest {
    ProductHibernateDAO productHibernateDAO = new ProductHibernateDAO();
    @Test
    public void testGetAll() {
        Assertions.assertNotNull(productHibernateDAO.getAll());
    }

    @Test
    public void addObj() {
        Product product = new Product();
        product.setName("Test");
        product.setCategory("Test");
        product.setPrice(0f);
        Assertions.assertNotNull(productHibernateDAO.addObj(product));
    }

    @Test
    public void getById() {
        Product product = productHibernateDAO.getById(1L);
        Assertions.assertEquals(1, product.getId());
    }
}
