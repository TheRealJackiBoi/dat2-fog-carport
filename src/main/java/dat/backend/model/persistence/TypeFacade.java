package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;

public class TypeFacade {

    public static void addType(String newType, ConnectionPool connectionPool) throws DatabaseException {
        TypeMapper.addType(newType, connectionPool);
    }

}
