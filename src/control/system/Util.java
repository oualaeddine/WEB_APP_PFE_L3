package control.system;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class Util {

    /**
     * @param object object we want to convert to JSON
     * @return String containing the object in parameter formatted tto JSON
     */
  public static String objectToJson(Object object) {
        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(object);
            System.out.println("JSON = " + json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}

/*    hedi galk mach affaire akhdem gson khir :3
        public static String objectToJson(Object object) {
        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(object);
            System.out.println("JSON = " + json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }  */