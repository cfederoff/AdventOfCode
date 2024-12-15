import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Puzzle2Day12 {
    static ArrayList<spaces> list = new ArrayList<>();
    static int size = 140;
    public static void main(String[] args){

        char[][] map = new char[size][size];
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day12/InputDay12.txt"));
            String line;
            int row = 0;
            while ((line = file.readLine()) != null){
                for (int i = 0; i <line.length(); i++){
                    map[row][i] = line.charAt(i);
                }
                row++;
            }
        } catch (Exception e){
            System.out.println("File Issue");
        }
        boolean[][] mapCheck = new boolean[size][size];
        int total = 0;
        for (int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if (!mapCheck[i][j]){
                    char currentCrop = map[i][j];
                    list.clear();
                    dfs(currentCrop,map,j,i,mapCheck);
                    int maxX = 0;
                    int maxY = 0;
                    for (int k = 0; k < list.size(); k++){
                        if (list.get(k).x > maxX){
                            maxX = list.get(k).x;
                        }
                        if (list.get(k).y > maxY){
                            maxY = list.get(k).y;
                        }
                    }
                    int edges = 0;
                    if (list.size() == 1){
                        edges = 4;
                    }
                    else if (list.size() == 2){
                        edges = 4;
                    }
                    else {
                        char[][] plotMap = new char[maxY + 3][maxX + 3];
                        for (int k = 0; k < list.size(); k++) {
                            plotMap[list.get(k).y + 1][list.get(k).x + 1] = 'X';
                        }
                        for (int k = 0; k < maxY + 2; k++) {
                            for (int l = 0; l < maxX + 2; l++) {
                                if (plotMap[k][l] == 'X') {
                                    if (plotMap[k - 1][l] == '\u0000' && plotMap[k][l - 1] == '\u0000') {
                                        edges++;
                                    } else if (plotMap[k - 1][l] == 'X' && plotMap[k][l - 1] == 'X' && plotMap[k - 1][l - 1] == '\u0000') {
                                        edges++;
                                    }
                                    if (plotMap[k - 1][l] == '\u0000' && plotMap[k][l + 1] == '\u0000') {
                                        edges++;
                                    } else if (plotMap[k - 1][l] == 'X' && plotMap[k][l + 1] == 'X' && plotMap[k - 1][l + 1] == '\u0000') {
                                        edges++;
                                    }
                                    if (plotMap[k + 1][l] == '\u0000' && plotMap[k][l + 1] == '\u0000') {
                                        edges++;
                                    } else if (plotMap[k + 1][l] == 'X' && plotMap[k][l + 1] == 'X' && plotMap[k + 1][l + 1] == '\u0000') {
                                        edges++;
                                    }
                                    if (plotMap[k + 1][l] == '\u0000' && plotMap[k][l - 1] == '\u0000') {
                                        edges++;
                                    } else if (plotMap[k + 1][l] == 'X' && plotMap[k][l - 1] == 'X' && plotMap[k + 1][l - 1] == '\u0000') {
                                        edges++;
                                    }
                                }
                            }
                        }
                    }
                    total += list.size()*edges;
                }
            }
        }
        System.out.println(total);
    }
    public static void dfs(char crop, char[][] map, int x, int y, boolean[][] boolMap){
        list.add(new spaces(x,y));
        boolMap[y][x] = true;
        if (x-1 > -1 && map[y][x-1] == crop && !boolMap[y][x-1]){
            dfs(crop,map,x-1,y,boolMap);
        }
        if (y-1 > -1 && map[y-1][x] == crop && !boolMap[y-1][x]){
            dfs(crop,map,x,y-1,boolMap);
        }
        if (x+1 < size && map[y][x+1] == crop && !boolMap[y][x+1]){
            dfs(crop,map,x+1,y,boolMap);
        }
        if (y+1 < size && map[y+1][x] == crop && !boolMap[y+1][x]){
            dfs(crop,map,x,y+1,boolMap);
        }
    }
    public static class spaces {
        int x;
        int y;
        public spaces(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
