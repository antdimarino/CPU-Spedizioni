package StatePattern;

public class Transizione {
    private Stato stato;

    public Transizione(String stato) {
        this.stato = StatoFactory.costruisciStato( stato );
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }
}
