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

                //dont clear this need them for our test
              /*
                // Remove all rows from all tables
                stmt.execute("delete from material_variants");

                // Inserts a few users
                stmt.execute("insert into material_variants (length, quantity, material_id) " +
                        "values ('320','1','1'),('170','6','3'), ('560','2','3')");
               */
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
    void getVariantsByMaterialId() throws DatabaseException {
        List<MaterialVariants> list = MaterialVariantsFacade.getVariantsByMaterialId(3, connectionPool);

        assertEquals(7, list.get(2).getVariantId());
        assertEquals(420, list.get(1).getLength());

    }

    @Test
    void getVariantByVariantId() throws DatabaseException {

        int variantID = MaterialVariantsFacade.addVariant(2, 200,2, connectionPool);

        MaterialVariants variants = MaterialVariantsFacade.getVariantByVariantId(variantID, connectionPool);

        assertEquals(200, variants.getLength());

        assertNotEquals(1, variants.getVariantId());
        assertEquals(variantID, variants.getVariantId());

        assertEquals(2, variants.getMaterialsId());

    }

    @Test
    void addVariant() throws DatabaseException {
        List<MaterialVariants> list = MaterialVariantsFacade.getVariantsByMaterialId(1, connectionPool);
        MaterialVariantsFacade.addVariant(1,250,8,connectionPool);

        List<MaterialVariants> newList = MaterialVariantsFacade.getVariantsByMaterialId(1,connectionPool);

        assertEquals(list.size()+1, newList.size());
    }
}