public class RaportRozstrzelania extends Raport {
    private final General general;
    private final int liczbaZolnierzyPrzed;

    public RaportRozstrzelania(General general) {
        super("ROZSTRZELANIE");
        this.general = general;
        this.liczbaZolnierzyPrzed = general.getArmia().size();
    }

    @Override
    public String generuj() {
        return getNaglowek() +
                String.format("""
                Generał: %s
                Liczba żołnierzy przed rozstrzelaniem: %d
                Liczba żołnierzy po rozstrzelaniu: %d
                """,
                        general.getNazwa(),
                        liczbaZolnierzyPrzed,
                        general.getArmia().size()
                );
    }
}