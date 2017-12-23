import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {

    @Test
    public void testFlik() {
        assertTrue(Flik.isSameNumber(127, 127));
        assertTrue(Flik.isSameNumber(125, 125));
        assertTrue(Flik.isSameNumber(129, 129));
    }
}
