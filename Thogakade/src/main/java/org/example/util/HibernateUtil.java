package org.example.util;

import org.example.entity.Customer;
import org.example.entity.Item;
import org.example.entity.Order;
import org.example.entity.OrderItem;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static{
        try{
            //Load configuration and mapping
            Configuration configuration = new Configuration();

            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Item.class);
            configuration.addAnnotatedClass(Order.class);
            configuration.addAnnotatedClass(OrderItem.class);

            //service registry
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            //crate session factory
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        }catch (Throwable ex){
            System.out.println("Session Factory created failed."+ex);
            throw new ExceptionInInitializerError(ex);

        }
    }
    // Method to get SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Method to close SessionFactory (optional for clean shutdown)
    public static void shutdown() {
        getSessionFactory().close();
    }


}
