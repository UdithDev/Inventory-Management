//package com.inventorysystem.dao;
//
//import com.inventorysystem.dao.custom.impl.CustomerDAOImpl;
//import com.inventorysystem.dao.custom.impl.ItemDAOImpl;
//import com.inventorysystem.dao.custom.impl.OrderDAOImpl;
//
//public class DAOFactory {
//    private static DAOFactory daoFactory;
//
//    private DAOFactory() {}
//
//    public static DAOFactory getDaoFactory() {
//        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
//    }
//
//    public enum DAOTypes {
//        CUSTOMER, ITEM, ORDER, ORDERITEMS
//    }
//
//    public SuperDAO getDAO(DAOTypes daoTypes) {
//        switch (daoTypes) {
//            case CUSTOMER:
//                return new CustomerDAOImpl();
//            case ITEM:
//                return new ItemDAOImpl();
//            case ORDER:
//                return new OrderDAOImpl();
//            default:
//                return null;
//        }
//    }
//}
