package dao.Impl;

import connectivity.HibernateSession;
import dao.ShopCartDAO;
import model.ShoppingCart;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ShoppingCartHibernateDAO implements ShopCartDAO {

    @Override
    public List<ShoppingCart> getAllProductByUser(Long userId) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            Query<ShoppingCart> query = session.createQuery("from ShoppingCart where userId = :id", ShoppingCart.class);
            query.setParameter("id", userId);
            return query.list();
        }
    }

    @Override
    public void deleteAllProductByUser(Long userId) {
        String hql = "DELETE FROM ShoppingCart where userId = :user_id";
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.createQuery(hql).setParameter("user_id", userId);
            session.getTransaction().commit();
        }


    }


    @Override
    public void deleteShopCart(Long userId, Long productId) {
        ShoppingCart shopCart = new ShoppingCart();
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            shopCart.setUserId(userId);
            shopCart.setProductId(productId);
            session.remove(shopCart);
            session.getTransaction().commit();
        }

    }

    @Override
    public void addShopCart(ShoppingCart shopCart) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.persist(shopCart);
            session.getTransaction().commit();
        }
    }

}
