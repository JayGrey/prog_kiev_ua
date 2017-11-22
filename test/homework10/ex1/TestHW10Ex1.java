package homework10.ex1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestHW10Ex1 {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder tempDir = new TemporaryFolder();

    @Test
    public void dictionaryPutTest() {
        Dictionary dictionary = new Dictionary();
        assertEquals(0, dictionary.size());

        dictionary.put("a", "b");
        assertEquals(1, dictionary.size());
    }

    @Test
    public void dictionaryPutNullFirstTest() {
        Dictionary dictionary = new Dictionary();
        thrown.expect(IllegalArgumentException.class);
        dictionary.put(null, "b");

    }

    @Test
    public void dictionaryPutNullSecondTest() {
        Dictionary dictionary = new Dictionary();
        thrown.expect(IllegalArgumentException.class);
        dictionary.put("null", null);

    }

    @Test
    public void dictionaryDifferentCaseTest() {
        Dictionary dictionary = new Dictionary();
        assertEquals(0, dictionary.size());

        dictionary.put("a", "b");
        dictionary.put("A", "b");
        dictionary.put("a", "B");
        dictionary.put("A", "b");

        assertEquals(1, dictionary.size());
    }

    @Test
    public void dictionaryPutWithSpacesTest() {
        Dictionary dictionary = new Dictionary();
        assertEquals(0, dictionary.size());

        dictionary.put("a", "  b  ");
        assertEquals("b", dictionary.get("a"));

        dictionary.put(" a ", "  c  ");
        assertEquals("c", dictionary.get("a"));
    }

    @Test
    public void getNullTest() {
        Dictionary dictionary = new Dictionary();

        thrown.expect(IllegalArgumentException.class);
        dictionary.get(null);
    }

    @Test
    public void getUnknownValueTest() {
        Dictionary dictionary = new Dictionary();
        assertEquals(0, dictionary.size());

        assertEquals(0, dictionary.get("").length());
    }

    @Test
    public void getKnownValueTest() {
        Dictionary dictionary = new Dictionary();

        dictionary.put("a", "b");
        assertEquals("b", dictionary.get("a"));

        dictionary.put("a", "B");
        assertEquals("b", dictionary.get("a"));
    }

    @Test
    public void saveNullFileNameTest() {
        Dictionary dictionary = new Dictionary();
        thrown.expect(IllegalArgumentException.class);
        dictionary.save(null);
    }

    @Test
    public void saveTest() throws IOException {
        File file = tempDir.newFile();
        Dictionary dictionary = new Dictionary();
        dictionary.put("a", "b");
        dictionary.save(file.getCanonicalPath());
        assertTrue(file.length() > 0);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String result = reader.readLine();
            assertEquals("a=b", result);
        }
    }

    @Test
    public void loadTest() throws IOException {
        File file = tempDir.newFile();

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("a=b");
            writer.write(System.lineSeparator());

            writer.write("c=d");
            writer.write(System.lineSeparator());

            writer.write("e=f");
            writer.write(System.lineSeparator());
        }

        Dictionary dictionary = new Dictionary();
        assertEquals(0, dictionary.size());

        dictionary.load(file.getCanonicalPath());
        assertEquals(3, dictionary.size());
        assertEquals("b", dictionary.get("a"));
    }

    @Test
    public void loadWithDirtyStringsTest() throws IOException {
        File file = tempDir.newFile();

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("a=   b   ");
            writer.write(System.lineSeparator());

            writer.write("c=d");
            writer.write(System.lineSeparator());

            writer.write("c = d");
            writer.write(System.lineSeparator());

            writer.write(System.lineSeparator());

            writer.write("cd");
            writer.write(System.lineSeparator());

            writer.write("  E  = F");
            writer.write(System.lineSeparator());
        }

        Dictionary dictionary = new Dictionary();
        assertEquals(0, dictionary.size());

        dictionary.load(file.getCanonicalPath());
        assertEquals(3, dictionary.size());
        assertEquals("b", dictionary.get("a"));
        assertEquals("f", dictionary.get("e"));
    }

    @Test
    public void translateTest() throws IOException {

        Dictionary dict = new Dictionary();
        dict.put("a", "b");
        dict.put("c", "d");

        Translate translate = new Translate(dict);

        assertEquals("b e, d.", translate.process("a e, c."));
        assertEquals(" b e, d.", translate.process(" a e, c."));
        assertEquals("b e, d. ", translate.process("a e, c. "));
    }


}
