package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class OrdersFacade {
    static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        return OrdersMapper.getAllOrders(connectionPool);
    }

    static Order getOrderByOrderId(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        return OrdersMapper.getOrderByOrderId(orderId, connectionPool);
    }

    static List<Order> getOrdersByUserId(int userId, ConnectionPool connectionPool) throws DatabaseException{
        return OrdersMapper.getOrdersByUserId(userId, connectionPool);
    }

    static int addOrder(int carportWidth, int carportLength, int carportHeight, int userId, int shedWidth, int shedLength,ConnectionPool connectionPool)throws DatabaseException{
        return OrdersMapper.addOrder(carportWidth,carportLength,carportHeight,userId,shedWidth,shedLength,connectionPool);
    }

    static void calculatePrices(int orderId, ConnectionPool connectionPool) throws  DatabaseException{
        OrdersMapper.calculatePrices(orderId, connectionPool);
    }

    static void adjustSalesPrice(int orderId, double newSalesPrice, ConnectionPool connectionPool) throws DatabaseException{
        OrdersMapper.adjustSalesPrice(orderId,newSalesPrice,connectionPool);
    }

    static void changeStatusByOrderIdToOrderPlaced(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        OrdersMapper.changeStatusByOrderIdToOrderPlaced(orderId, connectionPool);
    }

    static void changeStatusByOrderIdToPending(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        OrdersMapper.changeStatusByOrderIdToPending(orderId, connectionPool);
    }

    public static void changeStatusByOrderIdToAccepted(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        OrdersMapper.changeStatusByOrderIdToAccepted(orderId, connectionPool);
    }
}
