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
    public String generateNextOrderId(Session session) throws SQLException {
       Query query= session.createQuery("SELECT orderId from order_items ORDER BY id DESC limit 1");
        ResultSet resultSet =;

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);


        return null;
    }
}
