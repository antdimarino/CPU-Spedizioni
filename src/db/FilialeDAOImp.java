package db;

import javafx.util.Pair;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilialeDAOImp implements FilialeDAO {
    private Connection conn;

    @Override
    public Pair<Double, Double> queryCoord(String city) {
        String sql = "SELECT * FROM Filiale WHERE Città = ?";
        Pair<Double, Double> p = null;

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setString(1, city);

            ResultSet rs = smt.executeQuery();

            while( rs.next() )
            {
                p = new Pair<>(rs.getDouble("Lat"), rs.getDouble("Long"));
            }

            this.conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return p;
    }
}
