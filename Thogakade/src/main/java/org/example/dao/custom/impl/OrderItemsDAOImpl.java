package org.example.dao.custom.impl;

import com.sun.xml.bind.v2.model.core.ID;
import org.example.entity.OrderItem;
import org.example.dao.custom.OrderItemsDAO;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderItemsDAOImpl implements OrderItemsDAO {

    @Override
    public boolean save(OrderItem orderItem) {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(orderItem);
                transaction.commit();
                return true;
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public OrderItem findById(ID id) {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            return session.get(OrderItem.class, id);
        }
    }

    @Override
    public boolean update(OrderItem orderItem) {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(orderItem);
                transaction.commit();
                return true;
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                return false;
            }
        }
    }


    @Override
    public boolean delete(String id) {
        Transaction transaction = null;
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
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
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            return session.createQuery("FROM OrderItem", OrderItem.class).list();
        }
    }
}
