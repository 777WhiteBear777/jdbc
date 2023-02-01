package DAO.Impl;

import Connectivity.HibernateSession;
import DAO.CommonDAO;
import Model.UserDetails;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserDetailsHibernateDAO implements CommonDAO<UserDetails> {

    @Test
    @Override
    public List<UserDetails> getAll() {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            Query<UserDetails> query = session.createQuery("from UserDetails ", UserDetails.class);
            return query.list();
        }
    }

    @Test
    @Override
    public Long addObj(UserDetails obj) {
        Long id;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.persist(obj);
            id = obj.getId();
            session.getTransaction().commit();
        }
        return id;
    }

    @Test
    @Override
    public UserDetails getById(Long id) {
        UserDetails userDetails;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            userDetails = session.find(UserDetails.class, id);
        }

        return userDetails;
    }

    @Override
    public void update(UserDetails userDetails) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.merge(userDetails);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) {
        UserDetails userDetails;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            userDetails = session.find(UserDetails.class, id);
            session.remove(userDetails);
            session.getTransaction().commit();
        }
    }
}
