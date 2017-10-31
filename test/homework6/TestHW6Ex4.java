package homework6;

import homework6.ex4.FileCopy;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestHW6Ex4 {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testFromArgumentNull() {
        thrown.expect(IllegalArgumentException.class);
        new FileCopy().copy(null, ".");
    }

    @Test
    public void testFromArgumentNotDir() {
        thrown.expect(IllegalArgumentException.class);
        new FileCopy().copy("null", ".");
    }

    @Test
    public void testToArgumentNull() {
        thrown.expect(IllegalArgumentException.class);
        new FileCopy().copy("", null);
    }

    @Test
    public void testToArgumentNotDir() {
        thrown.expect(IllegalArgumentException.class);
        new FileCopy().copy(".", "null");
    }

    @Test
    public void testSrcAndDstTheSame() {
        thrown.expect(IllegalArgumentException.class);
        new FileCopy().copy(".", ".");
    }

    @Test
    public void testFileAmount() throws IOException {
        tempFolder.newFile();
        tempFolder.newFile();
        tempFolder.newFile();
        File dirTo = tempFolder.newFolder();

        assertEquals(0, dirTo.listFiles(File::isFile).length);

        new FileCopy().copy(tempFolder.getRoot().getCanonicalPath(),
                dirTo.getCanonicalPath());

        assertEquals(3, dirTo.listFiles(File::isFile).length);
    }

    @Test
    public void testFileEquality() throws IOException {
        File file = tempFolder.newFile();
        byte[] buffer = new byte[333];

        try (FileOutputStream fOut = new FileOutputStream(file)) {
            fOut.write(buffer);
        }

        File dirTo = tempFolder.newFolder();
        new FileCopy().copy(tempFolder.getRoot().getCanonicalPath(),
                dirTo.getCanonicalPath());

        File[] files = dirTo.listFiles(File::isFile);
        assertEquals(1, files.length);

        assertEquals(file.length(), files[0].length());
    }

    @Test
    public void testEmptyFromDir() throws IOException {
        assertEquals(0, tempFolder.getRoot().listFiles(File::isFile).length);
        new FileCopy().copy(tempFolder.getRoot().getCanonicalPath(),
                tempFolder.newFolder().getCanonicalPath());
    }
}
