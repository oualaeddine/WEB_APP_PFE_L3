package model.beans;

import model.enums.EtatClient;

import java.util.Date;

public class Rapport {
    private Visite visite;
    private EtatClient etatClient;
    private String commentaire;
    private boolean avis;


    public Rapport() {
    }

    public boolean isAvis() {
        return avis;
    }

    public void setAvis(boolean avis) {
        this.avis = avis;
    }

    public Visite getVisite() {
        return visite;
    }

    public void setVisite(Visite visite) {
        this.visite = visite;
    }

    public EtatClient getEtatClient() {
        return etatClient;
    }

    public void setEtatClient(EtatClient etatClient) {
        this.etatClient = etatClient;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


    public String getEtatClientString() {
        switch (etatClient) {
            case PRESENT:
                return "present";
            case ABSENT:
                return "absent";
            default:
                return null;
        }
    }

}
