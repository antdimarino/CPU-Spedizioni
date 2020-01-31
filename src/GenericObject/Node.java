package GenericObject;

import javafx.util.Pair;

public class Node {
    private String name;
    private Pair<Double, Double> coord;
    private double peso;

    public Node( String name ){
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pair<Double, Double> getCoord() {
        return coord;
    }

    public void setCoord(Pair<Double,Double> coord) {
        this.coord = coord;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
