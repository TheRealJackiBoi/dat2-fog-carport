package dat.backend.model.persistence;

import dat.backend.model.entities.ItemList;
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
                // TODO: Remove all rows from all tables - add your own tables here
                stmt.execute("delete from item_list");

                // TODO: Insert a few users - insert rows into your own tables here

                stmt.execute("insert into item_list (use_description, quantity, price, order_id) " +
                        "values ('Dette er en spær','15','250','7'),('Stolpe','6','699','7'), ('facade','1','120','7')");
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


        List<ItemList> itemListList = ItemListFacade.getItemListByOrderId(1,connectionPool);
        ItemList item = new ItemList(19, "Dette er en spær", 15, 250, 1);
        assertEquals(item.getQuantity(), itemListList.get(0).getQuantity());
    }

    @Test
    void addItem() throws DatabaseException {
        ItemListFacade.addItem(1,"Very good spær", 25, 67.25, connectionPool);

        List<ItemList> itemListList = ItemListFacade.getItemListByOrderId(1,connectionPool);
        assertEquals("Very good spær", itemListList.get(3).getUseDescription());
        assertEquals(67.25, itemListList.get(3).getPrice());
    }

    @Test
    void sumPrice() throws DatabaseException {
        //price when looking in the database
        double currentSum = 250+699+120;

        assertEquals(currentSum, ItemListFacade.sumPrice(1,connectionPool));

    }
}