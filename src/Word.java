import java.util.ArrayList;

public class Word {
    ArrayList<String> possibleWordList;

    public ArrayList<String> getAllPossibleWords(char[] letters, int wordlength){
        getWordsFromDictionary(wordlength);
        int iterator = 0;
        while(iterator < possibleWordList.size()) {
            if (!overallValidityCheck(letters, possibleWordList.get(iterator))) {
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

    public boolean restrictionCheck() {
        return false;
    }

    public void trimPossibleWords(){}

    public void getWordsFromDictionary(int wordlength){
        Dictionary dictionary = new Dictionary();
        possibleWordList = dictionary.getAllWordsByLength(wordlength);
        System.out.println(possibleWordList);
    }
}
