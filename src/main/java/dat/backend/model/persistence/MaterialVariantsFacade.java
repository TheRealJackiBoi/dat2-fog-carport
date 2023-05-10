package dat.backend.model.persistence;

import dat.backend.model.entities.MaterialVariants;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class MaterialVariantsFacade {

    public static List<MaterialVariants> getAllMaterialVariantsByItemListId(int itemListId, ConnectionPool connectionPool) throws DatabaseException {
        return MaterialVariantsMapper.getAllMaterialVariantsByItemListId(itemListId, connectionPool);
    }

    public static List<MaterialVariants> getVariantsByMaterialId(int materialId, ConnectionPool connectionPool) throws DatabaseException {
        return MaterialVariantsMapper.getVariantsByMaterialId(materialId, connectionPool);
    }

    public static MaterialVariants getVariantByVariantId(int variantId, ConnectionPool connectionPool) throws DatabaseException {
        return MaterialVariantsMapper.getVariantByVariantId(variantId, connectionPool);
    }

    public static int addVariant(int materialId, double length, int quantity, int itemListId, ConnectionPool connectionPool) throws DatabaseException{
        return MaterialVariantsMapper.addVariant(materialId,length,quantity,itemListId,connectionPool);
    }
}
