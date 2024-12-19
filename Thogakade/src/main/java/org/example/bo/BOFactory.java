package org.example.bo;

import org.example.bo.custom.impl.CustomerBOImpl;
import org.example.bo.custom.impl.ItemBOImpl;
import org.example.bo.custom.impl.OrderBOImpl;
import org.example.bo.util.exception.ServiceNotFoundException;

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

    public <T extends SuperBO> T getBO(BOTypes boTypes)  throws ServiceNotFoundException {
        switch (boTypes) {
            case CUSTOMER:
                return(T) new CustomerBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case ORDER:
                return (T)new OrderBOImpl();

            case ORDERITEMS:
            default:
                throw new ServiceNotFoundException("Service not found");
        }
    }
}
