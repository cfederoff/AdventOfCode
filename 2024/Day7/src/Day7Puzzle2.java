import java.io.BufferedReader;
import java.io.FileReader;

public class Day7Puzzle2 {
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
                for (int i = 0; i < Math.pow(3,numbers.length-1); i++){
                    int length = i;
                    String base3 = "";
                    for(int j = 0; j < numbers.length-1; j++){
                        if (length%3 == 0){
                            base3 = '0' + base3;
                        }
                        else if (length%3 == 1){
                            base3 = '1' + base3;
                        }
                        else {
                            base3 = '2' + base3;
                        }
                        length /= 3;
                    }
                    long runningTotal = 0;
                    int operations = 0;
                    for (int j = 0; j < numbers.length; j++){
                        if (base3.charAt(operations) == '0'){
                            operations++;
                            if (j == 0){
                                runningTotal += numbers[j] + numbers[j+1];
                                j++;
                            }
                            else {
                                runningTotal += numbers[j];
                            }
                        }
                        else if(base3.charAt(operations) == '1'){
                            operations++;
                            if (j == 0){
                                runningTotal += (long) numbers[j] * (long) numbers[j+1];
                                j++;
                            }
                            else {
                                runningTotal *= (long) numbers[j];
                            }
                        }
                        else {
                            operations++;
                            if (j == 0){
                                String value = Long.toString(numbers[j]) + Long.toString(numbers[j+1]);
                                runningTotal = Long.parseLong(value);
                                j++;
                            }
                            else {
                                String value = Long.toString(runningTotal) + Long.toString(numbers[j]);
                                runningTotal = Long.parseLong(value);
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
