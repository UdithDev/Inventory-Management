package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.CustomerDTO;
import org.example.dto.ItemDTO;
import org.example.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {
    String generateNextOrderId() throws SQLException;

    List<String> loadCustomerIds() throws SQLException;

    CustomerDTO searchByCustomerId(String customerId) throws SQLException;

    List<String> loadItemCodes() throws SQLException;

    ItemDTO searchByItemCode(String itemCode) throws SQLException;

    boolean placeOrder(OrdersDTO orderDTO) throws SQLException;
}
