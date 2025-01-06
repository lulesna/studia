public class RaportManewrow extends Raport {
    private General general;

    public RaportManewrow(General general) {
        super("MANEWRY");
        this.general = general;
    }

    @Override
    public String generuj() {
        int kosztManewrow = 0;
        for (Zolnierz z : general.getArmia()) {
            kosztManewrow += z.getStopien().getWartosc();
        }

        return getNaglowek() +
                String.format("""
                Generał: %s
                Liczba żołnierzy: %d
                Koszt manewrów: %d monet
                Stan złota po manewrach: %d
                """,
                        general.getNazwa(),
                        general.getArmia().size(),
                        kosztManewrow,
                        general.getZloteMonety()
                );
    }
}