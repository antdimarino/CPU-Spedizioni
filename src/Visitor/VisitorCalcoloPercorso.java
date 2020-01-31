package Visitor;

import GenericObject.Collo;
import GenericObject.Grafo;
import GenericObject.Tratta;
import Util.RandomString;
import db.TrattaDAO;
import db.TrattaDAOImp;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.List;

public class VisitorCalcoloPercorso {
    public void calcolaPercorso(Grafo grafo, Collo c)
    {
        DijkstraShortestPath alg = new DijkstraShortestPath( grafo.getGrafo() );
        GraphPath cammino = alg.getPath( grafo.getPartenza(), grafo.getDestinazione() );
        List<String> vertici =  cammino.getVertexList();
        TrattaDAO db = new TrattaDAOImp();

        for( int i = 0; i < vertici.size(); i++ )
        {
            if( i+1 < vertici.size() ) {
                String code = RandomString.getAlphaNumericString(10);

                Tratta t = new Tratta(code, vertici.get(i), vertici.get(i+1), c.getCode());

                db.insertTratta( t );
            }
        }
    }
}
