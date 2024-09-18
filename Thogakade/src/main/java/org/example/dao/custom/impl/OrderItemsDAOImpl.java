package org.example.dao.custom.impl;

import org.example.entity.OrderItem;
import org.example.dao.custom.OrderItemsDAO;

import java.sql.SQLException;
import java.util.List;

public class OrderItemsDAOImpl implements OrderItemsDAO {
    @Override
    public boolean save(OrderItem entity) throws SQLException {
        try {
            return CrudUtil.execute("INSERT INTO orderitem VALUES(?, ?, ?, ?)", entity.getOrderId(), entity.getItemCode(), entity.getQty(), entity.getUnitPrice());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderItem search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(OrderItem entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public List<OrderItem> getAll() throws SQLException {
        return null;
    }
}
