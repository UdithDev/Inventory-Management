package org.example.dao.custom.impl;

import com.sun.xml.bind.v2.model.core.ID;
import org.example.dao.custom.OrdersDAO;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mysql.cj.conf.PropertyKey.logger;

public class OrderDAOImpl implements OrdersDAO {


    @Override
    public boolean save(Order entity, Session Session) {
        return false;
    }

    @Override
    public boolean update(Order entity, Session session) {
        return false;
    }

    @Override
    public Boolean delete(String id, Session session) {
        return false;
    }

    @Override
    public List<Order> getAll(Session session) {
        return null;
    }
}
