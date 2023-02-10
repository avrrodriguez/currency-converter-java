
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Scanner;

public class CurrencyConverter {
  public static void main(String[] args) {

    ArrayList<String> convertArray = new ArrayList<>();

    convertArray = display();

    String conversionRate = currRateRequest(convertArray);

    System.out.println(convertArray);
    System.out.println(conversionRate);

  }

  public static ArrayList<String> display() {
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
    System.out.println("Getting conversion rate");
    System.out.println(convertArray);
    String conversionRate = "";

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(
            "https://api.freecurrencyapi.com/v1/latest?apikey=2NY30aWPBLN9FOoWNYgozv19MElGZ6rlchjcLos9&base_currency=USD&currencies=CAD"))
        .build();

    HttpResponse<String> response;
    try {
      response = client.send(request, BodyHandlers.ofString());

      System.out.println("here123");
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
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return conversionRate;
  }
}