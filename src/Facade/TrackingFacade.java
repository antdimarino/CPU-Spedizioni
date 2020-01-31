package Facade;

import FactoryMethod.Veicolo;
import GenericObject.Edge;
import GenericObject.Node;
import GenericObject.Tratta;
import db.*;
import javafx.util.Pair;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class TrackingFacade {

    public static int checkStatoPacco(String code){
        int check = 0;
        int h = 0, min = 0;

        List<Node> t = effettuaTracking(code);

        if( t == null)
            return 0;

        ColloDAO cdao = new ColloDAOImp();
        String data = cdao.queryData( code, t.get(0).getName() );

        System.out.println(data);

        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");
        DateTime d = formatter.parseDateTime( data );

        for( int i = 1; i < t.size(); i++ ){
            h += (int)t.get(i).getPeso();
            min += (int)(100 * (t.get(i).getPeso() - (int)t.get(i).getPeso())) ;
        }

        d = d.plusMinutes(min);
        d = d.plusHours(h);

        System.out.println(d);

        if( d.isBefore( new DateTime() ))
            check = 1;
        else
            check = 2;

        return check;
    }

    /**
     *  Metodo per determinare il numero di Citta` gia visitate
     *  Utilizza le differenze tra tempo totale di viaggio gia`
     *  percorso meno tempi di percorrenza per ogni citta`
     * @return num citta` visitate
     */
        public static List<String> determinaCityVisitate(String code){

        List<String> date = new ArrayList<>();

        TrattaDAO dao = new TrattaDAOImp();
        List<Tratta> t = dao.queryTratta(code);

        if(t.size() == 0)
            return null;

        VeicoloDAO vdao = new VeicoloDAOImp();
        Veicolo v = vdao.findVeicolo( t.get(0).getCodeVeicolo() );
        if( !v.getStato().equalsIgnoreCase("In Viaggio"))
            return null;

        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");
        DateTime dt = new DateTime();
        DateTime d = v.getDataUltimaModifica();
        date.add( d.toString(fmt) );


        for (Tratta tratta : t) {

            int min = (int) (100 * (tratta.getTempo() - (int) tratta.getTempo() ) );
            d = d.plusHours( (int) tratta.getTempo() );
            d.plusMinutes(min);

            String str = d.toString( fmt );
            if ( d.isBefore(dt) ){
                date.add( str );
            }
        }

        return date;
    }

    public static List<Node> effettuaTracking(String code){
        ColloDAO dao = new ColloDAOImp();
        String city = dao.queryMittente(code);

        TrattaDAO tdao = new TrattaDAOImp();
        List<Tratta> t = tdao.queryTratta(code);
        if( t.size() == 0)
            return null;

        List<Edge> e = ricomponiPercorso(t, city);

        return cercaCoordinate( e );
    }

    private static List<Node> cercaCoordinate( List<Edge> e){

        FilialeDAO dao = new FilialeDAOImp();
        List<Node> n = new ArrayList<>();

        for (int i = 0; i < e.size(); i++ ) {
            Pair<Double, Double> p = dao.queryCoord(e.get(i).getFirstNode().getName());
            if( i != e.size()-1 ) {

                e.get(i).getFirstNode().setCoord(p);
                e.get(i).getFirstNode().setPeso(e.get(i).getPeso());
                n.add( e.get(i).getFirstNode() );

            }else{

                e.get(i).getFirstNode().setCoord(p);
                e.get(i).getFirstNode().setPeso(e.get(i).getPeso());
                n.add( e.get(i).getFirstNode() );


                Pair<Double, Double> pi = dao.queryCoord(e.get(i).getSecondNode().getName());
                e.get(i).getSecondNode().setCoord(pi);
                e.get(i).getSecondNode().setPeso(e.get(i).getPeso());
                n.add( e.get(i).getSecondNode() );
            }
        }

        return n;
    }

    private static List<Edge> ricomponiPercorso(List<Tratta> t, String city){
        List<Edge> e = new ArrayList<>();

        for (Tratta tr: t) {
            if( tr.getPartenza().equalsIgnoreCase(city) ){
                e.add( new Edge( tr.getTempo(), new Node(tr.getPartenza()), new Node(tr.getDestinazione()) ));
            }
        }

        for(int i = 0; i < e.size(); i++ ){
            for (Tratta tratta : t) {
                if (e.get(i).getSecondNode().getName().equalsIgnoreCase(tratta.getPartenza())) {

                    e.add(new Edge(tratta.getTempo(), new Node(tratta.getPartenza()), new Node(tratta.getDestinazione())));
                }
            }
        }

        return e;
    }
}
