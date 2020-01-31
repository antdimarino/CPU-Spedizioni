package db;

import FactoryMethod.Veicolo;
import GenericObject.Collo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ColloDAOImp implements ColloDAO{
    private Connection conn;

    @Override
    public String queryMittente(String code) {
        String sql = "SELECT CittàMittente FROM Collo WHERE Code = ?";
        String city = null;

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setString(1, code);

            ResultSet rs = smt.executeQuery();

            while( rs.next() )
            {
                city = rs.getString("CittàMittente");
            }

            this.conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return city;
    }

    public void insertSqlCollo(Collo c)    {
        String sql = "INSERT INTO Collo VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ";

        System.out.println("Sto inserendo il collo con data partenze = " + c.getDataPartenza());

        try
        {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);
            System.out.println("getcon");
            System.out.println(c.getEmailM());
            System.out.println(c.getCode());

            smt.setString(1, c.getNomeMitt() );
            smt.setString(2, c.getNomeDest() );
            smt.setDouble(3, c.getPeso());
            smt.setString(4, c.getViaM() );
            smt.setString(5, c.getFilialeP());
            smt.setString(6, c.getEmailM() );
            smt.setString(7, c.getViaD() );
            smt.setString(8, c.getFilialeD() );
            smt.setString(9, c.getEmailD() );
            smt.setString(10, c.getCode() );
            smt.setString(11, c.getData());
            smt.setString(12, c.getDataPartenza());

            smt.executeUpdate();
            //System.out.println("Collo Inserito nel DB");
            this.conn.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Collo> queryColloDataNull(Veicolo v) {
        String sql = "SELECT * FROM Collo JOIN Tratta ON Collo.Code = Tratta.CodeCollo WHERE DataPartenza IS NULL AND CodeVeicolo = ? AND CittàPartenza = ?";

        List<Collo> c = new ArrayList<>();

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setString(1, v.getCodeVeicolo());
            smt.setString(2, v.getFilialeAppartenenza());

            ResultSet rs = smt.executeQuery();

            while( rs.next() )
            {
                c.add( new Collo( rs.getString("Code"), rs.getString("NomeMittente"), rs.getString("NomeDestinatario"), rs.getString("emailMittente"), rs.getString("emailDestinatario"),
                        rs.getString("ViaMittente"), rs.getString("ViaDestinatario"), rs.getDouble("Peso"), rs.getString("CittàMittente"), rs.getString("CittàDestinatario"), rs.getString("Data") ));
            }

            this.conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        System.out.println("NUMERO COLLI CON DATA NULL = " + c.get(0).getCode());
        return c;
    }

//    public void insertSqlCustodisci(String code, String filialeP)    {
//        String sql = "INSERT INTO Collo VALUES(?,?,?,?)";
//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//
//        try {
//            this.conn = ConnectorDb.getCon();
//            PreparedStatement smt = conn.prepareStatement(sql);
//
//            smt.setString(1, formatter.format(date));
//            smt.setString(2, code);
//            smt.setString(3, filialeP);
//            smt.setString(4, null);
//            smt.executeUpdate();
//
//            System.out.println("Inserimento data effettuato");
//            this.conn.close();
//        }
//        catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
//    }

    @Override
    public void updateDataPartenza(String code, String data) {
        String sql = "UPDATE Collo SET DataPartenza = ? WHERE Code = ?";

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setString(1, data);
            smt.setString(2, code);
            smt.executeUpdate();

            //System.out.println("Update data effettuato");
            this.conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String queryData(String code, String filialeP) {
        String sql = "SELECT DataPartenza FROM Collo WHERE Code = ? AND CittàMittente = ?";
        String data = null;

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setString(1, code);
            smt.setString(2, filialeP);

            ResultSet rs = smt.executeQuery();

            while( rs.next() ) {
                data = rs.getString("DataPartenza");
            }

            //System.out.println("query data effettuato");
            this.conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return data;
    }

}
