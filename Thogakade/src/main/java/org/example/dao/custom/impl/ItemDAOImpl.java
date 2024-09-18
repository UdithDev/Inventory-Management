package org.example.dao.custom.impl;

import org.example.dao.custom.ItemDAO;
import org.example.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public Item search(String code) throws SQLException {
        try {
            ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT * FROM Item WHERE code = ?", code);
            if (resultSet.next()) {
                return new Item(
                        resultSet.getString("code"),
                        resultSet.getString("description"),
                        resultSet.getDouble("unitPrice"),
                        resultSet.getInt("qtyOnHand")
                );
            }
            return null;
        } catch (Exception e) {
            throw new SQLException("Failed to search item", e);
        }
    }

    @Override
    public boolean update(Item entity) throws SQLException {
        try {
            return (Integer) CrudUtil.execute("UPDATE Item SET description = ?, unitPrice = ?, qtyOnHand = ? WHERE code = ?",
                    entity.getDescription(), entity.getUnitPrice(), entity.getQtyOnHand(), entity.getCode()) > 0;
        } catch (Exception e) {
            throw new SQLException("Failed to update item", e);
        }
    }

    @Override
    public boolean delete(String code) throws SQLException {
        try {
            return (Integer) CrudUtil.execute("DELETE FROM Item WHERE code = ?", code) > 0;
        } catch (Exception e) {
            throw new SQLException("Failed to delete item", e);
        }
    }

    @Override
    public List<Item> getAll() throws SQLException {
        try {
            ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT * FROM Item");
            List<Item> items = new ArrayList<>();
            while (resultSet.next()) {
                items.add(new Item(
                        resultSet.getString("code"),
                        resultSet.getString("description"),
                        resultSet.getDouble("unitPrice"),
                        resultSet.getInt("qtyOnHand")
                ));
            }
            return items;
        } catch (Exception e) {
            throw new SQLException("Failed to retrieve all items", e);
        }

    }
}
