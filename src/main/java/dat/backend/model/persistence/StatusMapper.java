package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
