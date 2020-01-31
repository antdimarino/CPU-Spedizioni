package db;

import GenericObject.Tratta;

import java.util.List;

public interface TrattaDAO {

    void updateTratta(String codeVeicolo, String codeCollo);

    void insertTratta(Tratta t);

    List<Tratta> queryTratta(String codeCollo);

    List<Tratta> queryVeicoli(String codeVeicolo);

    double queryTempo(String partenza, String destinazione);

    double queryTempoTotale(String codeCollo);
}
