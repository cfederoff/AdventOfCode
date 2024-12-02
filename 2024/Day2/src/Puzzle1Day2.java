import java.io.BufferedReader;
import java.io.FileReader;

public class Puzzle1Day2 {
    public static void main(String[] args) {
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day2/InputFile1.txt"));
            String line = "";
            int safeData = 0;
            while ((line = file.readLine()) != null){
                String[] input = line.split(" ");
                boolean increasing = true;
                int firstNumber = Integer.parseInt(input[0]);
                boolean safe = true;
                for (int i = 1; i < input.length; i++){
                    int secondNumber = Integer.parseInt(input[i]);
                    if (firstNumber == secondNumber){
                        safe = false;
                        break;
                    }
                    if (Math.abs(firstNumber-secondNumber) > 3) {
                        safe = false;
                        break;
                    }
                    if (i == 1){
                        if (firstNumber-secondNumber > 0){
                            increasing = false;
                        }
                    }
                    else {
                        if (increasing){
                            if (firstNumber-secondNumber > 0){
                                safe = false;
                                break;
                            }
                        }
                        else {
                            if (firstNumber-secondNumber < 0){
                                safe = false;
                                break;
                            }
                        }
                    }
                    firstNumber = secondNumber;
                }
                if (safe){
                    safeData++;
                }
            }
            System.out.println(safeData);
        } catch (Exception e){
            System.out.println("File not found");
        }
    }
}