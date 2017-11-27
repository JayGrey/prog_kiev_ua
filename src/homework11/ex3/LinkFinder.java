package homework11.ex3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LinkFinder {
    public Set<String> getLinks(String site) {
        return resolveLinks(site, parsePage(loadPage(site)));
    }

    Set<String> resolveLinks(String baseURL, Set<String> links) {
        try {
            final URI baseLink = new URI(baseURL);
            return links.stream()
                    .map((l) -> (baseLink.resolve(l).toString()))
                    .collect(Collectors.toSet());
        } catch (URISyntaxException e) {
            return Collections.emptySet();
        }
    }

    Set<String> parsePage(String page) {
        if (page == null) {
            return Collections.emptySet();
        }
        Set<String> links = new HashSet<>();
        String patternString = "<a\\s+href\\s*=\\s*\"(.+?)\"";
        Matcher matcher = Pattern.compile(patternString).matcher(page);
        while (matcher.find()) {
            links.add(matcher.group(1));
        }

        return links;
    }

    private String loadPage(String site) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(site);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            try (BufferedReader reader =
                         new BufferedReader(new InputStreamReader(
                                 conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
            conn.disconnect();

        } catch (IOException e) {
            return "";
        }
        return sb.toString();
    }


}
