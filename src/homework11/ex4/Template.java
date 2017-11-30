package homework11.ex4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Template {

    private static Template instance = null;

    private Map<String, String> templates;

    private Template() {
        templates = new HashMap<>();
    }

    public static Template getInstance() {
        if (instance == null) {
            instance = new Template();
        }

        return instance;
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
        return renderPage(name, Collections.emptyMap());
    }

    public String renderPage(String name, Map<String, String> params) {
        String result = templates.get(name);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (result.contains("{" + entry.getKey() + "}")) {
                result = result.replace("{" + entry.getKey() + "}",
                        entry.getValue());
            }
        }
        return result;
    }
}
