package db;

import GenericObject.Utente;


public interface UtentiDAO {

    Utente findUtente(String username, String password);
}
