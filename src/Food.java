import java.io.*;
import java.util.Scanner;

public class Food {
    public String[][] foodArray = new String[100][100];
    public int sayac = 0;
    //Reading text.
    public void readText() {
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader("food.txt")))) {
            while (sc.hasNextLine()) {
                String bilgiler = sc.nextLine().trim();
                foodArray[sayac] = bilgiler.split("\t");
                foodArray[sayac][0]=removeUTF8BOM(foodArray[sayac][0]);
                sayac++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //If there is UTF8 BOM in the text file.
    public static final String UTF8_BOM = "\uFEFF";
    private static String removeUTF8BOM(String strng) {
        if (strng.startsWith(UTF8_BOM)) {
            strng = strng.substring(1);
        }
        return strng;
    }
}
