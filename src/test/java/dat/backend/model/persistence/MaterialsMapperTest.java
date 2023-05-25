package dat.backend.model.persistence;

import dat.backend.model.entities.Materials;
import dat.backend.model.exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaterialsMapperTest {

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
                //cannot delete tables where there are keys being used elsewhere
                //stmt.execute("delete from materials");

                // TODO: Insert a few users - insert rows into your own tables here

                /*
                stmt.execute("insert into materials (description, unit, unit_price, type) " +
                        "values ('besalg','stk','25','misc'),('35mm','stk','42','misc'), ('50mm','stk','50','misc')");
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
    void getMaterialsByMaterialId() throws DatabaseException {
        Materials materials = MaterialsMapper.getMaterialsByMaterialId(4,connectionPool);

        assertEquals("20x15", materials.getDescription());
        assertEquals(97,materials.getUnitPrice());
    }

    @Test
    void getMaterialByType() throws DatabaseException {


    }

    @Test
    void getMaterialByDescription() throws DatabaseException {
        List<Materials> materials = MaterialsMapper.getMaterialByDescription("25x25", connectionPool);

        assertEquals(1, materials.size());
        assertEquals(120, materials.get(0).getUnitPrice());

    }

    @Test
    void addMaterial() throws DatabaseException {
        MaterialsMapper.addMaterial("Stor ting", "stk", 4000, "misc", connectionPool);

        List<Materials> list= MaterialsMapper.getMaterialByDescription("Stor ting", connectionPool);
        assertEquals("Stor ting",list.get(0).getDescription());
        assertEquals(4000, list.get(0).getUnitPrice());
    }
}