package org.example.dao;

import org.example.dao.SuperDAO;
import org.example.dao.custom.impl.CustomerDAOImpl;
import org.example.dao.custom.impl.ItemDAOImpl;
import org.example.dao.custom.impl.OrderDAOImpl;
import org.example.dao.custom.impl.OrderItemsDAOImpl;
import org.example.dao.util.exception.DaoNotFoundException;

public class DAOFactory {

    // Singleton pattern to ensure only one instance of the factory exists
    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, ITEM, ORDER, ORDERITEM
    }

    // Factory method to return the appropriate DAO implementation
    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return(T) new CustomerDAOImpl();  // Return Customer DAO implementation
            case ITEM:
                return(T) new ItemDAOImpl();  // Return Item DAO implementation
            case ORDER:
                return (T)new OrderDAOImpl();  // Return Order DAO implementation
            case ORDERITEM:
                return(T) new OrderItemsDAOImpl();  // Return OrderItem DAO implementation
            default:
                throw new DaoNotFoundException("DAO not found");
        }
    }
}
