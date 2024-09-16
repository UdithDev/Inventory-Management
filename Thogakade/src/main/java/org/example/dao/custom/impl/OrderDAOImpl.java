package org.example.dao.custom.impl;

import org.example.dao.custom.OrdersDAO;
import org.example.entity.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrdersDAO {
    @Override
    public boolean save(Order entity) throws SQLException {
        return false;
    }

    @Override
    public Order search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Order entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException {
        return null;
    }
}
