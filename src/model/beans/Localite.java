package model.beans;

import java.io.Serializable;

public class Localite implements Serializable {
    private int id;
    private String nom;

    public Localite() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
