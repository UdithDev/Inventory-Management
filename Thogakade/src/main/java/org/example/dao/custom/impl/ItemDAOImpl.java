package org.example.dao.custom.impl;

import org.example.dao.CrudUtil;
import org.example.dao.SuperDAO;
import org.example.dao.custom.ItemDAO;
import org.example.entity.Item;

import java.sql.SQLException;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item entity) throws SQLException {
        try {
            return (Integer) CrudUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?, ?, ?, ?)",
                    entity.getCode(), entity.getDescription(), entity.getUnitPrice(), entity.getQtyOnHand()) > 0;
        } catch (Exception e) {
            throw new SQLException("Failed to save item", e);
        }
    }

    @Override
    public Item search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Item entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public List<Item> getAll() throws SQLException {
        return null;
    }
}
