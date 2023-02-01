package Test;

import DAO.Impl.ProductJDBCDAO;
import Model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductJDBCDAOTest {
    ProductJDBCDAO productJDBCDAO = new ProductJDBCDAO();

    @Test
    public void testGetAll() {
        Assertions.assertNotNull(productJDBCDAO.getAll());
    }

    @Test
    public void addObj() {
        Product product = new Product();
        product.setName("Test");
        product.setCategory("Test");
        product.setPrice(0f);
        Assertions.assertNotNull(productJDBCDAO.addObj(product));
    }

    @Test
    public void getById() {
        Product product = productJDBCDAO.getById(1L);
        Assertions.assertEquals(1, product.getId());
    }
}
