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
        String hql = "DELETE FROM ShoppingCart where userId = :user_Id";
        String hql1 = "delete from ShoppingCart where userId = :userId";
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            // не работает
//            session.createQuery(hql1, ShoppingCart.class).setParameter("userId", userId).executeUpdate();
            //работает
            session.getTransaction().begin();
            List<ShoppingCart> list = session.createQuery("from ShoppingCart where userId = :id",ShoppingCart.class)
                    .setParameter("id",userId)
                    .list();
            for (ShoppingCart shoppingCart : list) {
                session.remove(shoppingCart);
            }
            session.getTransaction().commit();
            // не работает
//            Query<ShoppingCart> query = session.createQuery("DELETE FROM ShoppingCart WHERE userId = :id", ShoppingCart.class)
//                    .setParameter("id", userId);
//            int delete = query.executeUpdate();Ы
//            System.out.println(delete);
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
