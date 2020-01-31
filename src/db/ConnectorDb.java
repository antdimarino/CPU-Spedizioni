package db;

import java.sql.*;

public class ConnectorDb {

    private static Connection conn;

    public static Connection getCon()
    {
        try
        {
            String url = "jdbc:sqlite:logistica.db";
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            //System.out.println("Connesso!");
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println("Errore connessione db: "+ e.getMessage() );
        }

        return conn;
    }

}
