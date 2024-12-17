import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Puzzle1Day16 {
    static int size = 141;
    static int minimumScore = Integer.MAX_VALUE;
    static char[][] maze = new char[size][size];
    static int[][] mazeCounts = new int[size][size];
    public static void main(String[] args){
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                mazeCounts[i][j] = Integer.MAX_VALUE;
            }
        }
        boolean[][] check = new boolean[size][size];
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day16/InputDay16.txt"));
            String line;
            int row = 0;
            while ((line = file.readLine()) != null){
                for (int i = 0; i < line.length(); i++){
                    maze[row][i] = line.charAt(i);
                    if (line.charAt(i) == 'S'){
                        startX = i;
                        startY = row;
                    }
                }
                row++;
            }
        } catch (Exception e){
            System.out.println(e);
        }
        dfs(startX,startY,0,'>',check);
        System.out.println(minimumScore);
    }
    public static void dfs(int x, int y, int score, char direction, boolean[][] visited ){
        if (maze[y][x] == 'E'){
            if (score < minimumScore){
                minimumScore = score;

            }
        }
        else if (score < minimumScore){
            visited[y][x] = true;
            mazeCounts[y][x] = score;
            boolean[][] check = new boolean[size][size];
            if (!visited[y-1][x] && maze[y-1][x] != '#'){
                for (int i = 0; i < size; i++){
                    for (int j = 0; j < size; j++){
                        check[i][j] = visited[i][j];
                    }
                }
                if (direction != '^'){
                    if (score+1001 < mazeCounts[y-1][x]) {
                        dfs(x, y - 1, score + 1001, '^', check);
                    }
                }
                else {
                    if (score+1 < mazeCounts[y-1][x]) {
                        dfs(x, y - 1, score + 1, '^', check);
                    }
                }
            }
            if (!visited[y][x+1] && maze[y][x+1] != '#'){
                for (int i = 0; i < size; i++){
                    for (int j = 0; j < size; j++){
                        check[i][j] = visited[i][j];
                    }
                }
                if (direction != '>'){
                    if (score+1001 < mazeCounts[y][x+1]) {
                        dfs(x + 1, y, score + 1001, '>', check);
                    }
                }
                else {
                    if (score+1 < mazeCounts[y][x+1]) {
                        dfs(x + 1, y, score + 1, '>', check);
                    }
                }
            }
            if (!visited[y+1][x] && maze[y+1][x] != '#'){
                for (int i = 0; i < size; i++){
                    for (int j = 0; j < size; j++){
                        check[i][j] = visited[i][j];
                    }
                }
                if (direction != 'v'){
                    if (score+1001 < mazeCounts[y+1][x]) {
                        dfs(x, y + 1, score + 1001, 'v', check);
                    }
                }
                else {
                    if (score+1 < mazeCounts[y+1][x]) {
                        dfs(x,y+1,score+1, 'v',check);
                    }
                }
            }
            if (!visited[y][x-1] && maze[y][x-1] != '#'){
                for (int i = 0; i < size; i++){
                    for (int j = 0; j < size; j++){
                        check[i][j] = visited[i][j];
                    }
                }
                if (direction != '<'){
                    if (score+1001 < mazeCounts[y][x-1]) {
                        dfs(x-1,y,score+1001, '<',check);
                    }
                }
                else {
                    if (score+1 < mazeCounts[y][x-1]) {
                        dfs(x-1,y,score+1, '<',check);
                    }
                }
            }
        }
    }
}
