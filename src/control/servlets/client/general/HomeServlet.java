package control.servlets.client.general;

import control.servlets.MyServlet;
import model.beans.Localite;
import model.beans.Logement;
import model.db.daos.LocaliteDAO;
import model.db.daos.LogementDAO;
import model.enums.TypeLogement;
import model.enums.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

@SuppressWarnings("ALL")
@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isLoggedIn(request) || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.CLIENT) {
            if (request.getParameter("action") == null) {
                request.setAttribute("logements", new LogementDAO().getMostVisitedLogements());
                String what = request.getParameter("what");
                if (what == null) {
                    this.getServletContext().getRequestDispatcher("/jsp/client/home.jsp").forward(request, response);
                } else {
                    switch (what) {
                        case "404":
                            this.getServletContext().getRequestDispatcher("/jsp/client/404.jsp").forward(request, response);
                            break;
                        case "logement":
                            int id = Integer.parseInt(request.getParameter("id"));
                            if (new LogementDAO().getById(id) == null) {
                                this.getServletContext().getRequestDispatcher("/jsp/client/404.jsp").forward(request, response);
                            } else {
                                this.getServletContext().getRequestDispatcher("/jsp/client/detailsLogement.jsp?id=" + id).forward(request, response);
                            }
                            break;
                        case "contacter":
                            this.getServletContext().getRequestDispatcher("/jsp/client/nousContacter.jsp").forward(request, response);
                            break;
                        case "logements":
                            this.getServletContext().getRequestDispatcher("/jsp/client/logements.jsp").forward(request, response);
                            break;
                        case "stats":
                            this.getServletContext().getRequestDispatcher("/jsp/client/statsClient.jsp").forward(request, response);
                            break;
                    }
                }
            } else {
                String action = request.getParameter("action");
                LinkedList logements = new LogementDAO().getAll();
                if (action != null)
                    if (action.equals("search")) {

                        Logement logement = new Logement();

                        String type = request.getParameter("typeLogementSearch");
                        TypeLogement typeLogement = type.equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT;
                        logement.setTypeLogement(typeLogement);

                        int idLocal = 0;
                        if (!request.getParameter("localiteSearch").equals("null"))
                            idLocal = Integer.parseInt(request.getParameter("localiteSearch"));
                        Localite localite = (Localite) new LocaliteDAO().getById(idLocal);
                        logement.setLocalite(localite);

                        String[] prix = (request.getParameter("prix")).split(",");
                        double pMin = Double.parseDouble(prix[0]), pMax = Double.parseDouble(prix[1]);

                        String[] superficies = (request.getParameter("superficie")).split(",");
                        double sMin = Double.parseDouble(superficies[0]), sMax = Double.parseDouble(superficies[1]);

                        int nbrPieces = Integer.parseInt(request.getParameter("nbrPiecesSearch"));
                        logement.setNbrPieces(nbrPieces);

                        int nbrSdb = Integer.parseInt(request.getParameter("nbrSdbSearch"));
                        logement.setNbrSdb(nbrSdb);

                        boolean meuble = request.getParameter("meubles").equals("true");
                        logement.setMeubles(meuble);

                        boolean garage = request.getParameter("garage").equals("true");
                        logement.setAvecGarage(garage);

                        boolean jardin = request.getParameter("jardin").equals("true");
                        logement.setAvecJardin(jardin);

                        boolean soussol = request.getParameter("sousSol").equals("true");
                        logement.setAvecSousSol(soussol);

                        logements = new LogementDAO().getLogementsSelonCriteres(logement, pMax * 1000000, pMin * 1000000, sMax, sMin);


                    }
                request.setAttribute("logements", logements);
                this.getServletContext().getRequestDispatcher("/jsp/client/home.jsp").forward(request, response);
            }
        } else {
            redirectToDashboard(request, response);
        }

    }

}
