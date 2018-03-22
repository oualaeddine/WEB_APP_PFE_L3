package model.beans;

import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.enums.EtatVente;

import java.io.Serializable;
import java.util.Date;

public class Vente implements Serializable {
    private int id;
    private Date date;
    private Logement logement;
    private Employe agent;
    private Employe responsableVente;
    private Client client;
    private EtatVente etatVente;

    public Vente() {

    }

    public EtatVente getEtatVente() {
        return etatVente;
    }

    public void setEtatVente(EtatVente etatVente) {
        this.etatVente = etatVente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Logement getLogement() {
        return logement;
    }

    public void setLogement(Logement logement) {
        this.logement = logement;
    }

    public Employe getAgent() {
        return agent;
    }

    public void setAgent(Employe agent) {
        this.agent = agent;
    }

    public Employe getResponsableVente() {
        return responsableVente;
    }

    public void setResponsableVente(Employe responsableVente) {
        this.responsableVente = responsableVente;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
