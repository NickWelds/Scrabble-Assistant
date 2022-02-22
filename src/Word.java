import java.util.ArrayList;

public class Word {
    ArrayList<String> possibleWordList;

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

    public void getWordsFromDictionary(int wordlength){
        Dictionary dictionary = new Dictionary();
        possibleWordList = dictionary.getAllWordsByLength(wordlength);
        System.out.println(possibleWordList);
    }
}
