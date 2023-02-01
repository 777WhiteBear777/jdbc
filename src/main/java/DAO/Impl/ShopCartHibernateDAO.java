package DAO.Impl;

import Connectivity.HibernateSession;
import DAO.ShopCartDAO;
import Model.ShopCart;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShopCartHibernateDAO implements ShopCartDAO {

    @Test
    @Override
    public List<ShopCart> getAllProductByUser(Long userId) {
        try (Session session = HibernateSession.getSessionFactory().openSession()){
            Query<ShopCart> query = session.createQuery("from ShopCart where userId = :id", ShopCart.class);
            query.setParameter("id",userId);
            return query.list();
        }
    }

    @Override
    public void deleteAllProductByUser(Long userId) {
        String hql = "DELETE FROM ShopCart where userId = :user_id";
        try (Session session = HibernateSession.getSessionFactory().openSession()){
            Query<ShopCart> query = session.createQuery(hql, ShopCart.class);
            query.setParameter("user_id",userId);
        }


    }


    @Override
    public void deleteShopCart(Long userId, Long productId) {
        ShopCart shopCart = new ShopCart();
        try (Session session = HibernateSession.getSessionFactory().openSession()){
            session.getTransaction().begin();
            shopCart.setUserId(userId);
            shopCart.setProductId(productId);
            session.remove(shopCart);
            session.getTransaction().commit();
        }

    }

    @Override
    public void addShopCart(ShopCart shopCart) {
            try (Session session = HibernateSession.getSessionFactory().openSession()){
                session.getTransaction().begin();
                session.persist(shopCart);
                session.getTransaction().commit();
            }
    }
}
