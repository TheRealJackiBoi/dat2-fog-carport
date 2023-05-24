package dat.backend.model.persistence;

import dat.backend.model.entities.LinkItemListMaterialVariants;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LinkItemListMaterialsVariantsMapper {

    static void addLink(int itemListId, int materialVariantsId, ConnectionPool connectionPool) throws  DatabaseException{

        String sql = "INSERT INTO link_item_list_material_variants (item_list_id, material_variants_id) VALUES (?,?)";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,itemListId);
                ps.setInt(2, materialVariantsId);

            ps.executeUpdate();
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "Something went wrong when creating the link between the item list and the material variants");
        }
    }


}
