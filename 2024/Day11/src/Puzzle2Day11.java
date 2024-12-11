import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Puzzle2Day11 {
    static HashMap<String, Long> map = new HashMap<>();
    public static void main(String[] args){
        String stones = "";
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day11/InputDay11.txt"));
            stones = file.readLine();
        } catch (Exception e){
            System.out.println("File Error");
        }
        stones = stones + " ";
        int prevChar = 0;
        int blinks = 75;
        long count = 0;
        for (int i = 0; i < stones.length(); i++){
            if (stones.charAt(i) == ' '){
                count += StoneCount(stones.substring(prevChar, i), blinks);
                prevChar = i+1;
            }
        }
        System.out.println(count);
    }
    static long StoneCount(String c, long blinks){
        long count = 0;
        if (blinks == 0){
            map.put(c+","+0, (long) 1);
            return 1;
        }
        if (Long.parseLong(c) == 0){
            if (map.containsKey("0," + blinks)) {
                count += map.get("0," + blinks);
            }
            else {
                long amount = StoneCount("1",blinks-1);
                map.put("0," + blinks, amount);
                count += amount;
            }
        }
        else if (c.length() % 2 == 0){
            if (map.containsKey(c + "," + (blinks))){
                count += map.get(c+ "," + (blinks));
            }
            else {
                long number1 = Long.parseLong(c.substring(0,c.length()/2));
                long number2 = Long.parseLong(c.substring(c.length()/2));
                String string1 = String.valueOf(number1);
                String string2 = String.valueOf(number2);
                long result =  StoneCount(string1, blinks-1);
                result += StoneCount(string2, blinks-1);
                map.put(c+ "," + (blinks),result);
                count += result;
            }

        }
        else {
            if (map.containsKey(c+ "," + blinks)){
                count += map.get(c + "," + blinks);
            }
            else {
                long result = StoneCount(String.valueOf(Long.parseLong(c)*2024), blinks-1);
                map.put(c + "," + blinks, result);
                count += result;
            }
        }
        return count;
    }
}
