package homework11.ex4;

import java.io.IOException;


public class InternalServerError extends RuntimeException {
    public InternalServerError(IOException e) {
        super(e);
    }
}
