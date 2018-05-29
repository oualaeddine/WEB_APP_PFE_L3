package control.api;

import control.system.NotificationService;
import model.beans.Notification;
import model.db.daos.ClientNotificationDAO;
import model.db.daos.EmployeNotificationDAO;
import utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet(name = "NotificationsApi", urlPatterns = "/notificationsApi")
public class NotificationsApi extends API {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String myResponseBody = "walou";

        if (action != null) {
            String userId = request.getParameter("userId");
            int _userId = Integer.parseInt(userId);

            String userType = request.getParameter("userType");

            switch (action) {
                case "getMyNotifications":
                    LinkedList<Notification> notifications = null;
                    switch (userType) {
                        case "client":
                            notifications = new ClientNotificationDAO().getByClient(_userId);
                            break;
                        case "employee":
                            notifications = new EmployeNotificationDAO().getByEmployee(_userId);
                            break;
                    }
                    myResponseBody = JsonUtil.notificationsToJson(notifications);
                    break;
                case "register_token":
                    String token = request.getParameter("token");

                    myResponseBody = "" + new NotificationService().registerToken(_userId, token, userType);
                    break;
            }
        }
        response.getWriter().append(myResponseBody);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
