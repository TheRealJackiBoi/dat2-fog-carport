package dat.backend.model.services;

public class PartsCalculator {

    public static int calculateNumberOfPosts(double length, double width){
        //Posts = stolper

        // https://www.johannesfog.dk/byggematerialer/trae/stolper/97x97-mm-fuldkantet-fyr-impr-ntr-a-1385097097_0300
        // price pr post = 134,85

        int numberOfPosts = 4;
        double maxSpaceBetweenPosts = 310; //each posts can carry 3.1 meter of roof

        //adds additional posts, since corners are already added
        for (double i = maxSpaceBetweenPosts; length >= i; i = i + maxSpaceBetweenPosts){
            numberOfPosts = numberOfPosts+2;
        }


        //description: Stolper	nedgraves	90	cm.	i	jord
        return numberOfPosts;
    }

    public static int calculateNumberOfRafts(double length, double width){
        //Rafts = spær
        //We assume that our rafts are 4.5 cm in width and that there needs to be 55 cm between each
        // https://www.johannesfog.dk/byggematerialer/trae/spaertrae/47x100-mm-spaertrae-c24-1438047100_0360
        // price 89,81
        // price pr meter = 24.94

        //numbers are in CM
        double raftWidth = 4.5;
        double spaceBetweenRafts = 55;

        int numberOfRafts = 1;

        for(double i = 0; i < length; i = i + raftWidth + spaceBetweenRafts){
            numberOfRafts++;
        }

        //use description: Spær,	monteres	på	rem
        return numberOfRafts;
    }

    public static int calculateNumberOfRoofPlates(double length, double width){
        //Roofplates = tagplast

        //we assume that a "standard" plate is 109 cm x 360 cm
        // https://www.johannesfog.dk/byggematerialer/tag/tagplader-og-tilbehoer/tagplader/trapezplade-blatonet-109x360cm-5191094
        // pris pr stk 199kr
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
