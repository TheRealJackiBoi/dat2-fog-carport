package dat.backend.model.services;

import dat.backend.model.entities.MaterialVariants;
import dat.backend.model.entities.Materials;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.*;

import java.util.List;

public class ItemListPopulator {

    //this method populate the itemlist and the LINkED between itemlist and material_variants
    public static void populate(int orderId, double length, double width, double height, ConnectionPool connectionPool) throws DatabaseException {

        // these assure that it only happens once
        boolean postAdded = false;
        boolean raftsAdded = false;
        boolean raisingplatesAdded = false;
        boolean roofAdded = false;

        // TODO: make sure that we add the longest, if we dont have any that are long enough??


        //adds posts
        int numberOfPosts = PartsCalculator.calculateNumberOfPosts(length, width);
        //description found in fogs samlevejledning
        String postDescription = "Stolper nedgraves 90 cm. i jord";

        List<Materials> posts = MaterialsFacade.getMaterialByType("Posts",connectionPool);
        List<MaterialVariants> postsVariantsList = MaterialVariantsFacade.getVariantsByMaterialId(posts.get(0).getMaterialId(), connectionPool);
        for (MaterialVariants m: postsVariantsList) {
            if(m.getLength() >= height+90 && !postAdded){
                ItemListFacade.addPosts(orderId, postDescription,numberOfPosts, m.getMaterialsId(), connectionPool);
                postAdded = true;
            }
        }
        

        //adds rafts
        int numberOfRafts = PartsCalculator.calculateNumberOfRafts(length, width);
        //description found in fogs samlevejledning
        String raftsDescription = "Spær, monteres på rem";

        List<Materials> rafts = MaterialsFacade.getMaterialByType("Rafts", connectionPool);
        List<MaterialVariants> raftList = MaterialVariantsFacade.getVariantsByMaterialId(rafts.get(0).getMaterialId(), connectionPool);
        for (MaterialVariants m: raftList) {
            if(m.getLength() >= width && !raftsAdded){
                ItemListFacade.addRafts(orderId, raftsDescription, numberOfRafts, m.getMaterialsId(), connectionPool);
                raftsAdded = true;
            }
        }


        //adds raisingplates
        int numberOfRaisingPlates = 2;
        //description found in fogs samlevejledning
        String raisingPlatesDescription = "Remme i sider, sadles ned i stolper";

        List<Materials> raisingPlates = MaterialsFacade.getMaterialByType("Raisingplate", connectionPool);
        List<MaterialVariants> raisingPlatesVariants = MaterialVariantsFacade.getVariantsByMaterialId(raisingPlates.get(0).getMaterialId(), connectionPool);
        for(MaterialVariants m: raisingPlatesVariants){
            if(m.getLength() >= length && !raisingplatesAdded){
                ItemListFacade.addRaisingPlate(orderId, raisingPlatesDescription, numberOfRaisingPlates, m.getMaterialsId(), connectionPool);
                raisingplatesAdded = true;
            }
        }



        //adds roof
        int numberOfRoofPlates = PartsCalculator.calculateNumberOfRoofPlates(length, width);
        //description found in fogs samlevejledning
        String roofDescription = "tagplader monteres på spær";


        List<Materials> roofs = MaterialsFacade.getMaterialByType("Roofplates", connectionPool);
        List<MaterialVariants> roofPlatesVariants = MaterialVariantsFacade.getVariantsByMaterialId(roofs.get(0).getMaterialId(), connectionPool);
        for(MaterialVariants m: roofPlatesVariants){
            if (m.getLength() >= length && !roofAdded){
                ItemListFacade.addRoof(orderId, roofDescription, numberOfRoofPlates, m.getMaterialsId(), connectionPool);
                roofAdded = true;
            }
        }


        //now sums the price
        OrdersFacade.calculatePrices(orderId, connectionPool);

    }

}
