import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Word {
    ArrayList<String> possibleWordList;

    public void getAllPossibleWords(char[] letters, int wordlength){
        getWordList(wordlength);
        int iterator = 0;
        while(iterator < possibleWordList.size()) {
            if (!overallValidityCheck(letters, possibleWordList.get(iterator))) {
                possibleWordList.remove(iterator);
                iterator--;
            }
            iterator++;
        }
        System.out.println(possibleWordList);
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

    public boolean restrictionCheck() {
        return false;
    }

    public void trimPossibleWords(){}

    public void getWordList(int wordlength){
        Dictionary dictionary = new Dictionary();
        possibleWordList = dictionary.getAllWordsByLength(wordlength);
        System.out.println(possibleWordList);
        /*
        possibleWordList = new ArrayList<>();
        possibleWordList.add("ab");

         */
    }
}
