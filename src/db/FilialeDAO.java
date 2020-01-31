package db;

import javafx.util.Pair;

public interface FilialeDAO {
    Pair<Double, Double> queryCoord(String city );
}
