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

                    int userId = rs.getInt("id");
                    user = new User(userId, email, password, role);

                } else {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

    static void createUser(String email, String password, String name, int zip, String city, String address, String role, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        User user;
        String sql = "INSERT INTO user (email, password, name, zip, city, address, role) values (?,?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ps.setString(3, name);
                ps.setInt(4, zip);
                ps.setString(5, city);
                ps.setString(6, address);
                ps.setString(7, role);

                ps.executeUpdate();

            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert username into database");
        }
    }

    static List<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "SELECT * FROM user";

        List<User> userList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    int zip = resultSet.getInt("zip");
                    String city = resultSet.getString("city");
                    String address = resultSet.getString("address");
                    String role = resultSet.getString("role");

                    userList.add(new User(id, email, password, name, zip, city, address, role));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "there was an error retrieving information from the database");
        }

        return userList;
    }

    static User getUserByEmail(String email, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "SELECT * FROM user WHERE email = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String email_ = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    int zip = resultSet.getInt("zip");
                    String city = resultSet.getString("city");
                    String address = resultSet.getString("address");
                    String role = resultSet.getString("role");

                    User newUser = new User(id, email_, password, name, zip, city, address, role);
                    return newUser;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not fetch user information from email");
        }
        return null;
    }

    static User getUserById(int id, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "SELECT * FROM user WHERE id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    int zip = resultSet.getInt("zip");
                    String city = resultSet.getString("city");
                    String address = resultSet.getString("address");
                    String role = resultSet.getString("role");

                    User newUser = new User(id, email, password, name, zip, city, address, role);
                    return newUser;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not fetch user information from email");
        }
        return null;
    }

    static User updateUser(int id, String email, String password, String name, int zip, String city, String address, String role, ConnectionPool connectionPool) throws DatabaseException {
        //TODO: Check if new email is already taken by another user!

        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "UPDATE user SET email = ?, password = ?, name = ?, zip = ?, city = ?, address = ? WHERE id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ps.setString(3, name);
                ps.setInt(4, zip);
                ps.setString(5, city);
                ps.setString(6, address);
                ps.setInt(7, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Failed to update user information");

        }
        return null;
    }

   static List<String> checkEmail(ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        List<String> emailList = new ArrayList<>();
        String sql = "SELECT email FROM user";


        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String email_ = rs.getString("email");
                    emailList.add(email_);
                }
                return emailList;
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Unable to fetch database information");
        }
    }
}