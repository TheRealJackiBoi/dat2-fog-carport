package dat.backend.model.services;

import dat.backend.model.entities.MaterialVariants;
import dat.backend.model.entities.Materials;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.*;

import java.util.List;

/**
 * The type Item list populator.
 */
public class ItemListPopulator {

    /**
     * Populate.
     *
     * @param orderId        the order id
     * @param lengthInMeter  the length in meter
     * @param widthInMeter   the width in meter
     * @param heightInMeter  the height in meter
     * @param connectionPool the connection pool
     * @throws DatabaseException the database exception
     */
//this method populate the itemlist and the LINkED between itemlist and material_variants
    public static void populate(int orderId, double lengthInMeter, double widthInMeter, double heightInMeter, ConnectionPool connectionPool) throws DatabaseException {

        //THIS IS CHANGED FROM MAIN, ITS SPREAD MORE INTO SMALLER FUNCTIONS, WHICH IS CLEANER AND MORE IN JAVA SPIRIT OOP

        //RECALCULATE TO CM TO MATCH DATABASE
        double length = lengthInMeter * 100;
        double width = widthInMeter * 100;
        double height = heightInMeter * 100;

        //adds posts
        addPosts(orderId, length, width, height, connectionPool);

        //adds raisingplates
        addRaisinPlates(orderId, length, connectionPool);

        //adds rafts
        addRafts(orderId, length, width, connectionPool);

        //adds roof
        addRoofPlates(orderId, length, width, connectionPool);

        //now sums the price
        OrdersFacade.calculatePrices(orderId, connectionPool);

    }


    /**
     * Add raisin plates.
     *
     * @param orderId        the order id
     * @param length         the length
     * @param connectionPool the connection pool
     * @throws DatabaseException the database exception
     */
    static void addRaisinPlates(int orderId, double length, ConnectionPool connectionPool) throws DatabaseException {
        // these assure that it only happens once
        boolean raisingplatesAdded = false;

        //adds raisingplates
        int numberOfRaisingPlates = 2;
        //description found in fogs samlevejledning
        String raisingPlatesDescription = "Remme i sider, sadles ned i stolper";

        Materials raisingPlates = MaterialsFacade.getMaterialByType("Raisingplate", connectionPool);
        List<MaterialVariants> raisingPlatesVariants = MaterialVariantsFacade.getVariantsByMaterialId(raisingPlates.getMaterialId(), connectionPool);
        for (MaterialVariants m : raisingPlatesVariants) {
            if (m.getLength() >= length && !raisingplatesAdded) {
                ItemListFacade.addRaisingPlate(orderId, raisingPlatesDescription, numberOfRaisingPlates, m.getVariantId(), connectionPool);
                raisingplatesAdded = true;
            }
        }
        //if we dont have any that are long enough we need to add the longest twice
        if (!raisingplatesAdded) {
            for (MaterialVariants m : raisingPlatesVariants) {
                if (length == m.getLength() * 2) {
                    ItemListFacade.addRaisingPlate(orderId, raisingPlatesDescription, numberOfRaisingPlates * 2, m.getVariantId(), connectionPool);
                    raisingplatesAdded = true;
                }
            }
            if (!raisingplatesAdded) {
                for (MaterialVariants s : raisingPlatesVariants) {
                    if (length < s.getLength()*2) {
                        ItemListFacade.addRaisingPlate(orderId, raisingPlatesDescription, numberOfRaisingPlates * 2, s.getVariantId(), connectionPool);
                        raisingplatesAdded = true;
                        return;
                    }
                }
            }
        }
    }

    /**
     * Add rafts.
     *
     * @param orderId        the order id
     * @param length         the length
     * @param width          the width
     * @param connectionPool the connection pool
     * @throws DatabaseException the database exception
     */
    static void addRafts(int orderId, double length, double width, ConnectionPool connectionPool) throws DatabaseException{

        // these assure that it only happens once
        boolean raftsAdded = false;

        int numberOfRafts = PartsCalculator.calculateNumberOfRafts(length, width);
        //description found in fogs samlevejledning
        String raftsDescription = "Spær, monteres på rem";

        Materials rafts = MaterialsFacade.getMaterialByType("Rafts", connectionPool);
        List<MaterialVariants> raftList = MaterialVariantsFacade.getVariantsByMaterialId(rafts.getMaterialId(), connectionPool);
        for (MaterialVariants m : raftList) {
            if (m.getLength() >= width && !raftsAdded) {
                ItemListFacade.addRafts(orderId, raftsDescription, numberOfRafts, m.getVariantId(), connectionPool);
                raftsAdded = true;
            }
        }
        //if we dont have any that are long enough we need to add the longest twice or anohter if it adds up exactly
        if (!raftsAdded) {
            for (MaterialVariants m : raftList) {
                if (length == m.getLength() * 2) {
                    ItemListFacade.addRaisingPlate(orderId, raftsDescription, numberOfRafts * 2, m.getVariantId(), connectionPool);
                    raftsAdded = true;
                }
            }
            if (!raftsAdded) {
                for (MaterialVariants s : raftList) {
                    if (length < s.getLength()*2) {
                        ItemListFacade.addRaisingPlate(orderId, raftsDescription, numberOfRafts * 2, s.getVariantId(), connectionPool);
                        raftsAdded = true;
                        return;
                    }
                }
            }
        }

    }

    /**
     * Add posts
     *
     * @param orderId       the order id
     * @param length        the length
     * @param width         the width
     * @param height        the height
     * @param connectionPool    the connection pool
     * @throws DatabaseException    the database exception
     */
    static void addPosts(int orderId, double length, double width, double height, ConnectionPool connectionPool) throws  DatabaseException{
        // these assure that it only happens once
        boolean postAdded = false;

        int numberOfPosts = PartsCalculator.calculateNumberOfPosts(length, width);
        //description found in fogs samlevejledning
        String postDescription = "Stolper nedgraves 90 cm. i jord";

        Materials posts = MaterialsFacade.getMaterialByType("Posts", connectionPool);
        List<MaterialVariants> postsVariantsList = MaterialVariantsFacade.getVariantsByMaterialId(posts.getMaterialId(), connectionPool);
        for (MaterialVariants m : postsVariantsList) {
            if (m.getLength() >= height + 90 && !postAdded) {
                ItemListFacade.addPosts(orderId, postDescription, numberOfPosts, m.getVariantId(), connectionPool);
                postAdded = true;
            }
        }

    }

    /**
     * add roof plates
     *
     * @param orderId       the order id
     * @param length        the length
     * @param width         the width
     * @param connectionPool    the connection pool
     * @throws DatabaseException    the database exception
     */
    static void addRoofPlates(int orderId, double length, double width, ConnectionPool connectionPool) throws DatabaseException{

        //this could have been optimized, so there is less waste. But would have to consult the clint about if tagplast is dividable
        int numberOfRoofPlates = PartsCalculator.calculateNumberOfRoofPlates(length, width);

        //description found in fogs samlevejledning
        String roofDescription = "tagplader monteres på spær";

        //ALWAYS TAKES THESAME SINCE WE ONLY HAVE 1 ROFF PLATE IN STOCK
        Materials roofs = MaterialsFacade.getMaterialByType("Roofplates", connectionPool);
        List<MaterialVariants> roofPlatesVariants = MaterialVariantsFacade.getVariantsByMaterialId(roofs.getMaterialId(), connectionPool);

        ItemListFacade.addRoof(orderId, roofDescription, numberOfRoofPlates, roofPlatesVariants.get(0).getVariantId(), connectionPool);

    }
}
