import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day8Puzzle1 {
    public static void main(String[] args) {
        int size = 50;
        ArrayList<signal> signals = new ArrayList<>();
        boolean[][] boolMap = new boolean[size][size];
        try {
            BufferedReader reader = new BufferedReader(new FileReader("2024/Day8/InputDay8.txt"));
            String line;
            int row = 0;
            while((line = reader.readLine()) != null){
                for (int i = 0; i < line.length(); i++){
                    if (line.charAt(i) != '.'){
                        signals.add(new signal(line.charAt(i), i, row));
                    }
                }
                row++;
            }
        } catch (Exception e){
            System.out.println("File Error");
        }
        int total = 0;
        for (int i = 0; i < signals.size(); i++){
            for (int j = 0; j < signals.size(); j++){
                if (i!=j){
                    if (signals.get(i).signal == signals.get(j).signal){
                        int x = 2*signals.get(j).x - signals.get(i).x;
                        int y = 2*signals.get(j).y - signals.get(i).y;
                        if ((x > -1) && (y > -1) && (x < size) && (y < size) && (!boolMap[y][x])){
                            boolMap[y][x] = true;
                            total++;
                        }
                    }
                }
            }
        }
        System.out.println(total);
    }
    public static class signal {
        char signal;
        int x;
        int y;
        public signal(char signal, int x, int y){
            this.signal = signal;
            this.x = x;
            this.y = y;
        }
    }
}
