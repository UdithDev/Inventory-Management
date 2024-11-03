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
    public boolean save(OrderItem entity, Session Session) {
        return false;
    }

    @Override
    public boolean update(OrderItem entity, Session session) {
        return false;
    }

    @Override
    public Boolean delete(String id, Session session) {
        return false;
    }

    @Override
    public List<OrderItem> getAll(Session session) {
        return null;
    }
}
