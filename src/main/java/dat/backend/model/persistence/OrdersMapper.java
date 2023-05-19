package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdersMapper {

    //returns all the orders in the system
    static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException{
        Logger.getLogger("web").log(Level.INFO,"");

        List<Order> orderList = new ArrayList<>();

        String sql = "SELECT * FROM orders";
        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    int orderId = rs.getInt("order_id");
                    double materialCost = rs.getDouble("material_cost");
                    double salesPrice = rs.getDouble("sales_price");
                    double carportWidth = rs.getDouble("c_width");
                    double carportLength = rs.getDouble("c_length");
                    double carportHeight = rs.getDouble("c_height");
                    int userId = rs.getInt("user_id");
                    String status = rs.getString("status");
                    double shedWidth = rs.getDouble("s_width");
                    double shedLength = rs.getDouble("s_length");
                    orderList.add(new Order(orderId, materialCost, salesPrice, carportWidth, carportLength, carportHeight, userId, status, shedWidth, shedLength));
                }
            }
        }catch (SQLException e){
            throw new DatabaseException(e, "We couldnt get all the orders");
        }
        return orderList;
    }

    //returns 1 specific Order
    static Order getOrderByOrderId(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO,"");

        Order order = null;

        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try (Connection connection = connectionPool.getConnection() ){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,orderId);

                ResultSet rs = ps.executeQuery();

                if(rs.next()) {
                    double materialCost = rs.getDouble("material_cost");
                    double salesPrice = rs.getDouble("sales_price");
                    double carportWidth = rs.getDouble("c_width");
                    double carportLength = rs.getDouble("c_length");
                    double carportHeight = rs.getDouble("c_height");
                    int userId = rs.getInt("user_id");
                    String status = rs.getString("status");
                    double shedWidth = rs.getDouble("s_width");
                    double shedLength = rs.getDouble("s_length");
                    order = new Order(orderId, materialCost, salesPrice, carportWidth, carportLength, carportHeight, userId, status, shedWidth, shedLength);
                } else {
                    throw new DatabaseException("No orders for this order id");
                }
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "Something whet wrong when trying to get orders");
        }
        return order;
    }

    //Return all of a Users orders
    static List<Order> getOrdersByUserId(int userId, ConnectionPool connectionPool) throws DatabaseException{
        Logger.getLogger("web").log(Level.INFO, "");

        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ?";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,userId);

                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    int orderId = rs.getInt("order_id");
                    double materialCost = rs.getDouble("material_cost");
                    double salesPrice = rs.getDouble("sales_price");
                    double carportWidth = rs.getDouble("c_width");
                    double carportLength = rs.getDouble("c_length");
                    double carportHeight = rs.getDouble("c_height");
                    String status = rs.getString("status");
                    double shedWidth = rs.getDouble("s_width");
                    double shedLength = rs.getDouble("s_length");
                    orderList.add(new Order(orderId, materialCost, salesPrice, carportWidth, carportLength, carportHeight, userId, status, shedWidth, shedLength));
                }
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "Something went wrong when trying to get this users orders");
        }
        return orderList;
    }

    //AddOrder
    static int addOrder(double carportWidth, double carportLength, double carportHeight, int userId, double shedWidth, double shedLength,ConnectionPool connectionPool) throws DatabaseException{
        //Order id is autogenereted
        String sql = "INSERT INTO orders (material_cost, sales_price, c_width, c_length, c_height, user_id, status, s_width, s_length) VALUES (?,?,?,?,?,?,?,?,?)";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
                ps.setDouble(1, 0);
                ps.setDouble(2, 0);
                ps.setDouble(3, carportWidth);
                ps.setDouble(4,carportLength);
                ps.setDouble(5,carportHeight);
                ps.setInt(6, userId);
                ps.setString(7, "Creating");
                ps.setDouble(8,shedWidth);
                ps.setDouble(9,shedLength);

                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);

            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "something went wrong when trying to add the order");
        }
        return 0;
    }

    static void calculatePrices(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        //material cost is a sum from the item list

        double cost = ItemListFacade.sumPrice(orderId, connectionPool);
        //According to the Fog presentation video they had a 39% degree of coverage, so that is what we are gonna use
        double salesPrice = cost * 1.39;

        String sql = "UPDATE orders SET material_cost = ?, sales_price = ? WHERE order_id = ?";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setDouble(1 ,cost);
                ps.setDouble(2,salesPrice);
                ps.setInt(3, orderId);

                ps.executeUpdate();
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "Something went wrong when updateting the price");
        }

    }

    //As a sales person you should be able to adjust the sales price, incase the custormer is negotiating
    static void adjustSalesPrice(int orderId, double newSalesPrice, ConnectionPool connectionPool) throws DatabaseException{
        String sql = "UPDATE orders SET sales_price = (?) WHERE order_id = ?";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
             ps.setDouble(1 ,newSalesPrice);
             ps.setInt(2,orderId);

             ps.executeUpdate();
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "Something went wrong when updateting the price");
        }
    }

    //Change Order Status to Order_placed
    static void changeStatusByOrderIdToOrderPlaced(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        String sql = "UPDATE orders SET status = (?) WHERE order_id = ?";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1,"Order_placed");
                ps.setInt(2,orderId);
                ps.executeUpdate();
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "Something went wrong when trying to change status on this Order");
        }
    }

    //Change Order Status to Pending
    static void changeStatusByOrderIdToPending(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        String sql = "UPDATE orders SET status = (?) WHERE order_id = ?";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1,"Pending");
                ps.setInt(2,orderId);
                ps.executeUpdate();
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "Something went wrong when trying to change status on this Order");
        }
    }

    //Change Order Status to Accepted
    static void changeStatusByOrderIdToAccepted(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        String sql = "UPDATE orders SET status = (?) WHERE order_id = ?";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1,"Accepted");
                ps.setInt(2,orderId);
                ps.executeUpdate();
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "Something went wrong when trying to change status on this Order");
        }
    }
    //return a list of all orders in the database including the email
    static List<Order> getAllOrdersPlusEmail(ConnectionPool connectionPool) throws DatabaseException {

        Logger.getLogger("web").log(Level.INFO,"");
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT user.email, orders.* FROM user " +
                "INNER Join orders WHERE user.id=orders.user_id;";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    int orderId = rs.getInt("order_id");
                    double materialCost = rs.getDouble("material_cost");
                    double salesPrice = rs.getDouble("sales_price");
                    double carportWidth = rs.getDouble("c_width");
                    double carportLength = rs.getDouble("c_length");
                    double carportHeight = rs.getDouble("c_height");
                    int userId = rs.getInt("user_id");
                    String status = rs.getString("status");
                    double shedWidth = rs.getDouble("s_width");
                    double shedLength = rs.getDouble("s_length");
                    String email = rs.getString("email");
                    orderList.add(new Order(orderId, materialCost, salesPrice, carportWidth, carportLength, carportHeight, userId, status, shedWidth, shedLength, email));
                }
            }
        }catch (SQLException e){
            throw new DatabaseException(e, "We couldnt get all the orders");
        }
        return orderList;
    }
}
