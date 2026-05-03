import java.util.*;
import java.util.Scanner;

public class URLShortener {

    private static Map<String, String> urlMap = new HashMap<>();
    private static final String BASE_URL = "http://short.ly/";

    public static String shortenURL(String longURL) {
        String code = Long.toHexString(System.nanoTime()).substring(0, 6);
        urlMap.put(code, longURL);
        return BASE_URL + code;
    }

    public static String redirect(String shortURL) {
        String code = shortURL.replace(BASE_URL, "");
        if (urlMap.containsKey(code)) {
            return "Redirecting to: " + urlMap.get(code);
        } else {
            return "URL not found.";
        }
    }

    public static void listAll() {
        if (urlMap.isEmpty()) {
            System.out.println("No URLs stored.");
            return;
        }
        for (Map.Entry<String, String> entry : urlMap.entrySet()) {
            System.out.println(BASE_URL + entry.getKey() + " --> " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== URL SHORTENER =====");
            System.out.println("1. Shorten a URL");
            System.out.println("2. Redirect (lookup) a short URL");
            System.out.println("3. List all URLs");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter long URL: ");
                    String longURL = sc.nextLine();
                    System.out.println("Short URL: " + shortenURL(longURL));
                    break;
                case 2:
                    System.out.print("Enter short URL: ");
                    String shortURL = sc.nextLine();
                    System.out.println(redirect(shortURL));
                    break;
                case 3:
                    listAll();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}