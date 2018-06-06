package control.system;

import com.twilio.Twilio;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ContextListener implements ServletContextListener {

    // Public constructor is required by servlet spec
    public ContextListener() {
    }

    // TODO: 5/29/2018  get twilio credentials
    private static final String ACCOUNT_SID = "ACd4eed8271623412d543a34e3c3075aad";
    private static final String AUTH_TOKEN = "5513eaecf302e4189d826500bec1e41c";

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
        //  Util.sendSms("+213696689498", "test ta3 sms");

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
    }


    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }
}
