package StatePattern;

import FactoryMethod.Veicolo;

public interface Stato {
    Veicolo gestioneStatoVeicolo(Veicolo v);
}
