import java.util.ArrayList;
import java.io.*;

public class Sekretarz implements ObserwatorArmii {
    private ArrayList<Raport> raporty;
    private RaportUsuniecia tymczasowyRaport;

    public Sekretarz() {
        raporty = new ArrayList<>();
        tymczasowyRaport = null;
    }

    @Override
    public void aktualizuj(String akcja, General general1, General general2) {
        switch (akcja) {
            case "USUNIECIE_START":
                tymczasowyRaport = new RaportUsuniecia(general1);
                tymczasowyRaport.zapiszStanPrzed();
                break;
            case "USUNIECIE_KONIEC":
                if (tymczasowyRaport != null) {
                    tymczasowyRaport.zapiszStanPo();
                    raporty.add(tymczasowyRaport);
                    tymczasowyRaport = null;
                    break;
                }
            default:
                Raport raport = stworzRaport(akcja, general1, general2);
                if (raport != null) {
                    raporty.add(raport);
                }
                break;
        }
    }

    private Raport stworzRaport(String akcja, General general1, General general2) {
        switch (akcja) {
            case "BITWA":
                return new RaportBitwy(general1, general2);
            case "MANEWRY":
                return new RaportManewrow(general1);
            case "ZAKUP":
                return new RaportZakupu(general1);
            case "ROZSTRZELANIE":
                return new RaportRozstrzelania(general1);
            default:
                return null;
        }
    }

    public void zapiszRaporty(String sciezka) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(sciezka))) {
            for (Raport raport : raporty) {
                writer.println(raport.generuj());
                writer.println("--------------------------\n");
            }
        } catch (IOException e) {
            System.err.println("Błąd zapisu raportów: " + e.getMessage());
        }
    }
}
