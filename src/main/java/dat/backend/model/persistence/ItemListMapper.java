package dat.backend.model.persistence;

import dat.backend.model.entities.ItemList;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemListMapper {

    static List<ItemList> getItemListByOrderId(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO,"");

        List<ItemList> itemList = new ArrayList<>();
        String sql = "SELECT * FROM item_list WHERE order_id = ?";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1, orderId);

                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int itemListId = rs.getInt("item_list_id");
                    String useDescription = rs.getString("use_description");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    itemList.add(new ItemList(itemListId, useDescription, quantity, price,orderId));
                }
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "Something went wrong with getting all the items from the itemlist");
        }
        return itemList;
    }

    static int addItem(int orderId, String description, int quantity, double price, ConnectionPool connectionPool) throws DatabaseException{
        //
        String sql = "INSERT INTO item_list (use_description, quantity, price, order_id) VALUES (?,?,?,?)";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
                ps.setString(1,description);
                ps.setInt(2,quantity);
                ps.setDouble(3,price);
                ps.setInt(4,orderId);

                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "We couldnt add this item to the item_list");
        }
    }

    static double sumPrice(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        Logger.getLogger("web").log(Level.INFO,"");
        double costPrice = 0;
        List<ItemList> itemLists = getItemListByOrderId(orderId, connectionPool);

        for (ItemList i: itemLists){
            costPrice += i.getPrice();
        }
        return costPrice;
    }

}
