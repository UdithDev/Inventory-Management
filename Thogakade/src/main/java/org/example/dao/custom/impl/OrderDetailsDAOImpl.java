package org.example.dao.custom.impl;

import org.example.entity.OrderDetails;
import org.example.dao.custom.OrderDetailsDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public boolean save(OrderDetails orderDetails, Session session) {

        try {
            session.save(orderDetails);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OrderDetails orderDetails, Session session) {
        try {
            session.update(orderDetails);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean delete(String id, Session session) {
        try {
            OrderDetails orderDetails = session.get(OrderDetails.class, id);
            if (orderDetails != null) {
                session.delete(orderDetails);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<OrderDetails> getAll(Session session) {
        try {
            Query<OrderDetails> query = session.createQuery("FROM OrderDetails", OrderDetails.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OrderDetails search(String id, Session session) {
        try {
            return session.get(OrderDetails.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
