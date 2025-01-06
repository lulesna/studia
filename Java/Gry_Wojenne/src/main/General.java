import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class General extends JednostkaWojskowa implements Serializable {
    private ArrayList<Zolnierz> armia;
    private ArrayList<ObserwatorArmii> obserwatorzy;

    public General(String nazwa, int poczatkoweZloteMonety) {
        super(nazwa, poczatkoweZloteMonety);
        this.armia = new ArrayList<>();
        this.obserwatorzy = new ArrayList<>();
    }

    // siła wszystkich żołnierzy w armii
    @Override
    public int getSila() {
        int sumaSily = 0;
        for (Zolnierz zolnierz : armia) {
            sumaSily += zolnierz.getSila();
        }
        return sumaSily;
    }

    public ArrayList<Zolnierz> getArmia() {
        return new ArrayList<>(armia);
    }

    public void dodajObserwatora(ObserwatorArmii obserwator) {
        if (obserwator == null) {
            throw new IllegalArgumentException("Obserwator nie może być null");
        }
        obserwatorzy.add(obserwator);
    }

    public void powiadomObserwatorow(String akcja, General przeciwnik) {
        for (ObserwatorArmii obserwator : obserwatorzy) {
            obserwator.aktualizuj(akcja, this, przeciwnik);
        }
    }

    public void dodajZolnierza(Zolnierz zolnierz) {
        if (zolnierz == null) {
            throw new IllegalArgumentException("Żołnierz nie może być null");
        }
        armia.add(zolnierz);
    }

    public void kupZolnierza(StopienWojskowy stopien) {
        if (stopien == null) {
            throw new IllegalArgumentException("Stopień wojskowy nie może być null");
        }

        int koszt = 10 * stopien.getWartosc();
        if (zloteMonety >= koszt) {
            armia.add(new Zolnierz(stopien));
            odejmijZloto(koszt);
            powiadomObserwatorow("ZAKUP", null);
        } else {
            throw new IllegalStateException("Liczba złotych monet nie może być ujemna");
        }
    }

    public void przeprowadzManewry() {
        int koszt = 0;
        for (Zolnierz zolnierz : armia) {
            koszt += zolnierz.getStopien().getWartosc();
        }

        if (zloteMonety >= koszt) {
            for (Zolnierz zolnierz : armia) {
                zolnierz.zwiekszDoswiadczenie();
            }
            odejmijZloto(koszt);
            powiadomObserwatorow("MANEWRY", null);
        } else {
            System.out.println("Niewystarczająca liczba złotych monet");
        }
    }

    public void zaatakuj(General przeciwnik) {
        int silaAtakujacego = this.getSila();
        int silaPrzeciwnika = przeciwnik.getSila();

        // wygrywa ten, który posiada armię o większej łącznej sile
        if (silaAtakujacego > silaPrzeciwnika) {
            wygrana(przeciwnik);
        } else if (silaAtakujacego < silaPrzeciwnika) {
            przeciwnik.wygrana(this);
        } else {
            remis(przeciwnik);
        }
        powiadomObserwatorow("BITWA", przeciwnik);
    }

    public void usunMartwychZolnierzy() {
        ArrayList<Zolnierz> nowaArmia = new ArrayList<>();
        for (Zolnierz zolnierz : armia) {
            if (zolnierz.czyZywy()) {
                nowaArmia.add(zolnierz);
            }
        }
        armia = nowaArmia;
        powiadomObserwatorow("USUNIECIEY", null);
    }

    private void wygrana(General przegrany) {
        // przegrany przekazuje 10% swojego złota wygrywającemu
        int zdobyteZloto = przegrany.zloteMonety / 10;
        this.zloteMonety += zdobyteZloto;
        przegrany.zloteMonety -= zdobyteZloto;

        // każdy żołnierz z armii przegrywającej traci 1 punkt doświadczenia
        for (Zolnierz zolnierz : przegrany.getArmia()) {
            zolnierz.zmniejszDoswiadczenie();
        }
        przegrany.usunMartwychZolnierzy();

        // a z wygrywającej zyskuje jeden
        for (Zolnierz zolnierz : armia) {
            zolnierz.zwiekszDoswiadczenie();
        }
    }

    // w przypadku remisu każdy generał musi rozstrzelać jednego swojego losowo wybranego żołnierza
    private void remis(General przeciwnik) {
        rozstrzelLosowegoZolnierza();
        przeciwnik.rozstrzelLosowegoZolnierza();
    }

    public void rozstrzelLosowegoZolnierza() {
        if (!armia.isEmpty()) {
            int index = new Random().nextInt(armia.size());
            armia.remove(index);
            powiadomObserwatorow("ROZSTRZELANIE", null);
        }
    }
}
