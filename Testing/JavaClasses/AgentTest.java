package JavaClasses;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgentTest {

    String username = "Happy Days";
    Agent agent = new Agent(username, "happydays123","mohammed","fortune","happytdas@gmail.com",53425264);

    @AfterEach
    void tearDown() {
        agent = null;
        username = null;
    }

    @Test
    public void mainTest() {
        //Username validation test
        assertTrue(agent.getUsername().equals(username));
    }
}