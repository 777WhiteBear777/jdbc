package DAO.Impl;

import Connectivity.HibernateSession;
import DAO.OrderDAO;
import Model.Order;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class OrderHibernateDAO implements OrderDAO {
    @Override
    public List<Order> getAllOrder() {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            return session.createQuery("from Order", Order.class).list();
        }
    }

    @Override
    public List<Order> getAllOrderByUser(Long userId) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            Query<Order> query = session.createQuery("from Order where userId = :userId", Order.class);
            query.setParameter("userid", userId);
            return query.list();
        }
    }

    @Override
    public Long addOrder(Order order) throws SQLException {
        Long id;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.persist(order);
            id = order.getId();
            session.getTransaction().commit();
            return id;
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
    }
}
