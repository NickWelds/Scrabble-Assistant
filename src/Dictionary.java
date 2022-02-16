import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
    String filename = "Scrabble-Assistant" + File.separator + "Scrabble Words.txt";

    public ArrayList<String> getAllWordsByLength(int wordlength) {
        ArrayList<String> wordsUnderLength = new ArrayList<>();
        try{
            File dictionary = new File(filename);
            Scanner fileInput = new Scanner(dictionary);
            while (fileInput.hasNext()) {
                String lineInput = fileInput.nextLine();
                if (lineInput.length() <= wordlength) {
                    wordsUnderLength.add(lineInput);
                    //System.out.println(lineInput);
                }
            }
            fileInput.close();
        } catch (Exception e) {
            System.out.println("Dictionary file not found!");
        }
        return wordsUnderLength;
    }
}
