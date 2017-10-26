import homework3.TestHW3Ex2;
import homework3.TestHW3Ex3;
import homework4.TestHW4Ex1;
import homework4.TestHW4Ex2;
import homework4.TestHW4Ex3;
import homework4.TestHW4Ex4;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestHW3Ex2.class,
        TestHW3Ex3.class,
        TestHW4Ex1.class,
        TestHW4Ex2.class,
        TestHW4Ex3.class,
        TestHW4Ex4.class,
})
public class TestSuite {
}
