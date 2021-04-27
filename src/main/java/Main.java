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

    public static void main(String[] args) throws IOException, ParseException {
        new DayOfCovid("month/?region=russia", "2021-04-26");
    }
}
