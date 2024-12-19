import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Puzzle1Day19 {
    public static void main(String[] args){
        int designNumber = 417;
        String[] designs = new String[designNumber];
        ArrayList<String> patterns = new ArrayList<>();
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day19/InputDay19.txt"));
            designs = file.readLine().split(", ");
            file.readLine();
            String line;
            while((line= file.readLine()) != null){
                patterns.add(line);
            }
        } catch (IOException e){
            System.out.println("File Error");
        }
        for (int i = 0; i < designs.length; i++){
            for (int j = 0; j < designs.length-i-1; j++){
                if (designs[j].length() < designs[j+1].length()){
                    String hold = designs[j];
                    designs[j] = designs[j+1];
                    designs[j+1] = hold;
                }
            }
        }

        int count = 0;
        for (String pattern : patterns) {
            if (patternsCheck(pattern, designs)) {
                count++;
            }
        }
        System.out.println(count);
    }
    public static boolean patternsCheck(String pattern, String[] designs){
        if (pattern.isEmpty()){
            return true;
        }
        for (String design : designs) {
            if (design.length() <= pattern.length() && pattern.startsWith(design)) {
                if (patternsCheck(pattern.substring(design.length()), designs)) {
                    return true;
                }
            }
        }
        return false;
    }
}
