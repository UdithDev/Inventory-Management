package org.example.dao.custom.impl;


import com.sun.xml.bind.v2.model.core.ID;
import org.example.dao.custom.CustomerDAO;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer customer) {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(customer);
                transaction.commit();
                return true;
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                return false;
            }
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
        try(Session session=SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction=session.beginTransaction();

            try{
                session.save(customer);
                transaction.commit();
                return true;
            }catch (Exception e){
                if(transaction!=null) transaction.rollback();
                return false;
            }
        }
    }



    @Override
    public boolean delete(String id) {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Customer customer = session.load(Customer.class, id);
                session.delete(customer);
                transaction.commit();
                return true;
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public List<Customer> getAll() {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            return session.createQuery("FROM Customer", Customer.class).list();
        }
    }
}
