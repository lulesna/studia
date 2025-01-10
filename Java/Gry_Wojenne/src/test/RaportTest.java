import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.Assert.*;

public class RaportTest {
    private General general1;
    private General general2;
    private RaportBitwy raportBitwy;
    private RaportManewrow raportManewrow;
    private RaportRozstrzelania raportRozstrzelania;
    private RaportUsuniecia raportUsuniecia;
    private RaportZakupu raportZakupu;

    @Before
    public void setUp() {
        general1 = new General("Pierwszy", 100);
        general2 = new General("Drugi", 150);

        general1.kupZolnierza(StopienWojskowy.SZEREGOWY);
        general1.kupZolnierza(StopienWojskowy.KAPRAL);
        general2.kupZolnierza(StopienWojskowy.KAPITAN);
        general2.kupZolnierza(StopienWojskowy.MAJOR);

        raportBitwy = new RaportBitwy(general1, general2);
        raportManewrow = new RaportManewrow(general1);
        raportRozstrzelania = new RaportRozstrzelania(general1);
        raportUsuniecia = new RaportUsuniecia(general1);
        raportZakupu = new RaportZakupu(general1);
    }

    @Test
    public void testFormatDaty() {
        String raport = raportBitwy.generuj();
        LocalDateTime teraz = LocalDateTime.now();
        String oczekiwanyFormat = teraz.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        assertTrue("Raport powinien zawierać datę w formacie yyyy-MM-dd HH:mm",
                raport.contains(teraz.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
    }

    @Test
    public void testRaportBitwy() {
        String raport = raportBitwy.generuj();
        System.out.println(raport);

        assertTrue("Raport powinien zawierać nagłówek BITWA",
                raport.contains("| Raport: BITWA |"));

        assertTrue("Raport powinien zawierać nazwy generałów i ich siły",
                raport.contains("Generał Pierwszy") && raport.contains("Generał Drugi"));
        assertTrue("Raport powinien zawierać informacje o złocie",
                raport.contains("Stan złota:") &&
                        raport.contains("Pierwszy:") &&
                        raport.contains("Drugi:"));
        assertTrue("Raport powinien zawierać informacje o liczbie żołnierzy",
                raport.contains("Liczba żołnierzy:") &&
                        raport.contains("2"));
    }

    @Test
    public void testRaportManewrow() {
        String raport = raportManewrow.generuj();
        System.out.println(raport);

        assertTrue("Raport powinien zawierać nagłówek MANEWRY",
                raport.contains("| Raport: MANEWRY |"));
        assertTrue("Raport powinien zawierać nazwę generała",
                raport.contains("Generał: Pierwszy"));
        assertTrue("Raport powinien zawierać liczbę żołnierzy",
                raport.contains("Liczba żołnierzy: 2"));
        assertTrue("Raport powinien zawierać informację o złocie",
                raport.contains("Stan złota"));
    }

    @Test
    public void testRaportRozstrzelania() {
        String raport = raportRozstrzelania.generuj();
        System.out.println(raport);

        assertTrue("Raport powinien zawierać nagłówek ROZSTRZELANIE",
                raport.contains("| Raport: ROZSTRZELANIE |"));
        assertTrue("Raport powinien zawierać nazwę generała",
                raport.contains("Generał: Pierwszy"));
        assertTrue("Raport powinien zawierać początkową liczbę żołnierzy",
                raport.contains("Liczba żołnierzy przed rozstrzelaniem: 2"));

        general1.rozstrzelLosowegoZolnierza();
        RaportRozstrzelania nowyRaport = new RaportRozstrzelania(general1);
        String nowyRaportTekst = nowyRaport.generuj();
        assertTrue(nowyRaportTekst.contains("Liczba żołnierzy po rozstrzelaniu: 1"));
    }

    @Test
    public void testRaportUsuniecia() {
        Zolnierz szeregowy = general1.getArmia().get(0);
        szeregowy.zmniejszDoswiadczenie();

        raportUsuniecia.zapiszStanPrzed();
        general1.usunMartwychZolnierzy();
        raportUsuniecia.zapiszStanPo();

        String raport = raportUsuniecia.generuj();
        System.out.println(raport);

        assertTrue("Raport powinien zawierać nagłówek USUNIECIE",
                raport.contains("| Raport: USUNIECIE |"));
        assertTrue("Raport powinien zawierać nazwę generała",
                raport.contains("Generał: Pierwszy"));
        assertTrue("Raport powinien zawierać początkową liczbę żołnierzy",
                raport.contains("Liczba żołnierzy przed usunięciem: 2"));
        assertTrue("Raport powinien zawierać końcową liczbę żołnierzy",
                raport.contains("Liczba żołnierzy po usunięciu: 1"));
        assertTrue("Raport powinien zawierać liczbę usuniętych żołnierzy",
                raport.contains("Usunięto żołnierzy: 1"));
    }

    @Test
    public void testRaportZakupu() {
        String raport = raportZakupu.generuj();
        System.out.println(raport);

        assertTrue(raport.contains("| Raport: ZAKUP_ZOLNIERZA |"));
        assertTrue(raport.contains("Generał: Pierwszy"));
        assertTrue(raport.contains("Aktualna liczba żołnierzy: 2"));
        assertTrue(raport.contains("Pozostałe złoto:"));
        String stanArmii = raport.substring(raport.indexOf("Stan armii:"));
        assertTrue(stanArmii.contains("Stopień: SZEREGOWY"));
        assertTrue(stanArmii.contains("Stopień: KAPRAL"));
        assertTrue(stanArmii.contains("Doświadczenie: 1"));
    }

}