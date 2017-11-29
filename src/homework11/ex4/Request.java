package homework11.ex4;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Request {
    public enum Method {GET, POST}

    private Method method = Method.GET;
    private String path = "";
    private String protocol = "";
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> body = new HashMap<>();

    public Request(InputStream stream) {

        read(stream);
    }

    public void read(InputStream stream) {

        try {

            BufferedInputStream reader = new BufferedInputStream(stream);

            ByteArrayOutputStream requestHeader =
                    new ByteArrayOutputStream();

            ByteArrayOutputStream requestBody =
                    new ByteArrayOutputStream();

            int ch;
            while ((ch = reader.read()) != -1) {
                requestHeader.write(ch);
                if (requestHeader.toString().contains("\r\n\r\n")) {
                    parseHeader(requestHeader.toString());
                    if (headers.containsKey("content-length")) {

                        int bodyLength = Integer
                                .valueOf(headers.get("content-length"));

                        for (int i = 0; i < bodyLength; i++) {
                            requestBody.write(reader.read());
                        }
                        parseBody(requestBody.toString());
                    }
                    break;
                }
            }
        } catch (IOException e) {
            throw new InternalServerError(e);
        }
    }

    private void parseHeader(String inputString) {
        String[] strings = inputString.split("\r\n");
        if (strings.length < 1) {
            return;
        }

        for (int i = 0; i < strings.length; i++) {
            // status line
            if (i == 0) {
                String[] elements = strings[0].split("\\s+");
                if (elements.length != 3) {
                    return;
                }

                // method
                if (elements[0].equalsIgnoreCase("get")) {
                    method = Method.GET;
                } else if (elements[0].equalsIgnoreCase("post")) {
                    method = Method.POST;
                }

                // path
                path = elements[1].trim();

                // protocol
                protocol = elements[2].trim();

            } else {
                int sepIndex = strings[i].indexOf(':');

                headers.put(
                        strings[i].substring(0, sepIndex).trim().toLowerCase(),
                        strings[i].substring(sepIndex + 1).trim()
                );
            }
        }
    }

    private void parseBody(String s) {
        if (headers.containsKey("content-type")) {
            if (!headers.get("content-type")
                    .equalsIgnoreCase("application/x-www-form-urlencoded")) {
                throw new UnsupportedMediaTypeException();
            }

            for (String element : s.split("&")) {
                int index = element.indexOf('=');
                body.put(element.substring(0, index).replace('+', ' '),
                        element.substring(index + 1).replace('+', ' '));
            }
        }
        System.out.println("body: " + body);
    }


    public Method getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getProtocol() {
        return protocol;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public String toString() {
        return String.format("Request: %s %s %s", method, path, protocol);
    }
}
