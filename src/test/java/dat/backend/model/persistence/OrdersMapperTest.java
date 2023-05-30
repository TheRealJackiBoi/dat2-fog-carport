package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrdersMapperTest {

    private final static String USER = "root";
    private final static String PASSWORD = "StoreOliver";
    private final static String URL = "jdbc:mysql://Localhost:3306/cudia_dk_db_test";


    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass()
    {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection())
        {
            try (Statement stmt = testConnection.createStatement())
            {
                // Create test database - if not exist
                stmt.execute("CREATE DATABASE  IF NOT EXISTS cudia_dk_db_test;");
            }
        }
        catch (SQLException throwables)
        {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @BeforeEach
    void setUp()
    {
        try (Connection testConnection = connectionPool.getConnection())
        {
            try (Statement stmt = testConnection.createStatement())
            {

                //INSERTED THESE ONCE WHEN I STARTED TESTING
                /*
                // TODO: Insert a few orders
                stmt.execute("INSERT INTO orders (material_cost, sales_price, c_width, c_length, c_height, user_id, status, s_width, s_length) " +
                        "values ('6000','10000','250','300','250','4','Creating',null ,null ), " +
                        "('10000','15000','300','300','500','4','Order_placed',null , null ), " +
                        "('15000','20000','300','300','500','4','Accepted','100','100')");
                */
            }
        }
        catch (SQLException throwables)
        {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }



    @Test
    void testConnection() throws SQLException
    {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null)
        {
            connection.close();
        }
    }

    @Test
    void getAllOrders() throws DatabaseException {
        List<Order> orderList = OrdersMapper.getAllOrders(connectionPool);
        assertEquals(7, orderList.size());
    }

    @Test
    void getOrderByOrderId() throws DatabaseException {
        Order order = OrdersMapper.getOrderByOrderId(12, connectionPool);
        assertEquals(15000,order.getSalesPrice());

        assertEquals("Order_placed", order.getStatus());
        assertEquals(500, order.getCarportHeight());
        assertEquals(0,order.getShedWidth());

    }

    @Test
    void getOrdersByUserId() throws DatabaseException {
        List<Order> orderList = OrdersFacade.getOrdersByUserId(4,connectionPool);

        assertEquals(6,orderList.size());

        assertEquals("Accepted",orderList.get(2).getStatus());

    }

    @Test
    void addOrder() throws DatabaseException {
        List<Order> oldList = OrdersFacade.getOrdersByUserId(2,connectionPool);

        OrdersFacade.addOrder(520.5,300,600,2,300,100,connectionPool);

        List<Order> orderList = OrdersMapper.getOrdersByUserId(2,connectionPool);
        assertEquals(oldList.size()+1, orderList.size());

    }

    @Test
    void calculatePrices() throws DatabaseException {
        OrdersFacade.calculatePrices(1,connectionPool);
        Order order = OrdersFacade.getOrderByOrderId(1,connectionPool);
        assertEquals(1069, order.getMaterialCost());
        assertEquals(1069*1.39, order.getSalesPrice());
    }

    @Test
    void adjustSalesPrice() throws DatabaseException {
        OrdersFacade.adjustSalesPrice(1,1200,connectionPool);
        Order order = OrdersFacade.getOrderByOrderId(1,connectionPool);
        assertEquals(1200, order.getSalesPrice());
    }

    @Test
    void changeStatusByOrderIdToOrderPlaced() throws DatabaseException {
        OrdersFacade.changeStatusByOrderIdToOrderPlaced(1,connectionPool);
        Order order = OrdersFacade.getOrderByOrderId(1,connectionPool);
        assertEquals("Order_placed", order.getStatus());
    }

    @Test
    void changeStatusByOrderIdToPending() throws DatabaseException {
        OrdersFacade.changeStatusByOrderIdToPending(1,connectionPool);
        Order order = OrdersFacade.getOrderByOrderId(1,connectionPool);
        assertEquals("Pending", order.getStatus());
    }

    @Test
    void changeStatusByOrderIdToAccepted() throws DatabaseException {
        OrdersFacade.changeStatusByOrderIdToAccepted(1,connectionPool);
        Order order = OrdersFacade.getOrderByOrderId(1,connectionPool);
        assertEquals("Accepted", order.getStatus());
    }

    @Test
    void getAllOrdersPlusEmail() throws DatabaseException {
        List<Order> orderList = OrdersFacade.getAllOrdersPlusEmail(connectionPool);

        assertEquals("bjark@b.dk", orderList.get(0).getUserEmail());
        assertNotEquals("jack@asd.dk", orderList.get(0).getUserEmail());
    }

    @Test
    void changeStatusByOrderIdToCancelled() throws DatabaseException {
        User bjark = UserFacade.getUserByEmail("bjark@b.dk", connectionPool);
        int userId1 = bjark.getId();
        List<Order> orderList = OrdersFacade.getOrdersByUserId(userId1, connectionPool);

        OrdersFacade.changeStatusByOrderIdToCancelled(orderList.get(0).getOrderId(), connectionPool);
        Order order = OrdersFacade.getOrderByOrderId(orderList.get(0).getOrderId(), connectionPool);
        assertEquals("Cancelled", order.getStatus());
    }

    @Test
    void changeStatusByOrderIdToOrderCreating() throws DatabaseException {
        User bjark = UserFacade.getUserByEmail("bjark@b.dk", connectionPool);
        int userId1 = bjark.getId();
        List<Order> orderList = OrdersFacade.getOrdersByUserId(userId1, connectionPool);

        OrdersFacade.changeStatusByOrderIdToOrderCreating(orderList.get(1).getOrderId(), connectionPool);
        Order order = OrdersFacade.getOrderByOrderId(orderList.get(1).getOrderId(), connectionPool);
        assertEquals("Creating", order.getStatus());
    }

    @Test
    void updateSpecificOrderById() throws DatabaseException {
        User bjark = UserFacade.getUserByEmail("bjark@b.dk", connectionPool);
        int userId1 = bjark.getId();
        List<Order> orderList = OrdersFacade.getOrdersByUserId(userId1, connectionPool);

        OrdersFacade.updateSpecificOrderById(orderList.get(0).getOrderId(), 6060, 4206, 2999, connectionPool);
        Order order = OrdersFacade.getOrderByOrderId(orderList.get(0).getOrderId(), connectionPool);

        assertEquals(6060, order.getCarportWidth());
        assertEquals(4206, order.getCarportLength());

        assertNotEquals(250, order.getCarportWidth());

    }
}