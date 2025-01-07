import org.junit.Test;
import static org.junit.Assert.*;

public class StopienWojskowyTest {
    @Test
    public void testWartosc() {
        assertEquals(1, StopienWojskowy.SZEREGOWY.getWartosc());
        assertEquals(2, StopienWojskowy.KAPRAL.getWartosc());
        assertEquals(3, StopienWojskowy.KAPITAN.getWartosc());
        assertEquals(4, StopienWojskowy.MAJOR.getWartosc());
    }

    @Test
    public void testNastepnyStopien() {
        assertEquals(StopienWojskowy.KAPRAL, StopienWojskowy.SZEREGOWY.nastepnyStopien());
        assertEquals(StopienWojskowy.KAPITAN, StopienWojskowy.KAPRAL.nastepnyStopien());
        assertEquals(StopienWojskowy.MAJOR, StopienWojskowy.KAPITAN.nastepnyStopien());
        assertEquals(StopienWojskowy.MAJOR, StopienWojskowy.MAJOR.nastepnyStopien());
    }
}
