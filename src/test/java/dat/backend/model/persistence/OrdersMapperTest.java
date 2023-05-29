package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
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

    private static String USER = "root";
    private static String PASSWORD = "StoreOliver";
    private static String URL = "jdbc:mysql://Localhost:3306/cudia_dk_db_test";


    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);
        String deployed = System.getenv("DEPLOYED");
        if (deployed != null) {
            // Prod: hent variabler fra setenv.sh i Tomcats bin folder
            USER = System.getenv("JDBC_USER");
            PASSWORD = System.getenv("JDBC_PASSWORD");
            URL = System.getenv("JDBC_CONNECTION_STRING");
        }

        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {
                // Create test database - if not exist
                stmt.execute("CREATE DATABASE  IF NOT EXISTS cudia_dk_db_test;");
            }
        }
        catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @BeforeEach
    void setUp() {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {

                //Clears all the tabels
                stmt.execute("delete from item_list");
                stmt.execute("delete from orders");
                stmt.execute("delete from user");

                //creates a user which we need to create a order, since orders are linked to a user
                UserFacade.createUser("bjark@b.dk", "123", "bjark", 2100, "kbh", "det store slot","admin", connectionPool);
                User bjark = UserFacade.getUserByEmail("bjark@b.dk", connectionPool);
                int userId1 = bjark.getId();

                // Insert a few orders
                stmt.execute("INSERT INTO orders (material_cost, sales_price, c_width, c_length, c_height, user_id, status, s_width, s_length) " +
                        "values ('6000','10000','250','300','250',"+ userId1 +",'Creating',null ,null ), " +
                        "('10000','15000','300','300','500',"+ userId1 + ",'Order_placed',null , null ), " +
                        "('15000','20000','300','300','500',"+ userId1 +",'Accepted','100','100')");
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }



    @Test
    void testConnection() throws SQLException {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void getAllOrders() throws DatabaseException {
        List<Order> orderList = OrdersFacade.getAllOrders(connectionPool);
        assertEquals(3, orderList.size());
    }

    @Test
    void getOrderByOrderId() throws DatabaseException {
        User bjark = UserFacade.getUserByEmail("bjark@b.dk", connectionPool);
        int userId1 = bjark.getId();
        int newOrder = OrdersFacade.addOrder(310, 250,300,userId1,0,0,connectionPool);

        Order order = OrdersMapper.getOrderByOrderId(newOrder, connectionPool);

        assertEquals("Creating", order.getStatus());
        assertEquals(300, order.getCarportHeight());
        assertEquals(310,order.getCarportWidth());

    }

    @Test
    void getOrdersByUserId() throws DatabaseException {
        User bjark = UserFacade.getUserByEmail("bjark@b.dk", connectionPool);
        int userId1 = bjark.getId();
        List<Order> orderList = OrdersFacade.getOrdersByUserId(userId1,connectionPool);

        assertEquals(3,orderList.size());

        assertEquals("Accepted",orderList.get(2).getStatus());

    }

    @Test
    void addOrder() throws DatabaseException {
        User bjark = UserFacade.getUserByEmail("bjark@b.dk", connectionPool);
        int userId1 = bjark.getId();
        List<Order> oldList = OrdersFacade.getOrdersByUserId(userId1,connectionPool);

        OrdersFacade.addOrder(520.5,300,600,userId1,300,100,connectionPool);

        List<Order> orderList = OrdersMapper.getOrdersByUserId(userId1,connectionPool);
        assertEquals(oldList.size()+1, orderList.size());

    }

    @Test
    void calculatePrices() throws DatabaseException {
        //This needs a itemlist to work, but since we dont have that at the moment its hard to test
        /*
        User bjark = UserFacade.getUserByEmail("bjark@b.dk", connectionPool);
        int userId1 = bjark.getId();
        List<Order> orderList = OrdersFacade.getOrdersByUserId(userId1, connectionPool);

        OrdersFacade.calculatePrices(orderList.get(0).getOrderId(),connectionPool);
        Order order = OrdersFacade.getOrderByOrderId(orderList.get(0).getOrderId(),connectionPool);
        assertEquals(6000, order.getMaterialCost());
        assertEquals(6000*1.39, order.getSalesPrice());
         */
    }

    @Test
    void adjustSalesPrice() throws DatabaseException {
        User bjark = UserFacade.getUserByEmail("bjark@b.dk", connectionPool);
        int userId1 = bjark.getId();
        List<Order> orderList = OrdersFacade.getOrdersByUserId(userId1, connectionPool);
        OrdersFacade.adjustSalesPrice(orderList.get(0).getOrderId(),1200,connectionPool);
        Order order = OrdersFacade.getOrderByOrderId(orderList.get(0).getOrderId(),connectionPool);
        assertEquals(1200, order.getSalesPrice());
    }

    @Test
    void changeStatusByOrderIdToOrderPlaced() throws DatabaseException {
        User bjark = UserFacade.getUserByEmail("bjark@b.dk", connectionPool);
        int userId1 = bjark.getId();
        List<Order> orderList = OrdersFacade.getOrdersByUserId(userId1, connectionPool);

        OrdersFacade.changeStatusByOrderIdToOrderPlaced(orderList.get(0).getOrderId(),connectionPool);
        Order order = OrdersFacade.getOrderByOrderId(orderList.get(0).getOrderId(),connectionPool);
        assertEquals("Order_placed", order.getStatus());
    }

    @Test
    void changeStatusByOrderIdToPending() throws DatabaseException {
        User bjark = UserFacade.getUserByEmail("bjark@b.dk", connectionPool);
        int userId1 = bjark.getId();
        List<Order> orderList = OrdersFacade.getOrdersByUserId(userId1, connectionPool);

        OrdersFacade.changeStatusByOrderIdToPending(orderList.get(0).getOrderId(),connectionPool);
        Order order = OrdersFacade.getOrderByOrderId(orderList.get(0).getOrderId(),connectionPool);
        assertEquals("Pending", order.getStatus());
    }

    @Test
    void changeStatusByOrderIdToAccepted() throws DatabaseException {
        User bjark = UserFacade.getUserByEmail("bjark@b.dk", connectionPool);
        int userId1 = bjark.getId();
        List<Order> orderList = OrdersFacade.getOrdersByUserId(userId1, connectionPool);

        OrdersFacade.changeStatusByOrderIdToAccepted(orderList.get(0).getOrderId(),connectionPool);
        Order order = OrdersFacade.getOrderByOrderId(orderList.get(0).getOrderId(),connectionPool);
        assertEquals("Accepted", order.getStatus());
    }
}