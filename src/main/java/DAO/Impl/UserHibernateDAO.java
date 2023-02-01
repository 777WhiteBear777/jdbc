package DAO.Impl;

import Connectivity.HibernateSession;
import DAO.CommonDAO;
import Model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserHibernateDAO implements CommonDAO<User> {

    @Test
    @Override
    public List<User> getAll() {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User ", User.class);
            return query.list();
        }
    }

    @Test
    @Override
    public Long addObj(User user) {
        Long id;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.persist(user);
            id = user.getId();
            session.getTransaction().commit();
            return id;
        }
    }

    @Test
    @Override
    public User getById(Long id) {
        User user;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            user = session.find(User.class, id);
        }
        return user;
    }

    @Override
    public void update(User user) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.merge(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) {
        User user;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            user = session.find(User.class, id);
            session.remove(session.contains(user) ? user : session.merge(user));
            session.getTransaction().commit();
        }
    }
}
