package Connectivity;

import Model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateSession {

    private static SessionFactory sessionFactory;

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
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
