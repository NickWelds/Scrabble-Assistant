import java.util.ArrayList;

public class Score {

    public int length = 10;
    public int score;
    public boolean done = false;

    //public ArrayList<String> best_words = new ArrayList<String>();

    // one for word and one for score
    String[][] best_words = new String[length][2];

    public ArrayList<Integer> scores = new ArrayList<>();

    Word word = new Word();

    public void calculate_scores(){
        word.getAllPossibleWords(new char[]{'i', 'n', 'e', 'a', 'f', 'l', 'j'}, new char[]{'\0', 'a', '\0'}, 3);

        //goes through all words
        for (int index = 0; index < word.possibleWordList.size(); index++){


            score = 0;

            //goes through all characters
            for (int i = 0; i < word.possibleWordList.get(index).length(); i++){

                switch(word.possibleWordList.get(index).charAt(i)){
                    case 'A':
                    case 'E':
                    case 'I':
                    case 'L':
                    case 'N':
                    case 'O':
                    case 'R':
                    case 'S':
                    case 'T':
                    case 'U':
                        score += 1;
                        break;
                    case 'D':
                    case 'G':
                        score += 2;
                        break;
                    case 'B':
                    case 'C':
                    case 'M':
                    case 'P':
                        score += 3;
                        break;
                    case 'F':
                    case 'H':
                    case 'V':
                    case 'W':
                    case 'Y':
                        score += 4;
                        break;
                    case 'K':
                        score += 5;
                        break;
                    case 'J':
                    case 'X':
                        score += 8;
                        break;
                    case 'Q':
                    case 'Z':
                        score += 10;
                        break;
                }

            }

            scores.add(score);
        }
    }

    public void sort_words(){

        calculate_scores();

        //Sets best word array to blank and scores to 0
        for (int i = 0; i < length; i++){
            best_words[i][0] = "BLANK";
            best_words[i][1] = "0";
        }

        //goes through all words
        for (int i = 0; i < scores.size(); i++){

            //compares word to all best words
            for (int x = 0; x < length; x++){

                if (scores.get(i) > Integer.parseInt(best_words[x][1]) && !done){

                    //shift words
                    for (int y = length-1; y > x; y--){
                        best_words[y][0] = best_words[y-1][0];
                        best_words[y][1] = best_words[y-1][1];
                    }
                    best_words[x][0] = word.possibleWordList.get(i);
                    best_words[x][1] = scores.get(i).toString();

                    done = true;

                }
            }

            done = false;

        }

        System.out.println();

        //print best list
        for (int i = 0; i < length; i++){
            System.out.println((i+1) + ". " + best_words[i][0] + ", Score: " + best_words[i][1]);
        }
    }

    //top three words and their score
    public static void load_best_words(){}
}
