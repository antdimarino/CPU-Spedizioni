package Servlet;

import GenericObject.Utente;
import db.UntentiDAOImp;
import db.UtentiDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("nome");
        String password = request.getParameter("password");

        try{
            //hash della password con algoritmo SHA256
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] passBytes = password.getBytes();
            byte[] passHash = sha256.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for (byte hash : passHash) {
                sb.append(Integer.toString((hash & 0xff) + 0x100, 16).substring(1));
            }
            String pass_hash = sb.toString();

            System.out.println(pass_hash);

            UtentiDAO dao = new UntentiDAOImp();

            Utente u = dao.findUtente( username, pass_hash );

            if( u != null ){
                HttpSession session=request.getSession(true);
                session.setAttribute("username",username );
                response.sendRedirect("dipendente.jsp");
            }
            else{
                String message="Username o password errati";
                RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
                request.setAttribute("msg",message);
                rd.forward(request, response);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
