package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

public class UserFacade {
    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.login(email, password, connectionPool);
    }

    public static User createUser(String email, String password, String name, int zip, String city, String address, String role, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.createUser(email, password, name, zip, city, address, role, connectionPool);
    }

    public static User getUserByEmail(String email, ConnectionPool connectionPool) {
        return UserMapper.getUserByEmail(email, connectionPool);
    }

    public static User updateUser(String email, String password, String name, int zip, String city, String address, String role, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.updateUser(email, password, name, zip, city, address, role, connectionPool);
    }
}
