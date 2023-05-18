package dat.backend.model.persistence;

import dat.backend.model.entities.MaterialVariants;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MaterialVariantsMapper {
    

    static List<MaterialVariants> getVariantsByMaterialId(int materialId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<MaterialVariants> materialVariantsList = new ArrayList<>();

        String sql = "SELECT * FROM material_variants WHERE material_id = ?";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,materialId);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    int variantId = rs.getInt("variant_id");
                    double length = rs.getDouble("length");
                    int quantity = rs.getInt("quantity");
                    //int materialId = rs.getInt("material_id");
                    materialVariantsList.add(new MaterialVariants(variantId,length,quantity,materialId));
                }
            }
        }catch (SQLException e){
            throw new DatabaseException(e, "We couldnt get all the variants for this material");
        }
        return materialVariantsList;
    }

    static MaterialVariants getVariantByVariantId(int variantId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        MaterialVariants variant = null;

        String sql = "SELECT * FROM material_variants WHERE variant_id = ?";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,variantId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()){
                    //int variantId = rs.getInt("variant_id");
                    double length = rs.getDouble("length");
                    int quantity = rs.getInt("quantity");
                    int materialId = rs.getInt("material_id");
                    variant = new MaterialVariants(variantId,length,quantity,materialId);
                }
            }
        }catch (SQLException e){
            throw new DatabaseException(e, "We couldnt get all the variants for this material");
        }
        return variant;
    }

    static int addVariant(int materialId, double length, int quantity, int itemListId, ConnectionPool connectionPool) throws DatabaseException{
        //autogenerere variantId and returns it
        String sql = "INSERT INTO material_variants (length, quantity, material_id, item_list_id) VALUES (?,?,?,?)";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
                ps.setDouble(1,length);
                ps.setInt(2, quantity);
                ps.setInt(3, materialId);
                ps.setInt(4,itemListId);

                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();

                //returning the autogenereated keys
                return rs.getInt(1);
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "Something went wrong when trying to add this variant");
        }
        return 0;
    }

}
