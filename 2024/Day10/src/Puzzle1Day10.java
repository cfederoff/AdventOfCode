import java.io.BufferedReader;
import java.io.FileReader;

public class Puzzle1Day10 {
    static final int size = 55;
    public static void main(String[] args){
        int[][] numbers = new int[size][size];
        int[][] nines = new int[300][2];
        int nineCount = 0;
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day10/InputDay10.txt"));
            String line;
            int row = 0;
            while ((line = file.readLine()) != null){
                for (int i = 0; i < line.length(); i++){
                    if (line.charAt(i) == '.'){
                        numbers[row][i] = -1;
                    }
                    else if (line.charAt(i) - '0' == 9){
                        nines[nineCount][0] = row;
                        nines[nineCount][1] = i;
                        numbers[row][i] = 9;
                        nineCount++;
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
                    for (int k = 0; k < nineCount; k++){
                        numbers[nines[k][0]][nines[k][1]] = 9;
                    }
                }
            }
        }
        System.out.println(total);
    }
    static int dfs(int[][] map, int y, int x, int count){
        int paths = 0;
        if (map[y][x] == 9){
            map[y][x] = -1;
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
