package GenericObject;

public class Edge {
    private double peso;
    private Node firstNode;
    private Node secondNode;

    public Edge( double peso, Node a, Node b )
    {
        this.peso = peso;
        this.firstNode = a;
        this.secondNode = b;
    }

    public double getPeso() {
        return peso;
    }

    public Node getFirstNode() {
        return firstNode;
    }

    public Node getSecondNode() {
        return secondNode;
    }

    public void setFirstNode(Node firstNode) {
        this.firstNode = firstNode;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setSecondNode(Node secondNode) {
        this.secondNode = secondNode;
    }
}
