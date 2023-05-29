package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;


class UserMapperTest {
    // TODO: Change mysql login credentials if needed below

    private static String USER = "root";
    private static String PASSWORD = "gh9sp6vp4";
    private static String URL = "jdbc:mysql://localhost:3306/cudia_dk_db_test";


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

                // Delete test users
                stmt.execute("delete from item_list");
                stmt.execute("delete from orders");
                stmt.execute("delete from user");

                // Insert some users for testing
                stmt.execute("insert into user (email, password, name, zip, city, address) " +
                        "values ('adam@adam','1234','Adam','2840','kbh','kbhgade')," +
                        "('billy@billy','1234','Billy','2850','kbh','kbhgade1')," +
                        "('carol@carol','1234','Carol','2860','kbh','kbhgade3')");
            }
        } catch (SQLException throwables) {
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
    void login() throws DatabaseException {
        User expectedUser = new User("adam@adam", "1234", "customer");
        User actualUser = UserFacade.login("adam@adam", "1234", connectionPool);
      
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void invalidPasswordLogin() throws DatabaseException {

        assertThrows(DatabaseException.class, () -> UserFacade.login("adam@adam", "123", connectionPool));

    }

    @Test
    void invalidUserNameLogin() throws DatabaseException {

        assertThrows(DatabaseException.class, () -> UserFacade.login("adam@ada", "1234", connectionPool));
    }

    @Test
    void createUser() throws DatabaseException {
        UserFacade.createUser("jill@hotmail.co.uk", "1234", "Jill", 2840, "Copenhagen", "Østerbrogade", "salesman", connectionPool);
        User logInUser = UserFacade.login("jill@hotmail.co.uk", "1234", connectionPool);
        User expectedUser = new User("jill@hotmail.co.uk", "1234", "salesman");
        assertEquals(logInUser, expectedUser);
    }

    @Test
    void getUserByEmail() throws DatabaseException {
        UserFacade.createUser("getmyemail@hotmail.co.uk", "1234", "TestMe", 2840, "Holte", "Holtevej", "customer", connectionPool);
        UserFacade.getUserByEmail("getmyemail@hotmail.co.uk", connectionPool);
        assertEquals("getmyemail@hotmail.co.uk", "getmyemail@hotmail.co.uk");
    }

    @Test
    void updateUser() throws DatabaseException, SQLException {
        UserFacade.createUser("jack@hotmail.co.uk", "1234", "Jack", 2840, "København", "Østerbrogade", "admin", connectionPool);
        User oldUser = UserFacade.getUserByEmail("jack@hotmail.co.uk", connectionPool);
        int id = oldUser.getId();

        User newUser = UserFacade.updateUser(id, "Jack", 2840, "Copenhagen", "Østerbrogade", connectionPool);

        assertNotEquals(oldUser, newUser);
    }

    @Test
    void updateRole() throws DatabaseException, SQLException {
        UserFacade.createUser("test@test.com", "1234", "Test", 1234, "Test", "Test", "customer", connectionPool);
        User oldUser = UserFacade.getUserByEmail("test@test.com", connectionPool);
        int id = oldUser.getId();

        User newUser = UserFacade.updateRole("salesman", id, connectionPool);
        assertNotEquals(oldUser, newUser);
    }
}