package org.example.bo.custom.impl;

import org.example.bo.custom.OrderBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.CustomerDAO;
import org.example.dao.custom.ItemDAO;
import org.example.dao.custom.OrderDetailsDAO;
import org.example.dao.custom.OrdersDAO;
import org.example.dto.CustomerDTO;
import org.example.dto.ItemDTO;
import org.example.dto.OrderDTO;
import org.example.dto.SuperDTO;
import org.example.entity.Customer;
import org.example.entity.Item;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    private final OrdersDAO ordersDAO;
    private final CustomerDAO customerDAO;
    private final ItemDAO itemDAO;
    private final OrderDetailsDAO orderDetailsDAO;

    public OrderBOImpl() {
        ordersDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
        orderDetailsDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
        customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    }

    @Override
    public String generateNextOrderId() throws SQLException {
        try (Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession()) {
            return ordersDAO.generateNextOrderId(session);
        }
    }

    @Override
    public List<String> loadCustomerIds() throws SQLException {
        try (Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession()) {
            session.beginTransaction();
            List<String> customerIds = customerDAO.loadCustomerIds(session);
            session.getTransaction().commit();
            return customerIds;
        } catch (Exception e) {
            throw new SQLException("Failed to load customer IDs.", e);
        }
    }

    @Override
    public CustomerDTO searchByCustomerId(String customerId) throws SQLException {
        try (Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession()) {
            session.beginTransaction();
            Customer customer = customerDAO.search(customerId, session);
            session.getTransaction().commit();
            if (customer == null) {
                return null;
            }
            return new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone());
        }
    }

    @Override
    public List<String> loadItemCodes() throws SQLException {
        try (Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession().getSession()) {
            session.beginTransaction();
            List<String> itemCodes = itemDAO.loadItemCodes(session);
            session.getTransaction().commit();
            return itemCodes;
        } catch (Exception e) {
            throw new SQLException("Failed to load Items code.", e);
        }
    }

    @Override
    public ItemDTO searchByItemCode(String itemCode) throws SQLException {
        try (Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession()) {
            session.beginTransaction();
            Item item = itemDAO.search(itemCode, session);
            session.getTransaction().commit();
            if (item == null) {
                return null;
            }
            return new ItemDTO(item.getCode(), item.getDescription(), item.getPrice(), item.getQtyOnHand());
        }
    }

    @Override
    public boolean placeOrder(OrderDTO orderDTO) throws SQLException {
        return false;
    }


    @Override
    public Boolean save(OrderDTO entity) {
        return null;
    }

    @Override
    public Boolean update(OrderDTO entity) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public List<OrderDTO> getAll() {
        return null;
    }
}
