package db;

import FactoryMethod.ConcreteAutocarro;
import FactoryMethod.ConcreteFurgone;
import FactoryMethod.Veicolo;
import FactoryMethod.VeicoloFactory;
import org.joda.time.DateTime;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeicoloDAOImp implements VeicoloDAO {
    private Connection conn;

    @Override
    public void updateTempo(double tempo, String codeVeicolo) {
        String sql = "UPDATE Veicolo SET Durata = ? WHERE CodiceVeicolo = ?";

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setDouble(1, tempo);
            smt.setString(2, codeVeicolo);
            smt.executeUpdate();

            //System.out.println("Update Veicolo effettuato");
            this.conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void updateDataModifica(Veicolo v){
        String sql =  "UPDATE Veicolo SET DataUltimaModifica = ? WHERE CodiceVeicolo = ?";

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setString(1, v.getDataUltimaModifica().toString());
            smt.setString(2, v.getCodeVeicolo());
            smt.executeUpdate();

            //System.out.println("Update Veicolo effettuato");
            this.conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void updateStatoVeicolo(Veicolo v) {
        String sql =  "UPDATE Veicolo SET stato = ? WHERE CodiceVeicolo = ?";

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setString(1, v.getStato());
            smt.setString(2, v.getCodeVeicolo());
            smt.executeUpdate();

            //System.out.println("Update Veicolo effettuato");
            this.conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void updateCapienzaDispVeicolo(Veicolo v) {
        String sql =  "UPDATE Veicolo SET CapienzaDisponibile = ? WHERE CodiceVeicolo = ?";

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setInt(1, v.getCapienzaDisponibile());
            smt.setString(2, v.getCodeVeicolo());
            smt.executeUpdate();

            //System.out.println("Update Veicolo effettuato");
            this.conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void addVeicolo(Veicolo v) {
        String sql = "INSERT INTO Veicolo VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setString(1, v.getCodeVeicolo());
            smt.setString(2, v.getTipo());
            smt.setString(3, String.valueOf(v.getCapienza()));
            smt.setString(4, v.getFilialeAppartenenza());
            smt.setString(5, v.getDestinazione());
            smt.setString(6, v.getStato());
            smt.setDouble(7, v.getCapienzaDisponibile());
            smt.setString(8, v.getDataUltimaModifica().toString());
            smt.setDouble(9, v.getDurata());

            smt.executeUpdate();
            //System.out.println("Veicolo Inserito nel DB");
            this.conn.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Veicolo> findVeicoli(String partenza, String destinazione) {
        String sql = "SELECT * FROM Veicolo WHERE Destinazione = ? AND FilialeAppartenenza = ? AND stato = ?";
        List<Veicolo> veicoli = new ArrayList<>();

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setString(1, destinazione);
            smt.setString(2, partenza);
            smt.setString(3, "pronto");

            ResultSet rs = smt.executeQuery();

            while ( rs.next() )
            {
                double peso = rs.getDouble("CapienzaDisponibile");
                if( peso > 0) {
                    String date = rs.getString("DataUltimaModifica");
                    DateTime d = new DateTime(date);
                    String tipo = rs.getString("Tipo");
                    if (tipo.equalsIgnoreCase("Furgone")) {
                        VeicoloFactory factory = new ConcreteFurgone();
                        veicoli.add(factory.costruisciVeicolo(rs.getString("CodiceVeicolo"), tipo, rs.getInt("Capienza"), rs.getString("FilialeAppartenenza"), rs.getString("Destinazione"), rs.getString("stato"), rs.getInt("CapienzaDisponibile"), d, rs.getDouble("Durata")));
                    } else {
                        VeicoloFactory factory = new ConcreteAutocarro();
                        veicoli.add(factory.costruisciVeicolo(rs.getString("CodiceVeicolo"), tipo, rs.getInt("Capienza"), rs.getString("FilialeAppartenenza"), rs.getString("Destinazione"), rs.getString("stato"), rs.getInt("CapienzaDisponibile"), d, rs.getDouble("Durata")));
                    }
                }
            }

            //System.out.println("Query finVeicoli eseguita");
            this.conn.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return veicoli;
    }

    @Override
    public List<Veicolo> findAllVeicoli() {
        String sql = "SELECT * FROM Veicolo";
        List<Veicolo> veicoli = new ArrayList<>();

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            ResultSet rs = smt.executeQuery();

            while ( rs.next() )
            {
                String date = rs.getString("DataUltimaModifica");
                DateTime d = new DateTime(date);
                String tipo = rs.getString("Tipo");
                if(tipo.equalsIgnoreCase("Furgone")){
                    VeicoloFactory factory = new ConcreteFurgone();
                    veicoli.add( factory.costruisciVeicolo( rs.getString("CodiceVeicolo"), tipo, rs.getInt("Capienza"), rs.getString("FilialeAppartenenza"), rs.getString("Destinazione"), rs.getString("stato"), rs.getInt("CapienzaDisponibile"), d, rs.getDouble("Durata") ) );
                }
                else{
                    VeicoloFactory factory = new ConcreteAutocarro();
                    veicoli.add( factory.costruisciVeicolo( rs.getString("CodiceVeicolo"), tipo, rs.getInt("Capienza"), rs.getString("FilialeAppartenenza"), rs.getString("Destinazione"), rs.getString("stato"), rs.getInt("CapienzaDisponibile"), d, rs.getDouble("Durata") ) );
                }
            }

            //System.out.println("Query findAllVeicoli eseguita");
            this.conn.close();

        } catch (SQLException e){
            System.out.println( e.getMessage() );
        }

        return veicoli;
    }

    @Override
    public Veicolo findVeicolo(String code) {
        String sql = "SELECT * FROM Veicolo WHERE CodiceVeicolo = ?";
        Veicolo v = null;

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setString(1, code);

            ResultSet rs = smt.executeQuery();

            while ( rs.next() )
            {
                String date = rs.getString("DataUltimaModifica");
                DateTime d = new DateTime(date);
                String tipo = rs.getString("Tipo");
                if(tipo.equalsIgnoreCase("Furgone")){
                    VeicoloFactory factory = new ConcreteFurgone();
                    v = factory.costruisciVeicolo( rs.getString("CodiceVeicolo"), tipo, rs.getInt("Capienza"), rs.getString("FilialeAppartenenza"), rs.getString("Destinazione"), rs.getString("stato"), rs.getInt("CapienzaDisponibile"), d, rs.getDouble("Durata") );
                }
                else{
                    VeicoloFactory factory = new ConcreteAutocarro();
                    v = factory.costruisciVeicolo( rs.getString("CodiceVeicolo"), tipo, rs.getInt("Capienza"), rs.getString("FilialeAppartenenza"), rs.getString("Destinazione"), rs.getString("stato"), rs.getInt("CapienzaDisponibile"), d, rs.getDouble("Durata") );
                }
            }

            //System.out.println("Query finVeicolo eseguita");
            this.conn.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return v;
    }

}
