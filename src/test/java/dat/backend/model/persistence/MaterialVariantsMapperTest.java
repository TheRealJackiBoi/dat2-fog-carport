package dat.backend.model.persistence;

import dat.backend.model.entities.MaterialVariants;
import dat.backend.model.exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.lang.model.element.VariableElement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaterialVariantsMapperTest {

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
                /*
                // TODO: Remove all rows from all tables - add your own tables here
                stmt.execute("delete from material_variants");

                // TODO: Insert a few users - insert rows into your own tables here

                stmt.execute("insert into material_variants (length, quantity, material_id) " +
                        "values ('320','12','1'),('170','6','3'), ('560','2','3')");

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
    void getVariantsByMaterialId() throws DatabaseException {
        List<MaterialVariants> list = MaterialVariantsMapper.getVariantsByMaterialId(3, connectionPool);

        assertEquals(2, list.get(1).getQuantity());
    }

    @Test
    void getVariantByVariantId() throws DatabaseException {

        MaterialVariants variants = MaterialVariantsMapper.getVariantByVariantId(16, connectionPool);

        assertEquals(170, variants.getLength());

        assertNotEquals(1, variants.getVariantId());

    }

    @Test
    void addVariant() throws DatabaseException {
        List<MaterialVariants> list = MaterialVariantsMapper.getVariantsByMaterialId(3, connectionPool);
        MaterialVariantsMapper.addVariant(2,250,8,7,connectionPool);

        List<MaterialVariants> newList = MaterialVariantsMapper.getVariantsByMaterialId(3,connectionPool);

        assertEquals(list.size()+1, newList.size());
    }
}