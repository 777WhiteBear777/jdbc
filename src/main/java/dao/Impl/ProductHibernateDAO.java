package dao.Impl;

import connectivity.HibernateSession;
import dao.CommonDAO;
import model.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductHibernateDAO implements CommonDAO<Product> {

    @Override
    public List<Product> getAll() {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery("from Product ", Product.class);
            query.list();
            return query.list();
        }
    }

    @Override
    public Long addObj(Product product) {
        Long id;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.persist(product);
            id = product.getId();
            session.getTransaction().commit();
        }

        return id;
    }

    @Override
    public Product getById(Long id) {
        Product product;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            product = session.find(Product.class, id);
        }
        return product;
    }

    @Override
    public void update(Product product) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.merge(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) {
        Product product;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            product = session.find(Product.class, id);
            session.remove(product);
            session.getTransaction().commit();
        }
    }

}
