package utils;


import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.beans.humans.Person;
import model.db.daos.ClientDAO;
import model.db.daos.EmployeDAO;
import model.enums.EtatVente;
import model.enums.TablePage;
import model.enums.UserType;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import static model.enums.TablePage.*;
import static model.enums.UserType.AGENT;
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

    public static boolean sendEmail(String email, String objet, String content) {
        try {
            GoogleMail.Send("eritpimmobilier", "eritppfe", email, objet, content);
            System.out.println("Email sent: true");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("Email sent: false");
        return false;
    }

    public static String getBannirEmail(Client client) {
        String supportUrl = "/localhost:8080";
        String email = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <title>Vous avez été bannis</title>\n" +
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
                "        Compte ERITP\n" +
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
                "                      <h1 style=\"box-sizing: border-box; color: #2F3133; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 19px; font-weight: bold; margin-top: 0;\" align=\"left\">Bonjour " + client.getFullName() + ",</h1>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\">Votre compte ERITP a été bloqué par nos administrateurs<br>Vous n'aurez plus accès à votre compte et vos visites prévues on été annulées<br>" +
                "                      \n" +
                "                      <table class=\"body-action\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; margin: 30px auto; padding: 0; text-align: center; width: 100%;\">\n" +
                "                        <tr>\n" +
                "                          <td align=\"center\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; word-break: break-word;\">\n" +
                "                            \n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </table>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\"> Si vous souhaitez contester cette sanction, contactez nous en cliquant <a href=\"" + supportUrl + "\" style=\"box-sizing: border-box; color: #3869D4; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;\">ici</a> .</p>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\">Merci,\n" +
                "                        <br />L'équipe ERITP</p>\n" +
                "                      \n" +
                "                      <table class=\"body-sub\" style=\"border-top-color: #EDEFF2; border-top-style: solid; border-top-width: 1px; box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; margin-top: 25px; padding-top: 25px;\">\n" +
                "                        <tr>\n" +
                "                          <td style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; word-break: break-word;\">\n" +
                "                            <p class=\"sub\" style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"left\">Si vous avez un problème avec le bouton si dessus, collez le lien suivant dans votre navigateur.</p>\n" +
                "                            <p class=\"sub\" style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"left\">/localhost:8080</p>\n" +
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
                "                      <p class=\"sub align-center\" style=\"box-sizing: border-box; color: #AEAEAE; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"center\">© 2018 ERITP Immobilier. All rights reserved.</p>\n" +
                "                      <p class=\"sub align-center\" style=\"box-sizing: border-box; color: #AEAEAE; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"center\">\n" +
                "                        SARL ERITP\n" +
                "                        <br />UV 05, Ali mendjeli\n" +
                "                        <br />Constantine, Algérie\n" +
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
        return email;
    }

    public static String getRetablirClientEmail(Client client) {
        String supportUrl = "/localhost:8080";
        String email = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <title>Votre compte a été rétabli</title>\n" +
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
                "        Compte ERITP\n" +
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
                "                      <h1 style=\"box-sizing: border-box; color: #2F3133; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 19px; font-weight: bold; margin-top: 0;\" align=\"left\">Bonjour " + client.getFullName() + ",</h1>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\">Votre compte ERITP a été rétabli par nos administrateurs<br>Vous avez de nouveau accès à votre compte<br>Vous pouvez dès maintenant consulter nos logements et programmer des visites !" +
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
                "                                        <a href=\"" + supportUrl + "\" class=\"button button--green\" target=\"_blank\" style=\"-webkit-text-size-adjust: none; background: #22BC66; border-color: #22bc66; border-radius: 3px; border-style: solid; border-width: 10px 18px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.16); box-sizing: border-box; color: #FFF; display: inline-block; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; text-decoration: none;\">C'est parti !</a>\n" +
                "                                      </td>\n" +
                "                                    </tr>\n" +
                "                                  </table>\n" +
                "                                </td>\n" +
                "                              </tr>\n" +
                "                            </table>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </table>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\">À bientot,\n" +
                "                        <br />L'équipe ERITP</p>\n" +
                "                      \n" +
                "                      <table class=\"body-sub\" style=\"border-top-color: #EDEFF2; border-top-style: solid; border-top-width: 1px; box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; margin-top: 25px; padding-top: 25px;\">\n" +
                "                        <tr>\n" +
                "                          <td style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; word-break: break-word;\">\n" +
                "                            <p class=\"sub\" style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"left\">Si vous avez un problème avec le bouton si dessus, collez le lien suivant dans votre navigateur.</p>\n" +
                "                            <p class=\"sub\" style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"left\">/localhost:8080</p>\n" +
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
                "                      <p class=\"sub align-center\" style=\"box-sizing: border-box; color: #AEAEAE; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"center\">© 2018 ERITP Immobilier. All rights reserved.</p>\n" +
                "                      <p class=\"sub align-center\" style=\"box-sizing: border-box; color: #AEAEAE; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"center\">\n" +
                "                        SARL ERITP\n" +
                "                        <br />UV 05, Ali mendjeli\n" +
                "                        <br />Constantine, Algérie\n" +
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
        return email;
    }

    public static String getSuspendreEmail(Employe employe) {
        String agent = employe.getUserType() == AGENT ? " et vos visites ont été annulées." : "";
        String supportUrl = "/localhost:8080";
        String email = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <title>Vous avez été suspendu</title>\n" +
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
                "        Compte ERITP\n" +
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
                "                      <h1 style=\"box-sizing: border-box; color: #2F3133; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 19px; font-weight: bold; margin-top: 0;\" align=\"left\">Bonjour " + employe.getFullName() + ",</h1>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\">Votre compte ERITP a été suspendu par les administrateurs<br>Vous n'aurez plus accès à votre compte" + agent + "<br>" +
                "                      \n" +
                "                      <table class=\"body-action\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; margin: 30px auto; padding: 0; text-align: center; width: 100%;\">\n" +
                "                        <tr>\n" +
                "                          <td align=\"center\" style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; word-break: break-word;\">\n" +
                "                            \n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </table>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\"> Si vous souhaitez contester cette sanction, contactez nous en cliquant <a href=\"" + supportUrl + "\" style=\"box-sizing: border-box; color: #3869D4; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;\">ici</a> .</p>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\">Merci,\n" +
                "                        <br />L'équipe ERITP</p>\n" +
                "                      \n" +
                "                      <table class=\"body-sub\" style=\"border-top-color: #EDEFF2; border-top-style: solid; border-top-width: 1px; box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; margin-top: 25px; padding-top: 25px;\">\n" +
                "                        <tr>\n" +
                "                          <td style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; word-break: break-word;\">\n" +
                "                            <p class=\"sub\" style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"left\">Si vous avez un problème avec le bouton si dessus, collez le lien suivant dans votre navigateur.</p>\n" +
                "                            <p class=\"sub\" style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"left\">/localhost:8080</p>\n" +
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
                "                      <p class=\"sub align-center\" style=\"box-sizing: border-box; color: #AEAEAE; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"center\">© 2018 ERITP Immobilier. All rights reserved.</p>\n" +
                "                      <p class=\"sub align-center\" style=\"box-sizing: border-box; color: #AEAEAE; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"center\">\n" +
                "                        SARL ERITP\n" +
                "                        <br />UV 05, Ali mendjeli\n" +
                "                        <br />Constantine, Algérie\n" +
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
        return email;
    }

    public static String getReintegrerEmployeEmail(Employe employe) {
        String supportUrl = "/login";
        String email = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <title>Votre compte a été rétabli</title>\n" +
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
                "        Compte ERITP\n" +
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
                "                      <h1 style=\"box-sizing: border-box; color: #2F3133; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 19px; font-weight: bold; margin-top: 0;\" align=\"left\">Bonjour " + employe.getFullName() + ",</h1>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\">Votre compte ERITP a été rétabli par nos administrateurs<br>Vous avez de nouveau accès à votre compte<br>Vous pouvez dès maintenant acceder à votre tableau de bord en vous connectant avec vos identifiants:<br>Nom d\'utilisateur: " + employe.getUsername() + "<br>Mot de passe: " + employe.getPassword() +
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
                "                                        <a href=\"" + supportUrl + "\" class=\"button button--green\" target=\"_blank\" style=\"-webkit-text-size-adjust: none; background: #22BC66; border-color: #22bc66; border-radius: 3px; border-style: solid; border-width: 10px 18px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.16); box-sizing: border-box; color: #FFF; display: inline-block; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; text-decoration: none;\">C'est parti !</a>\n" +
                "                                      </td>\n" +
                "                                    </tr>\n" +
                "                                  </table>\n" +
                "                                </td>\n" +
                "                              </tr>\n" +
                "                            </table>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </table>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\">À bientot,\n" +
                "                        <br />L'équipe ERITP</p>\n" +
                "                      \n" +
                "                      <table class=\"body-sub\" style=\"border-top-color: #EDEFF2; border-top-style: solid; border-top-width: 1px; box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; margin-top: 25px; padding-top: 25px;\">\n" +
                "                        <tr>\n" +
                "                          <td style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; word-break: break-word;\">\n" +
                "                            <p class=\"sub\" style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"left\">Si vous avez un problème avec le bouton si dessus, collez le lien suivant dans votre navigateur.</p>\n" +
                "                            <p class=\"sub\" style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"left\">/localhost:8080</p>\n" +
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
                "                      <p class=\"sub align-center\" style=\"box-sizing: border-box; color: #AEAEAE; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"center\">© 2018 ERITP Immobilier. All rights reserved.</p>\n" +
                "                      <p class=\"sub align-center\" style=\"box-sizing: border-box; color: #AEAEAE; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"center\">\n" +
                "                        SARL ERITP\n" +
                "                        <br />UV 05, Ali mendjeli\n" +
                "                        <br />Constantine, Algérie\n" +
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
        return email;
    }

    public static String getWelcomeEmail(Client client) {
        String supportUrl = "/localhost:8080";
        String email = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <title>Bienvenue chez E.R.I.T.P</title>\n" +
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
                "        Compte ERITP\n" +
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
                "                      <h1 style=\"box-sizing: border-box; color: #2F3133; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 19px; font-weight: bold; margin-top: 0;\" align=\"left\">Bonjour " + client.getFullName() + ",</h1>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\">Votre compte ERITP a bien été créé<br>Vos identifants sont les suivants:<br>Nom d\'utilisateur: " + client.getUsername() + "<br>Mot de passe: " + client.getPassword() + "<br>Vous pouvez désormais programmer vos visites de nos logements !" +
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
                "                                        <a href=\"/localhost:8080\" class=\"button button--green\" target=\"_blank\" style=\"-webkit-text-size-adjust: none; background: #22BC66; border-color: #22bc66; border-radius: 3px; border-style: solid; border-width: 10px 18px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.16); box-sizing: border-box; color: #FFF; display: inline-block; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; text-decoration: none;\">C'est parti !</a>\n" +
                "                                      </td>\n" +
                "                                    </tr>\n" +
                "                                  </table>\n" +
                "                                </td>\n" +
                "                              </tr>\n" +
                "                            </table>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </table>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\"> Si vous n'avez jamais ouvert de compte chez nous, ignorez cet email ou  <a href=\"" + supportUrl + "\" style=\"box-sizing: border-box; color: #3869D4; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;\">contactez nous</a> si vous avez des questions.</p>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\">Thanks,\n" +
                "                        <br />L'équipe ERITP</p>\n" +
                "                      \n" +
                "                      <table class=\"body-sub\" style=\"border-top-color: #EDEFF2; border-top-style: solid; border-top-width: 1px; box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; margin-top: 25px; padding-top: 25px;\">\n" +
                "                        <tr>\n" +
                "                          <td style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; word-break: break-word;\">\n" +
                "                            <p class=\"sub\" style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"left\">Si vous avez un problème avec le bouton si dessus, collez le lien suivant dans votre navigateur.</p>\n" +
                "                            <p class=\"sub\" style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"left\">/localhost:8080</p>\n" +
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
                "                        SARL ERITP\n" +
                "                        <br />UV 05, Ali mendjeli\n" +
                "                        <br />Constantine, Algérie\n" +
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
        return email;
    }

    public static String getApprobationEmail(Employe employe) {
        Employe creator = (Employe) new EmployeDAO().getById(employe.getCreator());
        String email = "Bonjour " + employe.getNom() + " " + employe.getPrenom() + "<br>" +
                "Votre compte a été approuvé par " + creator.getNom() + " " + creator.getPrenom() + " qui vous a attribué le poste: " +
                employe.getUserType() + " <br> Vous pouvez désormais vous connecter à votre compte avec vos identifiants: " +
                "<br> Nom d'utilisateur: " + employe.getUsername() + "" +
                "<br> Mot de passe: " + employe.getPassword();
        return email;
    }

    public static String getForgotPasswordEmail(String email, UserType userType, String code) {
        String name;
        Employe employe = null;
        employe = new EmployeDAO().getByEmail(email);
        name = employe.getNom() + " " + employe.getPrenom();
        String actionUrl = "localhost:8080/ForgotPassword?linsa=" + employe.getId() + "&code=" + code;


        String supportUrl = "/contact";
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
                "                      <h1 style=\"box-sizing: border-box; color: #2F3133; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 19px; font-weight: bold; margin-top: 0;\" align=\"left\">Bonjour " + name + ",</h1>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\">Vous avez récemment demandé la réinitialisation du mot de passe de votre compte sur le site d'ERITP. Appuyez sur le bouton ci-dessous pour réinitialisé votre mot de passe.<strong style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;\">This password reset is only valid for the next 24 hours.</strong></p>\n" +
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
                "                                        <a href=\"" + actionUrl + "\" class=\"button button--green\" target=\"_blank\" style=\"-webkit-text-size-adjust: none; background: #22BC66; border-color: #22bc66; border-radius: 3px; border-style: solid; border-width: 10px 18px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.16); box-sizing: border-box; color: #FFF; display: inline-block; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; text-decoration: none;\">Reset your password</a>\n" +
                "                                      </td>\n" +
                "                                    </tr>\n" +
                "                                  </table>\n" +
                "                                </td>\n" +
                "                              </tr>\n" +
                "                            </table>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </table>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\"> If you did not request a password reset, please ignore this email or <a href=\"" + supportUrl + "\" style=\"box-sizing: border-box; color: #3869D4; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;\">contact support</a> if you have questions.</p>\n" +
                "                      <p style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; line-height: 1.5em; margin-top: 0;\" align=\"left\">Thanks,\n" +
                "                        <br />The HCH Immobilier Team</p>\n" +
                "                      \n" +
                "                      <table class=\"body-sub\" style=\"border-top-color: #EDEFF2; border-top-style: solid; border-top-width: 1px; box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; margin-top: 25px; padding-top: 25px;\">\n" +
                "                        <tr>\n" +
                "                          <td style=\"box-sizing: border-box; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; word-break: break-word;\">\n" +
                "                            <p class=\"sub\" style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"left\">If you’re having trouble with the button above, copy and paste the URL below into your web browser.</p>\n" +
                "                            <p class=\"sub\" style=\"box-sizing: border-box; color: #74787E; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 12px; line-height: 1.5em; margin-top: 0;\" align=\"left\">" + actionUrl + "</p>\n" +
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
                return "responsable_ventes";
            default:
                return null;
        }
    }

    public static String getPageTitleFromPageType(TablePage currentPage) {
        switch (currentPage) {
            case EMPLOYES:
                return "Employés";
            case VERSEMENTS_FOR_USER:
                return "Versements";
            case ANNULER_VENTE:
                return "Ventes en cours";
            case VENTES_EN_COURS:
                return "Ventes en cours";
            case VENTES_ANNULEES:
                return "Ventes annulées";
            case CONFIRMED_VENTES:
                return "Ventes confirmées";
            case VERSEMENTS_FOR_VENTE:
                return "Ventes";
            case MODIFIER_VISITE:
                return "Visites";
            case CLIENT_MES_LOGEMENT_VISITES:
                return "Mes logements visités";
            case CLIENT_MES_VENTES_EN_COURS:
                return "Mes ventes en cours";
            case CLIENT_MY_NOTIFICATIONS:
                return "Mes notifications";
            case CLIENT_MY_VISITS:
                return "Mes visites";
            case AJOUTER_VERSEMENT:
                return "Clients";
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
            case NOUVELLE_VENTE:
                return "Clients";
            case RAPPORTS_LIST:
                return "Rapports";
            case MY_CANCELED_VISITS:
                return "Visites annulées";
            case MY_PASSED_VISITS:
                return "Visites passées";
            case EMPLOYEE_NOTIFICATIONS:
                return "Notifications";
            case BANNIR_CLIENT:
                return "Clients";
        }
        return null;
    }

    public static TablePage getPageFromString(String tablePage) {
        switch (tablePage) {
            case "ANNULER_VENTE":
                return ANNULER_VENTE;
            case "BANNIR_CLIENT":
                return BANNIR_CLIENT;
            case "EMPLOYEE_NOTIFICATIONS":
                return EMPLOYEE_NOTIFICATIONS;
            case "MY_PASSED_VISITS":
                return MY_PASSED_VISITS;
            case "VENTES_EN_COURS":
                return VENTES_EN_COURS;
            case "VENTES_ANNULEES":
                return VENTES_ANNULEES;
            case "CONFIRMED_VENTES":
                return CONFIRMED_VENTES;
            case "VERSEMENTS_FOR_VENTE":
                return VERSEMENTS_FOR_VENTE;
            case "CLIENT_MY_VISITS":
                return CLIENT_MY_VISITS;
            case "CLIENT_MY_NOTIFICATIONS":
                return CLIENT_MY_NOTIFICATIONS;
            case "CLIENT_MES_VENTES_EN_COURS":
                return CLIENT_MES_VENTES_EN_COURS;
            case "CLIENT_MES_LOGEMENT_VISITES":
                return CLIENT_MES_LOGEMENT_VISITES;
            case "AJOUTER_VERSEMENT":
                return AJOUTER_VERSEMENT;
            case "NOUVELLE_VENTE":
                return NOUVELLE_VENTE;
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
            case "RAPPORTS_LIST":
                return RAPPORTS_LIST;
            case "MY_CANCELED_VISITS":
                return MY_CANCELED_VISITS;
            case "PLAINTES":
                return PLAINTES;

        }
        return null;
    }

    public static String getStringFromHorraire(int x) {
        switch (x) {
            case 1:
                return "08:00 - 10:00";
            case 2:
                return "10:00 - 12:00";
            case 3:
                return "12:00 - 14:00";
            case 4:
                return "14:00 - 16:00";
            default:
                return "NULL";
        }
    }

    public static Date getDateFromString(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            java.util.Date date = sdf.parse(dateString);
            System.out.println("the fufing dateString = " + dateString);
            System.out.println("the fufing date = " + date.toString());
            date1 = new Date(date.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    public static boolean isEmploye(Person attribute) {
        return false;
    }

    private final static String apiKey = "I added my key here";


    public static void sendSms(String tel, String notifContent) {
        // TODO: 5/29/2018
        System.out.println("sending sms notification : \ntel = [" + tel + "], notifContent = [" + notifContent + "]");

       /* Message message = Message
                .creator(new PhoneNumber("+213" + tel), // to
                        new PhoneNumber("+13092471806"), // from
                        notifContent)
                .create();

        System.out.println(message.getSid());*/
    }

    public static void sendMail(String email, String notifContent) {
        // TODO: 5/29/2018
        System.out.println("sending email notification : \nemail = [" + email + "], notifContent = [" + notifContent + "]");

        try {
            GoogleMail.Send("eritpimmobilier", "eritppfe", email, "Notification", notifContent);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendPush(int userId, String notifContent) {
        // TODO: 5/29/2018
        System.out.println("sending push notification : \nuserId = [" + userId + "], notifContent = [" + notifContent + "]");

        LinkedList<String> userTokens = new ClientDAO().getNotifTokens(userId);
        for (String token : userTokens) {
            sendPushNotifToToken(token, notifContent);
        }
    }

    private static void sendPushNotifToToken(String token, String message) {
        System.out.println("sendPushNotifToToken : \n token = [" + token + "], message = [" + message + "]");
        try {
            URL url;

            url = new URL("https://fcm.googleapis.com/fcm/send");

            HttpURLConnection conn;

            conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "key=" + apiKey);

            conn.setDoOutput(true);


            String input =
                    "{\"notification\" : " +
                            "{" +
                            "\"title\" : \"Test\"," +
                            "\"body\": \"" + message + "\"" +
                            "}," +
                            " \"to\":\"" + token + "\"" +
                            "}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + input);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}