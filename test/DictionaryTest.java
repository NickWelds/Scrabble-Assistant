import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing dictionary class functionality
 * @author Matt Dobaj
 */

class DictionaryTest {
    Dictionary dictionary;
    @BeforeEach
    void setUp() {
        dictionary = new Dictionary();
    }

    @Test
    @DisplayName("wordLengths that are positive integers should work")
    void positiveWordLengthTest() {
        for (int i = 0; i < 15; i++) {
            assertTrue(dictionary.getAllWordsByLength(i).size() >= 0, ("wordlength of " + i + " does not work"));
        }
    }

    @Test
    @DisplayName("Negative wordLengths should not break code")
    void negativeWordLengthTest() {
        assertEquals(0, dictionary.getAllWordsByLength(-1).size(), "Negative wordlength test failed");
    }

    @Test
    @DisplayName("Invalid filepath should through an exception.")
    void filepathTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        dictionary.filename = "/";
        dictionary.getAllWordsByLength(5);
        assertEquals("Dictionary file not found!" + System.lineSeparator(), outContent.toString(), "Exception not thrown.");
    }
}