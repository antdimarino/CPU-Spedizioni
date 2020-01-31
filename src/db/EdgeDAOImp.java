package db;

import GenericObject.Edge;
import GenericObject.Node;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EdgeDAOImp implements EdgeDAO{
    private Connection conn;

    public ArrayList<Edge> queryEdge()    {
        String sql = "SELECT * FROM Collegata";
        ArrayList<Edge> edges = new ArrayList<>();

        try {
            this.conn = ConnectorDb.getCon();
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(sql);

            while ( rs.next() )
            {
                edges.add( new Edge(  rs.getDouble( "Tempo"), new Node(rs.getString("CittàFilialeA")) , new Node(rs.getString("CittàFilialeB")) ));
            }

            //System.out.println("Query Edge eseguita");
            this.conn.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return edges;
    }
}
