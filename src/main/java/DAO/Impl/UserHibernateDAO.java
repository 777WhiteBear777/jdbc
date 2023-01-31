package DAO.Impl;

import Connectivity.HibernateSession;
import DAO.UserDAO;
import Model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserHibernateDAO implements UserDAO {


    @Override

    @Test
    public List<User> getAllUser() {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User ", User.class);
            return query.list();
        }
    }

    @Override
    public Integer addUser(User user) {
        int id;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.persist(user);
            id = user.getId();
            session.getTransaction().commit();
            return id;
        }
    }

    @Override
    public User getUser(int id) {
        User user;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            user = session.find(User.class, id);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.merge(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteUser(int id) {
        User user;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            user = session.find(User.class, id);
            session.remove(session.contains(user) ? user : session.merge(user));
            session.getTransaction().commit();
        }

    }
}
