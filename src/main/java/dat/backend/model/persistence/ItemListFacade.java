package dat.backend.model.persistence;

import dat.backend.model.entities.ItemList;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class ItemListFacade {

    static List<ItemList> getItemListByOrderId(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        return ItemListMapper.getItemListByOrderId(orderId, connectionPool);
    }

    public static double sumPrice(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        return ItemListMapper.sumPrice(orderId, connectionPool);
    }

}
