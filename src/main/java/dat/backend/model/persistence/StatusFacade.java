package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;

public class StatusFacade {

    public static void addStatus(String newStatus, ConnectionPool connectionPool) throws DatabaseException {
        StatusMapper.addStatus(newStatus, connectionPool);
    }
}
