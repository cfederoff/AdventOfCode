import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Puzzle2Day19 {
    public static void main(String[] args){
        int designNumber = 447;
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

        long count = 0;
        for (String pattern : patterns) {
            count += patternsCount(pattern, designs);
        }
        System.out.println(count);
    }
    static HashMap<String,Long> patternCount = new HashMap<>();
    public static long patternsCount(String pattern, String[] designs){
        if (pattern.isEmpty()){
            return 1;
        }
        long count = 0;
        for (String design : designs) {
            if (design.length() <= pattern.length() && pattern.startsWith(design)) {
                if (patternCount.containsKey(pattern.substring(design.length()))) {
                    count += patternCount.get(pattern.substring(design.length()));
                } else {
                    long amount = patternsCount(pattern.substring(design.length()), designs);
                    patternCount.put(pattern.substring(design.length()), amount);
                    count += amount;
                }
            }
        }
        return count;
    }
}
