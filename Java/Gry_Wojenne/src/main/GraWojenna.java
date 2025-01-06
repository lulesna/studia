import java.io.Serializable;

public class GraWojenna implements Serializable {
    private General general1;
    private General general2;
    private Sekretarz sekretarz;
    private static final int POCZATKOWE_ZLOTO = 50;

    public GraWojenna() {
        this.sekretarz = new Sekretarz();
        this.general1 = new General("Pierwszy", POCZATKOWE_ZLOTO);
        this.general2 = new General("Drugi", POCZATKOWE_ZLOTO);

        general1.dodajObserwatora(sekretarz);
        general2.dodajObserwatora(sekretarz);
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
