package control.ivs;

import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Play;

public class IVSConsts {
    public static final String FOURCHETTE_PRIX_MENU_SERVLET_URL = "/ivs_FOURCHETTE_PRIX",
            REGION_MENU_SERVLET_URL = "/ivs_REGION_MENU",
            REGION_MENU_HANDELER_SERVLET_URL = "/ivs_REGION_MENU_HANDELER",
            TYPE_MENU_SERVLET_URL = "/ivs_",
            ROOMS_MENU_HANDELER_SERVLET_URL = "/ivs_ ROOMS_MENU_HANDELER",
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

    public static final String WELCOME_MP3_URL = "";

    public static final String FR_PROGRAMMER_VISITE_MP3_URL = "",
            FR_FOURCHETTE_PRIX_MP3_URL = "",
            FR_REGION_MENU_MP3_URL = "",
            FR_SUPERFICIE_MP3_URL = "",
            FR_TYPE_MENU_MP3_URL = "",
            FR_NBR_PIECES_MP3_URL = "",
            YOU_CHOOSED_FRENCH_MP3_URL = "",
            FR_STANDARD_WELCOME_MESSAGE_MP3_URL = "",
            FR_MAIN_MENU_MP3_URL = "";
    public static final String AR_PROGRAMMER_VISITE_MP3_URL = "",
            AR_FOURCHETTE_PRIX_MP3_URL = "",
            AR_REGION_MENU_MP3_URL = "",
            AR_SUPERFICIE_MP3_URL = "",
            AR_TYPE_MENU_MP3_URL = "",
            AR_NBR_PIECES_MP3_URL = "",
            YOU_CHOOSED_ARABIC_MP3_URL = "",
            AR_STANDARD_WELCOME_MESSAGE_MP3_URL = "",
            AR_MAIN_MENU_MP3_URL = "";
    public static final String WELCOME_HANDELER_SERVLET_URL = "",
            WELCOME_MENU_SERVLET_URL = "";
    private static final String AR_NO_LOGEMENTS_MP3_URL = "",
            FR_NO_LOGEMENTS_MP3_URL = "";

    public static VoiceResponse getVoiceResponse(String language) {
        switch (language) {
            case "fr":
                return new VoiceResponse.Builder()
                        .play(new Play.Builder(IVSConsts.FR_NO_LOGEMENTS_MP3_URL).build())
                        .build();
            case "ar":
                return new VoiceResponse.Builder()
                        .play(new Play.Builder(IVSConsts.AR_NO_LOGEMENTS_MP3_URL).build())
                        .build();
            default:
                return new VoiceResponse.Builder()
                        .play(new Play.Builder(IVSConsts.FR_NO_LOGEMENTS_MP3_URL).build())
                        .build();
        }
    }
}