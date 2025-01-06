public class RaportUsuniecia extends Raport {
    private final General general;
    private final int liczbaZolnierzyPrzed;

    public RaportUsuniecia(General general) {
        super("USUNIECIE");
        this.general = general;
        this.liczbaZolnierzyPrzed = general.getArmia().size();
    }

    @Override
    public String generuj() {
        int usunieciZolnierze = liczbaZolnierzyPrzed - general.getArmia().size();

        return getNaglowek() +
                String.format("""
                Generał: %s
                Liczba żołnierzy przed usuniciem: %d
                Liczba żołnierzy po usunięciu: %d
                Usunięto żołnierzy: %d
                """,
                        general.getNazwa(),
                        liczbaZolnierzyPrzed,
                        general.getArmia().size(),
                        usunieciZolnierze
                );
    }
}