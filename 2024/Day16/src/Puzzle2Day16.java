import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Puzzle2Day16 {
    static int size = 141;
    static int minimumScore = Integer.MAX_VALUE;
    static char[][] maze = new char[size][size];
    static int[][] mazeCounts = new int[size][size];
    static ArrayList<boolean[][]> completedPaths = new ArrayList<>();
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
        mazeCounts[startY][startX] = 0;
        dfs(startX,startY,0,'>',check);
        for (int i = 0; i < completedPaths.size(); i++){
            for (int j = 0; j < size; j++){
                for (int k = 0; k < size; k++){
                    if (completedPaths.get(i)[j][k]){
                        check[j][k] = true;
                    }
                }
            }
        }
        int total = 2;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (check[i][j]){
                    total++;
                }
            }
        }
        System.out.println(total);
    }
    public static void dfs(int x, int y, int score, char direction, boolean[][] visited ){
        if (maze[y][x] == 'E'){
            if (score < minimumScore){
                minimumScore = score;
                completedPaths.clear();
                completedPaths.add(visited);
            }
            if (score == minimumScore){
                completedPaths.add(visited);
            }
        }
        else if (score < minimumScore){
            visited[y][x] = true;
            boolean[][] check = new boolean[size][size];
            if (!visited[y-1][x] && maze[y-1][x] != '#'){
                for (int i = 0; i < size; i++){
                    for (int j = 0; j < size; j++){
                        check[i][j] = visited[i][j];
                    }
                }
                if (direction != '^'){
                    if (score+1001 <= mazeCounts[y-1][x]) {
                        mazeCounts[y][x] = score+1000;
                        dfs(x, y - 1, score + 1001, '^', check);
                    }
                }
                else {
                    mazeCounts[y][x] = score;
                    if (score+1 <= mazeCounts[y-1][x]) {
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
                    if (score+1001 <= mazeCounts[y][x+1]) {
                        mazeCounts[y][x] = score+1000;
                        dfs(x + 1, y, score + 1001, '>', check);
                    }
                }
                else {
                    if (score+1 <= mazeCounts[y][x+1]) {
                        mazeCounts[y][x] = score;
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
                    if (score+1001 <= mazeCounts[y+1][x]) {
                        mazeCounts[y][x] = score+1000;
                        dfs(x, y + 1, score + 1001, 'v', check);
                    }
                }
                else {
                    if (score+1 <= mazeCounts[y+1][x]) {
                        mazeCounts[y][x] = score;
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
                    if (score+1001 <= mazeCounts[y][x-1]) {
                        mazeCounts[y][x] = score+1000;
                        dfs(x-1,y,score+1001, '<',check);
                    }
                }
                else {
                    if (score+1 <= mazeCounts[y][x-1]) {
                        mazeCounts[y][x] = score;
                        dfs(x-1,y,score+1, '<',check);
                    }
                }
            }
        }
    }
}
