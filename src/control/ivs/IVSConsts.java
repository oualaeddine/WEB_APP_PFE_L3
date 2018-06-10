package control.ivs;

import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;

public class IVSConsts {
    private static String url = "http://ivspfe.ddns.net:81/ivs/";


    public static final String FOURCHETTE_PRIX_MENU_SERVLET_URL = "/ivs_FOURCHETTE_PRIX",
            REGION_MENU_SERVLET_URL = "/ivs_REGION_MENU",
            REGION_MENU_HANDELER_SERVLET_URL = "/ivs_REGION_MENU_HANDELER",
            TYPE_MENU_SERVLET_URL = "/ivs_TYPE_MENU",
            ROOMS_MENU_HANDELER_SERVLET_URL = "/ivs_ROOMS_MENU_HANDELER",
            SUPERFICIE_MENU_HANDELER_SERVLET_URL = "/ivs_SUPERFICIE_MENU_HANDELER",
            RECAP_SERVLET_URL = "/ivs_RECAP",
            TYPE_MENU_HANDELER_SERVLET_URL = "/ivs_TYPE_MENU_HANDELER",
            ROOMS_NUMBER_SERVLET_URL = "/ivs_ROOMS_NUMBER",
            SUPERFICIE_SERVLET_URL = "/ivs_SUPERFICIE",
            WELCOME_MESSAGE_SERVLET_URL = "/ivs_WELCOME_MESSAGE",
            MAIN_MENU_SERVLET_URL = "/ivs_MAIN_MENU",
            MAIN_MENU_HANDELER_SERVLET_URL = "/ivs_MAIN_MENU_HANDELER",
            CALL_OPERATOR_SERVLET_URL = "/ivs_CALL_OPERATOR",
            PROGRAM_VISIT_SERVLET_URL = "/ivs_PROGRAM_VISIT",
            CHANGE_LANGUAGE_SERVLET_URL = "/ivs_CHANGE_LANGUAGE";
    public static final String FR_PROGRAMMER_VISITE_MP3_URL = "/20",
            FR_FOURCHETTE_PRIX_MP3_URL = url + "Fourchette_de_prix_FR.mp3",
            FR_REGION_MENU_MP3_URL = url + "/18",
            FR_SUPERFICIE_MP3_URL = url + "Superficie_FR.mp3",
            FR_TYPE_MENU_MP3_URL = url + "Type_de_logement_FR.mp3",
            FR_NBR_PIECES_MP3_URL = url + "Nombre_de_pieces_FR.mp3",
            YOU_CHOOSED_FRENCH_MP3_URL = url + "Main_menu_FR.mp3",
            FR_STANDARD_WELCOME_MESSAGE_MP3_URL = url + "",
            FR_MAIN_MENU_MP3_URL = url + "Main_menu_FR.mp3";
    public static final String WELCOME_HANDELER_SERVLET_URL = "/ivs_WELCOME_HANDELER",
            WELCOME_MENU_SERVLET_URL = "/ivs_WELCOME_MENU";
    public static final String WELCOME_MP3_URL = url + "Bienvenue_choix_langue_FR_AR.mp3";
    public static final String AR_PROGRAMMER_VISITE_MP3_URL = url + "",
            AR_FOURCHETTE_PRIX_MP3_URL = url + "Fourchette_AR.mp3",
            AR_REGION_MENU_MP3_URL = url + "",
            AR_SUPERFICIE_MP3_URL = url + "Superficie_AR.mp3",
            AR_TYPE_MENU_MP3_URL = url + "Type_de_logement_AR.mp3",
            AR_NBR_PIECES_MP3_URL = url + "Nombre_de_pieces_AR.mp3",
            YOU_CHOOSED_ARABIC_MP3_URL = url + "",
            AR_STANDARD_WELCOME_MESSAGE_MP3_URL = url + "",
            AR_MAIN_MENU_MP3_URL = url + "Main_menu_AR.mp3";

    private static final String AR_NO_LOGEMENTS_MP3_URL = url + "",
            FR_NO_LOGEMENTS_MP3_URL = url + "";

    public static VoiceResponse getVoiceResponse(String language) {
        switch (language) {
            case "fr":
                return new VoiceResponse.Builder()
                        //    .play(new Play.Builder(IVSConsts.FR_NO_LOGEMENTS_MP3_URL).build())
                        .say(new Say.Builder("il n'y a pas de logement !").language(Say.Language.FR_FR).build())
                        .build();
            case "ar":
                return new VoiceResponse.Builder()
                        .say(new Say.Builder("il n'y a pas de logement !").language(Say.Language.FR_FR).build())
                        .build();
            default:
                return new VoiceResponse.Builder()
                        .say(new Say.Builder("il n'y a pas de logement !").language(Say.Language.FR_FR).build())
                        .build();
        }
    }
}
