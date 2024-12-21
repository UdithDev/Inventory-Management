package org.example.util;

import org.example.entity.Customer;
import org.example.entity.Item;
import org.example.entity.Orders;
import org.example.entity.OrderDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
                addAnnotatedClass(Orders.class).
                addAnnotatedClass(OrderDetails.class);
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
