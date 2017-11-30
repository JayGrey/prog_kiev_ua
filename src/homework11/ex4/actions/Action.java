package homework11.ex4.actions;

import homework11.ex4.Request;
import homework11.ex4.Response;

public interface Action {
    Response process(Request request);
}
