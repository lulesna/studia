import org.junit.Before;
import org.junit.Test;
import java.io.File;

import static org.junit.Assert.*;

public class SekretarzTest {
    private GraWojenna gra;
    private Sekretarz sekretarz;
    private General general1;
    private General general2;

    @Before
    public void setUp() {
        gra = new GraWojenna();
        general1 = gra.getGeneral1();
        general2 = gra.getGeneral2();
        sekretarz = new Sekretarz();

        general1.kupZolnierza(StopienWojskowy.SZEREGOWY);
        general1.kupZolnierza(StopienWojskowy.KAPRAL);
        general2.kupZolnierza(StopienWojskowy.KAPITAN);
        general2.kupZolnierza(StopienWojskowy.MAJOR);

        sekretarz.aktualizuj("ZAKUP", general1, null);
        sekretarz.aktualizuj("MANEWRY", general1, null);
        sekretarz.aktualizuj("BITWA", general1, general2);
        sekretarz.aktualizuj("ROZSTRZELANIE", general1, null);
    }

    @Test
    public void testZapiszRaporty() {
        String testPath = "test_raporty.txt";
        sekretarz.zapiszRaporty(testPath);
        File file = new File(testPath);
        assertTrue("Plik z raportami powinien istnieć", file.exists());
        long numberOfLines = file.length();
        assertTrue("Plik powinien zawierać co najmniej jeden raport", numberOfLines > 0);
        // file.delete();
    }
}