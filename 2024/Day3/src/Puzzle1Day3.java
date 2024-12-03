import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle1Day3 {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("2024/Day3/InputDay3.txt"));
            String line = "";
            int total = 0;
            while ((line = reader.readLine()) != null){
                String regex = "mul\\(\\d+,\\d+\\)";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);
                List<String> matches = new ArrayList<>();
                while (matcher.find()){
                    matches.add(matcher.group());
                }
                for (int i = 0; i < matches.size(); i++){
                    List<String> finalMatches = new ArrayList<>();
                    regex = "\\d+";
                    pattern = Pattern.compile(regex);
                    matcher = pattern.matcher(matches.get(i));
                    while (matcher.find()){
                        finalMatches.add(matcher.group());
                    }
                    total += Integer.parseInt(finalMatches.get(0)) * Integer.parseInt(finalMatches.get(1));
                }
            }
            System.out.println(total);
        }catch (Exception e){
            System.out.println("Cant find file");
        }
    }
}