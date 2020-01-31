package StatePattern;

import FactoryMethod.Veicolo;
import GenericObject.Collo;
import db.ColloDAO;
import db.ColloDAOImp;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.List;

public class StatoPronto implements Stato {

    /**
     * Metodo che gestisce lo stato Pronto. L'unica transazione di stato possibile è da Pronto -> In viaggio
     * Si controlla la data di ultima modifica del veicolo, se la data precede le 9 di mattina del giorno in cui si controlla
     * allora si passa dallo stato Pronto allo stato In Viaggio. Se ci sarà il cambiamento di stato verrà aggiornata la
     * data di ultima modifica del veicolo.
     */
    @Override
    public Veicolo gestioneStatoVeicolo(Veicolo v) {

        DateTime dt = new DateTime();
        LocalDate dtLocal = dt.toLocalDate();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("Prima della query per colli null");

        ColloDAO cdao = new ColloDAOImp();
        List<Collo> c = cdao.queryColloDataNull(v);
        System.out.println(v.getCodeVeicolo());
        System.out.println(c.size());

        DateTime check = new DateTime(dtLocal.getYear(), dtLocal.getMonthOfYear(), dtLocal.getDayOfMonth(), 9, 0);

        if( v.getDataUltimaModifica().toLocalDate().isBefore( check.toLocalDate()) && v.getStato().equalsIgnoreCase("Pronto") && v.getCapienzaDisponibile() < 1000 ){
            v.setStato("In Viaggio");
            v.setDataUltimaModifica(dt);

            System.out.println("Prima di update colli null");
            for (Collo collo : c){
                cdao.updateDataPartenza(collo.getCode(), dt.toString(fmt));
            }

            System.out.println("Dopo di update colli null");

        }

        return v;
    }
}
