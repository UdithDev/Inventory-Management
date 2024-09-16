package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Orders;

import java.sql.SQLException;

public interface OrdersDAO extends CrudDAO<Orders> {
    String generateNextId() throws SQLException;
}
