package org.example.bo.custom.impl;

import org.example.bo.custom.CustomerBO;
import org.example.bo.util.Converter;
import org.example.dao.DAOFactory;
import org.example.dao.custom.CustomerDAO;
import org.example.dto.CustomerDTO;
import org.example.entity.Customer;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;


public class CustomerBOImpl implements CustomerBO {

    private final CustomerDAO customerDAO;

    public CustomerBOImpl() {
        customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    }

    @Override
    public Boolean save(CustomerDTO customerDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            customerDAO.save(Converter.getInstance().toCustomerEntity(customerDTO), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Customer not save");
        }
    }

    @Override
    public Boolean update(CustomerDTO customerDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            customerDAO.update(Converter.getInstance().toCustomerEntity(customerDTO), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Customer not updated");
        }

    }

    @Override
    public Boolean delete(String id) throws RuntimeException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
            transaction = session.beginTransaction(); // Start transaction
            customerDAO.delete(id, session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback only if the transaction is active
            }
            throw new RuntimeException("Customer not Deleted: " + exception.getMessage(), exception);
        }finally {
            if (session != null && session.isOpen()) {
                session.close(); // Close the session in the finally block to ensure it closes after all operations
            }
        }
    }


    @Override
    public List<CustomerDTO> getAll() throws RuntimeException {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        try (session) {
            List<Customer> customerList = customerDAO.getAll(session);
            if (customerList.size() > 0) {
                return customerList.stream().map(cusromer -> Converter.getInstance().tocustomerDTO(cusromer)).collect(Collectors.toList());
            }
        }
        throw new RuntimeException("Empty Customer Table!");
    }


    @Override
    public List<CustomerDTO> searchCustomerByText(String text) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        try (session) {
            List<Customer> searchCustomerByText = customerDAO.searchCustomerByText(text, session);
            if (searchCustomerByText.size() > 0) {
                return searchCustomerByText.stream().map(customer -> Converter.getInstance().tocustomerDTO(customer)).collect(Collectors.toList());
            }
            throw new RuntimeException("No any match found");
        }
    }


}