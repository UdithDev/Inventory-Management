package org.example.util;

import org.example.entity.Customer;
import org.example.entity.Item;
import org.example.entity.Order;
import org.example.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfig {
    private static SessionFactoryConfig sessionFactoryConfig;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfig() {
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.loadProperties("hibernate.properties");
        MetadataSources metadataSources = new MetadataSources(serviceRegistryBuilder.build());
        metadataSources.
                addAnnotatedClass(Customer.class).
                addAnnotatedClass(Item.class).
                addAnnotatedClass(Order.class).
                addAnnotatedClass(OrderItem.class);
        Metadata metadata = metadataSources.getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactoryConfig getSessionFactoryConfig(){
        return sessionFactoryConfig==null? sessionFactoryConfig=new SessionFactoryConfig():sessionFactoryConfig;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
