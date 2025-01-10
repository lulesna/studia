public class Zolnierz extends JednostkaWojskowa {
    private StopienWojskowy stopien;
    private int doswiadczenie;
    private static int licznik = 1;

    public Zolnierz(StopienWojskowy stopien) {
        super("Zolnierz" + licznik, 0);
        if (stopien == null) {
            throw new IllegalArgumentException("Stopień wojskowy nie może być null");
        }
        this.stopien = stopien;
        // początkowe doświadczenie
        this.doswiadczenie = 1;
        licznik++;
    }

    // gettery

    @Override
    public int getSila() {
        // siła żołnierza - iloczyn stopnia i doświadczenia
        return stopien.getWartosc() * doswiadczenie;
    }

    public StopienWojskowy getStopien() {
        return stopien;
    }

    public int getDoswiadczenie() {
        return doswiadczenie;
    }

    public void zwiekszDoswiadczenie() {
        doswiadczenie++;
        sprawdzAwans();
    }

    public void zmniejszDoswiadczenie() {
        doswiadczenie--;
    }

    private void sprawdzAwans() {
        if (doswiadczenie >= 5 * stopien.getWartosc() && stopien != StopienWojskowy.MAJOR) {
            stopien = stopien.nastepnyStopien();
            // reset doświadczenia po awansie
            doswiadczenie = 1;
        }
    }

    public boolean czyZywy() {
        return doswiadczenie >= 1;
    }
}
