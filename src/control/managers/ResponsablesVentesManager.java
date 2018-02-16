package control.managers;

import model.beans.Vente;
import model.beans.humans.ResponsableVente;
import model.db.daos.VentesDAO;

public class ResponsablesVentesManager {
    private final ResponsableVente responsableVente;

    public ResponsablesVentesManager(ResponsableVente responsableVente) {
        this.responsableVente = responsableVente;
    }

    public boolean confirmerVente(Vente vente){
        return new VentesDAO().confirm(vente.getLogement().getId());
    }
}
