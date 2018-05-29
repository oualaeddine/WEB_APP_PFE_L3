package control.system;

import model.beans.Notification;
import model.beans.humans.Person;
import model.db.daos.ClientDAO;
import model.db.daos.ClientNotificationDAO;
import model.db.daos.EmployeDAO;
import model.db.daos.EmployeNotificationDAO;
import utils.Util;

import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotificationService {

    public NotificationService() {
    }

    /**
     * this method schedule check and send tasks to check and send
     * notifications every "horraire"
     */
    void initService() {
        scheduleNotifs(8);
        scheduleNotifs(10);
        scheduleNotifs(12);
        scheduleNotifs(14);
    }


    /**
     * this method schedule check and send task at specific time
     *
     * @param horraire is the time that we want to send notifications at
     */
    private void scheduleNotifs(int horraire) {
        /* TODO: 5/29/2018
          getCurrentTime
          calculate delay between current time and @horraire
          schedule a task that repeats every 24h after the delay
          the task wil call sendNotifs()
          **/
        System.out.println("scheduling notifications send for horraire = " + horraire);

        int currentTime = 0;
        int initialDelay = currentTime - horraire;
        int repeatDelay = 60 * 60 * 24; //24h in seconds

        initScheduler(initialDelay, repeatDelay, horraire);
    }

    /**
     * @param initialDelay temps restant pour demarer la task
     * @param repeatDelay  the repeat rate (in our case it will be always 24 hours)
     * @param horraire     l'horraire au quel on doit verifier et envoyer des notifications
     */
    private void initScheduler(int initialDelay, int repeatDelay, int horraire) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(0);
        Runnable myRunnable;
        System.out.println("initializing scheduler for horraire = " + horraire);

        myRunnable = () -> checkAndSend(horraire);

        scheduler.scheduleWithFixedDelay(myRunnable, initialDelay, repeatDelay, TimeUnit.SECONDS);
    }

    /**
     * this method gets the list of notifs from the database and sends them
     *
     * @param horraire l'horraire au quel on doit verifier et envoyer des notifications
     */
    private void checkAndSend(int horraire) {
        // TODO: 5/29/2018 get list of scheduled notifs of tomorrow at horraire
        // TODO: 5/29/2018 send notifs
        LinkedList<Notification> ClientsNotifsToSend = new ClientNotificationDAO().getTomorrowsScheduledNotifs(horraire);
        LinkedList<Notification> EmployeesNotifsToSend = new EmployeNotificationDAO().getTomorrowsScheduledNotifs(horraire);
        System.out.println("checking and sending notifications for horraire = " + horraire);

        for (Notification notification : ClientsNotifsToSend) {
            sendNotif(notification);
        }
        for (Notification notification : EmployeesNotifsToSend) {
            sendNotif(notification);
        }
    }

    /**
     * this method sends a single notif
     *
     * @param notification notification to be sent
     */
    private void sendNotif(Notification notification) {
        String notifContent = notification.getContent();
        Person notifReceiver = notification.getDestinataire();

        System.out.println("sending : " + notification);

        Util.sendMail(notifReceiver.getEmail(), notifContent);
        Util.sendSms(notifReceiver.getTel(), notifContent);
        Util.sendPush(notifReceiver.getId(), notifContent);
    }

    public boolean registerToken(int userId, String token, String userType) {
        boolean registered = false;
        switch (userType) {
            case "client":
                ClientDAO clientDao = new ClientDAO();
                if (clientDao.tokenExists(token))
                    registered = clientDao.updateToken(userId, token);
                else
                    registered = clientDao.addToken(userId, token);
                break;
            case "employee":
                EmployeDAO employeDAO = new EmployeDAO();
                if (employeDAO.tokenExists(token))
                    registered = employeDAO.updateToken(userId, token);
                else
                    registered = employeDAO.addToken(userId, token);
                break;
        }
        return registered;
    }
}
