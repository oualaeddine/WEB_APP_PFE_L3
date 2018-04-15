package control.servlets.administration.auth;

import control.servlets.MyServlet;
import control.system.managers.AuthManager;
import model.beans.humans.Employe;
import model.db.daos.EmployeDAO;
import model.db.daos.OttDAO;
import model.enums.UserType;
import utils.GoogleMail;
import utils.Util;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/ForgotPassword"})
public class ForgotPasswordServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("code");
        if (token == null) {

            String adress = request.getParameter("emailAdress");
            UserType userType = Util.getUserTypeFromString(request.getParameter("selectType"));
            Employe employe = new EmployeDAO().getByEmail(adress);

            String code = new OttDAO().generateNewToken(employe.getId(),userType);
            try {
                GoogleMail.Send("hchimmobilier", "HchImmobilier1234", adress, "", "Récuperation du mot de passe", Util.getForgotPasswordEmail(adress,userType,code) );

            } catch (MessagingException e) {
                e.printStackTrace();
            }
            this.getServletContext().getRequestDispatcher("/html/forgot-password.html").forward(request,response);
            System.out.println(code);
        } else { //todo: test if token is valid
            int userId = Integer.parseInt(request.getParameter("linsa"));
            UserType userType = Util.getUserTypeFromString(request.getParameter("wech"));
            System.out.println(new OttDAO().verifyToken(token, userId, userType));
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("linsa") == null) {
            this.getServletContext().getRequestDispatcher("/html/forgot-password.html").forward(request, response);
        } else {
            System.out.println("ani f l'else taa doGet");
            String token = request.getParameter("code");
            int userId = Integer.parseInt(request.getParameter("linsa"));
            UserType userType = Util.getUserTypeFromString(request.getParameter("wech"));
            if (new OttDAO().verifyToken(token, userId, userType)) {
                Employe employe = (Employe) new EmployeDAO().getById(userId);
                String username = employe.getUsername();
                new AuthManager().createSessionForEmploye(username,request);
                this.getServletContext().getRequestDispatcher("/ChangePassword").forward(request, response);
            } else {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.print("<link href=\"//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css\" rel=\"stylesheet\" id=\"bootstrap-css\">\n" +
                        "<script src=\"//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js\"></script>\n" +
                        "<script src=\"//code.jquery.com/jquery-1.11.1.min.js\"></script>\n" +
                        "<!------ Include the above in your HEAD tag ---------->\n" +
                        "\n" +
                        "<div class=\"container\" align=\"center\">\n" +
                        "    <div class=\"row\">\n" +
                        "        <div class=\"col-md-12\">\n" +
                        "            <div class=\"error-template\">\n" +
                        "                <h1>\n" +
                        "                    Oops!</h1>\n" +
                        "                <h2>\n" +
                        "                    Erreur</h2>\n" +
                        "                <div class=\"error-details\">\n" +
                        "                    Désolé ! Le lien sur lequel vous avez cliqué n'est plus valable\n" +
                        "                </div>\n" +
                        "                <div class=\"error-actions\">\n" +
                        "                    <a href=\"http://www.jquery2dotnet.com\" class=\"btn btn-primary btn-lg\"><span class=\"glyphicon glyphicon-home\"></span>\n" +
                        "                        Page d'acceuil </a><a href=\"http://www.jquery2dotnet.com\" class=\"btn btn-default btn-lg\"><span class=\"glyphicon glyphicon-envelope\"></span> Contacter Support </a>\n" +
                        "                </div>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</div>");
            }
        }
    }
}
