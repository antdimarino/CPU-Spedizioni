package db;

import GenericObject.Tratta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrattaDAOImp implements TrattaDAO {
    private Connection conn;

    @Override
    public void updateTratta(String codeVeicolo, String codeCollo) {
        String sql = "UPDATE Tratta SET CodeVeicolo = ? WHERE CodeCollo = ?";

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setString(1, codeVeicolo);
            smt.setString(2, codeCollo);
            smt.executeUpdate();

            //System.out.println("Update Tratta effettuato");
            this.conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void insertTratta(Tratta t)    {
        double tempo = queryTempo(t.getPartenza(), t.getDestinazione());
        String sql = "INSERT INTO Tratta VALUES(?,?,?,?,?,?)";

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setString(1, t.getCodeTratta());
            smt.setString(2, t.getPartenza());
            smt.setString(3, t.getDestinazione());
            smt.setDouble(4, tempo);
            smt.setString(5, t.getCodeCollo());
            smt.setString(6, null);

            smt.executeUpdate();

            //System.out.println("Inserimento Tratta effettuato");
            this.conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Tratta> queryTratta(String codeCollo) {
        String sql = "SELECT * FROM Tratta WHERE CodeCollo = ?";
        List<Tratta> t = new ArrayList<>();

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setString(1, codeCollo);

            ResultSet rs = smt.executeQuery();

            while( rs.next() )
            {
                t.add( new Tratta(rs.getString("CodeTratta"), rs.getString("CittàPartenza"), rs.getString("CittàDestinazione"), codeCollo, rs.getString("CodeVeicolo") ,rs.getDouble("Tempo") ) );
            }

            this.conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return t;

    }

    @Override
    public List<Tratta> queryVeicoli(String codeVeicolo) {
        String sql = "SELECT * FROM Tratta WHERE CodeVeicolo = ?";
        List<Tratta> t = new ArrayList<>();

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setString(1, codeVeicolo);

            ResultSet rs = smt.executeQuery();

            while( rs.next() )
            {
                t.add( new Tratta(rs.getString("CodeTratta"), rs.getString("CittàPartenza"), rs.getString("CittàDestinazione"), rs.getString("CodeCollo"), codeVeicolo ,rs.getDouble("Tempo") ) );
            }

            this.conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return t;
    }

    public double queryTempo(String partenza, String destinazione){
        String sql = "SELECT Tempo FROM Collegata WHERE CittàFilialeA = '" + partenza + "' AND CittàFilialeB = '" + destinazione + "';";
        double peso = 0;
        //System.out.println(sql);

        try {
            this.conn = ConnectorDb.getCon();
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(sql);

            //System.out.println(sql);

            peso = rs.getDouble("Tempo");
            System.out.println(peso);
            this.conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return peso;
    }

    @Override
    public double queryTempoTotale(String codeCollo) {
        String sql = "SELECT Tempo FROM Tratta WHERE CodeCollo = ?";
        double peso = 0;

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setString(1, codeCollo);

            ResultSet rs = smt.executeQuery();

            while( rs.next() )
            {
                double p = rs.getDouble("Tempo");
                peso += p;
            }

            this.conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return peso;
    }
}
