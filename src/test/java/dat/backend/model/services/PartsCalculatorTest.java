package dat.backend.model.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartsCalculatorTest {

    @Test
    void calculateNumberOfPosts() {

        assertEquals(6, PartsCalculator.calculateNumberOfPosts(600, 600));

        assertEquals(8, PartsCalculator.calculateNumberOfPosts(780, 600));

        assertEquals(4, PartsCalculator.calculateNumberOfPosts(300, 300));
    }

    @Test
    void calculateNumberOfRafts() {

        //we adds the same numbers as given in the "samlevejledning", to see if our calculator finds the same number
        assertEquals(15,PartsCalculator.calculateNumberOfRafts(780, 600));

    }

    @Test
    void calculateNumberOfRoofPlates() {

        assertEquals(3,PartsCalculator.calculateNumberOfRoofPlates(360,300));


    }
}