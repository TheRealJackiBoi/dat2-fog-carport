package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.Part;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PartsMapper {

    //returns this orders parts
    static List<Part> getPartsListByOrderId(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO,"");

        List<Part> list = new ArrayList<>();

        String sql = "SELECT * FROM parts WHERE order_id = ?";

        try (Connection connection = connectionPool.getConnection() ){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,orderId);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String useDescription = rs.getString("use_description");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getInt("price");
                    int variantId = rs.getInt("variant_id");
                    double length = rs.getDouble("length");
                    int materialId = rs.getInt("material_id");
                    String dimension = rs.getString("dimensions");
                    String unit = rs.getString("unit");
                    list.add(new Part(orderId, useDescription, quantity, price, variantId, length, materialId, dimension, unit));
                }
            }
        } catch (SQLException e){
            throw new DatabaseException(e, "Something whet wrong when trying to get orders");
        }
        return list;
    }

}
