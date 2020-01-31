package GenericObject;

import Visitor.VisitorCalcoloPercorso;
import db.EdgeDAO;
import db.EdgeDAOImp;
import db.NodeDAO;
import db.NodeDAOImp;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.ArrayList;

public class Grafo {

    private String partenza;
    private String destinazione;
    private SimpleWeightedGraph g;
    private static Grafo instance;


    private Grafo(){
        g = new SimpleWeightedGraph< String, DefaultWeightedEdge >( DefaultWeightedEdge.class );
        this.setNode();
        this.setEdges();
        System.out.println("Costruttore");
    }

    public void accept(VisitorCalcoloPercorso visitor, Collo c)
    {
        visitor.calcolaPercorso(this, c);
    }

    public static synchronized Grafo getInstance() {
        if( instance == null )
        {
            instance = new Grafo();
        }
        return instance;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public SimpleWeightedGraph getGrafo(){ return this.g; }

    public String getDestinazione() {
        return destinazione;
    }

    public String getPartenza() {
        return partenza;
    }

    private void setEdges() {
        EdgeDAO db = new EdgeDAOImp();
        ArrayList<Edge> edges = db.queryEdge();

        for (Edge edge : edges) {
            g.addEdge(edge.getFirstNode().getName(), edge.getSecondNode().getName());
            g.setEdgeWeight(edge.getFirstNode().getName(), edge.getSecondNode().getName(), edge.getPeso());
        }
        System.out.println("setEdge");

    }

    private void setNode(){
        NodeDAO db = new NodeDAOImp();
        ArrayList<Node> nodes = db.queryNode();

        for (Node node : nodes)
            g.addVertex(node.getName());

        System.out.println("setNode");
    }
}
