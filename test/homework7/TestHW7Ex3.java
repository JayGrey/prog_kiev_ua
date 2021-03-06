package homework7;

import homework7.ex3.FileSearch;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestHW7Ex3 {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testPathNull() {
        thrown.expect(IllegalArgumentException.class);
        new FileSearch(null, "").find();
    }

    @Test
    public void testFilenameNull() {
        thrown.expect(IllegalArgumentException.class);
        new FileSearch(".", null).find();
    }

    @Test
    public void testFilenameEmpty() {
        thrown.expect(IllegalArgumentException.class);
        new FileSearch(".", "  ").find();
    }

    @Test
    public void testPathNotDir() {
        thrown.expect(IllegalArgumentException.class);
        new FileSearch(" ", "abc").find();
    }

    @Test
    public void testEmptyResult() throws IOException {

        String[] result = new FileSearch(
                tempFolder.getRoot().getCanonicalPath(), "abc").find();

        assertEquals(0, result.length);
    }

    @Test
    public void testFind() throws IOException {

        File file = tempFolder.newFile();

        String[] result = new FileSearch(
                tempFolder.getRoot().getCanonicalPath(), file.getName()).find();

        assertEquals(1, result.length);
    }

    @Test
    public void testFileInNestedDirs() throws IOException {

        File nFolder = tempFolder.newFolder("dir_a");
        File nnFolder = tempFolder.newFolder("dir_a", "dir_b");

        File file = tempFolder.newFile("expected.txt");
        new File(tempFolder.getRoot(), "unexpected.txt").createNewFile();

        new File(nFolder, file.getName()).createNewFile();
        new File(nFolder, "unexpected.txt").createNewFile();

        new File(nnFolder, file.getName()).createNewFile();
        new File(nnFolder, "unexpected.txt").createNewFile();

        String[] result = new FileSearch(
                tempFolder.getRoot().getCanonicalPath(), file.getName()).find();

        assertEquals(3, result.length);
    }
}
