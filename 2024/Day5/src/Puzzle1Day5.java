import java.io.BufferedReader;
import java.io.FileReader;

public class Puzzle1Day5 {
    public static void main(String args[]){
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day5/InputDay5.txt"));
            int sizeOfInputs = 1176;
            int[][] numbers = new int[sizeOfInputs][2];
            String line;
            int size = 0;
            int total = 0;
            while (!(line = file.readLine()).isEmpty()){
                String[] information = line.split("\\|");
                numbers[size][0] = Integer.parseInt(information[0]);
                numbers[size][1] = Integer.parseInt(information[1]);
                size++;
            }
            while ((line = file.readLine()) != null){
                String[] information = line.split(",");
                int[] numbersToCheck = new int[information.length];
                for (int i = 0; i < information.length; i++){
                    numbersToCheck[i] = Integer.parseInt(information[i]);
                }
                int[][] numbersCopy = new int[sizeOfInputs][2];
                size = 0;
                for (int i = 0; i < sizeOfInputs; i++){
                    boolean firstNumberFound = false;
                    boolean secondNumberFound = false;
                    for (int j = 0; j < numbersToCheck.length; j++){
                        if (numbers[i][0] == numbersToCheck[j]){
                            firstNumberFound = true;
                            if (secondNumberFound){
                                break;
                            }
                        }
                        if (numbers[i][1] == numbersToCheck[j]){
                            secondNumberFound = true;
                            if (firstNumberFound){
                                break;
                            }
                        }
                    }
                    if (firstNumberFound && secondNumberFound){
                        numbersCopy[size][0] = numbers[i][0];
                        numbersCopy[size][1] = numbers[i][1];
                        size++;
                    }
                }
                boolean orderingCorrect = true;
                for (int i = 0; i < numbersToCheck.length; i++){
                    int currentNumber = numbersToCheck[i];
                    for (int j = 0; j < size; j++){
                        if (numbersCopy[j][1] == currentNumber){
                            orderingCorrect = false;
                            break;
                        }
                    }
                    if (!orderingCorrect){
                        break;
                    } else {
                        int j = 0;
                        while (j < size){
                            if (numbersCopy[j][0] == currentNumber || numbersCopy[j][1] == currentNumber){
                                numbersCopy[j][0] = numbersCopy[size-1][0];
                                numbersCopy[j][1] = numbersCopy[size-1][1];
                                numbersCopy[size-1][0] = 0;
                                numbersCopy[size-1][1] = 0;
                                size--;
                            }
                            else {
                                j++;
                            }
                        }
                    }
                }
                if (orderingCorrect){
                    total += numbersToCheck[numbersToCheck.length/2];
                }
            }
            System.out.println(total);
        } catch (Exception e){
            System.out.println("File error");
        }

    }
}
