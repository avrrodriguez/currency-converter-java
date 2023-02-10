
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Scanner;

public class CurrencyConverter {
  public static Double main(String[] args) {
    // Gets array list with initial currency, currency to convert to, and amount to
    // convert
    // gets conversion rate between initial currency and currency to convert to,
    // returns the final converted amount of money.

    ArrayList<String> convertArray = new ArrayList<>();

    convertArray = display();

    String conversionRate = currRateRequest(convertArray);

    System.out.println(convertArray);
    System.out.println(conversionRate);

    Double currencyConverted = Double.valueOf(convertArray.get(2)) * Double.valueOf(conversionRate);

    return currencyConverted;
  }

  public static ArrayList<String> display() {
    // Displays to user to enter the initial currency and currency to convert to
    // as well as amount to convert from.
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> convertMessage = new ArrayList<String>();

    System.out.println("Enter the currency you would like to convert from (3 letters).");
    String currFrom = scanner.nextLine();
    convertMessage.add(currFrom);

    System.out.println("Enter the currency you would like to convert to (3 letters).");
    String currTo = scanner.nextLine();
    convertMessage.add(currTo);

    System.out.println("Enter the amount you would like to convert.");
    String amount = scanner.nextLine();
    convertMessage.add(amount);

    scanner.close();

    return convertMessage;
  }

  private static String currRateRequest(ArrayList<String> convertArray) {
    // Makes request to third party api to get conversion rate from initial
    // currency to currency to convert to.

    System.out.println("Getting conversion rate");
    System.out.println(convertArray);
    String conversionRate = "";

    String uri = String.format(
        "https://api.freecurrencyapi.com/v1/latest?apikey=2NY30aWPBLN9FOoWNYgozv19MElGZ6rlchjcLos9&base_currency=%s&currencies=%s",
        convertArray.get(0), convertArray.get(1));
    // System.out.println(uri);

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(uri))
        .build();

    HttpResponse<String> response;
    try {
      response = client.send(request, BodyHandlers.ofString());

      System.out.println(response.statusCode());
      System.out.println(response.body());
      conversionRate = response.body();

      String[] list = conversionRate.split(":");
      for (String a : list) {
        conversionRate = a;
      }

      conversionRate = conversionRate.replace("}", "");

      return conversionRate;

    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return conversionRate;
  }
}