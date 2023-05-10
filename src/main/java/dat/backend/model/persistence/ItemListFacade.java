package dat.backend.model.persistence;

import dat.backend.model.entities.ItemList;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class ItemListFacade {

    public static List<ItemList> getItemListByOrderId(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        return ItemListMapper.getItemListByOrderId(orderId, connectionPool);
    }

    public static int addItem(int orderId, String description, int quantity, double price, ConnectionPool connectionPool) throws DatabaseException{
        return ItemListMapper.addItem(orderId, description, quantity, price, connectionPool);
    }

    public static double sumPrice(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        return ItemListMapper.sumPrice(orderId, connectionPool);
    }

}
