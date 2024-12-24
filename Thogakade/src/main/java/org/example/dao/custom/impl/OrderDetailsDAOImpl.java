package org.example.dao.custom.impl;

import org.example.entity.OrderDetails;
import org.example.dao.custom.OrderDetailsDAO;
import org.hibernate.Session;


import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public boolean save(OrderDetails entity, Session Session) {
        return false;
    }

    @Override
    public boolean update(OrderDetails entity, Session session) {
        return false;
    }

    @Override
    public Boolean delete(String id, Session session) {
        return false;
    }

    @Override
    public List<OrderDetails> getAll(Session session) {
        return null;
    }

    @Override
    public OrderDetails search(String id, Session session) {
        return null;
    }
}
