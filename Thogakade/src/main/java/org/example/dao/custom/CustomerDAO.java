package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer> {

    Customer get(String id);
}
