import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Raport {
    protected final LocalDateTime dataUtworzenia;
    protected final String typAkcji;

    public Raport(String typAkcji) {
        this.typAkcji = typAkcji;
        this.dataUtworzenia = LocalDateTime.now();
    }

    public abstract String generuj();

    protected String getNaglowek() {
        return String.format("| Raport: %s |\nData: %s\n",
                typAkcji,
                dataUtworzenia.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }
}
