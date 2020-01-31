package GenericObject;

public class Tratta {
    private String codeTratta;
    private String partenza;
    private String destinazione;
    private String codeCollo;
    private String codeVeicolo;
    private double tempo;

    public Tratta(String codeTratta, String partenza, String destinazione, String codeCollo, String codeVeicolo , double tempo) {
        this.codeTratta = codeTratta;
        this.partenza = partenza;
        this.destinazione = destinazione;
        this.codeCollo = codeCollo;
        this.codeVeicolo = codeVeicolo;
        this.tempo = tempo;
    }

    public Tratta(String codeTratta, String partenza, String destinazione, String codeCollo) {
        this.codeTratta = codeTratta;
        this.partenza = partenza;
        this.destinazione = destinazione;
        this.codeCollo = codeCollo;
    }

    public String getCodeTratta() {
        return codeTratta;
    }

    public String getPartenza() {
        return partenza;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public String getCodeCollo() {
        return codeCollo;
    }

    public String getCodeVeicolo() {
        return codeVeicolo;
    }

    public void setCodeTratta(String codeTratta) {
        this.codeTratta = codeTratta;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public void setCodeCollo(String codeCollo) {
        this.codeCollo = codeCollo;
    }

    public void setCodeVeicolo(String codeVeicolo) {
        this.codeVeicolo = codeVeicolo;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }
}
