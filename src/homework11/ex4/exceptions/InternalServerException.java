package homework11.ex4.exceptions;

import java.io.IOException;


public class InternalServerException extends WebServerException {
    public InternalServerException(IOException e) {
        super(e);
    }

    public InternalServerException(String message) {
        super(message);
    }
}
