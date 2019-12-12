package JavaClasses;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    String username = "Happy Days";
    Admin admin = new Admin(username, "happydays123","mohammed","fortune","happytdas@gmail.com",53425264);

    @AfterEach
    void tearDown() {
        admin = null;
        username = null;
    }

    @Test
    public void mainTest() {
        //Username validation test
        assertTrue(admin.getUsername().equals(username));
    }
}