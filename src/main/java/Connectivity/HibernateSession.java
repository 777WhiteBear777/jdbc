package Connectivity;

import Model.*;
import com.mysql.cj.xdevapi.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.util.Properties;


public class HibernateSession {

          private static SessionFactory sessionFactory;

          private HibernateSession() {}

          public static SessionFactory getSessionFactory() {
               if (sessionFactory == null) {
                    try {
                         Configuration configuration = new Configuration().configure();
                         configuration.addAnnotatedClass(User.class);
                         configuration.addAnnotatedClass(UserDetails.class);
                         configuration.addAnnotatedClass(ShopCart.class);
                         configuration.addAnnotatedClass(Order.class);
                         configuration.addAnnotatedClass(Product.class);

                    } catch (Exception e) {
                         System.out.println("Исключение!" + e);
                    }
               }
               return sessionFactory;
          }
}
