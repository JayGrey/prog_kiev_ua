import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestSuite.class);
        System.out.format("run %d test(s) in %d ms%n", result.getRunCount(),
                result.getRunTime());
        if (!result.wasSuccessful()) {
            System.out.format("failed: %d%n%n", result.getFailureCount());
            for (Failure failure : result.getFailures()) {
                System.out.println(failure);
            }
        } else {
            System.out.println("all tests passed"
                    + (result.getIgnoreCount() > 0 ? ", ignored: "
                    + result.getIgnoreCount() : ""));
        }
    }
}
