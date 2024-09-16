//package com.inventorysystem.dao.custom.impl;
//
//import com.inventorysystem.dao.CrudUtil;
//import com.inventorysystem.dao.custom.CustomerDAO;
//import com.inventorysystem.entity.Customer;
//
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomerDAOImpl implements CustomerDAO {
//    @Override
//    public boolean save(Customer customer) throws SQLException {
//        try {
//            return (Integer) CrudUtil.execute("INSERT INTO Customer (id, name, address, contact) VALUES (?, ?, ?, ?)",
//                    customer.getId(), customer.getName(), customer.getAddress(), customer.getContact()) > 0;
//        } catch (Exception e) {
//            throw new SQLException("Failed to save customer", e);
//        }
//    }
//
//    @Override
//    public Customer search(String id) throws SQLException {
//        try {
//            ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer WHERE id = ?", id);
//            if (resultSet.next()) {
//                return new Customer(
//                        resultSet.getString("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("address"),
//                        resultSet.getString("contact")
//                );
//            }
//            return null;
//        } catch (Exception e) {
//            throw new SQLException("Failed to search customer", e);
//        }
//    }
//
//    @Override
//    public boolean update(Customer entity) throws SQLException {
//        try {
//            return (Integer) CrudUtil.execute("UPDATE Customer SET name = ?, address = ?, contact = ? WHERE id = ?",
//                    entity.getName(), entity.getAddress(), entity.getContact(), entity.getId()) > 0;
//        } catch (Exception e) {
//            throw new SQLException("Failed to update customer", e);
//        }
//    }
//
//    @Override
//    public boolean delete(String id) throws SQLException {
//        try {
//            return (Integer) CrudUtil.execute("DELETE FROM Customer WHERE id = ?", id) > 0;
//        } catch (Exception e) {
//            throw new SQLException("Failed to delete customer", e);
//        }
//    }
//
//    @Override
//    public List<Customer> getAll() throws SQLException {
//        try {
//            ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT * FROM Customer");
//            List<Customer> customers = new ArrayList<>();
//            while (resultSet.next()) {
//                customers.add(new Customer(
//                        resultSet.getString("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("address"),
//                        resultSet.getString("contact")
//                ));
//            }
//            return customers;
//        } catch (Exception e) {
//            throw new SQLException("Failed to retrieve all customers", e);
//        }
//    }
//
//    @Override
//    public Customer get(String id) {
//        return null;
//    }
//
//    @Override
//    public List<String> loadCustomerIds() throws SQLException {
//        List<String> arrayList = new ArrayList<>();
//        ResultSet rst = null;
//        try {
//            rst = CrudUtil.execute("SELECT id FROM customers ORDER BY id ASC");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        while (rst.next()) {
//            arrayList.add(rst.getString(1));
//        }
//        return arrayList;
//    }
//}
