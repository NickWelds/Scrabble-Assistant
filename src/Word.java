import java.util.ArrayList;

public class Word {
    ArrayList<String> possibleWordList;

    /**
     * getAllPossibleWords gives an Arraylist of every word that meets the letter restricts and can be made up from the users letters.
     * @param letters An array of letters that the user has available.
     * @param restrictions An array of characters that the word must have in those locations. Has the character '\0'
     *                     if blank and can be any character.
     * @param wordlength The maximum possible length of a word.
     * @return possibleWordList, an arraylist of every word that fits the criteria.
     */
    public ArrayList<String> getAllPossibleWords(char[] letters, char[] restrictions, int wordlength){
        getWordsFromDictionary(wordlength);
        int iterator = 0;
        while(iterator < possibleWordList.size()) {
            if (!overallValidityCheck(letters, possibleWordList.get(iterator)) || !restrictionCheck(restrictions, possibleWordList.get(iterator))) {
                possibleWordList.remove(iterator);
                iterator--;
            }
            iterator++;
        }
        return possibleWordList;
    }

    /**
     * overallValidityCheck sees if a given word can be created using the letters that the user has.
     * @param letters An array of the letters the user has available to use.
     * @param testWord The word that is being checked against the letters.
     * @return true if the testWord can be made from the letters.
     *         false if the testWord cannot be made from the letters.
     */

    public boolean overallValidityCheck(char[] letters, String testWord) {
        ArrayList<Character> tempLetters = new ArrayList<>();
        for(int i = 0; i < letters.length; i++) {
            tempLetters.add(Character.toUpperCase(letters[i]));
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
     * @param wordlength The maximum possible length of a word.
     */

    public void getWordsFromDictionary(int wordlength){
        Dictionary dictionary = new Dictionary();
        possibleWordList = dictionary.getAllWordsByLength(wordlength);
    }
}
