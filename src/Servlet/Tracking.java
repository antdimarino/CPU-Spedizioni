package Servlet;

import Facade.TrackingFacade;
import GenericObject.Edge;
import GenericObject.Node;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet( name = "Tracking")
public class Tracking extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("codeSpedizione");

        List<Node> e = TrackingFacade.effettuaTracking(code);
        List<String> s = TrackingFacade.determinaCityVisitate(code);
        int check = TrackingFacade.checkStatoPacco(code);
        String m = "Codice errato! Non e' mai stato spedito un pacco con questo codice.";
        String m2 = "Il pacco e' gia' stato consegnato!";

        System.out.println(check);

        if(check == 0)
            req.setAttribute("m", m);
        else if( check == 1)
            req.setAttribute("m", m2);

        if( e != null )
            req.setAttribute("coord", e);
        if( s != null )
            req.setAttribute("date", s);
        RequestDispatcher rd = req.getRequestDispatcher("tracking.jsp");
        rd.forward(req, resp);
    }
}
