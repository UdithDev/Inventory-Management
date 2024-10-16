package org.example.util;

import org.example.entity.Customer;
import org.example.entity.Item;
import org.example.entity.Order;
import org.example.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfig {
    private static SessionFactoryConfig sessionFactoryConfig;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfig() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(OrderItem.class);

        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
            configuration.setProperties(properties);
        } catch (IOException e) {
            System.out.println("Hibernate properties file not found");
        }

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance() {
        if (sessionFactoryConfig == null) {
            sessionFactoryConfig = new SessionFactoryConfig();
        }
        return sessionFactoryConfig;
    }

    public final Session getSession() {
        return sessionFactory.openSession();
    }
}
