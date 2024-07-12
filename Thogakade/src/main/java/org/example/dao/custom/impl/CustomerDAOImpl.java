package org.example.dao.custom.impl;

import org.example.dao.CrudUtil;
import org.example.dao.SuperDAO;
import org.example.dao.custom.CustomerDAO;
import org.example.entity.Customer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer customer) throws SQLException {
        try {
            return (Integer) CrudUtil.execute("INSERT INTO Customer (id, name, address, contact) VALUES (?, ?, ?, ?)",
                    customer.getId(), customer.getName(), customer.getAddress(), customer.getContact()) > 0;
        } catch (Exception e) {
            throw new SQLException("Failed to save customer", e);
        }
    }

    @Override
    public Customer search(String id) throws SQLException {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer WHERE id = ?", id);
            if (resultSet.next()) {
                return new Customer(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("contact")
                );
            }
            return null;
        } catch (Exception e) {
            throw new SQLException("Failed to search customer", e);
        }
    }

    @Override
    public boolean update(Customer entity) throws SQLException {
        try {
            return (Integer) CrudUtil.execute("UPDATE Customer SET name = ?, address = ?, contact = ? WHERE id = ?",
                    entity.getName(), entity.getAddress(), entity.getContact(), entity.getId()) > 0;
        } catch (Exception e) {
            throw new SQLException("Failed to update customer", e);
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        try {
            return (Integer) CrudUtil.execute("DELETE FROM Customer WHERE id = ?", id) > 0;
        } catch (Exception e) {
            throw new SQLException("Failed to delete customer", e);
        }
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        try {
            ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT * FROM Customer");
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("contact")
                ));
            }
            return customers;
        } catch (Exception e) {
            throw new SQLException("Failed to retrieve all customers", e);
        }
    }

    @Override
    public Customer get(String id) {
        return null;
    }
}
