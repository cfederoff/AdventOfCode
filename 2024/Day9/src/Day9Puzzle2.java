import java.io.BufferedReader;
import java.io.FileReader;

public class Day9Puzzle2 {
    public static void main (String[] args){
        String input = "";
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day9/InputDay9.txt"));
            input = file.readLine();
        } catch (Exception e){
            System.out.println("File error");
        }
        String[] id = new String[100000];
        int count = 0;
        for (int i = 0; i < input.length();i++){
            if (i % 2 == 0){
                int letters = input.charAt(i) - '0';
                for (int j = 0; j < letters; j++){
                    id[count] = String.valueOf(i / 2);
                    count++;
                }
            }
            else {
                int blanks = input.charAt(i) - '0';
                for (int j = 0; j < blanks; j++){
                    id[count] = ".";
                    count++;
                }
            }
        }
        int start = 0;
        int end = count-1;
        while(!(id[start].equals("."))){
            start++;
        }
        while (start < end){
            String hold;
            while(id[end].equals(".")){
                end--;
            }
            hold = id[end];
            int length = 0;
            while (id[end].equals(hold)){
                end--;
                length++;
                if (end == -1){
                    break;
                }
            }
            int startingPoint = start;
            while (startingPoint < end){
                int blankLength = 0;
                while (id[startingPoint].equals(".")){
                    startingPoint++;
                    blankLength++;
                }
                if (blankLength >= length){
                    startingPoint -= blankLength;
                    for (int i = 0; i < length; i++){
                        id[startingPoint+i] = id[end+1];
                    }
                    for (int i = 0; i < length; i++){
                        id[end+1+i] = ".";
                    }
                    break;
                }
                startingPoint++;
            }

        }
        long total = 0;
        for (int i = 0; i < count; i++){
            if (!id[i].equals(".")){
                long value = Integer.parseInt(id[i]);
                total += value * i;
            }
        }
        System.out.println(total);
    }
}
