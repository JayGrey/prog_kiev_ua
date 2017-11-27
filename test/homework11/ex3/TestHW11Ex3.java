package homework11.ex3;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestHW11Ex3 {
    @Test
    public void testParsePageNull() {
        LinkFinder lf = new LinkFinder();
        Set<String> links = lf.parsePage(null);

        assertEquals(0, links.size());
    }

    @Test
    public void testParsePageEmpty() {
        LinkFinder lf = new LinkFinder();
        Set<String> links = lf.parsePage("");

        assertEquals(0, links.size());
    }

    @Test
    public void testParseResolveLinkAbsolute() {
        LinkFinder lf = new LinkFinder();

        Set<String> links = new HashSet<>();
        links.add("http://def.com");

        Set<String> result = lf.resolveLinks("http://abc.org", links);

        assertEquals(1, result.size());
        assertTrue(result.contains("http://def.com"));
    }

    @Test
    public void testParseResolveLinkRelative() {
        LinkFinder lf = new LinkFinder();

        Set<String> links = new HashSet<>();
        links.add("/page");
        links.add("page1");
        links.add("./page2");
        Set<String> result = lf.resolveLinks("http://abc.org/", links);
        result.forEach(System.out::println);
        assertEquals(3, result.size());
        assertTrue(result.contains("http://abc.org/page"));
        assertTrue(result.contains("http://abc.org/page1"));
        assertTrue(result.contains("http://abc.org/page2"));
    }
}
