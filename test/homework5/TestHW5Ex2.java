package homework5;

import homework5.ex2.SimilarWords;
import homework5.ex2.StringContainer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TestHW5Ex2 {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSCAdd() {
        StringContainer sc = new StringContainer();
        assertEquals(0, sc.size());

        sc.add("a");
        sc.add("b");
        sc.add("c");

        assertEquals(3, sc.size());
    }

    @Test
    public void testSCAddNull() {
        thrown.expect(IllegalArgumentException.class);
        StringContainer sc = new StringContainer();
        sc.add("a");
        sc.add(null);
    }

    @Test
    public void testSCContains() {
        StringContainer sc = new StringContainer();
        sc.add("a");
        sc.add("b");

        assertTrue(sc.contains("a"));
        assertFalse(sc.contains("A"));
    }

    @Test
    public void testSCResize() {
        StringContainer sc = new StringContainer();
        for (int i = 0; i < 15; i++) {
            sc.add("" + ('a' + i));
        }
        assertEquals(15, sc.size());
    }

    @Test
    public void testSCIterator() {
        StringContainer sc = new StringContainer();
        for (int i = 0; i < 15; i++) {
            sc.add("" + ('a' + i));
        }

        int i = 0;
        int iteration = 0;
        for (String s : sc) {
            iteration++;
            if (!s.equals("" + ('a' + i))) {
                fail();
            }
            i += 1;
        }
        assertEquals(15, iteration);
    }

    @Test
    public void testFirstArg() throws IOException {
        File file = tempFolder.newFile();
        thrown.expect(IllegalArgumentException.class);
        SimilarWords.findWords("", file.getCanonicalPath());
    }

    @Test
    public void testSecondArg() throws IOException {
        File file = tempFolder.newFile();
        thrown.expect(IllegalArgumentException.class);
        SimilarWords.findWords(file.getCanonicalPath(), "");
    }

    @Test
    public void testFindWordsSameFile() throws IOException {
        File file = tempFolder.newFile();
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        writer.format("a b c %n d e f");
        writer.close();
        StringContainer container = SimilarWords.findWords(
                file.getCanonicalPath(), file.getCanonicalPath());

        assertEquals(6, container.size());
    }

    @Test
    public void testFindWordsDifferentFiles() throws IOException {
        File file1 = tempFolder.newFile();
        File file2 = tempFolder.newFile();

        PrintWriter writer = new PrintWriter(new FileWriter(file1));
        writer.format("a b c %n d e f");
        writer.close();

        writer = new PrintWriter(new FileWriter(file2));
        writer.format("z b Z %n Z Z f%n");
        writer.close();

        StringContainer container = SimilarWords.findWords(
                file1.getCanonicalPath(), file2.getCanonicalPath());

        assertEquals(2, container.size());
    }

    @Test
    public void testWriteResult() throws IOException {
        File file = tempFolder.newFile();
        StringContainer sc = new StringContainer();
        sc.add("a");
        sc.add("b");
        sc.add("c");

        SimilarWords.writeResult(sc, file.getCanonicalPath());

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            for (String s : sc) {
                if (!s.equals(scanner.next())) {
                    fail();
                }
            }
        }
        scanner.close();
    }
}
