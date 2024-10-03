package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.CustomerDTO;

import java.util.List;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO customerDTO);

    boolean updateCustomer(CustomerDTO customerDTO);

    boolean deleteCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO searchCustomer(String customerId);
}
