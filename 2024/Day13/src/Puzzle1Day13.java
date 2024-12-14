import java.io.BufferedReader;
import java.io.FileReader;

public class Puzzle1Day13 {
    public static void main(String[] args){
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day13/InputDay13.txt"));
            String line;
            int[][] matrix = {{0,0},{0,0}};
            int[] solution = {0,0};
            int total = 0;
            while ((line = file.readLine()) != null){
                matrix[0][0] = Integer.parseInt(line.split(" |: |, ")[2].substring(2));
                matrix[1][0] = Integer.parseInt(line.split(" |: |, ")[3].substring(2));
                line = file.readLine();
                matrix[0][1] = Integer.parseInt(line.split(" |: |, ")[2].substring(2));
                matrix[1][1] = Integer.parseInt(line.split(" |: |, ")[3].substring(2));
                line = file.readLine();
                solution[0] = Integer.parseInt(line.split(" |: |, ")[1].substring(2));
                solution[1] = Integer.parseInt(line.split(" |: |, ")[2].substring(2));
                line = file.readLine();
                int detA = matrix[0][0] *matrix[1][1] - matrix[0][1] *matrix[1][0];
                int yArea =  matrix[0][0] *solution[1] -  solution[0] *matrix[1][0];
                int xArea =  (solution[0]) *matrix[1][1] -  solution[1] *matrix[0][1];
                float xfloat = (float) xArea/detA;
                float yfloat = (float) yArea/detA;
                if ((((int) xfloat) == xfloat) && ((int) xfloat) == xfloat){
                    int x = (int) xfloat;
                    int y = (int) yfloat;
                    total += 3*x + y;
                }
            }
            System.out.println(total);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
