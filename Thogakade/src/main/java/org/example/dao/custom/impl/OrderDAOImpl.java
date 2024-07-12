package org.example.dao.custom.impl;

import org.example.dao.SuperDAO;
import org.example.dao.custom.OrdersDAO;
import org.example.entity.Orders;

import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrdersDAO {
    @Override
    public boolean save(Orders entity) throws SQLException {
        return false;
    }

    @Override
    public Orders search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Orders entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public List<Orders> getAll() throws SQLException {
        return null;
    }
}
