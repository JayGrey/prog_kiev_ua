package homework11.ex4.exceptions;


public class WebServerException extends RuntimeException {
    public WebServerException() {
    }

    public WebServerException(Throwable cause) {
        super(cause);
    }

    public WebServerException(String message) {
        super(message);
    }
}
