package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UnitMapper {

    static void addUnit(String newUnit,ConnectionPool connectionPool) throws DatabaseException {
        //Order id is autogenereted
        String sql = "INSERT INTO units (unit) VALUES (?)";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, newUnit);

                ps.executeUpdate();
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "something went wrong when trying to add the order");
        }
    }
}
