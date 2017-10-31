package homework6;

import homework6.ex5.FileMonitor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

public class TestHW6Ex5 {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testCheckArgNull() {
        thrown.expect(IllegalArgumentException.class);
        new FileMonitor(null);
    }

    @Test
    public void testCheckNotDir() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("argument is not a directory");

        new FileMonitor("null");
    }

    @Test
    public void test() throws IOException, InterruptedException {


        new FileMonitor(tempFolder.getRoot().getCanonicalPath()).start();

        tempFolder.newFile();

        tempFolder.newFile();

        File file = tempFolder.newFile();

        file.delete();

    }
}
