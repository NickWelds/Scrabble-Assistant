import java.util.ArrayList;

/**
 * @author Kyle Wilson
 * The 'Score' class intakes a list of possible words, calculates their scores
 * and orders them into a list of given length
 */
public class Score {

    //initializes variables
    public int list_length = 10;
    public int word_length;
    public int score;
    public int multiplier;
    public boolean done = false;

    // one for word and one for score
    String[][] best_words = new String[list_length][2];

    //creates new arraylist of scores
    public ArrayList<Integer> scores = new ArrayList<>();

    //creates new word object
    Word word = new Word();
    GUI gui = new GUI();

    /**
     * The 'calculate scores' method takes all the possible words found in the Word class.
     * It then goes through each word and calculates their scores, putting them into an arraylist
     * in the same order as the possible words arraylist.
     */
    public void calculate_scores(int[] multiplier_list, char[] characters, char[] restrictions, int w_length){
        this.word_length = w_length;

        //gets all possible words with given array of characters/restrictions
        word.getAllPossibleWords(characters, restrictions, word_length);

        //goes through all words
        for (int index = 0; index < word.possibleWordList.size(); index++){

            //resets initial score
            score = 0;

            //goes through all characters
            for (int i = 0; i < word.possibleWordList.get(index).length(); i++){

                //finds multiplier for given character
                this.multiplier = multiplier_list[i];

                //adds score based on letter
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
                        score += 1 *multiplier;
                        break;
                    case 'D':
                    case 'G':
                        score += 2 *multiplier;
                        break;
                    case 'B':
                    case 'C':
                    case 'M':
                    case 'P':
                        score += 3 *multiplier;
                        break;
                    case 'F':
                    case 'H':
                    case 'V':
                    case 'W':
                    case 'Y':
                        score += 4 *multiplier;
                        break;
                    case 'K':
                        score += 5 *multiplier;
                        break;
                    case 'J':
                    case 'X':
                        score += 8 *multiplier;
                        break;
                    case 'Q':
                    case 'Z':
                        score += 10 *multiplier;
                        break;
                }

            }

            //adds score to array list in same order as possible word arraylist
            scores.add(score);
        }
    }

    /**
     * The 'sort words' method uses the scores of all possible words calculated in
     * the 'calculate scores' method. It takes the top scores/words and sorts them
     * into a 2-d array that is the specified length, and prints the top words along
     * with their scores.
     */
    public ArrayList<String> sort_words(int[] multiplier_list, char[] characters, char[] restrictions, int w_length){

        //calculate the possible word scores
        calculate_scores(multiplier_list, characters, restrictions, w_length);

        /*
        //Sets best word array to blank and scores to 0
        for (int i = 0; i < list_length; i++){
            best_words[i][0] = "BLANK";
            best_words[i][1] = "0";
        }

         */

        //goes through all possible words
        for (int i = 0; i < scores.size(); i++){

            //compares word to all best words
            for (int x = 0; x < list_length; x++){

                if (scores.get(i) > Integer.parseInt(best_words[x][1]) && !done){

                    //shift words
                    for (int y = list_length-1; y > x; y--){
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


        ArrayList<String> final_word_list = new ArrayList<>();

        for (int i = 0; i < list_length; i++){
            final_word_list.add(best_words[i][0]);
        }


        return final_word_list;
    }
}
