import org.junit.Before;
import org.junit.Test;
import java.io.File;

import static org.junit.Assert.*;

public class SekretarzTest {
    private Sekretarz sekretarz;
    private General general;

    @Before
    public void setUp() {
        sekretarz = new Sekretarz();
        general = new General("Test", GraWojenna.POCZATKOWE_ZLOTO);
        sekretarz.aktualizuj("ZAKUP", general, null);
    }

    @Test
    public void testZapiszRaporty() {
        String testPath = "test_raporty.txt";
        sekretarz.zapiszRaporty(testPath);
        File file = new File(testPath);
        assertTrue(file.exists());
        file.delete();
    }
}