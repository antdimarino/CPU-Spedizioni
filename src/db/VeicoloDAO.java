package db;

import FactoryMethod.Veicolo;

import java.util.List;

public interface VeicoloDAO {

    void updateTempo(double tempo, String codeVeicolo);

    void updateDataModifica(Veicolo v);

    void updateStatoVeicolo(Veicolo v);

    void updateCapienzaDispVeicolo( Veicolo v );

    void addVeicolo( Veicolo v );

    List<Veicolo> findVeicoli( String partenza, String destinazione );

    List<Veicolo> findAllVeicoli();

    Veicolo findVeicolo(String code);
}
