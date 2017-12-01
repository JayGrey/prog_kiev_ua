package homework11.ex4;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Response {

    private static final String LINE_SEPARATOR = "\r\n";
    private final Map<String, String> headers;

    public enum StatusCode {
        _200(200, "OK"),
        _404(404, "Not Found"),
        _500(500, "Internal Server Error"
        );

        private final String description;
        private final int code;

        StatusCode(int code, String description) {
            this.code = code;
            this.description = description;

        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    private String body;

    private StatusCode statusCode;

    public Response(StatusCode statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
        headers = new HashMap<>();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public void write(OutputStream stream) {
        if (stream == null) {
            return;
        }

        PrintWriter writer = new PrintWriter(stream);

        StringBuilder output = new StringBuilder();

        output.append(String.format("HTTP/1.1 %d %s%s", statusCode.getCode(),
                statusCode.getDescription(), LINE_SEPARATOR));

        headers.put("Content-Type", "text/html; charset=UTF-8");
        headers.put("Content-length", Integer.toString(body.getBytes().length));

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            output.append(String.format("%s: %s%s", entry.getKey(),
                    entry.getValue(), LINE_SEPARATOR));
        }
        output.append(LINE_SEPARATOR);
        output.append(body);

        writer.write(output.toString());
        writer.flush();

    }
}
