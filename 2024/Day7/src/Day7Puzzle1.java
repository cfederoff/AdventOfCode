import java.io.BufferedReader;
import java.io.FileReader;

public class Day7Puzzle1 {
    public static void main(String[] args){
        long total = 0;
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day7/Day7Input.txt"));
            String line;
            while ((line = file.readLine()) != null){
                String[] information = line.split(" ");
                long numberTotal = Long.parseLong(information[0].substring(0, information[0].length()-1));
                int[] numbers = new int[information.length-1];
                for (int i = 1; i < information.length; i++){
                    numbers[i-1] = Integer.parseInt(information[i]);
                }
                for (int i = 0; i < Math.pow(2,numbers.length-1); i++){
                    int length = i;
                    String binary = "";
                    for(int j = 0; j < numbers.length-1; j++){
                        if (length%2 == 0){
                            binary = '0' + binary;
                        }
                        else {
                            binary = '1' + binary;
                        }
                        length /= 2;
                    }
                    long runningTotal = 0;
                    int operations = 0;
                    for (int j = 0; j < numbers.length; j++){
                        if (binary.charAt(operations) == '0'){
                            operations++;
                            if (j == 0){
                                runningTotal += numbers[j] + numbers[j+1];
                                j++;
                            }
                            else {
                                runningTotal += numbers[j];
                            }
                        }
                        else {
                            operations++;
                            if (j == 0){
                                runningTotal += (long) numbers[j] * (long) numbers[j+1];
                                j++;
                            }
                            else {
                                runningTotal *= (long) numbers[j];
                            }
                        }
                    }
                    if (runningTotal == numberTotal){
                        total += numberTotal;
                        break;
                    }
                }
            }
        } catch (Exception e){
            System.out.println("File Issue");
        }
        System.out.println(total);
    }
}
