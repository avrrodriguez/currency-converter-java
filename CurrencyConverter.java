
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    try {
      URL url = new URL("https://api.freecurrencyapi.com/v1/latest?apikey=2NY30aWPBLN9FOoWNYgozv19MElGZ6rlchjcLos9");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      String parameters = "currencies=AED";

      connection.setRequestProperty("Content-Type",
          "application/x-www-form-urlencoded");

      connection.setRequestProperty("Content-Length",
          Integer.toString(parameters.getBytes().length));
      connection.setRequestProperty("Content-Language", "en-US");

      connection.setDoOutput(true);
      DataOutputStream out = new DataOutputStream(connection.getOutputStream());
      out.writeBytes(parameters);
      out.flush();
      out.close();

    } finally {
      return "1.2";
    }

  }
}