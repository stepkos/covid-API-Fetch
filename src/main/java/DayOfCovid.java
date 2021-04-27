import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class DayOfCovid {

    private final static String baseUrl = "https://api.quarantine.country/api/v1/spots/";

    // To fetch
    public int total_cases;
    public int deaths;
    public int recovered;
    public int tested;

    // To count
    public double death_ratio;          // wspolczynnik zgonow
    public double recovery_ratio;       // wspolczynnik wyzdrowien
    public double positive_test_ratio;  // wspolczynnik pozytywnych testow


    public DayOfCovid(String restOfUrl, String date) throws IOException, ParseException {
        String jsonStr = FetchHttp.getJsonAsStr(baseUrl.concat(restOfUrl));

        JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonStr);
        System.out.println("Status: ".concat(jsonObj.get("status").toString()));

        JSONObject data = (JSONObject) jsonObj.get("data");
        System.out.println(data.get(date));

    }
}
