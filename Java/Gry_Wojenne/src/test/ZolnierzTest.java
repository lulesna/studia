import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ZolnierzTest {
    private Zolnierz zolnierz;

    @Before
    public void setUp() {
        zolnierz = new Zolnierz(StopienWojskowy.SZEREGOWY);
    }

    @Test
    public void testTworzenieZolnierza() {
        assertEquals(StopienWojskowy.SZEREGOWY, zolnierz.getStopien());
        assertEquals(1, zolnierz.getDoswiadczenie());
        assertEquals(1, zolnierz.getSila()); // 1 (stopień) * 1 (doświadczenie)
        assertTrue(zolnierz.czyZywy());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTworzenieZolnierzaZNullowymStopniem() {
        new Zolnierz(null);
    }

    @Test
    public void testZwiekszanieDoswiadczenia() {
        assertEquals(1, zolnierz.getSila());
        zolnierz.zwiekszDoswiadczenie();
        assertEquals(2, zolnierz.getSila());
    }

    @Test
    public void testZmniejszanieDoswiadczeniaISmierci() {
        assertTrue(zolnierz.czyZywy());
        zolnierz.zmniejszDoswiadczenie();
        assertFalse(zolnierz.czyZywy());
    }

    @Test
    public void testAwansSzeregowy() {
        // doświadczenie = 4
        for (int i = 1; i < 4; i++) {
            zolnierz.zwiekszDoswiadczenie();
            assertEquals(StopienWojskowy.SZEREGOWY, zolnierz.getStopien());
        }
        zolnierz.zwiekszDoswiadczenie(); // doświadczenie = 5
        assertEquals(StopienWojskowy.KAPRAL, zolnierz.getStopien());
        assertEquals(1, zolnierz.getDoswiadczenie()); // reset doświadczenia
    }

    @Test
    public void testAwansKapral() {
        Zolnierz kapral = new Zolnierz(StopienWojskowy.KAPRAL);
        // doświadczenie = 9
        for (int i = 1; i < 9; i++) {
            kapral.zwiekszDoswiadczenie();
            assertEquals(StopienWojskowy.KAPRAL, kapral.getStopien());
        }
        kapral.zwiekszDoswiadczenie(); // doświadczenie = 10
        assertEquals(StopienWojskowy.KAPITAN, kapral.getStopien());
        assertEquals(1, kapral.getDoswiadczenie()); // reset
    }

    @Test
    public void testAwansKapitan() {
        Zolnierz kapitan = new Zolnierz(StopienWojskowy.KAPITAN);
        // doświadczenie = 14
        for (int i = 1; i < 14; i++) {
            kapitan.zwiekszDoswiadczenie();
            assertEquals(StopienWojskowy.KAPITAN, kapitan.getStopien());
        }
        kapitan.zwiekszDoswiadczenie(); // doświadczenie = 15
        assertEquals(StopienWojskowy.MAJOR, kapitan.getStopien());
        assertEquals(1, kapitan.getDoswiadczenie()); // reset
    }

    @Test
    public void testBrakAwansuMajora() {
        Zolnierz major = new Zolnierz(StopienWojskowy.MAJOR);
        // doświadczenie = 20
        for (int i = 0; i < 20; i++) {
            major.zwiekszDoswiadczenie();
            assertEquals(StopienWojskowy.MAJOR, major.getStopien()); // brak awansu
        }
        assertEquals(21, major.getDoswiadczenie()); // brak resetu
    }

    @Test
    public void testUnikalnaNazwa() {
        Zolnierz zolnierz1 = new Zolnierz(StopienWojskowy.SZEREGOWY);
        Zolnierz zolnierz2 = new Zolnierz(StopienWojskowy.SZEREGOWY);
        assertNotEquals(zolnierz1.getNazwa(), zolnierz2.getNazwa());
    }
}