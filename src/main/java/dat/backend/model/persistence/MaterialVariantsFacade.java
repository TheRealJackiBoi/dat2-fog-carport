package dat.backend.model.persistence;

import dat.backend.model.entities.MaterialVariants;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class MaterialVariantsFacade {

    public static List<MaterialVariants> getVariantsByMaterialId(int materialId, ConnectionPool connectionPool) throws DatabaseException {
        return MaterialVariantsMapper.getVariantsByMaterialId(materialId, connectionPool);
    }

    public static MaterialVariants getVariantByVariantId(int variantId, ConnectionPool connectionPool) throws DatabaseException {
        return MaterialVariantsMapper.getVariantByVariantId(variantId, connectionPool);
    }

    public static int addVariant(int materialId, double length, int quantity, ConnectionPool connectionPool) throws DatabaseException {
        return MaterialVariantsMapper.addVariant(materialId, length, quantity, connectionPool);
    }
}
