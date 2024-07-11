package com.thogakade.dao.custom.impl;

import com.thogakade.dao.SuperDAO;
import com.thogakade.dao.custom.CustomerDAO;
import com.thogakade.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer entity) throws SQLException {
        return false;
    }

    @Override
    public Customer search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Customer entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        return null;
    }
}
