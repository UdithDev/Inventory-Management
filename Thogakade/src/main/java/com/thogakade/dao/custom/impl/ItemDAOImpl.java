package com.thogakade.dao.custom.impl;

import com.thogakade.dao.custom.ItemDAO;
import com.thogakade.entity.Item;

import java.sql.SQLException;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item entity) throws SQLException {
        return false;
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
