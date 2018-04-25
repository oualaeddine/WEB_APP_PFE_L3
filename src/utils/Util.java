package utils;


import com.google.gson.Gson;
import model.beans.humans.Employe;
import model.db.daos.*;
import model.enums.EtatVente;
import model.enums.TablePage;
import model.enums.UserType;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static model.enums.TablePage.*;
import static model.enums.UserType.OPERATEUR;

public class Util {
    public static EtatVente getEtatVenteFromString(String etat) {
        switch (etat) {
            case "confirmee":
                return EtatVente.CONFIRMEE;
            case "annulee":
                return EtatVente.ANNULEE;
            case "encours":
                return EtatVente.EN_COURS;
        }
        return null;
    }
    public static String getApprobationEmail(Employe employe) {
        Employe creator = (Employe) new EmployeDAO().getById(employe.getCreator());
        String email = "Bonjour " + employe.getNom() + " " + employe.getPrenom() + "\n" +
                "Votre compte a été approuvé par " + creator.getNom() + " " + creator.getPrenom() + " qui vous a attribué le poste: " +
                employe.getUserType() + " \n Vous pouvez désormais vous connecter à votre compte avec vos identifiants: " +
                "\n Nom d'utilisateur: "+employe.getUsername()+"" +
                "\n Mot de passe: "+employe.getPassword();
        return email;
    }

    public static String getForgotPasswordEmail(String email, UserType userType, String code) {
        String name; Employe employe = null;
        employe = new EmployeDAO().getByEmail(email);
        name = employe.getNom()+" "+employe.getPrenom();
        String actionUrl = "localhost:8080/ForgotPassword?linsa="+employe.getId()+"&code="+code+"&wech="+getStringFromType(userType);



        String supportUrl="/contact";
        String msg = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <title>Set up a new password for HCH Immobilier</title>\n" +
                "    \n" +
                "    \n" +
                "  </head>\n" +
                "  <body style=\"-webkit-text-size-adjust: none; box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; height: 100%; line-height: 1.4; margin: 0; width: 100% !important;\" bgcolor=\"#F2F4F6\"><style type=\"text/css\">\n" +
                "body {\n" +
                "width: 100% !important; height: 100%; margin: 0; line-height: 1.4; background-color: #F2F4F6; color: #74787E; -webkit-text-size-adjust: none;\n" +
                "}\n" +
                "@media only screen and (max-width: 600px) {\n" +
                "  .email-body_inner {\n" +
                "    width: 100% !important;\n" +
                "  }\n" +
                "  .email-footer {\n" +
                "    width: 100% !important;\n" +
                "  }\n" +
                "}\n" +
                "@media only screen and (max-width: 500px) {\n" +
                "  .button {\n" +
                "    width: 100% !important;\n" +
                "  }\n" +
                "}\n" +
                "</style>\n" +
                "    <span class=\"preheader\" style=\"box-sizing: border-box; display: none !important; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 1px; line-height: 1px; max-height: 0; max-width: 0; mso-hide: all; opacity: 0; overflow: hidden; visibility: hidden;\">Use this link to reset your password. The link is only valid for 24 hours.</span>\n" +
                "    <table class=\"email-wrapper\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; margin: 0; padding: 0; width: 100%;\" bgcolor=\"#F2F4F6\">\n" +
                "      <tr>\n" +
                "        <td align=\"center\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; word-break: break-word;\">\n" +
                "          <table class=\"email-content\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; margin: 0; padding: 0; width: 100%;\">\n" +
                "            <tr>\n" +
                "              <td class=\"email-masthead\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; padding: 25px 0; word-break: break-word;\" align=\"center\">\n" +
                "                <a href=\"https://example.com\" class=\"email-masthead_name\" style=\"box-sizing: border-box; color: #bbbfc3; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; font-weight: bold; text-decoration: none; text-shadow: 0 1px 0 white;\">\n" +
                "        HCH Immobilier account\n" +
                "      </a>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            \n" +
                "            <tr>\n" +
                "              <td class=\"email-body\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"-premailer-cellpadding: 0; -premailer-cellspacing: 0; border-bottom-color: #EDEFF2; border-bottom-style: solid; border-bottom-width: 1px; border-top-color: #EDEFF2; border-top-style: solid; border-top-width: 1px; box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; margin: 0; padding: 0; width: 100%; word-break: break-word;\" bgcolor=\"#FFFFFF\">\n" +
                "                <table class=\"email-body_inner\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; margin: 0 auto; padding: 0; width: 570px;\" bgcolor=\"#FFFFFF\">\n" +
                "                  \n" +
                "                  <tr>\n" +
                "                    <td class=\"content-cell\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; padding: 35px; word-break: break-word;\">\n" +
                "                      <h1 style=\"box-sizing: border-box; color: #2F3133; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 19px; font-weight: bold; margin-top: 0;\" align=\"left\">Hi "+name+",</h1>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\">You recently requested to reset your password for your HCH Immobilier account. Use the button below to reset it. <strong style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;\">This password reset is only valid for the next 24 hours.</strong></p>\n" +
                "                      \n" +
                "                      <table class=\"body-action\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; margin: 30px auto; padding: 0; text-align: center; width: 100%;\">\n" +
                "                        <tr>\n" +
                "                          <td align=\"center\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; word-break: break-word;\">\n" +
                "                            \n" +
                "                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;\">\n" +
                "                              <tr>\n" +
                "                                <td align=\"center\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; word-break: break-word;\">\n" +
                "                                  <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;\">\n" +
                "                                    <tr>\n" +
                "                                      <td style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; word-break: break-word;\">\n" +
                "                                        <a href=\""+actionUrl+"\" class=\"button button--green\" target=\"_blank\" style=\"-webkit-text-size-adjust: none; background: #22BC66; border-color: #22bc66; border-radius: 3px; border-style: solid; border-width: 10px 18px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.16); box-sizing: border-box; color: #FFF; display: inline-block; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; text-decoration: none;\">Reset your password</a>\n" +
                "                                      </td>\n" +
                "                                    </tr>\n" +
                "                                  </table>\n" +
                "                                </td>\n" +
                "                              </tr>\n" +
                "                            </table>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </table>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\"> If you did not request a password reset, please ignore this email or <a href=\""+supportUrl+"\" style=\"box-sizing: border-box; color: #3869D4; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;\">contact support</a> if you have questions.</p>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\">Thanks,\n" +
                "                        <br />The HCH Immobilier Team</p>\n" +
                "                      \n" +
                "                      <table class=\"body-sub\" style=\"border-top-color: #EDEFF2; border-top-style: solid; border-top-width: 1px; box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; margin-top: 25px; padding-top: 25px;\">\n" +
                "                        <tr>\n" +
                "                          <td style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; word-break: break-word;\">\n" +
                "                            <p class=\"sub\" style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"left\">If you’re having trouble with the button above, copy and paste the URL below into your web browser.</p>\n" +
                "                            <p class=\"sub\" style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"left\">"+actionUrl+"</p>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </table>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; word-break: break-word;\">\n" +
                "                <table class=\"email-footer\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; margin: 0 auto; padding: 0; text-align: center; width: 570px;\">\n" +
                "                  <tr>\n" +
                "                    <td class=\"content-cell\" align=\"center\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; padding: 35px; word-break: break-word;\">\n" +
                "                      <p class=\"sub align-center\" style=\"box-sizing: border-box; color: #AEAEAE; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"center\">© 2018 HCH Immobilier. All rights reserved.</p>\n" +
                "                      <p class=\"sub align-center\" style=\"box-sizing: border-box; color: #AEAEAE; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"center\">\n" +
                "                        HCH Immobilier, LLC\n" +
                "                        <br />1234 Street Rd.\n" +
                "                        <br />Suite 1234\n" +
                "                      </p>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </body>\n" +
                "</html>";
        return msg;

    }

