package control.system.managers;

import model.beans.Vente;
import model.beans.humans.ResponsableVente;
import model.db.daos.ClientDAO;
import model.db.daos.ResponsableVentesDAO;
import model.db.daos.VentesDAO;

public class ResponsablesVentesManager {
    private final ResponsableVente responsableVente;

    public ResponsablesVentesManager(ResponsableVente responsableVente) {
        this.responsableVente = responsableVente;
    }

    public boolean confirmerVente(Vente vente){
        return new VentesDAO().confirm(vente);
    }
    public boolean annulerVente(Vente vente){
        return new VentesDAO().cancelVente(vente);
    }

    public boolean changePassword(String mdp) {
        return new ResponsableVentesDAO().changePassword(responsableVente.getId(),mdp);
    }
    public boolean banirClient(int id){
        return new ClientDAO().banById(id);
    }

}
