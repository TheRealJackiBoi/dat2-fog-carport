package dat.backend.model.services;

public class PartsCalculator {

    public static int calculateNumberOfPosts(double length, double width){
        //Posts = stolper

        // https://www.johannesfog.dk/byggematerialer/trae/stolper/97x97-mm-fuldkantet-fyr-impr-ntr-a-1385097097_0300
        // pris pr stk = 134,85

        int numberOfPosts = 0;
        double maxSpaceBetweenPosts = 3.1; //each posts can carry 3.1 meter of roof


        //calculate for 1 side of the carport
        for(double i = 0; i < length; i = i + maxSpaceBetweenPosts){
            numberOfPosts++;
        }

        //if the user wants 8.5 meter long, then 4 stolper wont be enough so we need to add another one. but if the user adds the excat length that fits our measurements then we wont add another one.
        if(maxSpaceBetweenPosts*numberOfPosts < length){
            numberOfPosts++;
        }

        //now calculate posts needed for the width

        //adds posts if the corport is wider than 6.2 meters. Then adds additionelly if they are wider than 6.2+3.1
        if(width > maxSpaceBetweenPosts*2){
            for(double i = maxSpaceBetweenPosts*2; i < width; i= i + maxSpaceBetweenPosts){
                numberOfPosts++;
            }
        }

        //returns the number * 2 since, we have only calculated what is needed for 1 side.

        //description: Stolper	nedgraves	90	cm.	i	jord
        return numberOfPosts*2;
    }

    public static int calculateNumberOfRafts(double length, double width){
        //Rafts = spær
        //We assume that our rafts are 45 cm in width and that there needs to be 0.55 cm between each
        // https://www.johannesfog.dk/byggematerialer/trae/spaertrae/47x100-mm-spaertrae-c24-1438047100_0360
        // pris 89,81
        // meter pris = 24.94

        double raftWidth = 0.45;
        double spaceBetweenRafts = 0.55;

        int numberOfRafts = 0;

        for(double i = 0.55; i < length; i = i + raftWidth + spaceBetweenRafts){
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
        double roofPlatesWidth = 1.09;
        double roofPlatesLength = 3.60;

        int numberOfRoofPlates;
        int numberOfPlatesForLength = 0;
        int numberOfPlatesForWidth = 0;

        for (double i = 0; i < length; i=i+roofPlatesLength){
            numberOfPlatesForLength++;
        }
        if(numberOfPlatesForLength*roofPlatesLength < length){
            numberOfPlatesForLength++;
        }

        for (double i = 0; i < width; i=i+roofPlatesWidth){
            numberOfPlatesForWidth++;
        }
        if(numberOfPlatesForWidth*roofPlatesWidth < width){
            numberOfPlatesForWidth++;
        }

        //we multiply the number needed for the width with the number needed in the length to fill the whole area
        numberOfRoofPlates = numberOfPlatesForLength*numberOfPlatesForWidth;

        //use description: tagplader	monteres	på	spær
        return numberOfRoofPlates;
    }


}
