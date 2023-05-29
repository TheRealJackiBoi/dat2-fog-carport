package dat.backend.model.persistence;

import dat.backend.model.entities.ItemList;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemListMapperTest {

    private static String USER = "root";
    private static String PASSWORD = "StoreOliver";
    private static String URL = "jdbc:mysql://Localhost:3306/cudia_dk_db_test";


    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass()
    {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);
        String deployed = System.getenv("DEPLOYED");
        if (deployed != null) {
            // Prod: hent variabler fra setenv.sh i Tomcats bin folder
            USER = System.getenv("JDBC_USER");
            PASSWORD = System.getenv("JDBC_PASSWORD");
            URL = System.getenv("JDBC_CONNECTION_STRING");
        }

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
                // TODO: Remove all rows from all tables - add your own tables here
                stmt.execute("delete from item_list"); // wipes database clean
                stmt.execute("delete from orders");
                stmt.execute("delete from user");

                UserFacade.createUser("bjark@b.dk", "123", "bjark", 2100, "kbh", "det store slot","admin", connectionPool);
                User bjark = UserFacade.getUserByEmail("bjark@b.dk", connectionPool);
                int userId1 = bjark.getId();

                // TODO: Insert a few orders
                stmt.execute("INSERT INTO orders (material_cost, sales_price, c_width, c_length, c_height, user_id, status, s_width, s_length) " +
                        "values ('6000','10000','250','300','250',"+ userId1 +",'Creating',null ,null )");

                // TODO: Insert a few users - insert rows into your own tables here
                List<Order> orderList = OrdersFacade.getOrdersByUserId(userId1, connectionPool);
                stmt.execute("insert into item_list (use_description, quantity, price, order_id) " +
                        "values ('Dette er en spær','15','250',"+ orderList.get(0).getOrderId()+"),('Stolpe','6','699',"+ orderList.get(0).getOrderId()+"), ('facade','1','120',"+ orderList.get(0).getOrderId()+")");
            } catch (DatabaseException e) {
                e.printStackTrace();
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
    void getItemListByOrderId() throws DatabaseException {
        //chooseing an arbitary high number to show that it sends exception if the "item" is not found in database
        List<ItemList> emptylist = new ArrayList<>();
        assertEquals(emptylist,ItemListFacade.getItemListByOrderId(999999, connectionPool));

        User bjark = UserFacade.getUserByEmail("bjark@b.dk", connectionPool);
        int userId1 = bjark.getId();
        List<Order> orderList = OrdersFacade.getOrdersByUserId(userId1, connectionPool);


        List<ItemList> itemListList = ItemListFacade.getItemListByOrderId(orderList.get(0).getOrderId(),connectionPool);
        ItemList item = new ItemList(19, "Dette er en spær", 15, 250, 1);
        assertEquals(item.getQuantity(), itemListList.get(0).getQuantity());
    }

    @Test
    void addItem() throws DatabaseException {
        User bjark = UserFacade.getUserByEmail("bjark@b.dk", connectionPool);
        int userId1 = bjark.getId();
        List<Order> orderList = OrdersFacade.getOrdersByUserId(userId1, connectionPool);

        ItemListFacade.addItem(orderList.get(0).getOrderId(),"Very good spær", 25, 67.25, connectionPool);

        List<ItemList> itemListList = ItemListFacade.getItemListByOrderId(orderList.get(0).getOrderId(),connectionPool);
        assertEquals("Very good spær", itemListList.get(3).getUseDescription());
        assertEquals(67.25, itemListList.get(3).getPrice());
    }

    @Test
    void sumPrice() throws DatabaseException {
        User bjark = UserFacade.getUserByEmail("bjark@b.dk", connectionPool);
        int userId1 = bjark.getId();
        List<Order> orderList = OrdersFacade.getOrdersByUserId(userId1, connectionPool);

        //price when looking in the database
        double currentSum = 250+699+120;

        assertEquals(currentSum, ItemListFacade.sumPrice(orderList.get(0).getOrderId(),connectionPool));

    }
}