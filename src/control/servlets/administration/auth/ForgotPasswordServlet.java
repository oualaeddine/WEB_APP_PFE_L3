package control.servlets.administration.auth;

import control.servlets.MyServlet;
import control.system.managers.AuthManager;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.daos.ClientDAO;
import model.db.daos.EmployeDAO;
import model.db.daos.OttClientDAO;
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
        System.out.println("Post taa forgotpassword servlet");
        String isClient = request.getParameter("client");
        if (isClient != null) {
            String token = request.getParameter("code");
            if (token == null) {
                System.out.println("token = null");
                String address = request.getParameter("emailAdress");
                Client client = new ClientDAO().getByEmail(address);
                if (client != null && isClient.equals("true")) {
                    String code = new OttClientDAO().generateNewToken(client.getId());
                    Util.sendEmail(address, "Récuperation du mot de passe", Util.getForgotPasswordEmail(address, code, true));
                    this.getServletContext().getRequestDispatcher("/jsp/client/forgotPassword.jsp").forward(request, response);
                    System.out.println(code);
                }
            } else {
                int userId = Integer.parseInt(request.getParameter("linsa"));
                System.out.println("verify token: " + new OttClientDAO().verifyToken(token, userId));
            }
        } else {
            System.out.println("Client = null");
            String token = request.getParameter("code");
            if (token == null) {

                String adress = request.getParameter("emailAdress");
                Employe employe = new EmployeDAO().getByEmail(adress);

                String code = new OttDAO().generateNewToken(employe.getId());
                try {
                    GoogleMail.Send("eritpimmobilier", "eritppfe", adress, "Récuperation du mot de passe", Util.getForgotPasswordEmail(adress, code, false));

                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                this.getServletContext().getRequestDispatcher("/html/forgot-password.html").forward(request, response);
                System.out.println(code);
            } else {
                int userId = Integer.parseInt(request.getParameter("linsa"));
                System.out.println(new OttDAO().verifyToken(token, userId));

            }
        }

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("linsa") == null) {
            if (request.getParameter("client").equals("true")) {
                this.getServletContext().getRequestDispatcher("/jsp/client/forgotPassword.jsp").forward(request, response);
            } else {
                this.getServletContext().getRequestDispatcher("/html/forgot-password.html").forward(request, response);
            }
        } else {
            String isClient = request.getParameter("client");
            if (isClient.equals("true")) {
                String token = request.getParameter("code");
                int userId = Integer.parseInt(request.getParameter("linsa"));
                if (new OttClientDAO().verifyToken(token, userId)) {
                    Client client = (Client) new ClientDAO().getById(userId);
                    String username = client.getUsername();
                    new AuthManager().createSessionForClient(username, request);
                    this.getServletContext().getRequestDispatcher("/ClientServlet?what=changerMdp").forward(request, response);
                } else {
                    System.out.println("verify token = false");
                    response.setContentType("text/html; charset=UTF-8");
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
                            "                    <a href=\"/localhost:8080\" class=\"btn btn-primary btn-lg\"><span class=\"glyphicon glyphicon-home\"></span>\n" +
                            "                        Page d'acceuil </a>\n" +
                            "                </div>\n" +
                            "            </div>\n" +
                            "        </div>\n" +
                            "    </div>\n" +
                            "</div>");
                }
            } else {
                String token = request.getParameter("code");
                int userId = Integer.parseInt(request.getParameter("linsa"));
                if (new OttDAO().verifyToken(token, userId)) {
                    Employe employe = (Employe) new EmployeDAO().getById(userId);
                    String username = employe.getUsername();
                    new AuthManager().createSessionForEmploye(username, request);
                    this.getServletContext().getRequestDispatcher("/ChangePassword").forward(request, response);
                } else {
                    response.setContentType("text/html; charset=UTF-8");
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
                            "                    <a href=\"localhost:8080\" class=\"btn btn-primary btn-lg\"><span class=\"glyphicon glyphicon-home\"></span>\n" +
                            "                        Page d'acceuil </a>\n" +
                            "                </div>\n" +
                            "            </div>\n" +
                            "        </div>\n" +
                            "    </div>\n" +
                            "</div>");
                }
            }

        }
    }
}
