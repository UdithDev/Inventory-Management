package org.example.dao.custom.impl;

import com.sun.xml.bind.v2.model.core.ID;
import org.example.dao.custom.ItemDAO;
import org.example.entity.Customer;
import org.example.entity.Item;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class ItemDAOImpl implements ItemDAO {


    @Override
    public boolean save(Item item) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(item);
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
    public Item findById(ID id) {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            return session.get(Item.class, id);
        }
    }

    @Override
    public boolean update(Item item) {
       Session session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        try{
            session.update(item);
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
    public boolean delete(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Item item = session.load(Item.class, id);
            session.delete(item);
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
    public List<Item> getAll() {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            return session.createQuery("FROM Item ", Item.class).list();
        }
    }
}
