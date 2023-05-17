package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;

public class LinkItemListMaterialsVariantsFacade {

    public static void addLink(int itemListId, int materialVariantsId, ConnectionPool connectionPool) throws DatabaseException {
        LinkItemListMaterialsVariantsMapper.addLink(itemListId, materialVariantsId, connectionPool);
    }

}
