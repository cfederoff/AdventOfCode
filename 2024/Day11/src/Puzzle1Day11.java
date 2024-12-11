import java.io.BufferedReader;
import java.io.FileReader;

public class Puzzle1Day11 {
    public static void main(String[] args){
        String stones = "";
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day11/InputDay11.txt"));
            stones = file.readLine();
        } catch (Exception e){
            System.out.println("File Error");
        }
        for (int blinks = 0; blinks < 25; blinks++) {
            String newStones = "";
            int length = stones.length();
            for (int i = 0; i < length; i++) {
                if (stones.charAt(i) == ' ') {
                    continue;
                }
                int j = i;
                while (j < stones.length() && stones.charAt(j) != ' ') {
                    j++;
                }
                long number = Long.parseLong(stones.substring(i, j));
                if (number == 0) {
                    newStones = newStones + "1 ";
                } else {
                    if (String.valueOf(number).length() % 2 == 0) {
                        String numberConvert = String.valueOf(number);
                        long numberOne = Integer.parseInt(numberConvert.substring(0, numberConvert.length() / 2));
                        long numberTwo = Integer.parseInt(numberConvert.substring(numberConvert.length() / 2));
                        newStones = newStones + numberOne + " " + numberTwo + " ";
                    } else {
                        number *= 2024;
                        String newNumber = String.valueOf(number);
                        newStones = newStones + newNumber + " ";

                    }
                }
                i = j;
            }
            stones = newStones;
        }
        int count = 0;
        for (int i = 0; i < stones.length(); i++){
            if (stones.charAt(i) == ' '){
                count++;
            }
        }
        System.out.println(count);
    }
}
