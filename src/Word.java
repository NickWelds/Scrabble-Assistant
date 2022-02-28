import java.util.ArrayList;

/**
 * Is responsible for finding all possible words given different letter restrictions.
 * Interacts with Dictionary Class.
 * @author Matt Dobaj
 */

public class Word {
    ArrayList<String> possibleWordList;

    /**
     * getAllPossibleWords gives an Arraylist of every word that meets the letter restricts and can be made up from the users letters.
     * It adds the restriction letters onto the letters for the purpose of finding words where the restriction letters are not in the letters.
     * @param inputLetters An array of letters that the user has available.
     * @param restrictions An array of characters that the word must have in those locations. Has the character '\0'
     *                     if blank and can be any character.
     * @param wordLength The maximum possible length of a word.
     */
    public void getAllPossibleWords(char[] inputLetters, char[] restrictions, int wordLength){
        getWordsFromDictionary(wordLength);
        ArrayList<Character> letters = new ArrayList<>();
        for (int i = 0; i < inputLetters.length; i++) {
            letters.add(inputLetters[i]);
        }
        for (int i = 0; i < restrictions.length; i++) {
            if (restrictions[i] != '\0') {
                letters.add(restrictions[i]);
            }
        }
        int iterator = 0;
        while(iterator < possibleWordList.size()) {
            if (!overallValidityCheck(letters, possibleWordList.get(iterator)) || !restrictionCheck(restrictions, possibleWordList.get(iterator))) {
                possibleWordList.remove(iterator);
                iterator--;
            }
            iterator++;
        }
    }

    /**
     * overallValidityCheck sees if a given word can be created using the letters that the user has.
     * @param letters An arraylist of the letters the user has available to use, includes restriction characters.
     * @param testWord The word that is being checked against the letters.
     * @return true if the testWord can be made from the letters.
     *         false if the testWord cannot be made from the letters.
     */

    public boolean overallValidityCheck(ArrayList<Character> letters, String testWord) {
        ArrayList<Character> tempLetters = new ArrayList<>();
        for(int i = 0; i < letters.size(); i++) {
            tempLetters.add(Character.toUpperCase(letters.get(i)));
        }
        for (int i = 0; i < testWord.length(); i++) {
            char testLetter = testWord.charAt(i);
            if (tempLetters.contains(testLetter)) {
                tempLetters.remove(tempLetters.indexOf(testLetter));
            }
            else {
                return false;
            }
        }
        return true;
    }

    /**
     * restrictionCheck is a check that determines if a word meets letter requirements (i.e. the 3rd letter must be an 'A').
     * It goes through the word character by character, and sees if the character matches at a given location.
     * @param restrictions An array of characters that the word must have in those locations. Has the character '\0'
     *                     if blank and can be any character.
     * @param testWord The word that is being checked against the restrictions.
     * @return true if the word has the specified characters in the given locations.
     *         false if the word does not have the characters in the given locations, or the restrictions are longer than the word.
     */

    public boolean restrictionCheck(char[] restrictions, String testWord) {
        int i = 0;
        while(i < restrictions.length) {
            if (i >= testWord.length()) {
                if (restrictions[i] != '\0' ) {
                    return false; // because there are restrictions past the length of the word, it is not valid
                }
            }
            else if (Character.toUpperCase(restrictions[i]) != testWord.charAt(i) && restrictions[i] != '\0') {
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * getWordsFromDictionary creates a new dictionary object and populates Word's possibleWordList with
     * every word under a given length, found using the dictionary object's getAllWordsByLength().
     * @param wordLength The maximum possible length of a word.
     */

    public void getWordsFromDictionary(int wordLength){
        Dictionary dictionary = new Dictionary();
        possibleWordList = dictionary.getAllWordsByLength(wordLength);
    }

    /**
     * getPossibleWordList() returns the wordlist containing the words that are possible to be made.
     */
    public ArrayList<String> getPossibleWordList() {
        return possibleWordList;
    }
}
