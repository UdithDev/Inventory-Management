package org.example.dao.custom.impl;

import com.sun.xml.bind.v2.model.core.ID;
import org.example.dao.custom.OrdersDAO;
import org.example.entity.Customer;
import org.example.entity.Orders;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mysql.cj.conf.PropertyKey.logger;

public class OrderDAOImpl implements OrdersDAO {


    private static String splitOrderId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("Or0");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "Or0" + id;
        }
        return "Or01";
    }

    @Override
    public boolean save(Orders entity, Session Session) {
        return false;
    }

    @Override
    public boolean update(Orders entity, Session session) {
        return false;
    }

    @Override
    public Boolean delete(String id, Session session) {
        return null;
    }

    @Override
    public List<Orders> getAll(Session session) {
        return null;
    }


    @Override
    public String generateNextOrderId(Session session) {
        Query<String> query = session.createQuery("SELECT id FROM Orders ORDER BY id DESC", String.class);
        query.setMaxResults(1);// Fetch the most recent order ID
        List<String> resultSet = query.list();
        return (resultSet.isEmpty()) ? splitOrderId(null) : splitOrderId(resultSet.get(0));

    }
}
