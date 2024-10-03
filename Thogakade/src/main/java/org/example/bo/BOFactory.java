package org.example.bo;

import org.example.bo.SuperBO;
import org.example.bo.custom.impl.CustomerBOImpl;
import org.example.bo.custom.impl.ItemBOImpl;
import org.example.bo.custom.impl.OrderBOImpl;
import org.example.bo.custom.impl.OrderItemsBOImpl;
import org.example.dao.custom.impl.OrderItemsDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;


    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER, ITEM, ORDER, ORDERITEMS
    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDER:
                return new OrderBOImpl();

            case ORDERITEMS:
                return new OrderItemsBOImpl();
            default:
                return null;
        }
    }
}
