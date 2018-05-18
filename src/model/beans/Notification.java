package model.beans;

import model.beans.humans.Person;

import java.sql.Timestamp;

public class Notification {
    private int id;
    private Person destinataire;
    private String content;
    private Timestamp timestamp;

    public Notification() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Person destinataire) {
        this.destinataire = destinataire;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
