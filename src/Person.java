import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Person {
    public String[][] personArray = new String[100][100];

    public int[][] calorie = new int[100][6];

    public int sayac = 0;
    private int sayac2 = 0;
    //Reading text.
    public void readText(){
        try(Scanner sc = new Scanner(new BufferedReader(new FileReader("people.txt")))){
            while(sc.hasNextLine()){
                String bilgiler = sc.nextLine().trim();
                personArray[sayac] = bilgiler.split("\t");
                personArray[sayac][0] = removeUTF8BOM(personArray[sayac][0]);
                sayac++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(String[] a : personArray){
            if(a[0] == null || a[0].isEmpty()){
                break;
            }
            //If person is male
            if(a[2].equals("male")){
                double gecici;
                gecici = 66 + (13.75*Double.parseDouble(a[3])) + (5*Integer.parseInt(a[4])) - (6.8*(2018-Integer.parseInt(a[5])));
                long round = Math.round(gecici);
                if(a[0].length() == 5){
                    calorie[sayac2][0] = Integer.parseInt(a[0]);
                    calorie[sayac2][1] = (int)round;
                    calorie[sayac2][2] = 0;
                    calorie[sayac2][3] = 0;
                    calorie[sayac2][4] = 0;
                }
                else{
                    calorie[sayac2][0] = Integer.parseInt(a[0].substring(1,6));
                    calorie[sayac2][1] = (int)round;
                    calorie[sayac2][2] = 0;
                    calorie[sayac2][3] = 0;
                    calorie[sayac2][4] = 0;
                }
            }
            //If person is female.
            else if(a[2].equals("female")){
                double gecici;
                gecici = 665 + (9.6*Double.parseDouble(a[3])) + (1.7*Integer.parseInt(a[4])) - (4.7*(2018-Integer.parseInt(a[5])));
                long round = Math.round(gecici);
                calorie[sayac2][0] = Integer.parseInt(a[0]);
                calorie[sayac2][1] = (int)round;
                calorie[sayac2][2] = 0;
                calorie[sayac2][3] = 0;
                calorie[sayac2][4] = 0;
            }
            sayac2++;
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
