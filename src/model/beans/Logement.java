package model.beans;

import java.io.Serializable;

public class Logement implements Serializable {
    private int id, nbrPieces, nbrFacades;
    private Localite localite;
    private String titre, description, adresse;
    private boolean avecJardin;
    private double superficie;

    public Logement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrPieces() {
        return nbrPieces;
    }

    public void setNbrPieces(int nbrPieces) {
        this.nbrPieces = nbrPieces;
    }

    public int getNbrFacades() {
        return nbrFacades;
    }

    public void setNbrFacades(int nbrFacades) {
        this.nbrFacades = nbrFacades;
    }

    public Localite getLocalite() {
        return localite;
    }

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public boolean isAvecJardin() {
        return avecJardin;
    }

    public void setAvecJardin(boolean avecJardin) {
        this.avecJardin = avecJardin;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }
}
