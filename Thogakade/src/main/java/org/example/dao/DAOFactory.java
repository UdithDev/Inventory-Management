
import org.example.dao.SuperDAO;
import org.example.dao.custom.impl.ItemDAOImpl;
import org.example.dao.custom.impl.OrderDAOImpl;

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
    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return new CustomerDAOImpl();  // Return Customer DAO implementation
            case ITEM:
                return new ItemDAOImpl();  // Return Item DAO implementation
            case ORDER:
                return new OrderDAOImpl();  // Return Order DAO implementation
            case ORDERITEM:
                return new OrderItemDAOImpl();  // Return OrderItem DAO implementation
            default:
                return null;  // Return null if no matching DAO type is found
        }
    }
}
