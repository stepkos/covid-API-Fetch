// All
// https://api.quarantine.country/api/v1/spots/summary

// Spec Region
// https://api.quarantine.country/api/v1/spots/month/?region=russia

// List of regions
// https://api.quarantine.country/api/v1/regions

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    int choice;
    String restOfUrl;
    String date;
    String filePath;

    public Menu() throws IOException, ParseException {
        System.out.println("Witaj w aplikacji covid-API-Fetch - pobieranie statystyk o covid");
        System.out.println("Wybierz region");
        System.out.println("1. Caly swiat");
        System.out.println("2. Polska");
        System.out.println("3. USA");
        System.out.println("4. Rosja");
        System.out.println("5. Niemcy");

        Scanner input = new Scanner(System.in);
        choice = input.nextInt();

        switch (choice) {
            case 1 -> restOfUrl = "summary";
            case 2 -> restOfUrl = "month/?region=poland";
            case 3 -> restOfUrl = "month/?region=us";
            case 4 -> restOfUrl = "month/?region=russia";
            case 5 -> restOfUrl = "month/?region=germany";
            default -> {
                System.out.println("Nie ma takiej opcji");
                return;
            }
        }

        System.out.println("Podaj date w formacie YYYY-MM-DD z przedzialu miesiac wstecz");
        date = input.next();

        System.out.println("Wybierz opcje");
        System.out.println("1. Wypisz w konsoli");
        System.out.println("2. Zapisz do pliku");
        System.out.println("3. Oba");
        choice = input.nextInt();

        switch (choice) {
            case 1 -> System.out.println(new DayOfCovid(restOfUrl, date));
            case 2 -> {
                System.out.println("Wpisz sciezke do pliku rekomendowana: result/dayOfCovid.json");
                filePath = input.next();
                new DayOfCovid(restOfUrl, date).saveToJSONFile(filePath);
            }
            case 3 -> {
                System.out.println("Wpisz sciezke do pliku rekomendowana: result/dayOfCovid.json");
                filePath = input.next();
                DayOfCovid cov = new DayOfCovid(restOfUrl, date);
                System.out.println(cov);
                cov.saveToJSONFile(filePath);
            }
            default -> System.out.println("Nie ma takiej opcji");
        }
    }
}