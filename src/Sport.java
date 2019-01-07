import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Sport {
    public String[][] sportArray = new String[1000][100];
    public int sayac = 0;
    //Reading text.
    public void readText(){
        try(Scanner sc = new Scanner(new BufferedReader(new FileReader("sport.txt")))){
            while(sc.hasNextLine()){
                String bilgiler = sc.nextLine();
                sportArray[sayac] = bilgiler.split("\t");
                sportArray[sayac][0] = removeUTF8BOM(sportArray[sayac][0]);
                sayac++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    // If there is UTF-8 BOM  in the text file.
    public static final String UTF8_BOM = "\uFEFF";
    private static String removeUTF8BOM(String strng) {
        if (strng.startsWith(UTF8_BOM)) {
            strng = strng.substring(1);
        }
        return strng;
    }
}
