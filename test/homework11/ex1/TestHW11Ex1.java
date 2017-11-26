package homework11.ex1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestHW11Ex1 {
    @Rule
    public TemporaryFolder tempDir = new TemporaryFolder();

    @Test
    public void testValidLinkWithProto() {
        assertTrue(LinkChecker.checkLink("google.com"));
    }

    @Test
    public void testValidLinkWithWhitespace() {
        String url = "https://www.google.com/search?q=java util";
        assertTrue(LinkChecker.checkLink(url));
    }

    @Test
    public void testGeneral() throws IOException {
        File file = tempDir.newFile();
        PrintWriter writer = new PrintWriter(file);

        writer.println("docs.oracle.com");
        writer.println("https://prog.kiev.ua/forum");
        writer.close();

        LinkChecker.main(new String[]{file.getCanonicalPath()});
    }

    @Test
    public void testDuplicateLinks() throws IOException {
        File file = tempDir.newFile();
        PrintWriter writer = new PrintWriter(file);

        writer.println("docs.oracle.com");
        writer.println("docs.oracle.com");
        writer.println("https://prog.kiev.ua/forum");
        writer.close();

        Set<String> links = LinkChecker.getLinksFromFile(
                file.getCanonicalPath());

        assertEquals(2, links.size());
    }

}
