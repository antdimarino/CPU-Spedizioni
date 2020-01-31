package FactoryMethod;

import org.joda.time.DateTime;

public abstract class Veicolo {
    public abstract String getCodeVeicolo();
    public abstract void setCodeVeicolo(String codeVeicolo);
    public abstract String getTipo();
    public abstract void setTipo(String tipo);
    public abstract String getFilialeAppartenenza();
    public abstract void setFilialeAppartenenza(String filialeAppartenenza);
    public abstract String getDestinazione();
    public abstract void setDestinazione(String destinazione);
    public abstract String getStato();
    public abstract void setStato(String stato);
    public abstract void setCapienza(int capienza);
    public abstract int getCapienzaDisponibile();
    public abstract void setCapienzaDisponibile(int capienzaDisponibile);
    public abstract int getCapienza();
    public abstract DateTime getDataUltimaModifica();
    public abstract void setDataUltimaModifica(DateTime dataUltimaModifica);
    public abstract double getDurata();
    public abstract void setDurata(double durata);
}