    public static String objectToJson(Object object) {
        String json;
        json = new Gson().toJson(object);
        return json;
    }

    public static UserType getUserTypeFromString(String type) {
        switch (type) {
            case "personal":
                return UserType.CLIENT;
            case "admin":
                return UserType.ADMIN;
            case "agent":
                return UserType.AGENT;
            case "operateur":
                return OPERATEUR;
            case "responsable_ventes":
                return UserType.RESPONSABLE_VENTES;
            case "SU":
                return UserType.SU;
            default:
                return null;
        }
    }

    public static String getStringFromType(UserType type) {
        switch (type) {
            case CLIENT:
                return ClientDAO.TABLE_NAME;
            case AGENT:
                return "agent";
            case OPERATEUR:
                return "operateur";
            case ADMIN:
                return "admin";
            case SU:
                return "SU";
            case RESPONSABLE_VENTES:
                return "responsable_vente";
            default:
                return null;
        }
    }

    public static String getPageTitleFromPageType(TablePage currentPage) {
        switch (currentPage) {
            case SUSPENDRE_ADMIN:
                return "Administrateurs";
            case SIGNALEMENT:
                return "Signalement";
            case PLAINTES:
                return "Plaintes";
            case SIGNALER_CLIENT:
                return "Clients";
            case CLIENTS_FOR_AGENT:
                return "Clients";
            case ASSIGNER_REGION:
                return "Agents";
            case VENTES:
                return "Ventes";
            case PASSED_VISITS:
                return "Visites passées";
            case LOGEMENTS:
                return "Logements";
            case LOGEMENTS_FOR_USER:
                return "Mes Logements";
            case LOGEMENTS_VENDUS:
                return "Logements vendus";
            case FROZEN_LOGEMENTS:
                return "Logements gelés";
            case LOGEMENTS_NON_VENDUS:
                return "Logements non vendus";
            case AGENTS:
                return "Agents";
            case SUSPENDED_AGENTS:
                return "Agents suspendus";
            case OPERATEURS:
                return "Operateurs";
            case SUSPENDED_OPERATEURS:
                return "Operateurs suspendus";
            case RESPONSABLES_VENTES:
                return "Responsables ventes";
            case SUSPENDED_RESPONSABLES_VENTES:
                return "Responsables ventes suspendus";
            case VISITES:
                return "Visites";
            case REPORTED_VISITES:
                return "Visites reportées";
            case CANCELED_VISITES:
                return "Visites Annulées";
            case AGENT_VISITES:
                return "Mes Visites";
            case PROGRAMMED_VISITES:
                return "Visites programmées";
            case ADMINS:
                return "Admins";
            case LOCALITES:
                return "Localites";
            case CLIENTS:
                return "Clients";
            case CLIENTS_FOR_USER:
                return "Mes Clients";
            case BANNED_CLIENTS:
                return "Clients bannis";
            case MESSAGES:
                return "Boite de reception";
            case ADMINISTRATION_MESSAGES_FOR_USER:
                return "Boite de reception Administration";
            case CLIENTS_MESSAGES_FOR_USER:
                return "Boite de reception clients";
            case APPROUVER_EMPLOYE:
                return "Employés non approuvés";
            case SUSPENDRE_EMPLOYE:
                return "Employés";
            case GELER_LOGEMENT:
                return "Logements non vendus";
            case MODIFIER_PROFIL:
                return "Visites à venir";
            case ETABLIR_RAPPORT:
                return "Visites";
        }
     return null;
    }

