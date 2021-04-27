import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

public class DayOfCovid {

    private final static String baseUrl = "https://api.quarantine.country/api/v1/spots/";

    // To fetch
    private int total_cases;
    private int deaths;
    private int recovered;
    private int tested;

    // To count
    private double death_ratio;      // wspolczynnik zgonow
    private double recovery_ratio;   // wspolczynnik wyzdrowien
    private double tests_ratio;      // wspolczynnik testow na ilosc przypadkow


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

    @Override
    public String toString() {
        return "DayOfCovid{" +
                "total_cases=" + total_cases +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                ", tested=" + tested +
                ", death_ratio=" + death_ratio +
                ", recovery_ratio=" + recovery_ratio +
                ", tests_ratio=" + tests_ratio +
                '}';
    }

    public void setTotal_cases(int total_cases) {
        this.total_cases = total_cases;
        countRatio();
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
        countRatio();
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
        countRatio();
    }

    public void setTested(int tested) {
        this.tested = tested;
        countRatio();
    }

    public int getTotal_cases() {
        return total_cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getTested() {
        return tested;
    }

    public double getDeath_ratio() {
        return death_ratio;
    }

    public double getRecovery_ratio() {
        return recovery_ratio;
    }

    public double getTests_ratio() {
        return tests_ratio;
    }
}
