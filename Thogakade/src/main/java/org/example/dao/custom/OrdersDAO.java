package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Orders;
import org.hibernate.Session;

import java.sql.SQLException;

public interface OrdersDAO extends CrudDAO<Orders> {
    String generateNextOrderId(Session session) throws SQLException;
}
