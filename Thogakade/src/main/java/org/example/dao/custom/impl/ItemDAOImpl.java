package org.example.dao.custom.impl;

import com.sun.xml.bind.v2.model.core.ID;
import org.example.dao.custom.ItemDAO;
import org.example.entity.Customer;
import org.example.entity.Item;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean save(Item item, Session session) {
        session.save(item);
        return true;
    }

    @Override
    public boolean update(Item item, Session session) {
        session.update(item);
        return true;
    }

    @Override
    public Boolean delete(String id, Session session) {
        Item item = session.get(Item.class, id);
        if (item == null) {
            throw new RuntimeException("Item with ID " + id + " not found, cannot delete.");
        }
        session.delete(item);
        System.out.println("item successfully deleted in DAO.");
        return true;
    }

    @Override
    public List<Item> getAll(Session session) {
        String sql = "FROM Item ";
        Query query = session.createQuery(sql);
        List<Item> list = query.list();
        return list;

    }

    @Override
    public Item search(String id) {
        return null;
    }

    @Override
    public List<Item> searchItemByText(String text, Session session) {
        Query query = session.createQuery("FROM Item  WHERE description LIKE '%" + text + "%'");
        List<Item> list = query.list();
        return list;
    }
}
