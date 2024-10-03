package org.example.dao.custom.impl;


import com.sun.xml.bind.v2.model.core.ID;
import org.example.dao.custom.CustomerDAO;
import org.example.entity.Customer;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer customer) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(customer);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public Customer findById(ID id) {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            return session.get(Customer.class, id);
        }
    }

    @Override
    public boolean update(Customer customer) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(customer);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            return false;
        }
    }



    @Override
    public boolean delete(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Customer customer = session.load(Customer.class, id);
            session.delete(customer);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public List<Customer> getAll() {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            return session.createQuery("FROM Customer", Customer.class).list();
        }
    }
}
