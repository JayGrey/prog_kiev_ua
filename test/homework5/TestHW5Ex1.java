package homework5;

import homework5.ex1.FileCopy;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestHW5Ex1 {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private boolean filesOnly(File f) {
        return f.isFile();
    }

    @Test
    public void testSrcArg() {
        thrown.expect(IllegalArgumentException.class);

        FileCopy.copyFilesByExtension("", ".", "txt");
    }

    @Test
    public void testDstArg() {
        thrown.expect(IllegalArgumentException.class);

        FileCopy.copyFilesByExtension(".", ",,,", "txt");
    }

    @Test
    public void testExtArg() {
        thrown.expect(IllegalArgumentException.class);

        FileCopy.copyFilesByExtension(".", ".", "  ");
    }

    @Test
    public void testSamePath() {
        thrown.expect(IllegalArgumentException.class);

        FileCopy.copyFilesByExtension(".", ".", "txt");
    }

    @Test
    public void testSrcPathAsFile() throws IOException {
        File file = tempFolder.newFile();

        thrown.expect(IllegalArgumentException.class);

        FileCopy.copyFilesByExtension(file.getCanonicalPath(), ".", "txt");
    }

    @Test
    public void testDstPathAsFile() throws IOException {
        File file = tempFolder.newFile();

        thrown.expect(IllegalArgumentException.class);

        FileCopy.copyFilesByExtension(".", file.getCanonicalPath(), "txt");
    }

    @Test
    public void testCopyFiles() throws IOException {

        File directoryTo = tempFolder.newFolder();

        assertEquals(0, tempFolder.getRoot().listFiles(this::filesOnly).length);

        tempFolder.newFile("a.txt");
        tempFolder.newFile("b.txt");
        tempFolder.newFile("c.doc");

        assertEquals(3, tempFolder.getRoot().listFiles(this::filesOnly).length);
        assertEquals(0, directoryTo.listFiles(this::filesOnly).length);

        FileCopy.copyFilesByExtension(tempFolder.getRoot().getCanonicalPath(),
                directoryTo.getCanonicalPath(), "txt");

        assertEquals(3, tempFolder.getRoot().listFiles(this::filesOnly).length);
        assertEquals(2, directoryTo.listFiles(this::filesOnly).length);
    }

    @Test
    public void testFileSize() throws IOException {
        File file = tempFolder.newFile("a.txt");
        assertEquals(0, file.length());

        byte[] data = new byte[1024 * 1024];
        try (FileOutputStream fout = new FileOutputStream(file)) {
            fout.write(data);
        } catch (IOException e) {
            fail();
        }

        assertEquals(data.length, file.length());

        File directoryTo = tempFolder.newFolder();
        FileCopy.copyFilesByExtension(tempFolder.getRoot().getCanonicalPath(),
                directoryTo.getCanonicalPath(), "txt");

        File[] files = directoryTo.listFiles(this::filesOnly);
        assertEquals(1, files.length);
        assertEquals(data.length, files[0].length());
    }

    @Test
    public void testFileEquality() throws IOException {
        File file = tempFolder.newFile("a.txt");
        File directoryTo = tempFolder.newFolder();

        byte[] data = new byte[1024];
        Random random = new Random();
        random.nextBytes(data);
        try (FileOutputStream fout = new FileOutputStream(file)) {
            fout.write(data);
        } catch (IOException e) {
            fail();
        }

        FileCopy.copyFilesByExtension(tempFolder.getRoot().getCanonicalPath(),
                directoryTo.getCanonicalPath(), "txt");
        File[] files = directoryTo.listFiles(this::filesOnly);
        assertEquals(1, files.length);
        for (int i = 0; i < data.length; i++) {
            try (FileInputStream fIn1 = new FileInputStream(file);
                 FileInputStream fIn2 = new FileInputStream((files[0]))) {
                if(fIn1.read() != fIn2.read()) {
                    fail();
                }
            } catch (IOException e) {
                fail();
            }
        }
    }
}
