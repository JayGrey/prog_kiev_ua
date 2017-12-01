package homework11.ex4.exceptions;


public class PageNotFoundException extends WebServerException {
    public PageNotFoundException(String path) {
        super("Error: page " + path + " not found");
    }
}
