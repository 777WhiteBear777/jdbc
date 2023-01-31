package DAO.Impl;

import Connectivity.HibernateSession;
import DAO.CommonDAO;
import DAO.ProductDAO;
import Model.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductHibernateDAO implements CommonDAO<Product> {
    @Override
    public List<Product> getAllProduct() {
        try (Session session = HibernateSession.getSessionFactory().openSession()){
            Query<Product> query = session.createQuery("from Product ", Product.class);
            return query.list();

        }

    }

    @Override
    public Product add(Product product) {

        return null;
    }

    @Override
    public Product get(int id) {
        return null;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(int id) {

    }
}
