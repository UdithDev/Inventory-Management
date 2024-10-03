package org.example.bo.custom.impl;

import org.example.bo.custom.CustomerBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.CustomerDAO;
import org.example.dto.CustomerDTO;
import org.example.entity.Customer;

import java.util.ArrayList;
import java.util.List;


public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);


    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getEmail(),
                customerDTO.getPhone());
        return customerDAO.save(customer);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getEmail(),
                customerDTO.getPhone());

        return customerDAO.update(customer);

    }

    @Override
    public boolean deleteCustomer(CustomerDTO customerDTO) {
        return customerDAO.delete(customerDTO.getId());
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customer> customers = customerDAO.getAll();

        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getEmail()));
        }

        return customerDTOS;
    }

    @Override
    public CustomerDTO searchCustomer(String customerId) {
        return null;
    }
}