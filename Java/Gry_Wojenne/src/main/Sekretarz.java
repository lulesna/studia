import java.util.ArrayList;
import java.io.*;

public class Sekretarz implements ObserwatorArmii, Serializable {
    private ArrayList<Raport> raporty;

    public Sekretarz() {
        raporty = new ArrayList<>();
    }

    @Override
    public void aktualizuj(String akcja, General general1, General general2) {
        Raport raport = stworzRaport(akcja, general1, general2);
        if (raport != null) {
            raporty.add(raport);
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
            case "USUNIECIE":
                return new RaportUsuniecia(general1);
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
                writer.println("--------------------------");
            }
        } catch (IOException e) {
            System.err.println("Błąd zapisu raportów: " + e.getMessage());
        }
    }
}
