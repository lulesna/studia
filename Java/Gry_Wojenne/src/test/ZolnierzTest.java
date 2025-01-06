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
        // Szeregowy -> Kapral (5 * 1 = 5 punktów doświadczenia)
        for (int i = 1; i < 5; i++) {
            zolnierz.zwiekszDoswiadczenie();
            assertEquals(StopienWojskowy.SZEREGOWY, zolnierz.getStopien());
        }
        zolnierz.zwiekszDoswiadczenie(); // 5-te doświadczenie
        assertEquals(StopienWojskowy.KAPRAL, zolnierz.getStopien());
        assertEquals(1, zolnierz.getDoswiadczenie()); // Reset doświadczenia
    }

    @Test
    public void testAwansKapral() {
        Zolnierz kapral = new Zolnierz(StopienWojskowy.KAPRAL);
        // Kapral -> Kapitan (5 * 2 = 10 punktów doświadczenia)
        for (int i = 1; i < 10; i++) {
            kapral.zwiekszDoswiadczenie();
            assertEquals(StopienWojskowy.KAPRAL, kapral.getStopien());
        }
        kapral.zwiekszDoswiadczenie(); // 10-te doświadczenie
        assertEquals(StopienWojskowy.KAPITAN, kapral.getStopien());
        assertEquals(1, kapral.getDoswiadczenie());
    }

    @Test
    public void testAwansKapitan() {
        Zolnierz kapitan = new Zolnierz(StopienWojskowy.KAPITAN);
        // Kapitan -> Major (5 * 3 = 15 punktów doświadczenia)
        for (int i = 1; i < 15; i++) {
            kapitan.zwiekszDoswiadczenie();
            assertEquals(StopienWojskowy.KAPITAN, kapitan.getStopien());
        }
        kapitan.zwiekszDoswiadczenie(); // 15-te doświadczenie
        assertEquals(StopienWojskowy.MAJOR, kapitan.getStopien());
        assertEquals(1, kapitan.getDoswiadczenie());
    }

    @Test
    public void testBrakAwansuMajora() {
        Zolnierz major = new Zolnierz(StopienWojskowy.MAJOR);
        // Major nie powinien awansować (nawet przy 5 * 4 = 20 punktach doświadczenia)
        for (int i = 0; i < 20; i++) {
            major.zwiekszDoswiadczenie();
            assertEquals(StopienWojskowy.MAJOR, major.getStopien());
        }
        assertEquals(21, major.getDoswiadczenie()); // Doświadczenie powinno rosnąć
    }

    @Test
    public void testUnikalneId() {
        Zolnierz zolnierz1 = new Zolnierz(StopienWojskowy.SZEREGOWY);
        Zolnierz zolnierz2 = new Zolnierz(StopienWojskowy.SZEREGOWY);
        assertNotEquals(zolnierz1.getNazwa(), zolnierz2.getNazwa());
    }
}