public class Main{
  //testing this pls work
  public static void main(String[] args) {
        Word word = new Word();
        char[] test = {'a','a','h'};
        char[] restrictions = {'\0', 'a', '\0'};
        word.getAllPossibleWords(test, 2);
    }
}
