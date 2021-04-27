import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

public class DayOfCovid {

    private final static String baseUrl = "https://api.quarantine.country/api/v1/spots/";

    // To fetch
    public int total_cases;
    public int deaths;
    public int recovered;
    public int tested;

    // To count
    public double death_ratio;      // wspolczynnik zgonow
    public double recovery_ratio;   // wspolczynnik wyzdrowien
    public double tests_ratio;      // wspolczynnik pozytywnych testow


    public DayOfCovid(String restOfUrl, String date) throws IOException, ParseException, ExceptionInInitializerError {
        String jsonStr = FetchHttp.getJsonAsStr(baseUrl.concat(restOfUrl));

        JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonStr);
        System.out.println("Status: ".concat(jsonObj.get("status").toString()));

        JSONObject data = (JSONObject) jsonObj.get("data");
        if (data.get(date) == null) {
            System.out.println("Brak infomacji o danych z tego dnia");
            throw new ExceptionInInitializerError();
        }

        JSONObject day = (JSONObject) data.get(date);
        this.total_cases = Integer.parseInt(day.get("total_cases").toString());
        this.deaths = Integer.parseInt(day.get("deaths").toString());
        this.recovered = Integer.parseInt(day.get("recovered").toString());
        this.tested = Integer.parseInt(day.get("tested").toString());

        countRatio();
    }

    public void countRatio() {
        death_ratio = (double) deaths / total_cases;
        recovery_ratio = (double) recovered / total_cases;
        tests_ratio = (double) tested / total_cases;
    }
}
