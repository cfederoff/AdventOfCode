import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Puzzle1Day25 {
    public static void main(String[] args){
        ArrayList<heights> keys = new ArrayList<>();
        ArrayList<heights> locks = new ArrayList<>();
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day25/InputDay25.txt"));
            String line = "";
            while((line = file.readLine()) != null){
                char[][] heights = new char[7][5];
                int row = 0;
                while (line != null && !line.isEmpty()){
                    for (int i = 0; i < line.length(); i++){
                        heights[row][i] = line.charAt(i);
                    }
                    row++;
                    line = file.readLine();
                }
                ArrayList<Integer> heightAmount = new ArrayList<>();
                for (int i = 0; i < 5; i++){
                    int count = 0;
                    for (int j = 1; j < 6; j++){
                        if (heights[j][i] == '#'){
                            count++;
                        }
                    }
                    heightAmount.add(count);
                }
                if (heights[0][0] == '.'){
                    keys.add(new heights(heightAmount.get(0),heightAmount.get(1),heightAmount.get(2),
                            heightAmount.get(3),heightAmount.get(4)));
                }
                else {
                    locks.add(new heights(heightAmount.get(0),heightAmount.get(1),heightAmount.get(2),
                            heightAmount.get(3),heightAmount.get(4)));
                }
            }
        } catch (IOException e){
            System.out.println("File Error");
        }
        int total = 0;
        for (int i = 0; i < locks.size(); i++){
            heights lock = locks.get(i);
            for (int j = 0; j < keys.size(); j++){
                heights key = keys.get(j);
                if (lock.firstHeight+ key.firstHeight <= 5 && lock.secondHeight+ key.secondHeight <= 5 &&
                        lock.thirdHeight+ key.thirdHeight <= 5 && lock.fourthHeight+ key.fourthHeight <= 5 &&
                        lock.fifthHeight+ key.fifthHeight <= 5){
                    total++;
                }
            }
        }
        System.out.println(total);
    }
    public static class heights {
        int firstHeight;
        int secondHeight;
        int thirdHeight;
        int fourthHeight;
        int fifthHeight;
        public heights(int firstHeight, int secondHeight, int thirdHeight, int fourthHeight, int fifthHeight){
            this.firstHeight = firstHeight;
            this.secondHeight = secondHeight;
            this.thirdHeight = thirdHeight;
            this.fourthHeight = fourthHeight;
            this.fifthHeight = fifthHeight;

        }
    }
}
