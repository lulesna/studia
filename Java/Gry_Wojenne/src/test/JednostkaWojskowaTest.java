import org.junit.Test;
import static org.junit.Assert.*;

public class JednostkaWojskowaTest {

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorZNullNazwą() {
        new General(null, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorZUjemnymZlotem() {
        new General("Test", -1);
    }

    @Test
    public void testDodawanieIZdejmowanieZlota() {
        JednostkaWojskowa jednostka = new General("Test", 100);

        jednostka.dodajZloto(50);
        assertEquals(150, jednostka.getZloteMonety());

        jednostka.odejmijZloto(30);
        assertEquals(120, jednostka.getZloteMonety());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDodanieUjemnegoZlota() {
        JednostkaWojskowa jednostka = new General("Test", 100);
        jednostka.dodajZloto(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOdejmowanieUjemnegoZlota() {
        JednostkaWojskowa jednostka = new General("Test", 100);
        jednostka.odejmijZloto(-10);
    }

    @Test(expected = IllegalStateException.class)
    public void testOdejmowanieZbytDuzoZlota() {
        JednostkaWojskowa jednostka = new General("Test", 100);
        jednostka.odejmijZloto(200);
    }
}
