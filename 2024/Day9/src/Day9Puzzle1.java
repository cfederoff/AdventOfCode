import java.io.BufferedReader;
import java.io.FileReader;

public class Day9Puzzle1 {
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
        while (start != end){
            if (!id[start].equals(".")){
                start++;
                continue;
            }
            if (id[end].equals(".")){
                end--;
                continue;
            }
            String hold = id[start];
            id[start] = id[end];
            id[end] = hold;
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
