package FactoryMethod;

import org.joda.time.DateTime;

public interface VeicoloFactory {

    Veicolo costruisciVeicolo(String codeVeicolo, String tipo, int capienza, String filialeAppartenenza, String destinazione, String stato, int capienzaDisponibile, DateTime dataUltimaModifica, double durata);
}
