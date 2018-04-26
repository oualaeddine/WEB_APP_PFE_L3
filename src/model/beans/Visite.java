package model.beans;

import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.enums.EtatVisite;

import java.io.Serializable;
import java.sql.Date;

public class Visite implements Serializable {
    private int id;
    private Date timestamp;
    private int horraire;
    private Logement logement;
    private Employe agent;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getHorraire() {
        return horraire;
    }

    public void setHorraire(int horraire) {
        this.horraire = horraire;
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


    @Override
    public String toString() {
        return "Visite{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", horraire=" + horraire +
                ", logement=" + logement +
                ", agent=" + agent +
                ", client=" + client +
                ", etatVisite=" + etatVisite +
                '}';
    }
}
