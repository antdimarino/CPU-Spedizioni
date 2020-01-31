package Servlet;

import Facade.SpedizioneFacade;
import GenericObject.Collo;
import GenericObject.Grafo;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static java.lang.Thread.sleep;

@WebServlet(name = "Inserimento")
public class Inserimento extends HttpServlet {

    private Grafo grafo;

    public Inserimento()
    {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String nomeMittente = request.getParameter("nameMittente");
        String nomeDestinatario = request.getParameter("nameDestinatario");
        String emailMittente = request.getParameter("emailMittente");
        String emailDestinatario = request.getParameter("emailDestinatario");
        String indirizzoMittente = request.getParameter("viaM");
        String indirizzoDestinatario = request.getParameter("viaD");
        double peso = Double.parseDouble(request.getParameter("peso"));
        String filialePartenza = request.getParameter("filialePartenza");
        String filialeDestinazione = request.getParameter("filialeDestinazione");

        DateTime dt = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");

        Collo c = new Collo(nomeMittente, nomeDestinatario, emailMittente, emailDestinatario, indirizzoMittente, indirizzoDestinatario, peso, filialePartenza, filialeDestinazione, dt.toString(fmt));
        System.out.println("Collo creato");
        System.out.println(c.getDataPartenza());

        SpedizioneFacade sp = new SpedizioneFacade( c );
        grafo = Grafo.getInstance();
        synchronized (grafo) {
            System.out.println("Sto andando a dormire");
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Il thread si e appena svegliato");
            grafo.setPartenza(filialePartenza);
            grafo.setDestinazione(filialeDestinazione);
            sp.richiestaSpedizione(grafo);
        }

        RequestDispatcher rd=request.getRequestDispatcher("confirmation.jsp");
        rd.forward(request, response);
    }

}
