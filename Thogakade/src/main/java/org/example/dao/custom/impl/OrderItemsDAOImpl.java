package org.example.dao.custom.impl;

import com.sun.xml.bind.v2.model.core.ID;
import org.example.entity.OrderItem;
import org.example.dao.custom.OrderItemsDAO;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class OrderItemsDAOImpl implements OrderItemsDAO {

    @Override
    public boolean save(OrderItem orderItem) {
        Transaction transaction = null;
        try (Session session = SessionFactoryConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(orderItem);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public OrderItem findById(ID id) {
        try (Session session = SessionFactoryConfig.getSessionFactory().openSession()) {
            return session.get(OrderItem.class, id);
        }
    }

    @Override
    public boolean update(OrderItem orderItem) {
        Transaction transaction = null;
        try (Session session = SessionFactoryConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(orderItem);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(ID id) {
        Transaction transaction = null;
        try (Session session = SessionFactoryConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            OrderItem orderItem = session.get(OrderItem.class, id);
            if (orderItem != null) {
                session.delete(orderItem);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<OrderItem> getAll() {
        try (Session session = SessionFactoryConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM OrderItem", OrderItem.class).list();
        }
    }
}
