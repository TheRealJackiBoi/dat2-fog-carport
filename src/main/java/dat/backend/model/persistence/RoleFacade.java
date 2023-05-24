package dat.backend.model.persistence;

import dat.backend.model.entities.Role;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class RoleFacade {

    public static Role addnewRole(String role, ConnectionPool connectionPool) throws DatabaseException {
        return RoleMapper.addNewRole(role, connectionPool);
    }
    public static List<Role> getAllRoles(ConnectionPool connectionPool) throws DatabaseException {
        return RoleMapper.getAllRoles(connectionPool);
    }
}
