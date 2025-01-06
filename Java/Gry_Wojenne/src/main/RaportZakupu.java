public class RaportZakupu extends Raport {
    private final General general;

    public RaportZakupu(General general) {
        super("ZAKUP_ZOLNIERZA");
        this.general = general;
    }

    @Override
    public String generuj() {
        return getNaglowek() +
                String.format("""
                                Generał: %s
                                Aktualna liczba żołnierzy: %d
                                Pozostałe złoto: %d monet
                                Stan armii:
                                %s
                                """,
                        general.getNazwa(),
                        general.getArmia().size(),
                        general.getZloteMonety(),
                        generujStanArmii()
                );
    }

    private String generujStanArmii() {
        StringBuilder stan = new StringBuilder();
        for (Zolnierz z : general.getArmia()) {
            stan.append(String.format("""
                            - ID: %s, Stopień: %s, Doświadczenie: %d, Siła: %d
                            """,
                    z.getNazwa(),
                    z.getStopien(),
                    z.getDoswiadczenie(),
                    z.getSila()
            ));
        }
        return stan.toString();
    }
}