package dat.backend.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
@Disabled
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
                // TODO: Remove all rows from all tables - add your own tables here
                stmt.execute("delete from user");

                // TODO: Insert a few users - insert rows into your own tables here
                stmt.execute("insert into user (email, password, name, zip, city, address) " +
                        "values ('user','1234','user','2840','kbh','kbhgade'),('user2','1234','user2','2850','kbh','kbhgade1'), ('user3','1234','user3','2860','kbh','kbhgade3')");
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
        if (connection != null)
        {
            connection.close();
        }
    }

    @Test
    void login() throws DatabaseException {
        User expectedUser = new User("user", "1234", "customer");
        User actualUser = UserFacade.login("user", "1234", connectionPool);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void invalidPasswordLogin() throws DatabaseException {
        assertThrows(DatabaseException.class, () -> UserFacade.login("user@gmail.com", "123", connectionPool));
    }

    @Test
    void invalidUserNameLogin() throws DatabaseException {
        assertThrows(DatabaseException.class, () -> UserFacade.login("bob@gmail.com", "1234", connectionPool));
    }

    @Test
    void createUser() throws DatabaseException
    {
        UserFacade.createUser("jill@hotmail.co.uk", "1234", "Jill", 2840, "Copenhagen", "Østerbrogade", "", connectionPool);
        User logInUser = UserFacade.login("jill@hotmail.co.uk", "1234", connectionPool);
        User expectedUser = new User("jill@hotmail.co.uk", "1234", "customer");
    }
}