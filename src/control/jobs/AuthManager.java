package control.jobs;

import model.beans.humans.*;
import model.db.daos.*;

public class AuthManager {
    private AdminsDAO adminsDAO;
    private AgentsDAO agentsDAO;
    private ClientDAO clientDAO;
    private OperateurDAO operateurDAO;
    private ResponsableVentesDAO responsableVentesDAO;

    public AuthManager(){
        adminsDAO=new AdminsDAO();
        agentsDAO=new AgentsDAO();
        clientDAO=new ClientDAO();
        operateurDAO=new OperateurDAO();
        responsableVentesDAO=new ResponsableVentesDAO();
    }

    public boolean authAdmin(String username,String password){
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        return adminsDAO.exists(admin);
    }
    public boolean authAgent(String username,String password){
        Agent agent = new Agent();
        agent.setUsername(username);
        agent.setPassword(password);
        return agentsDAO.exists(agent);
    }
    public boolean authClient(String username,String password){
        Client client = new Client();
        client.setUsername(username);
        client.setPassword(password);
        return clientDAO.exists(client);
    }
    public boolean authOperateur(String username,String password){
        Operateur operateur = new Operateur();
        operateur.setUsername(username);
        operateur.setPassword(password);
        return operateurDAO.exists(operateur);
    }
    public boolean authResponsable(String username,String password){
        ResponsableVente responsableVente = new ResponsableVente();
        responsableVente.setUsername(username);
        responsableVente.setPassword(password);
        return responsableVentesDAO.exists(responsableVente);
    }

}

