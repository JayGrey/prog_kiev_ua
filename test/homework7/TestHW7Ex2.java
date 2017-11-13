package homework7;

import homework7.ex2.FileCopy;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestHW7Ex2 {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder tempDir = new TemporaryFolder();

    @Test
    public void FilenameIsNullTest() {
        thrown.expect(IllegalArgumentException.class);
        new FileCopy().copy(null, ".");
    }

    @Test
    public void PathNullTest() {
        thrown.expect(IllegalArgumentException.class);
        new FileCopy().copy("", null);
    }

    @Test
    public void FilenameNotExistsTest() {
        thrown.expect(IllegalArgumentException.class);
        new FileCopy().copy("abc", ".");
    }

    @Test
    public void PathNotExistsTest() {
        thrown.expect(IllegalArgumentException.class);
        new FileCopy().copy("abc", ",");
    }

    @Test
    public void FilenameAndPathInTheSameDirTest() throws IOException {
        thrown.expect(IllegalArgumentException.class);

        File file = tempDir.newFile();

        new FileCopy().copy(file.getCanonicalPath(),
                tempDir.getRoot().getCanonicalPath());
    }

    @Test
    public void copyNamesTest() throws IOException {
        File file = tempDir.newFile();
        File dir = tempDir.newFolder();

        assertEquals(0, dir.list().length);

        new FileCopy().copy(file.getCanonicalPath(), dir.getCanonicalPath());

        String[] files = dir.list();
        assertEquals(1, files.length);

        assertEquals(file.getName(), files[0]);

    }

    @Test
    public void copySizeTest() throws IOException {
        File file = tempDir.newFile();
        File dir = tempDir.newFolder();
        final int FILE_SIZE = 773 * 1024;

        assertEquals(0, dir.list().length);

        try (FileOutputStream out = new FileOutputStream(file)) {
            for (int i = 0; i < FILE_SIZE; i++) {
                out.write(0);
            }
        }

        new FileCopy().copy(file.getCanonicalPath(), dir.getCanonicalPath());

        File[] files = dir.listFiles();
        assertEquals(1, files.length);

        assertEquals(file.length(), files[0].length());

    }
}
