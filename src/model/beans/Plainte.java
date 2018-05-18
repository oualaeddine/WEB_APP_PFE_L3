package model.beans;

import model.beans.humans.Client;

import java.sql.Date;

public class Plainte {
    private int id;
    private Client plaignant;
    private String contenu;
    private Date date;


    public Plainte() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getPlaignant() {
        return plaignant;
    }

    public void setPlaignant(Client plaignant) {
        this.plaignant = plaignant;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
