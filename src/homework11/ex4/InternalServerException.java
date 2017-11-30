package homework11.ex4;

import java.io.IOException;


public class InternalServerException extends RuntimeException {
    public InternalServerException(IOException e) {
        super(e);
    }
}
