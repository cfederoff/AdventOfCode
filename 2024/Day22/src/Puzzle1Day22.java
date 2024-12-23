import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle1Day22 {
    public static void main(String[] args){
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day22/InputDay22.txt"));
            String line;
            long total = 0;
            while ((line = file.readLine()) != null){
                long num = Integer.parseInt(line);
                for (int i = 0; i < 2000; i++) {
                    num = num ^ (num * 64) % 16777216;
                    num = num ^ (num / 32) % 16777216;
                    num = num ^ (num * 2048) % 16777216;
                }
                total += num;
            }
            System.out.println(total);
        } catch (IOException e){
            System.out.println("File Issue");
        }
    }
}
