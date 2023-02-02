package dao.Impl;

import connectivity.HibernateSession;
import dao.OrderDAO;
import model.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class OrderHibernateDAO implements OrderDAO {

    @Override
    public List<Order> getAllOrder() {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            return session.createQuery("FROM Order", Order.class).list();
        }
    }

    @Override
    public List<Order> getAllOrderByUser(Long userId) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            Query<Order> query = session.createQuery("from Order where userId = :userId", Order.class);
            query.setParameter("userId", userId);
            return query.list();
        }
    }

    @Override
    public Long addOrder(Order order) throws SQLException {
        Long id = null;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(order);
            id = order.getId();
            transaction.commit();
            return id;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

}
