package org.example.bo.custom.impl;

import org.example.bo.custom.CustomerBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.CustomerDAO;
import org.example.dto.CustomerDTO;
import org.example.entity.Customer;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {


    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public void addCustomer(CustomerDTO customer) throws Exception {
        customerDAO.save(new Customer(customer.getId(), customer.getName(), customer.getAddress(), customer.getContact()));
    }

    @Override
    public void updateCustomer(CustomerDTO customer) throws Exception {
        customerDAO.update(new Customer(customer.getId(), customer.getName(), customer.getAddress(), customer.getContact()));
    }

    @Override
    public void deleteCustomer(String id) throws Exception {
        customerDAO.delete(id);
    }

    @Override
    public CustomerDTO getCustomer(String id) throws Exception {
        Customer customer = customerDAO.search(id);
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getContact());
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customer> customers = customerDAO.getAll();
        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getContact()));
        }
        return customerDTOS;
    }
}
