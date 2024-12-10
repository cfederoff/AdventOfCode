import java.io.BufferedReader;
import java.io.FileReader;

public class Puzzle2Day10 {
    static final int size = 55;
    public static void main(String[] args){
        int[][] numbers = new int[size][size];
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day10/InputDay10.txt"));
            String line;
            int row = 0;
            while ((line = file.readLine()) != null){
                for (int i = 0; i < line.length(); i++){
                    if (line.charAt(i) == '.'){
                        numbers[row][i] = -1;
                    }
                    else {
                        numbers[row][i] = line.charAt(i) - '0';
                    }
                }
                row++;
            }
        } catch (Exception e){
            System.out.println("File Error");
        }
        int total = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (numbers[i][j] == 0){
                    total += dfs(numbers,i,j,0);
                }
            }
        }
        System.out.println(total);
    }
    static int dfs(int[][] map, int y, int x, int count){
        int size = 55;
        int paths = 0;
        if (map[y][x] == 9){
            return 1;
        }
        if (y-1 > -1){
            if (map[y-1][x] == count+1){
                paths += dfs(map, y-1, x, count+1);
            }
        }
        if (x-1 > -1){
            if (map[y][x-1] == count+1){
                paths += dfs(map, y, x-1, count+1);
            }
        }
        if (y+1 < size){
            if (map[y+1][x] == count+1){
                paths += dfs(map, y+1, x, count+1);
            }
        }
        if (x+1 < size){
            if (map[y][x+1] == count+1){
                paths += dfs(map, y, x+1, count+1);
            }
        }
        return paths;
    }
}
