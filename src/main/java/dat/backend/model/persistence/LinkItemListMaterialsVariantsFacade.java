package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;

public class LinkItemListMaterialsVariantsFacade {

    public static void addLink(int itemListId, int materialVariantsId, ConnectionPool connectionPool) throws DatabaseException {
        LinkItemListMaterialsVariantsMapper.addLink(itemListId, materialVariantsId, connectionPool);
    }

    static void addPost(int itemListId, ConnectionPool connectionPool) throws DatabaseException{
        LinkItemListMaterialsVariantsMapper.addPost(itemListId, connectionPool);
    }

    static void addRafts(int itemListId, int materialVariantsId, ConnectionPool connectionPool) throws DatabaseException{
        LinkItemListMaterialsVariantsMapper.addRafts(itemListId, materialVariantsId, connectionPool);
    }

    static void addRaisingPlate(int itemListId, int materialVariantsId, ConnectionPool connectionPool) throws DatabaseException{
        LinkItemListMaterialsVariantsMapper.addRaisingPlate(itemListId, materialVariantsId, connectionPool);
    }

    static void addRoof(int itemListId, int materialVariantsId, ConnectionPool connectionPool) throws DatabaseException{
        LinkItemListMaterialsVariantsMapper.addRoof(itemListId, materialVariantsId, connectionPool);
    }
}
