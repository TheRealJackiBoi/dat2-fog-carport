package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class UserMapper {
    static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String role = rs.getString("role");
                    user = new User(email, password, role);
                } else {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

    static User createUser(String email, String password, String name, int zip, String city, String address, String role, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        User user;
        String sql = "insert into user (email, password, name, zip, city, address, role) values (?,?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ps.setString(3, name);
                ps.setInt(4, zip);
                ps.setString(5, city);
                ps.setString(6, address);
                ps.setString(7, role);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    user = new User(email, password, role);
                } else {
                    throw new DatabaseException("The user with username = " + email + " could not be inserted into the database");
                }
            }
        }
        catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert username into database");
        }
        return user;
    }

    public static User getUserByEmail(String email, ConnectionPool connectionPool) {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "SELECT * FROM user WHERE email = ?";

        List<User> userList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String email_ = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    int zip = resultSet.getInt("zip");
                    String city = resultSet.getString("city");
                    String address = resultSet.getString("address");
                    String role = resultSet.getString("role");

                    User newUser = new User(email_, password, name, zip, city, address, role);
                    return newUser;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User updateUser(String email, String password, String name, int zip, String city, String address, String role, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "UPDATE user SET email = ?, name = ?, password = ?, zip = ?, city = ?, address = ? WHERE email = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, name);
                ps.setString(3, password);
                ps.setInt(4, zip);
                ps.setString(5, city);
                ps.setString(6,  address);
                ps.setString(7, role);
                ps.executeUpdate();
            }
        } catch(SQLException e) {
            throw new DatabaseException(e, "Failed to update user information");

        }
        return null;
    }
}
