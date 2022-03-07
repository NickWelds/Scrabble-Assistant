import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Testing Score class functionality
 * @author Kyle Wilson
 */
class ScoreTest {

    Score test;
    @BeforeEach
    void setUP() {
        test = new Score();
    }

//Tests for 'calculate_scores' method
    @Test
    @DisplayName("Score for first word, 'AB' must be 4")
    void test1_calculate_scores() {
        test.calculate_scores(new int[]{1, 1}, new char[]{'a', 'b'}, new char[]{}, 2);
        assertEquals(4, test.scores.get(0));
    }

    @Test
    @DisplayName("Score for first word, 'AB' must be 8 with two 2x multipliers")
    void test2_calculate_scores() {
        test.calculate_scores(new int[]{2, 2}, new char[]{'a', 'b'}, new char[]{}, 2);
        assertEquals(8, test.scores.get(0));
    }

    @Test
    @DisplayName("Score arraylist should be empty for word length 0")
    void test3_calculate_scores() {
        test.calculate_scores(new int[]{}, new char[]{}, new char[]{}, 0);
        assertEquals(0, test.scores.size());
    }
    @Test
    @DisplayName("Score for first word due to second letter restriction 'a', 'AA' should be 2")
    void test4_calculate_scores() {
        test.calculate_scores(new int[]{1, 1, 1}, new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'}, new char[]{'\0', 'a', '\0'}, 3);
        assertEquals(2, test.scores.get(0));
    }


    //Tests for 'sort_words' method
    @Test
    @DisplayName("Best word should be 'DECAF'")
    void test1_sort_words() {
        test.sort_words(new int[]{1, 1, 1, 1, 1, 1, 1}, new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'}, new char[]{}, 7);
        assertEquals("DECAF", test.final_word_list.get(0));
    }

    @Test
    @DisplayName("Best word with first letter multipler of 2x should be 'FACED'")
    void test2_sort_words() {
        test.sort_words(new int[]{2, 1, 1, 1, 1, 1, 1}, new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'}, new char[]{}, 7);
        assertEquals("FACED", test.final_word_list.get(0));
    }

    @Test
    @DisplayName("Final word list should be empty for word length 0")
    void test3_sort_words() {
        test.sort_words(new int[]{}, new char[]{}, new char[]{}, 0);
        assertEquals(0, test.final_word_list.size());
    }

    @Test
    @DisplayName("Best word due to second letter restriction 'c', should be 'ACE'")
    void test4_sort_words() {
        test.sort_words(new int[]{1, 1, 1}, new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'}, new char[]{'\0', 'c', '\0'}, 3);
        assertEquals("ACE", test.final_word_list.get(0));
    }
}