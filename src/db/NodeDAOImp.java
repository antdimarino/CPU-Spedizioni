package db;

import GenericObject.Node;
import java.sql.*;
import java.util.ArrayList;



public class NodeDAOImp implements NodeDAO {
    private Connection conn;

    public ArrayList<Node> queryNode()    {
        String sql = "SELECT * FROM Filiale";
        ArrayList<Node> nodes = new ArrayList<>();

        try {
            this.conn = ConnectorDb.getCon();
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(sql);

            while ( rs.next() )
            {
                nodes.add( new Node( rs.getString("Città") ) );
            }

            //System.out.println("Query Node eseguita");
            this.conn.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return nodes;
    }
}
