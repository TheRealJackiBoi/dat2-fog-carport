package dat.backend.model.persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
                // TODO: Remove all rows from all tables - add your own tables here
                stmt.execute("delete from orders");

                // TODO: Insert a few users - insert rows into your own tables here

                stmt.execute("INSERT INTO cudia_dk_db.orders (material_cost, sales_price, c_width, c_length, c_height, user_id, status, s_width, s_length) " +
                        "values ('6000','10000','250','300','250','1','user','',''),('10000','15000','300','300','500','1','user','',''), ('15000','20000','300','300','500','1','user','100','100')");
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
    void getAllOrders() {
    }

    @Test
    void getOrderByOrderId() {
    }

    @Test
    void getOrdersByUserId() {
    }

    @Test
    void addOrder() {
    }

    @Test
    void calculatePrices() {
    }

    @Test
    void adjustSalesPrice() {
    }

    @Test
    void changeStatusByOrderIdToOrderPlaced() {
    }

    @Test
    void changeStatusByOrderIdToPending() {
    }

    @Test
    void changeStatusByOrderIdToAccepted() {
    }
}