package homework11.ex1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class LinkChecker {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: LinkChecker <filename>");
            System.exit(1);
        }

        for (String link : getLinksFromFile(args[0])) {
            System.out.format("%c %s%n", checkLink(link) ? '+' : '-', link);
        }
    }

    static boolean checkLink(String link) {
        try {
            Map<String, String> address = parseAddress(link);
            URL url = new URL(address.getOrDefault("protocol", "http") + "://"
                    + address.getOrDefault("host", "") + "/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            conn.disconnect();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    static Set<String> getLinksFromFile(String fileName) {
        Set<String> result = new HashSet<>();

        try (BufferedReader reader
                     = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0) {
                    result.add(line.trim());
                }
            }
        } catch (IOException e) {
            return Collections.emptySet();
        }

        return result;
    }

    private static Map<String, String> parseAddress(String address) {
        if (address == null) {
            return Collections.emptyMap();
        }

        Map<String, String> result = new HashMap<>();

        if (address.contains("://")) {
            result.put("protocol", address.split("://")[0]);
            address = address.split("://")[1];
        }

        if (address.contains("/")) {
            result.put("host", address.split("/")[0]);
        } else {
            result.put("host", address);
        }

        return result;
    }
}
