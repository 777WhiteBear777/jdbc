package dao.Impl;

import connectivity.HibernateSession;
import dao.CommonDAO;
import model.UserDetails;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDetailsHibernateDAO implements CommonDAO<UserDetails> {

    @Override
    public List<UserDetails> getAll() {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            Query<UserDetails> query = session.createQuery("from UserDetails ", UserDetails.class);
            return query.list();
        }
    }

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
