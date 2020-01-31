package Servlet;

import Gestione.TransizioniStato;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Sincronizzazione")
public class Sincronizzazione extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String work = req.getParameter("bt");

        if( work.equalsIgnoreCase("Sincronizza Veicoli") )
            TransizioniStato.sincronizzaStatiVeicoli();

        String message="Sincronizzazione effettuata";
        RequestDispatcher rd = req.getRequestDispatcher("dipendente.jsp");
        req.setAttribute("msg",message);
        rd.forward(req, resp);
    }
}
