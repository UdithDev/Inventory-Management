package org.example.bo.custom.impl;

import org.example.bo.custom.ItemBO;
import org.example.bo.util.Converter;
import org.example.dao.DAOFactory;
import org.example.dao.SuperDAO;
import org.example.dao.custom.ItemDAO;
import org.example.dto.CustomerDTO;
import org.example.dto.ItemDTO;
import org.example.dto.SuperDTO;
import org.example.entity.Customer;
import org.example.entity.Item;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemBOImpl implements ItemBO {
    private final ItemDAO itemDAO;

    public ItemBOImpl() {
        itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    }


    @Override
    public Boolean save(ItemDTO itemDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            itemDAO.save(Converter.getInstance().toItemEntity(itemDTO), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Customer not save");
        }
    }

    @Override
    public Boolean update(ItemDTO itemDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            itemDAO.update(Converter.getInstance().toItemEntity(itemDTO), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Item not updated");
        }
    }


    @Override
    public Boolean delete(String id) throws RuntimeException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
            transaction = session.beginTransaction(); // Start transaction
            itemDAO.delete(id, session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback only if the transaction is active
            }
            throw new RuntimeException("Item not Deleted: " + exception.getMessage(), exception);
        }finally {
            if (session != null && session.isOpen()) {
                session.close(); // Close the session in the finally block to ensure it closes after all operations
            }
        }
    }

    @Override
    public List <ItemDTO>getAll() {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        try (session) {
            List<Item> itemList = itemDAO.getAll(session);
            if (itemList.size() > 0) {
                return itemList.stream().map(item -> Converter.getInstance().toItemDTO(item)).collect(Collectors.toList());
            }
        }
        throw new RuntimeException("Empty Student list!");
    }

    @Override
    public List<ItemDTO> searchItemByText(String text) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        try (session) {
            List<Item> searchItemByText = itemDAO.searchItemByText(text,session);
            if (searchItemByText.size() > 0) {
                return searchItemByText.stream().map(item -> Converter.getInstance().toItemDTO(item)).collect(Collectors.toList());
            }
            throw new RuntimeException("No any match found");
        }
    }
}
