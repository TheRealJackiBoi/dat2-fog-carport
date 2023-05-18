package dat.backend.model.persistence;

import dat.backend.model.entities.Role;
import dat.backend.model.exceptions.DatabaseException;

public class RoleFacade {

    public static Role addnewRole(String role, ConnectionPool connectionPool) throws DatabaseException {
        return RoleMapper.addNewRole(role, connectionPool);

    }
}
