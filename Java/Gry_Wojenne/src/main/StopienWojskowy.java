public enum StopienWojskowy {
    SZEREGOWY(1),
    KAPRAL(2),
    KAPITAN(3),
    MAJOR(4);

    private int wartosc;

    StopienWojskowy(int wartosc) {
        this.wartosc = wartosc;
    }

    public int getWartosc() {
        return wartosc;
    }

    public StopienWojskowy nastepnyStopien() {
        if (this == SZEREGOWY) return KAPRAL;
        if (this == KAPRAL) return KAPITAN;
        if (this == KAPITAN) return MAJOR;
        return MAJOR;
    }
}
