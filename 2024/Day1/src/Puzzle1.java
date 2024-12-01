import java.io.BufferedReader;
import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Puzzle1 {
    public static void main(String[] args) {
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day1/InputFile.txt"));
            Queue<Integer> pq1 = new PriorityQueue<>();
            Queue<Integer> pq2 = new PriorityQueue<>();
            String line = "";
            while ((line = file.readLine() ) != null){
                String[] results = line.split("   ");
                pq1.offer(Integer.parseInt(results[0]));
                pq2.offer(Integer.parseInt(results[1]));
            }
            int total = 0;
            while (!pq1.isEmpty()){
                total += Math.abs(pq1.poll()-pq2.poll());
            }
            System.out.println(total);
        } catch (Exception e){
            System.out.println("File not found");
        }
    }
}