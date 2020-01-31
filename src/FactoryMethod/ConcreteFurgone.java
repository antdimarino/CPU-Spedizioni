package FactoryMethod;

import org.joda.time.DateTime;

public class ConcreteFurgone implements VeicoloFactory {
    public Veicolo costruisciVeicolo(String codeVeicolo, String tipo, int capienza, String filialeAppartenenza, String destinazione, String stato, int capienzaDisponibile, DateTime dataUltimaModifica, double durata){
        return new Furgone(codeVeicolo, tipo, capienza, filialeAppartenenza, destinazione, stato, capienzaDisponibile, dataUltimaModifica, durata);
    }
}
