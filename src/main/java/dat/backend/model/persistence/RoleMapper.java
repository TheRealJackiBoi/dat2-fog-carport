package dat.backend.model.persistence;

import dat.backend.model.entities.Role;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleMapper {

    static Role addNewRole(String role1, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        Role role;
        String sql = "insert into role (role) values (?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, role1);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1)
                {
                    role = new Role(role1);
                } else
                {
                    throw new DatabaseException("The role with rolename = " + role1 + " could not be inserted into the database");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert the role into the database");
        }
        return role;
    }
}
