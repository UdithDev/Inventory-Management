package org.example.bo.custom.impl;

import javafx.scene.control.Alert;
import org.example.bo.custom.OrderBO;
import org.example.bo.util.Converter;
import org.example.dao.DAOFactory;
import org.example.dao.custom.CustomerDAO;
import org.example.dao.custom.ItemDAO;
import org.example.dao.custom.OrderDetailsDAO;
import org.example.dao.custom.OrdersDAO;
import org.example.dto.*;
import org.example.entity.Customer;
import org.example.entity.Item;
import org.example.entity.OrderDetails;
import org.example.entity.Orders;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    private final OrdersDAO ordersDAO;
    private final CustomerDAO customerDAO;
    private final ItemDAO itemDAO;
    private final OrderDetailsDAO orderDetailsDAO;
    private final Converter converter;

    public OrderBOImpl() {
        ordersDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
        orderDetailsDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
        customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
        converter = Converter.getInstance();
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
            return converter.tocustomerDTO(customer);
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
            return converter.toItemDTO(item);
        }
    }

    @Override
    public boolean placeOrder(OrderDTO orderDTO) throws SQLException {
        try (Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession()) {
            session.beginTransaction();

            Orders orderEntity = converter.toOrdersEntity(
                    orderDTO, customerDAO.search(orderDTO.getCustomerId(), session), null
            );
            boolean isOrderSave = ordersDAO.save(orderEntity, session);
            if (!isOrderSave) {
                session.getTransaction().rollback();
                return false;
            }

            for (OrderDetailsDTO detailsDTO : orderDTO.getOrderDetails()) {
                Item item = itemDAO.search(detailsDTO.getItem(), session);
                if (item == null) {
                    session.getTransaction().rollback();
                    throw new SQLException("Item with code " + detailsDTO.getItem() + " not found.");
                }

                int updatedQty = item.getQtyOnHand() - detailsDTO.getQuantity();
                if (updatedQty < 0) {
                    session.getTransaction().rollback();
                    throw new SQLException("Insufficient stock for item: " + item.getCode());
                }

                item.setQtyOnHand(updatedQty);
                boolean isItemUpdated = itemDAO.update(item, session);
                if (!isItemUpdated) {
                    session.getTransaction().rollback();
                    return false;
                }

                OrderDetails orderDetailsEntity = converter.toOrderDetailsEntity(detailsDTO, orderEntity);
                boolean isOrderDetailsSaved = orderDetailsDAO.save(orderDetailsEntity, session);
                if (!isOrderDetailsSaved) {
                    session.getTransaction().rollback();
                    return false;
                }
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            showErrorAlert("Failed to place the order: " + e.getMessage());
            throw new SQLException("Failed to place the order.", e);
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Order Placement Error");
        alert.setContentText(message);
        alert.showAndWait();
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
