package model.beans;

import model.beans.humans.Client;
import model.beans.humans.Employe;

public class Signalement {
    private int id;
    private Employe plaignant;
    private Client  client;
    private String motif;

    public Signalement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employe getPlaignant() {
        return plaignant;
    }

    public void setPlaignant(Employe plaignant) {
        this.plaignant = plaignant;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }
}
