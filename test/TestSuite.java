import homework3.ex2.TestHW3Ex2;
import homework3.ex3.TestHW3Ex3;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestHW3Ex2.class,
        TestHW3Ex3.class,
        //TestHW4Ex1.class
})
public class TestSuite {
}
