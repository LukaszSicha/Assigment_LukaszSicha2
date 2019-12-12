package JavaClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {
    public int id1 = 1234;

    Property property = new Property(id1,"hellohello", "1234567789Hello", "Limerick", "Limerick", "Limerick", "W13", "X91H782",25.00);

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        property = null;
    }

    @Test
    public void mainTest() {
        //ID validation test
        assertTrue(property.getPropertyId() == id1);
    }


}