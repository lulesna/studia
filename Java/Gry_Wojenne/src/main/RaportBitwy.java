public class RaportBitwy extends Raport {
    private General general1;
    private General general2;

    public RaportBitwy(General general1, General general2) {
        super("BITWA");
        this.general1 = general1;
        this.general2 = general2;
    }

    @Override
    public String generuj() {
        return getNaglowek() +
            String.format("""
                Generał %s (siła: %d) vs Generał %s (siła: %d)
                
                Stan złota:
                - %s: %d monet
                - %s: %d monet
                
                Liczba żołnierzy:
                - %s: %d
                - %s: %d
                """,
                    general1.getNazwa(), general1.getSila(),
                    general2.getNazwa(), general2.getSila(),
                    general1.getNazwa(), general1.getZloteMonety(),
                    general2.getNazwa(), general2.getZloteMonety(),
                    general1.getNazwa(), general1.getArmia().size(),
                    general2.getNazwa(), general2.getArmia().size()
            );
    }
}
