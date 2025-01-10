public class RaportUsuniecia extends Raport {
    private final General general;
    private int liczbaZolnierzyPrzed;
    private int liczbaZolnierzyPo;

    public RaportUsuniecia(General general) {
        super("USUNIECIE");
        this.general = general;
    }

    public void zapiszStanPrzed() {
        this.liczbaZolnierzyPrzed = general.getArmia().size();
    }

    public void zapiszStanPo() {
        this.liczbaZolnierzyPo = general.getArmia().size();
    }

    @Override
    public String generuj() {
        int usunieciZolnierze = liczbaZolnierzyPrzed - liczbaZolnierzyPo;

        return getNaglowek() +
                String.format("""
                Generał: %s
                Liczba żołnierzy przed usunięciem: %d
                Liczba żołnierzy po usunięciu: %d
                Usunięto żołnierzy: %d
                """,
                        general.getNazwa(),
                        liczbaZolnierzyPrzed,
                        liczbaZolnierzyPo,
                        usunieciZolnierze
                );
    }
}
