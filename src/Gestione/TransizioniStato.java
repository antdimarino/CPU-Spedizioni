package Gestione;

import FactoryMethod.Veicolo;
import StatePattern.Stato;
import StatePattern.Transizione;
import db.VeicoloDAO;
import db.VeicoloDAOImp;

import java.util.List;

public class TransizioniStato {


    public static void sincronizzaStatiVeicoli(){
        VeicoloDAO dao = new VeicoloDAOImp();
        List<Veicolo> veicolo = dao.findAllVeicoli();

        for (Veicolo v: veicolo) {

            Transizione t = new Transizione( v.getStato() );
            Stato statoVeicolo = t.getStato();

            System.out.println(v.getDurata());

            Veicolo ve = statoVeicolo.gestioneStatoVeicolo( v);

            dao.updateDataModifica(ve);
            dao.updateCapienzaDispVeicolo(ve);
            dao.updateStatoVeicolo(ve);
        }

    }
}
