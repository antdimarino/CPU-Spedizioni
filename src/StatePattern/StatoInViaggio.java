package StatePattern;

import FactoryMethod.Veicolo;
import org.joda.time.DateTime;
import org.joda.time.Period;

public class StatoInViaggio implements Stato {
    /**
     * Metodo che gestisce lo stato In Viaggio. L'unica transazione di stato possibile è da In Viaggio -> Pronto se si
     * verificano le condizioni per il cambio stato.
     * Verrà valutato da quanto tempo il veicolo è in viaggio se il tempo supera la durata effettiva del viaggio che
     * dovrebbe compiere allora il veicolo è riportato allo stato Pronto.
     * Se la durata effettiva del viaggio non supera la durata prevista il veicolo resta nello stato In Viaggio
     */
    @Override
    public Veicolo gestioneStatoVeicolo(Veicolo v) {

        Period p = new Period(v.getDataUltimaModifica(), new DateTime());
        int ore = p.getHours();

        System.out.println("ore = " + ore);
        System.out.println("tempo = " + v.getDurata());

        if( v.getStato().equalsIgnoreCase("In Viaggio") && ore >= v.getDurata()){
            v.setCapienzaDisponibile(1000);
            v.setStato("Pronto");
            v.setDataUltimaModifica(new DateTime());
        }

        return v;
    }
}
