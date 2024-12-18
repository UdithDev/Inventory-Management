package org.example.dao.custom.impl;


import com.sun.xml.bind.v2.model.core.ID;
import org.example.dao.custom.CustomerDAO;
import org.example.entity.Customer;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer customer, Session session) throws RuntimeException {
        session.save(customer);
        return true;
    }


    @Override
    public boolean update(Customer customer, Session session) throws RuntimeException {
        session.update(customer);
        return true;
    }

    @Override
    public Boolean delete(String id, Session session) throws RuntimeException {
        Customer customer = session.get(Customer.class, id);
        if (customer == null) {
            throw new RuntimeException("Customer with ID " + id + " not found, cannot delete.");
        }
        session.delete(customer);
        System.out.println("Customer successfully deleted in DAO.");
        return true;
    }

    @Override
    public List<Customer> getAll(Session session) {
        String sql = "FROM Customer ";
        Query query = session.createQuery(sql);
        List<Customer> list = query.list();
        return list;
    }


    @Override
    public List<Customer> searchCustomerByText(String text, Session session) {
        Query query = session.createQuery("FROM Customer  WHERE name LIKE '%" + text + "%'");
        List<Customer> list = query.list();
        return list;
    }
}
