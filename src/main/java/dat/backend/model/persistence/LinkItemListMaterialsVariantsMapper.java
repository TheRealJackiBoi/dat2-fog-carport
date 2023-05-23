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

    static void addPost(int itemListId, ConnectionPool connectionPool) throws DatabaseException{
        String sql = "INSERT INTO link_item_list_material_variants (item_list_id, material_variants_id) VALUES (?,?)";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,itemListId);
                //3 is posts material ID, so 5-7 is its variants - 6 is 420cm
                ps.setInt(2, 6);

                ps.executeUpdate();
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "Something went wrong when creating the link between the item list and the material variants");
        }
    }

    static void addRafts(int itemListId, int materialVariantsId, ConnectionPool connectionPool) throws DatabaseException{
        String sql = "INSERT INTO link_item_list_material_variants (item_list_id, material_variants_id) VALUES (?,?)";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,itemListId);
                //2 is rafts material ID, so 3-4 is its variants - 3 is 360cm
                ps.setInt(2, materialVariantsId);

                ps.executeUpdate();
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "Something went wrong when creating the link between the item list and the material variants");
        }
    }

    static void addRaisingPlate(int itemListId, int materialVariantsId, ConnectionPool connectionPool) throws DatabaseException{
        String sql = "INSERT INTO link_item_list_material_variants (item_list_id, material_variants_id) VALUES (?,?)";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,itemListId);
                //1 is raisingplates material ID, so 1-2 is its variants - 2 is 480cm
                ps.setInt(2, materialVariantsId);

                ps.executeUpdate();
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "Something went wrong when creating the link between the item list and the material variants");
        }
    }

    static void addRoof(int itemListId, int materialVariantsId, ConnectionPool connectionPool) throws DatabaseException{
        String sql = "INSERT INTO link_item_list_material_variants (item_list_id, material_variants_id) VALUES (?,?)";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,itemListId);
                //4 is roof material ID, so 8 is its variants - 8
                ps.setInt(2, materialVariantsId);

                ps.executeUpdate();
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "Something went wrong when creating the link between the item list and the material variants");
        }
    }

}
