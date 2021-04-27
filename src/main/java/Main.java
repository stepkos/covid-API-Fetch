import org.json.simple.parser.ParseException;
import java.io.IOException;

// All
// https://api.quarantine.country/api/v1/spots/summary

// Spec Region
// https://api.quarantine.country/api/v1/spots/month/?region=russia

// List of regions
// https://api.quarantine.country/api/v1/regions


public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        try {
            DayOfCovid cov = new DayOfCovid("month/?region=russia", "2021-04-26");
            cov.saveToJSONFile("result/dayOfCovid.json");
        }
        catch (ExceptionInInitializerError e) {
            System.out.println("Brak infomacji");
        }
    }
}
