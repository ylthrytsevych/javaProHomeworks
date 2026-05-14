package hw13.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataHandlerTest {

    @Test
    void testModifyNumber() {
        DataHandler dataHandler = new DataHandler();
        int result1 = dataHandler.modify(7);
        assertEquals(21, result1);
        int result2 = dataHandler.modify(4);
        assertEquals(12, result2);
        int result3 = dataHandler.modify(-2);
        assertEquals(-6, result3);

    }

    @Test
    void testModifyZero() {
        DataHandler dataHandler = new DataHandler();
        assertEquals(0, dataHandler.modify(0));
    }

    @Test
    void testModifyLargeNumberOverflow() {
        DataHandler dataHandler = new DataHandler();
        int expectedOverflowResult = -1294967296;
        assertEquals(expectedOverflowResult, dataHandler.modify(1_000_000_000));
    }
}