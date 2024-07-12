package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {
    void addCustomer(CustomerDTO customer) throws Exception;
    void updateCustomer(CustomerDTO customer) throws Exception;
   void deleteCustomer(String id) throws Exception;
    CustomerDTO getCustomer(String id) throws Exception;
    ArrayList<CustomerDTO> getAllCustomers() throws Exception;
}
