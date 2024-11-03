package org.example.dao.custom;


import org.example.dao.CrudDAO;
import org.example.entity.Customer;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    List<Customer> searchCustomerByText(String text, Session session);
}
