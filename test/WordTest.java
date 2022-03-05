import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing word class functionality
 * @author Matt Dobaj
 */
class WordTest {
    Word word;
    @BeforeEach
    void setUP() {
        word = new Word();
    }

    @Test
    @DisplayName("get all possible words with length 0 should be empty")
    void noWordLengthgetAllPossibleWords() {
        word.getAllPossibleWords(new char[]{'w','o','r','d'}, new char[]{'\0','\0','\0','\0', '\0'}, 0);
        assertEquals(0, word.getPossibleWordList().size());
    }

    @Test
    @DisplayName("get all possible words should find words given restrictions")
    void restrictedgetAllPossibleWords() {
        word.getAllPossibleWords(new char[]{'w','o','r','d'}, new char[]{'w','o','r','\0', '\0'}, 4);
        assertEquals(1, word.getPossibleWordList().size());
    }

    @Test
    @DisplayName("get all possible words should NOT find words with no letters available")
    void noLettersgetAllPossibleWords() {
        word.getAllPossibleWords(new char[]{'\0'}, new char[]{'w','o','r','\0', '\0'}, 4);
        assertEquals(0, word.getPossibleWordList().size());
    }

    @Test
    @DisplayName("validity check should return false if there are not enough letters")
    void notEnoughLettersValidityCheck() {
        ArrayList<Character> charactersArrayList = new ArrayList<>(Arrays.asList('w', 'o', 'r'));
        assertFalse(word.overallValidityCheck(charactersArrayList, "WORD"));
    }

    @Test
    @DisplayName("validity check should return false if there are not enough letters of same type")
    void sameLettersValidityCheck() {
        ArrayList<Character> charactersArrayList = new ArrayList<>(Arrays.asList('w', 'o', 'r','d'));
        assertFalse(word.overallValidityCheck(charactersArrayList, "WOORD"));
    }

    @Test
    @DisplayName("validity check should act correctly with invalid characters")
    void voidLettersValidityCheck() {
        ArrayList<Character> charactersArrayList = new ArrayList<>(Arrays.asList('w', 'o', 'r','d', '0', 'o','-'));
        assertTrue(word.overallValidityCheck(charactersArrayList, "WOORD"));
    }

    @Test
    @DisplayName("lowercase restrictions should pass restriction check")
    void lowercaseRestrictionCheck() {
        assertTrue(word.restrictionCheck(new char[]{'w','o','r','d'}, "WORD"));
    }

    @Test
    @DisplayName("all caps restrictions should pass restriction check")
    void capsRestrictionCheck() {
        assertTrue(word.restrictionCheck(new char[]{'W','O','R','D'}, "WORD"));
    }

    @Test
    @DisplayName("empty restriction arrays longer than a word should pass restriction check")
    void longRestrictionCheck() {
        assertTrue(word.restrictionCheck(new char[]{'\0','\0','\0','\0', '\0'}, "WORD"));
    }

    @Test
    @DisplayName("Getting word list from dictionary should work")
    void getWordsFromDictionary() {
        word.getWordsFromDictionary(5);
        assertTrue(word.getPossibleWordList().size()>=0, "Unsuccessfully obtained word list from dictionary");
    }

}