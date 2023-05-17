package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;

public class LinkItemListMaterialsVariantsFacade {

    public static void addLink(int itemListId, int materialVariantsId, ConnectionPool connectionPool) throws DatabaseException {
        LinkItemListMaterialsVariantsMapper.addLink(itemListId, materialVariantsId, connectionPool);
    }

    static void addPost(int itemListId, ConnectionPool connectionPool) throws DatabaseException{
        LinkItemListMaterialsVariantsMapper.addPost(itemListId, connectionPool);
    }

    static void addRafts(int itemListId, ConnectionPool connectionPool) throws DatabaseException{
        LinkItemListMaterialsVariantsMapper.addRafts(itemListId, connectionPool);
    }

    static void addRaisingPlate(int itemListId, ConnectionPool connectionPool) throws DatabaseException{
        LinkItemListMaterialsVariantsMapper.addRaisingPlate(itemListId, connectionPool);
    }

    static void addRoof(int itemListId, ConnectionPool connectionPool) throws DatabaseException{
        LinkItemListMaterialsVariantsMapper.addRoof(itemListId, connectionPool);
    }
}
