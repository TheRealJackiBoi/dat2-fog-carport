package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class UserFacade {
    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.login(email, password, connectionPool);
    }

    public static int createUser(String email, String password, String name, int zip, String city, String address, String role, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.createUser(email, password, name, zip, city, address, role, connectionPool);
    }

    public static List<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.getAllUsers(connectionPool);
    }

    public static User updateUser(int id, String name, int zip, String city, String address, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.updateUser(id, name, zip, city, address, connectionPool);
    }

    public static User updateRole(String role, int id, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.updateRole(role, id, connectionPool);
    }

    public static User getUserByEmail(String email, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.getUserByEmail(email, connectionPool);
    }

    public static User getUserById(int id, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.getUserById(id, connectionPool);
    }

    public static List<String> checkEmail(ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.checkEmail(connectionPool);
    }
}
