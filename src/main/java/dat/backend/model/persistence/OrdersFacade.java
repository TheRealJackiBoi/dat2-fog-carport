package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class OrdersFacade {
    public static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        return OrdersMapper.getAllOrders(connectionPool);
    }

    public static Order getOrderByOrderId(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        return OrdersMapper.getOrderByOrderId(orderId, connectionPool);
    }

    public static List<Order> getOrdersByUserId(int userId, ConnectionPool connectionPool) throws DatabaseException{
        return OrdersMapper.getOrdersByUserId(userId, connectionPool);
    }

    public static int addOrder(double carportWidth, double carportLength, double carportHeight, int userId, double shedWidth, double shedLength,ConnectionPool connectionPool)throws DatabaseException{
        return OrdersMapper.addOrder(carportWidth,carportLength,carportHeight,userId,shedWidth,shedLength,connectionPool);
    }

    public static void calculatePrices(int orderId, ConnectionPool connectionPool) throws  DatabaseException{
        OrdersMapper.calculatePrices(orderId, connectionPool);
    }

    public static void adjustSalesPrice(int orderId, double newSalesPrice, ConnectionPool connectionPool) throws DatabaseException{
        OrdersMapper.adjustSalesPrice(orderId,newSalesPrice,connectionPool);
    }

    public static void changeStatusByOrderIdToOrderPlaced(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        OrdersMapper.changeStatusByOrderIdToOrderPlaced(orderId, connectionPool);
    }

    public static void changeStatusByOrderIdToPending(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        OrdersMapper.changeStatusByOrderIdToPending(orderId, connectionPool);
    }

    public static void changeStatusByOrderIdToAccepted(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        OrdersMapper.changeStatusByOrderIdToAccepted(orderId, connectionPool);
    }
    public static List<Order> getAllOrdersPlusEmail(ConnectionPool connectionPool) throws DatabaseException{
        return OrdersMapper.getAllOrdersPlusEmail(connectionPool);
    }

    public static void updateSpecificOrderById(int orderId, double width, double length, double height, ConnectionPool connectionPool) throws DatabaseException {
        OrdersMapper.updateSpecificOrderById(orderId, width, length, height, connectionPool);
    }

    public static void changeStatusByOrderIdToCancelled(int order_id, ConnectionPool connectionPool) throws DatabaseException {
        OrdersMapper.changeStatusByOrderIdToCancelled(order_id, connectionPool);
    }

    public static void changeStatusByOrderIdToOrderCreating(int order_id, ConnectionPool connectionPool) throws DatabaseException {
        OrdersMapper.changeStatusByOrderIdToOrderCreating(order_id, connectionPool);
    }

    }
