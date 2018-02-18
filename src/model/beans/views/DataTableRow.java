package model.beans.views;

import model.beans.Logement;
import model.beans.Message;
import model.beans.Vente;
import model.beans.Visite;
import model.beans.humans.Agent;
import model.beans.humans.Client;
import model.beans.humans.Operateur;
import model.beans.humans.ResponsableVente;
import model.enums.DataTableRowFormat;
import model.enums.UserType;
import sun.rmi.runtime.Log;

public class DataTableRow {

    private final DataTableRowFormat dataFormat;
    private final Object object;
    private String html;

    public DataTableRow(DataTableRowFormat dataFormat, Object object) {
        this.object = object;
        this.dataFormat = dataFormat;
        setupHtml();
    }

    private void setupHtml() {
        switch (dataFormat) {
            case LOGEMENT:
                setupHtmlForLogement();
                break;
            case ADMIN:
                setupHtmlForLogement();
                break;
            case AGENT:
                setupHtmlForAgent();
                break;
            case OPERATEUR:
                setupHtmlForOperateur();
                break;
            case CLIENT:
                setupHtmlForClient();
                break;
            case MESSAGE:
                setupHtmlForMessage();
                break;
            case RESPONSABLE_VENTES:
                setupHtmlForResponsableVentes();
                break;
            case VENTE:
                setupHtmlForVente();
                break;
            case VISITE:
                setupHtmlForVisite();
                break;
        }
    }

    private void setupHtmlForOperateur() {
        Operateur operateur = (Operateur) object;
    }

    private void setupHtmlForClient() {
        Client client = (Client) object;
    }

    private void setupHtmlForMessage() {
        Message message = (Message) object;
    }

    private void setupHtmlForResponsableVentes() {
        ResponsableVente responsableVente = (ResponsableVente) object;
    }

    private void setupHtmlForVente() {
        Vente vente = (Vente) object;
    }

    private void setupHtmlForAgent() {
        Agent agent = (Agent) object;
    }

    private void setupHtmlForVisite() {
        Visite visite = (Visite) object;
    }

    private void setupHtmlForLogement() {
        Logement logement = (Logement) object;
    }

    public String getHtml() {
        return html;
    }
}
