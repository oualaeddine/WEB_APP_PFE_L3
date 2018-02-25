package model.beans;

import model.beans.humans.Agent;
import model.beans.humans.Client;
import model.enums.EtatVisite;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Visite implements Serializable {
    private int id;
    private Timestamp timestamp;
    private Logement logement;
    private Agent agent;
    private Client client;
    private EtatVisite etatVisite;

    public Visite() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return timestamp;
    }

    public void setTime(Timestamp time) {
        this.timestamp = time;
    }

    public Logement getLogement() {
        return logement;
    }

    public void setLogement(Logement logement) {
        this.logement = logement;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public EtatVisite getEtatVisite() {
        return etatVisite;
    }

    public void setEtatVisite(EtatVisite etatVisite) {
        this.etatVisite = etatVisite;
    }
}
