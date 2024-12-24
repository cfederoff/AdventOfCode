import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Puzzle2Day24 {
    public static void main(String[] args){
        HashMap<String,Integer> values = new HashMap<>();
        int instructionCount = 222;
        String[] Instructions = new String[instructionCount];
        String xBinary = "";
        String yBinary = "";
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day24/InputDay24.txt"));
            String line;
            while (!(line = file.readLine()).isEmpty()){
                values.put(line.substring(0,3), line.charAt(line.length()-1)-'0');
                if (line.charAt(0) == 'x'){
                    xBinary =  line.charAt(line.length()-1)-'0' + xBinary;
                }
                if (line.charAt(0) == 'y'){
                    yBinary = line.charAt(line.length()-1)-'0' + yBinary;
                }
            }
            int currentInstruction = 0;
            while ((line = file.readLine()) != null){
                Instructions[currentInstruction] = line;
                currentInstruction++;
            }
        } catch (IOException e){
            System.out.println("File Issue");
        }
        for (int i = 0; i < instructionCount; i++){
            runInstruction(Instructions[i], values, Instructions);
        }
        String binaryExpression = "";
        for (int i = 0; i < 99; i++) {
            String formatted = String.format("z%02d", i);
            if (values.containsKey(formatted)){
                binaryExpression = values.get(formatted) + binaryExpression;
            }
            else {
                break;
            }
        }
        System.out.println(Long.parseLong(xBinary,2) + Long.parseLong(yBinary,2));
        System.out.println(Long.parseLong(binaryExpression,2));
        String[] swaps = new String[]{"ncd","nfj","vkg","z37","cqr","z20","qnw","z15"};
        Arrays.sort(swaps);
        for (int i = 0; i < swaps.length; i++){
            System.out.print(swaps[i]);
            if (i + 1 < swaps.length){
                System.out.print(',');
            }
        }
    }
    public static void runInstruction(String instruction, HashMap<String, Integer> hashMap, String[] Instructions){
        String[] information = instruction.split(" ");
        if (!hashMap.containsKey(information[0])){
            for (int i = 0; i < Instructions.length; i++){
                String[] currentInstruction = Instructions[i].split(" ");
                if (currentInstruction[4].equals(information[0])){
                    runInstruction(Instructions[i], hashMap, Instructions);
                    break;
                }
            }
        }
        if (!hashMap.containsKey(information[2])){
            for (int i = 0; i < Instructions.length; i++){
                String[] currentInstruction = Instructions[i].split(" ");
                if (currentInstruction[4].equals(information[2])){
                    runInstruction(Instructions[i], hashMap, Instructions);
                    break;
                }
            }
        }
        if (information[1].equals("AND")){
            int result = hashMap.get(information[0]) & hashMap.get(information[2]);
            hashMap.put(information[4],result);
        }
        else if (information[1].equals("XOR")){
            int result = hashMap.get(information[0]) ^ hashMap.get(information[2]);
            hashMap.put(information[4],result);
        }
        else {
            int result = hashMap.get(information[0]) | hashMap.get(information[2]);
            hashMap.put(information[4],result);
        }
    }
}
