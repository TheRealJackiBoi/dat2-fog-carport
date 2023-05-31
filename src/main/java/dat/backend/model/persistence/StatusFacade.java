package dat.backend.model.persistence;

import dat.backend.model.entities.Status;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class StatusFacade {

    public static void addStatus(String newStatus, ConnectionPool connectionPool) throws DatabaseException {
        StatusMapper.addStatus(newStatus, connectionPool);
    }

    public static List<Status> getAllStatus(ConnectionPool connectionPool) throws DatabaseException {
        return StatusMapper.getAllStatus(connectionPool);
    }

    }
