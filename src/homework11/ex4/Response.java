package homework11.ex4;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Response {
    public enum StatusCode {
        _200(200, "OK"),
        _404(404, "Not Found"),
        _500(500, "Internal Server Error");


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
        //todo: rewrite form headers
        PrintWriter writer = new PrintWriter(stream);
        String output = String.format("HTTP/1.1 %d %s\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n\r\n%s",
                statusCode.getCode(), statusCode.getDescription(), body);

        writer.write(output);
        writer.flush();

    }
}
