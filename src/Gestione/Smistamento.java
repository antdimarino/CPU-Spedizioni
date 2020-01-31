package Gestione;

import FactoryMethod.ConcreteAutocarro;
import FactoryMethod.ConcreteFurgone;
import FactoryMethod.Veicolo;
import FactoryMethod.VeicoloFactory;
import GenericObject.Collo;
import Util.RandomString;
import db.*;
import org.joda.time.DateTime;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Random;

public class Smistamento {

    /**
     * metodo che determinerà il veicolo da utilizzare per spedire il pacco
     *
     * @param c oggetto che verrà spedito
     */
    public static void determinaVeicolo(Collo c) {
        VeicoloDAO dao = new VeicoloDAOImp();
        TrattaDAO t = new TrattaDAOImp();
        //ColloDAO cdao = new ColloDAOImp();

        List<Veicolo> veicoli =  dao.findVeicoli( c.getFilialeP(), c.getFilialeD() );

        DateTime dt = new DateTime();
        //DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");
        //cdao.updateDataPartenza(c.getCode(), dt.toString(formatter) );
        Veicolo ve;

        if( veicoli.size() == 0 ){
            System.out.println("Non ci sono Veicoli pronti");

            ve = creaVeicolo(dt, c.getFilialeP(), c.getFilialeD(), (int) (1000 - c.getPeso()));
            dao.addVeicolo(ve);

            t.updateTratta( ve.getCodeVeicolo(), c.getCode());
            double tempo = t.queryTempoTotale( c.getCode());
            dao.updateTempo(tempo, ve.getCodeVeicolo());
        }
        else{
            System.out.println("Prima di nextFit");
            String codeVeicolo = nextFit(veicoli, c.getPeso());

            if( codeVeicolo == null){
                System.out.println("nextFit non ha trovato veicoli disponibili, Quindi bisogna crearne uno nuovo");

                ve = creaVeicolo(dt, c.getFilialeP(), c.getFilialeD(), (int) (1000 - c.getPeso()));
                dao.addVeicolo(ve);
                codeVeicolo = (ve.getCodeVeicolo());


                t.updateTratta( codeVeicolo, c.getCode());
                double tempo = t.queryTempoTotale( c.getCode());
                dao.updateTempo(tempo, ve.getCodeVeicolo());
            }

            t.updateTratta( codeVeicolo, c.getCode());
        }
    }

    /**
     * metodo privato che verrà utilizzato da determinaVeicolo per determinare in quale veicolo verrà inserito il pacco.
     * @return string code veicolo in cui verrà inserito il pacco
     */
      private static String nextFit(List<Veicolo> v, double peso){
          String code = null;
          DateTime dt = new DateTime();

          for (Veicolo veicolo : v) {
              DateTime ultimaModifica = veicolo.getDataUltimaModifica();

              if ( veicolo.getCapienzaDisponibile() - peso > 0 && dt.toLocalDate().isEqual( ultimaModifica.toLocalDate() ) ){

                  code = veicolo.getCodeVeicolo();
                  veicolo.setCapienzaDisponibile((int) (veicolo.getCapienzaDisponibile() - peso));
                  VeicoloDAO dao = new VeicoloDAOImp();
                  dao.updateCapienzaDispVeicolo( veicolo );
                  dao.updateDataModifica( veicolo );
                  System.out.println("Trovato un veicolo disponibile");
                  return code;
              }
          }

          return code;
    }

    private static Veicolo creaVeicolo( DateTime dt, String filialeP, String filialeD, int capD){
          Veicolo ve;

          String[] tipo = {"Furgone", "Autocarro"};
          Random rand = new Random();

          if( tipo[ rand.nextInt(2) ].equalsIgnoreCase("Furgone") ) {
              VeicoloFactory v = new ConcreteFurgone();
              ve = v.costruisciVeicolo(RandomString.getAlphaNumericString(10), "Furgone", 1000, filialeP, filialeD, "pronto", capD, dt, 0);
          }
          else{
              VeicoloFactory v = new ConcreteAutocarro();
              ve = v.costruisciVeicolo(RandomString.getAlphaNumericString(10), "Autocarro", 1000, filialeP, filialeD, "pronto", capD, dt, 0);
          }

          return ve;
    }
}
