import java.io.Serializable;

public abstract class JednostkaWojskowa implements Serializable {
    protected String nazwa;
    protected int zloteMonety;

    public JednostkaWojskowa(String nazwa, int zloteMonety) {
        if (nazwa == null) {
            throw new IllegalArgumentException("Nazwa nie może być null");
        }
        if (zloteMonety < 0) {
            throw new IllegalArgumentException("Liczba złotych monet nie może być ujemna");
        }
        this.nazwa = nazwa;
        this.zloteMonety = zloteMonety;
    }

    public abstract int getSila();

    public String getNazwa() {
        return nazwa;
    }

    public int getZloteMonety() {
        return zloteMonety;
    }

    public void dodajZloto(int liczba) {
        if (liczba < 0) {
            throw new IllegalArgumentException("Liczba złotych monet nie może być ujemna");
        }
        zloteMonety += liczba;
    }

    public void odejmijZloto(int liczba) {
        if (liczba < 0) {
            throw new IllegalArgumentException("Liczba złotych monet nie może być ujemna");
        }
        if (liczba > zloteMonety) {
            throw new IllegalStateException("Niewystarczająca liczba złotych monet");
        }
        zloteMonety -= liczba;
    }           
}