import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

// All
// https://api.quarantine.country/api/v1/spots/summary

// Spec Region
// https://api.quarantine.country/api/v1/spots/month/?region=russia

// List of regions
// https://api.quarantine.country/api/v1/regions


public class Main {

    public static String getJsonInStr(String APIurl) throws IOException {
        String inline = "";

        try {
            URL url = new URL(APIurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != 200){
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }
            else {
                Scanner scanner = new Scanner(url.openStream());
                while(scanner.hasNext()) {
                    inline = inline.concat(scanner.nextLine());
                }
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return inline;
    }

    public static void main(String[] args) throws IOException, ParseException {
        String jsonInStr = getJsonInStr("https://api.quarantine.country/api/v1/spots/month/?region=russia");
        // System.out.println(jsonInStr);


        JSONParser parser = new JSONParser();
        JSONObject data_obj = (JSONObject) parser.parse(jsonInStr);
        System.out.println(data_obj.get("status"));

        JSONObject tmp = (JSONObject) data_obj.get("data");
        System.out.println(tmp.get("2021-04-27"));


//        JSONArray tablica = (JSONArray) data_obj.get("data");
//        for (int i = 0; i < tablica.size(); i++){
//            JSONObject new_obj = (JSONObject) tablica.get(i);
//            System.out.println(new_obj.get("total_cases"));
//            System.out.println(new_obj.get("deaths"));
//            System.out.println(new_obj.get("recovered"));
//        }
    }
}
