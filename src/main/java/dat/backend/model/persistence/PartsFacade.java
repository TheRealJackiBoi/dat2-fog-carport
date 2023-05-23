package dat.backend.model.persistence;

import dat.backend.model.entities.Part;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class PartsFacade {

    public static List<Part> getPartsListByOrderId(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        return PartsMapper.getPartsListByOrderId(orderId, connectionPool);
    }
}
