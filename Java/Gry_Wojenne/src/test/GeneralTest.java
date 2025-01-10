import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class GeneralTest {
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
    public void testTworzenieGenerala() {
        assertEquals("Pierwszy", general1.getNazwa());
        assertEquals(gra.poczatkoweZloto, general1.getZloteMonety());
        assertEquals(0, general1.getSila());
        assertTrue(general1.getArmia().isEmpty());

        assertEquals("Drugi", general2.getNazwa());
        assertEquals(gra.poczatkoweZloto, general2.getZloteMonety());
        assertEquals(0, general2.getSila());
        assertTrue(general2.getArmia().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPustaNazwa() {
        new General("", gra.poczatkoweZloto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUjemneZloto() {
        new General("Test", -10);
    }

    @Test
    public void testKupowanieZolnierza() {
        general1.kupZolnierza(StopienWojskowy.SZEREGOWY);
        assertEquals(1, general1.getArmia().size());
        assertEquals(gra.poczatkoweZloto - 10, general1.getZloteMonety());

        general1.kupZolnierza(StopienWojskowy.KAPITAN);
        assertEquals(2, general1.getArmia().size());
        assertEquals(gra.poczatkoweZloto - 40, general1.getZloteMonety());

        assertEquals(4, general1.getSila());
    }

    @Test(expected = IllegalStateException.class)
    public void testNieudanyZakupZolnierza() {
        General biednyGeneral = new General("Generał bez pieniędzy", 1);
        biednyGeneral.kupZolnierza(StopienWojskowy.SZEREGOWY);
        assertEquals(0, biednyGeneral.getArmia().size());
        assertEquals(1, biednyGeneral.getZloteMonety());
    }

    @Test
    public void testPrzeprowadzanieManewrow() {
        general1.kupZolnierza(StopienWojskowy.SZEREGOWY);
        general1.kupZolnierza(StopienWojskowy.KAPRAL);

        int poczatkoweSaldo = general1.getZloteMonety();
        general1.przeprowadzManewry();

        assertEquals(2, general1.getArmia().get(0).getDoswiadczenie());
        assertEquals(2, general1.getArmia().get(1).getDoswiadczenie());
        assertEquals(poczatkoweSaldo - 3, general1.getZloteMonety());
    }

    @Test
    public void testUsunMartwychZolnierzy() {
        general1.dodajZloto(200);
        general1.kupZolnierza(StopienWojskowy.SZEREGOWY);
        general1.kupZolnierza(StopienWojskowy.KAPRAL);
        general1.kupZolnierza(StopienWojskowy.KAPITAN);
        ArrayList<Zolnierz> armia = general1.getArmia();

        armia.get(0).zmniejszDoswiadczenie(); // zmniejszenie doświadczenia o 1 dla szeregowego

        for (int i = 0; i < 2; i++) { // zmniejszenie doświadczenia o 2 dla kaprala
            armia.get(1).zmniejszDoswiadczenie();
        }

        general1.usunMartwychZolnierzy();

        ArrayList<Zolnierz> armiaPoUsunieciu = general1.getArmia();
        assertEquals(1, armiaPoUsunieciu.size());
        assertEquals(StopienWojskowy.KAPITAN, armiaPoUsunieciu.get(0).getStopien());
    }


    @Test
    public void testUsunMartwychZolnierzyWszyscyZywi() {
        general1.kupZolnierza(StopienWojskowy.SZEREGOWY);
        general1.kupZolnierza(StopienWojskowy.KAPRAL);

        int poczatkowaWielkoscArmii = general1.getArmia().size();
        general1.usunMartwychZolnierzy();
        assertEquals(poczatkowaWielkoscArmii, general1.getArmia().size());
    }

    @Test
    public void testUsunMartwychZolnierzyPustaArmia() {
        assertTrue(general1.getArmia().isEmpty());
        general1.usunMartwychZolnierzy();
        assertTrue(general1.getArmia().isEmpty());
    }

    @Test
    public void testBitwaWygrana() {
        General silny = new General("Silny", 50);
        General slaby = new General("Slaby", 50);

        silny.kupZolnierza(StopienWojskowy.KAPITAN);
        slaby.kupZolnierza(StopienWojskowy.SZEREGOWY);

        int poczatkoweZlotoG1 = silny.getZloteMonety();
        int poczatkoweZlotoG2 = slaby.getZloteMonety();

        silny.zaatakuj(slaby);

        assertEquals(poczatkoweZlotoG1 + (poczatkoweZlotoG2 / 10), silny.getZloteMonety());
        assertEquals(poczatkoweZlotoG2 - (poczatkoweZlotoG2 / 10), slaby.getZloteMonety());
        assertEquals(2, silny.getArmia().get(0).getDoswiadczenie());
        assertTrue(slaby.getArmia().isEmpty());
    }

    @Test
    public void testBitwaRemis() {
        general1.kupZolnierza(StopienWojskowy.SZEREGOWY);
        general2.kupZolnierza(StopienWojskowy.SZEREGOWY);

        general1.zaatakuj(general2);

        assertTrue(general1.getArmia().isEmpty());
        assertTrue(general2.getArmia().isEmpty());
    }

    @Test
    public void testRozstrzelLosowegoZolnierza() {
        // pusta armia
        general1.rozstrzelLosowegoZolnierza();
        assertTrue(general1.getArmia().isEmpty());

        // jeden żołnierz
        general1.kupZolnierza(StopienWojskowy.SZEREGOWY);
        assertEquals(1, general1.getArmia().size());
        general1.rozstrzelLosowegoZolnierza();
        assertTrue(general1.getArmia().isEmpty());

        // kilka żołnierzy
        general1.dodajZloto(200);
        general1.kupZolnierza(StopienWojskowy.SZEREGOWY);
        general1.kupZolnierza(StopienWojskowy.KAPRAL);
        general1.kupZolnierza(StopienWojskowy.KAPITAN);
        int poczatkowaWielkoscArmii = general1.getArmia().size();

        general1.rozstrzelLosowegoZolnierza();
        assertEquals(poczatkowaWielkoscArmii - 1, general1.getArmia().size());
    }
}
