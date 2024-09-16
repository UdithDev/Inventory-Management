//package com.inventorysystem.bo;
//
//import com.inventorysystem.bo.custom.impl.CustomerBOImpl;
//import com.inventorysystem.bo.custom.impl.ItemBOImpl;
//import com.inventorysystem.bo.custom.impl.OrderBOImpl;
//
//public class BOFactory {
//    private static BOFactory boFactory;
//
//
//    private BOFactory() {
//    }
//
//    public static BOFactory getBoFactory() {
//        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
//    }
//
//    public enum BOTypes{
//        CUSTOMER, ITEM, ORDER
//    }
//
//    public SuperBO getBO(BOTypes boTypes) {
//        switch (boTypes) {
//            case CUSTOMER:
//                return new CustomerBOImpl();
//            case ITEM:
//                return new ItemBOImpl();
//            case ORDER:
//                return new OrderBOImpl();
//            default:
//                return null;
//        }
//    }
//}