    public static TablePage getPageFromString(String tablePage) {
        switch (tablePage) {
            case "ETABLIR_RAPPORT":
                return ETABLIR_RAPPORT;
            case "MODIFIER_VISITE":
                return MODIFIER_VISITE;
            case "GELER_LOGEMENT":
                return GELER_LOGEMENT;
            case "SUSPENDRE_ADMIN":
                return SUSPENDRE_ADMIN;
            case "SUSPENDRE_EMPLOYE":
                return SUSPENDRE_EMPLOYE;
            case "APPROUVER_EMPLOYE":
                return APPROUVER_EMPLOYE;
            case "SIGNALEMENT":
                return SIGNALEMENT;
            case "SIGNALER_CLIENT":
                return SIGNALER_CLIENT;
            case "CLIENTS_FOR_AGENT":
                return CLIENTS_FOR_AGENT;
            case "ASSIGNER_REGION":
                return ASSIGNER_REGION;
            case "VENTES":
                return VENTES;
            case "LOGEMENTS":
                return LOGEMENTS;
            case "LOGEMENTS_FOR_USER":
                return LOGEMENTS_FOR_USER;
            case "LOGEMENTS_VENDUS":
                return LOGEMENTS_VENDUS;
            case "FROZEN_LOGEMENTS":
                return FROZEN_LOGEMENTS;
            case "LOGEMENTS_NON_VENDUS":
                return LOGEMENTS_NON_VENDUS;
            case "AGENTS":
                return AGENTS;
            case "SUSPENDED_AGENTS":
                return SUSPENDED_AGENTS;
            case "OPERATEURS":
                return OPERATEURS;
            case "SUSPENDED_OPERATEURS":
                return SUSPENDED_OPERATEURS;
            case "RESPONSABLES_VENTES":
                return RESPONSABLES_VENTES;
            case "SUSPENDED_RESPONSABLES_VENTES":
                return SUSPENDED_RESPONSABLES_VENTES;
            case "VISITES":
                return VISITES;
            case "REPORTED_VISITES":
                return REPORTED_VISITES;
            case "CANCELED_VISITES":
                return CANCELED_VISITES;
            case "AGENT_VISITES":
                return AGENT_VISITES;
            case "PROGRAMMED_VISITES":
                return PROGRAMMED_VISITES;
            case "PASSED_VISITS":
                return PASSED_VISITS;
            case "ADMINS":
                return ADMINS;
            case "LOCALITES":
                return LOCALITES;
            case "CLIENTS":
                return CLIENTS;
            case "CLIENTS_FOR_USER":
                return CLIENTS_FOR_USER;
            case "BANNED_CLIENTS":
                return BANNED_CLIENTS;
            case "MESSAGES":
                return MESSAGES;
            case "ADMINISTRATION_MESSAGES_FOR_USER":
                return ADMINISTRATION_MESSAGES_FOR_USER;
            case "CLIENTS_MESSAGES_FOR_USER":
                return CLIENTS_MESSAGES_FOR_USER;
        }
        return VISITES;
    }
    public static Date getDateFromString(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date date1 = null;
        try {
            java.util.Date date = sdf.parse(dateString);
            date1 = new Date(date.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }
}