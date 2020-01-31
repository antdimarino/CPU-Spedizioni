package FactoryMethod;

import org.joda.time.DateTime;

public class ConcreteAutocarro implements VeicoloFactory {
    @Override
    public Veicolo costruisciVeicolo(String codeVeicolo, String tipo, int capienza, String filialeAppartenenza, String destinazione, String stato, int capienzaDisponibile, DateTime dataUltimaModifica, double durata) {
        return new AutoCarro(codeVeicolo, tipo, capienza, filialeAppartenenza, destinazione, stato, capienzaDisponibile, dataUltimaModifica, durata);
    }

}
