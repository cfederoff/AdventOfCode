import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Puzzle2 {
    public static void main(String[] args) {
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day1/InputFile.txt"));
            ArrayList<Integer> a1 = new ArrayList<>();
            HashMap<Integer, Integer> map = new HashMap<>();
            String line = "";
            while ((line = file.readLine() ) != null) {
                String[] results = line.split("   ");
                a1.add(Integer.parseInt(results[0]));
                int number2 = Integer.parseInt(results[1]);
                if (!map.containsKey(number2)){
                    map.put(number2, 1);
                }
                else {
                    map.put(number2,map.get(number2)+1);
                }
            }

            int total = 0;
            for (int i = 0; i < a1.size(); i++){
                if (map.containsKey(a1.get(i))){
                    total += a1.get(i)*map.get(a1.get(i));
                }
            }
            System.out.println(total);
        } catch (Exception e){
            System.out.println("File not found");
        }
    }
}
