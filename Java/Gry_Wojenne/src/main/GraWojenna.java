public class GraWojenna {
    private General general1;
    private General general2;
    private Sekretarz sekretarz;
    public int poczatkoweZloto = 100;

    public GraWojenna() {
        this.sekretarz = new Sekretarz();
        this.general1 = new General("Pierwszy", poczatkoweZloto);
        this.general2 = new General("Drugi", poczatkoweZloto);

        general1.dodajObserwatora(sekretarz);
        general2.dodajObserwatora(sekretarz);
    }

    public void setPoczatkoweZloto(int nowaLiczbaZlota) {
        if (nowaLiczbaZlota >= 0) {
            poczatkoweZloto = nowaLiczbaZlota;
        } else {
            throw new IllegalArgumentException("Liczba złotych monet nie może być ujemna");
        }
    }

    public void przeprowadzBitwe() {
        general1.zaatakuj(general2);
    }

    public General getGeneral1() {
        return general1;
    }

    public General getGeneral2() {
        return general2;
    }
}
