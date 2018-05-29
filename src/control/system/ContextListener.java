package control.system;

import com.twilio.Twilio;
import utils.Util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ContextListener implements ServletContextListener {

    // Public constructor is required by servlet spec
    public ContextListener() {
    }

    // TODO: 5/29/2018  get twilio credentials
    private static final String ACCOUNT_SID = "SKe4f7014bb5182752592814ce207e6aab";
    private static final String AUTH_TOKEN = "f6e170b9f25f223d44d6bb351649384a";

    // -------------------------------------------------------
    // ContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed).
         You can initialize servlet context related data here.
      */
//        initializeNotificationsService();
        initializeTwilio();
    }

    private void initializeTwilio() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    /**
     * this method initializes the notification service when the app is deployed
     */
    private void initializeNotificationsService() {
        System.out.println("initializing the notifications service");
        new NotificationService().initService();
        Util.sendSms("696689498", "test ta3 sms");
    }


    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }
}
