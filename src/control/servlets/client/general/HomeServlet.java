package control.servlets.client.general;

import control.servlets.MyServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String what = request.getParameter("what");
        if (what == null) {
            this.getServletContext().getRequestDispatcher("/jsp/client/home.jsp").forward(request, response);
        } else {
            switch (what) {
                case "logements":
                    this.getServletContext().getRequestDispatcher("/jsp/client/logements.jsp").forward(request, response);
                    break;
                case "stats":
                    this.getServletContext().getRequestDispatcher("/jsp/client/statsClient.jsp").forward(request, response);
                    break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
