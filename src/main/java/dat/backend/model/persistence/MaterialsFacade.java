package dat.backend.model.persistence;

import dat.backend.model.entities.Materials;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class MaterialsFacade {

    public static Materials getMaterialsByMaterialId(int materialId, ConnectionPool connectionPool) throws DatabaseException{
        return MaterialsMapper.getMaterialsByMaterialId(materialId, connectionPool);
    }

    static List<Materials> getMaterialByType(String type, ConnectionPool connectionPool) throws DatabaseException{
        return MaterialsMapper.getMaterialByType(type, connectionPool);
    }

    static List<Materials> getMaterialByDescription(String description, ConnectionPool connectionPool) throws DatabaseException{
        return MaterialsMapper.getMaterialByDescription(description, connectionPool);
    }

    static int addMaterial(String description, String unit, double unitPrice, String type, ConnectionPool connectionPool) throws DatabaseException{
        return MaterialsMapper.addMaterial(description, unit, unitPrice, type, connectionPool);
    }

}
