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
            URL = System.getenv("JDBC_CONNECTION_STRING") + "_test";
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
                // we choose not to delete all the data in materials because we need these data for other tests, and adding them back is a pain
                //cannot delete tables where there are keys being used elsewhere
                //stmt.execute("delete from materials");

                //removes the material i add in one of our tests
                stmt.execute("DELETE FROM materials WHERE description = ('Stor ting')");


                /*
                stmt.execute("insert into materials (description, unit, unit_price, type) " +
                        "values ('besalg','stk','25','misc'),('35mm','stk','42','misc'), ('50mm','stk','50','misc')");
                */
            }
        }
        catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException
    {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void getMaterialsByMaterialId() throws DatabaseException {
        Materials materials = MaterialsMapper.getMaterialsByMaterialId(3,connectionPool);

        assertEquals("97X97 MM FULDKANTET FYR IMPR", materials.getDescription());
        assertEquals(44.95,materials.getUnitPrice());
    }

    @Test
    void getMaterialByType() throws DatabaseException {
        Materials materials = MaterialsFacade.getMaterialByType("Raisingplate", connectionPool);

        assertEquals(20.94, materials.getUnitPrice());
        assertEquals("45X95 MM FYR TRYKIMP", materials.getDescription());

    }

    @Test
    void getMaterialByDescription() throws DatabaseException {
        List<Materials> materials = MaterialsMapper.getMaterialByDescription("45X100 MM SPÆRTRÆ HØVLET", connectionPool);

        assertEquals(1, materials.size());
        assertEquals(24.94, materials.get(0).getUnitPrice());

    }

    @Test
    void addMaterial() throws DatabaseException {
        int materialID =  MaterialsMapper.addMaterial("Stor ting", "meter", 4000, "Raisingplate", connectionPool);

        Materials material = MaterialsMapper.getMaterialsByMaterialId( materialID, connectionPool);
        assertEquals("Stor ting",material.getDescription());
        assertEquals(4000, material.getUnitPrice());

    }

    @Test
    void getAllMaterials() throws DatabaseException {
        //we know there is 4 materials in the database

        List<Materials> list = MaterialsFacade.getAllMaterials(connectionPool);

        assertEquals(4, list.size());

    }

    @Test
    void adjustCostPrice() throws DatabaseException {

        Materials materials = MaterialsFacade.getMaterialsByMaterialId(1, connectionPool);
        double price = materials.getUnitPrice();

        MaterialsFacade.adjustCostPrice(1, 3232, connectionPool);
        Materials materialsWithNewPrice = MaterialsFacade.getMaterialsByMaterialId(1, connectionPool);
        assertEquals(3232, materialsWithNewPrice.getUnitPrice());

        //sets the price back to what is was
        MaterialsFacade.adjustCostPrice(1, price, connectionPool);
    }
}