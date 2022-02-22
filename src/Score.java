import java.util.ArrayList;

public class Score {

    public int length;
    public int score;

    //public ArrayList<String> best_words = new ArrayList<String>();

    // one for word and one for score
    String[][] best_words = new String[length][2];

    public ArrayList<Integer> scores = new ArrayList<>();

    Word word = new Word();

    public void calculate_scores(){
        score = 0;

        for (int index = 0; index < word.possibleWordList.size(); index++){

            for (int i = 0; i < word.possibleWordList.get(index).length(); i++){

                switch(word.possibleWordList.get(index).charAt(i)){
                    case 'a':
                    case 'e':
                    case 'i':
                    case 'l':
                    case 'n':
                    case 'o':
                    case 'r':
                    case 's':
                    case 't':
                    case 'u':
                        score += 1;
                        break;
                    case 'd':
                    case 'g':
                        score += 2;
                        break;
                    case 'b':
                    case 'c':
                    case 'm':
                    case 'p':
                        score += 3;
                        break;
                    case 'f':
                    case 'h':
                    case 'v':
                    case 'w':
                    case 'y':
                        score += 4;
                        break;
                    case 'k':
                        score += 5;
                        break;
                    case 'j':
                    case 'x':
                        score += 8;
                        break;
                    case 'q':
                    case 'z':
                        score += 10;
                        break;
                }

            }

            scores.add(score);
        }
    }

    // NOT DONE
    /*
    public void sort_words(){

        for (int i = 0; i < scores.size(); i++){
            if (i == 0){
                best_words[0] = word.possibleWordList.get(i);
            }
            if (scores.get(i) > )

            if (word.possibleWordList.get(i).)
        }
    }

     */
    //top three words and their score
    public static void load_best_words(){}
}
