package db;

import GenericObject.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UntentiDAOImp implements UtentiDAO {
    private Connection conn;

    @Override
    public Utente findUtente(String username, String password) {
        Utente u = null;

        try {
            this.conn = ConnectorDb.getCon();
            PreparedStatement smt = conn.prepareStatement("select * from utenti where username=? and password=? ");

            smt.setString(1,username);
            smt.setString(2,password);

            ResultSet set = smt.executeQuery();
            u = new Utente(set.getString("username"), set.getString("password"));
            this.conn.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return u;
    }
}
