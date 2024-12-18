package org.example.bo.custom.impl;

import org.example.bo.custom.OrderBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.CustomerDAO;
import org.example.dao.custom.ItemDAO;
import org.example.dao.custom.OrdersDAO;
import org.example.dto.CustomerDTO;
import org.example.dto.ItemDTO;
import org.example.dto.OrderDTO;
import org.example.dto.SuperDTO;

import java.sql.SQLException;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    private final OrdersDAO ordersDAO;
    private final CustomerDAO customerDAO;
    private final ItemDAO itemDAO;

    public OrderBOImpl() {
        ordersDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
        customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    }

    @Override
    public String generateNextOrderId() throws SQLException {
        return null;
    }

    @Override
    public List<String> loadCustomerIds() throws SQLException {
        return null;
    }

    @Override
    public CustomerDTO searchByCustomerId(String customerId) throws SQLException {
        return null;
    }

    @Override
    public List<String> loadItemCodes() throws SQLException {
        return null;
    }

    @Override
    public ItemDTO searchByItemCode(String itemCode) throws SQLException {
        return null;
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