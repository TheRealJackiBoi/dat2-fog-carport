package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;

public class UnitFacade {

    public static void addUnit(String newUnit, ConnectionPool connectionPool) throws DatabaseException {
        UnitMapper.addUnit(newUnit, connectionPool);
    }

}
