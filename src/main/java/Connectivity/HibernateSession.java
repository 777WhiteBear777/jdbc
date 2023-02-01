package Connectivity;

import Model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateSession {

    private static SessionFactory sessionFactory;
    private final static Logger LOGGER = LogManager.getLogger(JDBC.class.getName());

    private HibernateSession() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().
                        addAnnotatedClass(User.class).
                        addAnnotatedClass(UserDetails.class).
                        addAnnotatedClass(Product.class).
                        addAnnotatedClass(ShopCart.class).
                        addAnnotatedClass(Order.class).
                        buildSessionFactory();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        return sessionFactory;
    }
}
