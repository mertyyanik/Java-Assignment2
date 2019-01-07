import java.io.*;
import java.util.Scanner;

public class Commands {
    Sport sport = new Sport();
    Food food = new Food();
    Person person = new Person();
    public String[][] commands = new String[1000][100];
    public int sayac = 0;
    //Reading text.
    public void readText(String args){
        try(Scanner sc = new Scanner(new BufferedReader(new FileReader(args)))){
            while(sc.hasNextLine()){
                String bilgiler = sc.nextLine().trim();
                commands[sayac] = bilgiler.split("\t");
                commands[sayac][0] = removeUTF8BOM(commands[sayac][0]);
                sayac++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void command() {
        int sayac = 0;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File("monitoring.txt")))) {
            sport.readText();
            food.readText();
            person.readText();
            for (String[] command : commands) {
                if (command[0] == null || command[0].isEmpty()) {
                    break;
                }
                //If command's first character is '1'
                if ((command[0].split("")[0].equals("1")) || (command[0].split("").length == 6 && command[0].split("")[1].equals("1"))) {
                    if (command[1].split("")[0].equals("1")) {
                        for (String[] food : food.foodArray) {
                            if (food[0] == null) {
                                break;
                            }
                            if (food[0].equals(command[1])) {
                                writer.write(command[0] + " has taken   " + (Integer.parseInt(command[2]) * Integer.parseInt(food[2])) + "kcal  from    " + food[1] + "\n");
                                if(commands[sayac+1][0] != null){
                                    writer.write("***************\n");
                                }
                                for (int[] calories : person.calorie) {
                                    if (calories[0] == -1) {
                                        break;
                                    }
                                    if (calories[0] == Integer.parseInt(command[0])) {
                                        calories[2] += Integer.parseInt(command[2]) * Integer.parseInt(food[2]);
                                        calories[4] = -(calories[1] - calories[2] + calories[3]);
                                    }
                                }
                            }
                        }
                    //If the command first character is '2'
                    } else if (command[1].split("")[0].equals("2")) {
                        for (String[] sport : sport.sportArray) {
                            if (sport[0] == null) {
                                break;
                            }
                            if (sport[0].equals(command[1])) {
                                int gecici = (Integer.parseInt(sport[2]) * Integer.parseInt(command[2]) / 60);
                                writer.write(command[0] + " has burned  " + gecici + "kcal  thanks  to  " + sport[1]+"\n");
                                if(commands[sayac+1][0] != null){
                                    writer.write("***************\n");
                                }
                                for (int[] calories : person.calorie) {
                                    if (calories[0] == -1) {
                                        break;
                                    }
                                    if (calories[0] == Integer.parseInt(command[0])) {
                                        calories[3] += gecici;
                                        calories[4] = -(calories[1] - calories[2] + calories[3]);
                                    }
                                }
                            }
                        }
                    }
                //If the command is directly equals to 'printList'
                } else if (command[0].equals("printList")) {
                    for (int[] calori : person.calorie) {
                        if (calori[0] == -1) {
                            break;
                        }
                        if (calori[2] != 0 || calori[3] != 0) {
                            for (String[] isim : person.personArray) {
                                if (isim[0] == null) {
                                    break;
                                }
                                if (String.valueOf(calori[0]).equals(isim[0])) {
                                    writer.write(isim[1] + "  " + (2018 - Integer.parseInt(isim[5])) + "    " + calori[1] + "kcal  " +
                                            calori[2] + "kcal   " + calori[3] + "kcal   " + calori[4] + "kcal"+"\n");
                                }
                            }
                        }
                    }
                    if(commands[sayac+1][0] != null){
                        writer.write("***************\n");
                    }
                //If the command is 'print(.....)'
                } else {
                    String sayi = command[0].substring(6, 11).trim();
                    for (int[] calori : person.calorie) {
                        if (calori[0] == Integer.parseInt(sayi)) {
                            if (calori[0] == -1) {
                                break;
                            }
                            for (String[] isim : person.personArray) {
                                if (isim[0] == null) {
                                    break;
                                }
                                if (sayi.equals(isim[0])) {
                                    writer.write(isim[1] + "  " + (2018 - Integer.parseInt(isim[5])) + "    " + calori[1] + "kcal  " +
                                            calori[2] + "kcal   " + calori[3] + "kcal   " + calori[4] + "kcal"+"\n");
                                    if(commands[sayac+1][0] != null){
                                        writer.write("***************\n");
                                    }
                                }
                            }
                        }
                    }
                }
               sayac++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static final String UTF8_BOM = "\uFEFF";
    private static String removeUTF8BOM (String strng){
        if (strng.startsWith(UTF8_BOM)) {
            strng = strng.substring(1);
        }
        return strng;
    }
}
