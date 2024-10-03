package org.example.dao.custom.impl;

import com.sun.xml.bind.v2.model.core.ID;
import org.example.dao.custom.OrdersDAO;
import org.example.entity.Order;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAOImpl implements OrdersDAO {


    @Override
    public boolean save(Order order) {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(order);
                transaction.commit();
                return true;
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                // Log the exception
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, e);
                return false;
            }
        }

    }

    @Override
    public Order findById(ID id) {
        return null;
    }

    @Override
    public boolean update(Order entity) {
        try (){

        }
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
