package dat.backend.model.services;

/**
 * The type Parts calculator.
 */
public class PartsCalculator {

    /**
     * Calculate number of posts int.
     * Calculates how many posts a carport should've.
     * Each post can at the maximum be 3.1 meters away from each other
     *
     * @param length the length of the carport
     * @param width  the width of the carport
     * @return the int -> Amount of posts
     */
    public static int calculateNumberOfPosts(double length, double width){
        //Posts = stolper

        int numberOfPosts = 4;
        double maxSpaceBetweenPosts = 310; //each posts can carry 3.1 meter of roof

        //adds additional posts, since corners are already added
        for (double i = maxSpaceBetweenPosts; length >= i; i = i + maxSpaceBetweenPosts){
            numberOfPosts = numberOfPosts+2;
        }

        return numberOfPosts;
    }

    /**
     * Calculate number of rafts int.
     * Calculates the amount of rafts needed for each carport.
     * They are 4.5 cm in width and there needs to be 55cm between each raft
     *
     * @param length the length of the carport
     * @param width  the width of the carport
     * @return the int -> Amount of rafts needed for the carport
     */
    public static int calculateNumberOfRafts(double length, double width){
        //Rafts = spær
        //We assume that our rafts are 4.5 cm in width and that there needs to be 55 cm between each

        //numbers are in CM
        double raftWidth = 4.5;
        double spaceBetweenRafts = 55;

        int numberOfRafts = 1;

        for(double i = 0; i < length; i = i + raftWidth + spaceBetweenRafts){
            numberOfRafts++;
        }

        return numberOfRafts;
    }

    /**
     * Calculate number of roof plates int.
     * Calculates the amount of roof plates needed to cover the roof of the carport
     * Each plate is of the dimensions 109 cm x 360 cm
     *
     * @param length the length
     * @param width  the width
     * @return the int -> Amount of roof plates needed to cover the roof of the carport
     */
    public static int calculateNumberOfRoofPlates(double length, double width){
        //Roofplates = tagplast
        //we assume that a "standard" plate is 109 cm x 360 cm

        double roofPlatesWidth = 109;
        double roofPlatesLength = 360;

        int numberOfRoofPlates;
        int numberOfPlatesForLength = 0;
        int numberOfPlatesForWidth = 0;

        for (double i = 0; i < length; i=i+roofPlatesLength){
            numberOfPlatesForLength++;
        }

        for (double i = 0; i < width; i=i+roofPlatesWidth){
            numberOfPlatesForWidth++;
        }

        //we multiply the number needed for the width with the number needed in the length to fill the whole area
        numberOfRoofPlates = numberOfPlatesForLength*numberOfPlatesForWidth;

        //use description: tagplader	monteres	på	spær
        return numberOfRoofPlates;
    }

}
