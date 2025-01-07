import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraWojennaTest {
    private GraWojenna gra;
    private General general1;
    private General general2;

    @Before
    public void setUp() {
        gra = new GraWojenna();
        general1 = gra.getGeneral1();
        general2 = gra.getGeneral2();
    }

    @Test
    public void testInicjalizacjaGry() {
        assertNotNull(gra.getGeneral1());
        assertNotNull(gra.getGeneral2());
        assertEquals("Pierwszy", gra.getGeneral1().getNazwa());
        assertEquals("Drugi", gra.getGeneral2().getNazwa());
        assertEquals(GraWojenna.POCZATKOWE_ZLOTO, gra.getGeneral1().getZloteMonety());
        assertEquals(GraWojenna.POCZATKOWE_ZLOTO, gra.getGeneral2().getZloteMonety());
        assertEquals(0, gra.getGeneral1().getArmia().size());
        assertEquals(0, gra.getGeneral2().getArmia().size());
    }

    @Test
    public void testBitwa() {
        General general1 = gra.getGeneral1();
        General general2 = gra.getGeneral2();

        general1.kupZolnierza(StopienWojskowy.KAPRAL);
        general1.kupZolnierza(StopienWojskowy.KAPRAL);
        general2.kupZolnierza(StopienWojskowy.SZEREGOWY);

        gra.przeprowadzBitwe();

        int poczatkoweZlotoG1 = general1.getZloteMonety();
        int poczatkoweZlotoG2 = general2.getZloteMonety();
        int poczatkoweDoswiadczenieG1_1 = general1.getArmia().get(0).getDoswiadczenie();
        int poczatkoweDoswiadczenieG1_2 = general1.getArmia().get(1).getDoswiadczenie();

        gra.przeprowadzBitwe();

        assertEquals(poczatkoweZlotoG1 + (poczatkoweZlotoG2 / 10), general1.getZloteMonety());
        assertEquals(poczatkoweZlotoG2 - (poczatkoweZlotoG2 / 10), general2.getZloteMonety());

        assertEquals(poczatkoweDoswiadczenieG1_1 + 1, general1.getArmia().get(0).getDoswiadczenie());
        assertEquals(poczatkoweDoswiadczenieG1_2 + 1, general1.getArmia().get(1).getDoswiadczenie());
        assertTrue(general2.getArmia().isEmpty());
    }

    @Test
    public void testSekwencjaBitew() {
        general1.kupZolnierza(StopienWojskowy.KAPRAL);
        general2.kupZolnierza(StopienWojskowy.SZEREGOWY);

        gra.przeprowadzBitwe();
        assertEquals(1, general1.getArmia().size());
        assertEquals(0, general2.getArmia().size());

        gra.przeprowadzBitwe();
        assertEquals(1, general1.getArmia().size());
        assertEquals(0, general2.getArmia().size());

        general2.dodajZloto(80);
        general2.kupZolnierza(StopienWojskowy.MAJOR);
        general2.kupZolnierza(StopienWojskowy.MAJOR);

        gra.przeprowadzBitwe();
        assertEquals(1, general1.getArmia().size());
        assertEquals(2, general2.getArmia().size());
    }
}
