package DAO.Impl;

import Connectivity.HibernateSession;
import DAO.CommonDAO;
import Model.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductHibernateDAO implements CommonDAO<Product> {

    @Test
    @Override
    public List<Product> getAll() {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery("from Product ", Product.class);
            query.list();
            return query.list();
        }
    }

    @Test
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

    @Test
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
