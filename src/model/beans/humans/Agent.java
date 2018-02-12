package model.beans.humans;

import model.beans.Localite;

import java.io.Serializable;

public class Agent extends Employe  {
    private Localite localite;

    public Agent() {
    }

    public Localite getLocalite() {
        return localite;
    }

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }
}
