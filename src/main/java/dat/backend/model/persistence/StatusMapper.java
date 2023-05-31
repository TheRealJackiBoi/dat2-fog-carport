package dat.backend.model.persistence;

import dat.backend.model.entities.Role;
import dat.backend.model.entities.Status;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusMapper {

    static void addStatus(String newStatus,ConnectionPool connectionPool) throws DatabaseException {
        //Order id is autogenereted
        String sql = "INSERT INTO status (status) VALUES (?)";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, newStatus);

                ps.executeUpdate();
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "something went wrong when trying to add the order");
        }
    }

    static List<Status> getAllStatus(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM status";

        List<Status> statuslist = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String status = resultSet.getString("status");

                    statuslist.add(new Status(status));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "there was an error retrieving information from the database");
        }

        return statuslist;
    }

}
