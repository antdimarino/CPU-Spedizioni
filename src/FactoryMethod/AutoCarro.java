package FactoryMethod;

import org.joda.time.DateTime;

public class AutoCarro extends Veicolo{
    private String codeVeicolo;
    private String tipo;
    private int capienza;
    private String filialeAppartenenza;
    private String destinazione;
    private String stato;
    private int capienzaDisponibile;
    private DateTime dataUltimaModifica;
    private double durata;

    public AutoCarro(String codeVeicolo, String tipo, int capienza, String filialeAppartenenza, String destinazione, String stato, int capienzaDisponibile, DateTime dataUltimaModifica, double durata) {
        this.codeVeicolo = codeVeicolo;
        this.tipo = tipo;
        this.capienza = capienza;
        this.filialeAppartenenza = filialeAppartenenza;
        this.destinazione = destinazione;
        this.stato = stato;
        this.capienzaDisponibile = capienzaDisponibile;
        this.dataUltimaModifica = dataUltimaModifica;
        this.durata = durata;
    }


    @Override
    public String getCodeVeicolo() {
        return codeVeicolo;
    }

    @Override
    public void setCodeVeicolo(String codeVeicolo) {
        this.codeVeicolo = codeVeicolo;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int getCapienza() {
        return capienza;
    }

    @Override
    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    @Override
    public String getFilialeAppartenenza() {
        return filialeAppartenenza;
    }

    @Override
    public void setFilialeAppartenenza(String filialeAppartenenza) {
        this.filialeAppartenenza = filialeAppartenenza;
    }

    @Override
    public String getDestinazione() {
        return destinazione;
    }

    @Override
    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    @Override
    public String getStato() {
        return stato;
    }

    @Override
    public void setStato(String stato) {
        this.stato = stato;
    }

    @Override
    public int getCapienzaDisponibile() {
        return capienzaDisponibile;
    }

    @Override
    public void setCapienzaDisponibile(int capienzaDisponibile) {
        this.capienzaDisponibile = capienzaDisponibile;
    }

    @Override
    public DateTime getDataUltimaModifica() {
        return dataUltimaModifica;
    }

    @Override
    public void setDataUltimaModifica(DateTime dataUltimaModifica) {
        this.dataUltimaModifica = dataUltimaModifica;
    }

    @Override
    public double getDurata() {
        return durata;
    }

    @Override
    public void setDurata(double durata) {
        this.durata = durata;
    }
}
