package model.beans;

import model.enums.TypeLogement;

import java.io.Serializable;
import java.util.LinkedList;

public class Logement implements Serializable {
    private int id, nbrPieces, nbrSdb, etage;
    private Localite localite;
    private String titre, description, adresse;
    private boolean avecJardin;
    private boolean avecGarage;
    private boolean avecSousSol;
    private TypeLogement typeLogement;
    private boolean meubles;
    private Location location;
    private boolean isGele;
    private double superficie, prix;
    private LinkedList<String> pictures;

    public Logement() {
    }

    public LinkedList<String> getPictures() {
        return pictures;
    }

    public void setPictures(LinkedList<String> pictures) {
        this.pictures = pictures;
    }

    public boolean isGele() {
        return isGele;
    }

    public void setGele(boolean gele) {
        isGele = gele;
    }

    public TypeLogement getTypeLogement() {
        return typeLogement;
    }

    public void setTypeLogement(TypeLogement typeLogement) {
        this.typeLogement = typeLogement;
    }

    public boolean isMeubles() {
        return meubles;
    }

    public void setMeubles(boolean meubles) {
        this.meubles = meubles;
    }



    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getNbrSdb() {
        return nbrSdb;
    }

    public void setNbrSdb(int nbrSdb) {
        this.nbrSdb = nbrSdb;
    }

    public boolean isAvecGarage() {
        return avecGarage;
    }

    public void setAvecGarage(boolean avecGarage) {
        this.avecGarage = avecGarage;
    }

    public boolean isAvecSousSol() {
        return avecSousSol;
    }

    public void setAvecSousSol(boolean avecSousSol) {
        this.avecSousSol = avecSousSol;
    }


    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }


    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
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

    @Override
    public String toString() {
        return "Logement{" +
                "id=" + id +
                ", nbrPieces=" + nbrPieces +
                ", nbrSdb=" + nbrSdb +
                ", etage=" + etage +
                ", localite=" + localite +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", adresse='" + adresse + '\'' +
                ", avecJardin=" + avecJardin +
                ", avecGarage=" + avecGarage +
                ", avecSousSol=" + avecSousSol +
                ", typeLogement=" + typeLogement +
                ", meubles=" + meubles +
                ", location=" + location +
                ", superficie=" + superficie +
                ", prix=" + prix +
                ", gelé=" + isGele +
                '}';
    }
}
