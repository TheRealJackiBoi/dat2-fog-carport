package dat.backend.model.persistence;

import dat.backend.model.entities.MaterialVariants;
import dat.backend.model.entities.Materials;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MaterialsMapper {

    static Materials getMaterialsByMaterialId(int materialId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        Materials materials = null;

        String sql = "SELECT * FROM materials WHERE material_id = ?";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,materialId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()){
                    //int materialId = rs.getInt("material_id");
                    String description = rs.getString("description");
                    String unit = rs.getString("unit");
                    double unitPrice = rs.getDouble("unit_price");
                    String type = rs.getString("type");
                    materials = new Materials(materialId,description,unit,unitPrice,type);
                }
            }
        }catch (SQLException e){
            throw new DatabaseException(e, "We couldnt get the material");
        }
        return materials;
    }

    static List<Materials> getMaterialByType(String type, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<Materials> list = new ArrayList<>();

        String sql = "SELECT * FROM materials WHERE type = ?";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1,type);
                ResultSet rs = ps.executeQuery();

                while (rs.next()){
                    int materialId = rs.getInt("material_id");
                    String description = rs.getString("description");
                    String unit = rs.getString("unit");
                    double unitPrice = rs.getDouble("unit_price");
                    //String type = rs.getString("type");
                    list.add(new Materials(materialId,description,unit,unitPrice,type));
                }
            }
        }catch (SQLException e){
            throw new DatabaseException(e, "We couldnt get the material");
        }
        return list;
    }

    static List<Materials> getMaterialByDescription(String description, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<Materials> list = new ArrayList<>();

        String sql = "SELECT * FROM materials WHERE description = ?";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1,description);
                ResultSet rs = ps.executeQuery();

                while (rs.next()){
                    int materialId = rs.getInt("material_id");
                    //String description = rs.getString("description");
                    String unit = rs.getString("unit");
                    double unitPrice = rs.getDouble("unit_price");
                    String type = rs.getString("type");
                    list.add(new Materials(materialId,description,unit,unitPrice,type));
                }
            }
        }catch (SQLException e){
            throw new DatabaseException(e, "We couldnt get the material");
        }
        return list;
    }

    static int addMaterial(String description, String unit, double unitPrice, String type, ConnectionPool connectionPool) throws DatabaseException{
        //autogenerere variantId and returns it
        //Used if Fog expands their material list
        String sql = "INSERT INTO materials (description, unit, unit_price, type) VALUES (?,?,?,?)";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
                ps.setString(1, description);
                ps.setString(2, unit);
                ps.setDouble(3, unitPrice);
                ps.setString(4, type);

                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();

                //returning the autogenereated key
                return rs.getInt(1);
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "Something went wrong when trying to add this material");
        }
        return 0;
    }

    static List<Materials> getAllMaterials(ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<Materials> list = new ArrayList<>();

        String sql = "SELECT * FROM materials";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();

                while (rs.next()){
                    int materialId = rs.getInt("material_id");
                    String description = rs.getString("description");
                    String unit = rs.getString("unit");
                    double unitPrice = rs.getDouble("unit_price");
                    String type = rs.getString("type");
                    list.add(new Materials(materialId,description,unit,unitPrice,type));
                }
            }
        }catch (SQLException e){
            throw new DatabaseException(e, "We couldnt get the material");
        }
        return list;
    }
}
