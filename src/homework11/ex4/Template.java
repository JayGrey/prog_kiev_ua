package homework11.ex4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Template {

    private Map<String, String> templates;

    public Template() {
        templates = new HashMap<>();
    }

    public void loadPage(String templateName, String fileName) {
        if (templateName == null || fileName == null
                || !new File(fileName).isFile()) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader
                     = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        templates.put(templateName, sb.toString());
    }

    public String renderPage(String name) {
        return templates.get(name);
    }
}
