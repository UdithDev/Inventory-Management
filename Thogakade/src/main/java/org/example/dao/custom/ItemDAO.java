package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Customer;
import org.example.entity.Item;
import org.hibernate.Session;

import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
    List<Item> searchItemByText(String text, Session session);
}
