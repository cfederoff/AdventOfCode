import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Puzzle1Day12 {
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
        HashMap<String, Integer> totals = new HashMap<>();
        for (int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if (!mapCheck[i][j]){
                    char currentCrop = map[i][j];
                    list.clear();
                    dfs(currentCrop,map,j,i,mapCheck);
                    int perimeter = 0;
                    if (list.size() == 1){
                        perimeter = 4;
                    }
                    else if (list.size() == 2){
                        perimeter = 6;
                    }
                    else {
                        for (int x = 0; x < list.size(); x++){
                            int cropPerimeter = 4;
                            int cropX = list.get(x).x;
                            int cropY = list.get(x).y;
                            for (int y = 0; y < list.size(); y++){
                                if (x!=y){
                                    if (cropX-1 == list.get(y).x && cropY == list.get(y).y){
                                        cropPerimeter--;
                                    }
                                    if (cropX == list.get(y).x && cropY-1 == list.get(y).y){
                                        cropPerimeter--;
                                    }
                                    if (cropX+1 == list.get(y).x && cropY == list.get(y).y){
                                        cropPerimeter--;
                                    }
                                    if (cropX == list.get(y).x && cropY-1 == list.get(y).y){
                                        cropPerimeter--;
                                    }
                                }
                            }
                            perimeter += cropPerimeter;
                        }
                    }
                    if (totals.containsKey(String.valueOf(currentCrop))){
                        totals.put(String.valueOf(currentCrop),
                                totals.get(String.valueOf(currentCrop))+perimeter*list.size());
                    }
                    else {
                        totals.put(String.valueOf(currentCrop), perimeter*list.size());
                    }
                }
            }
        }
        int total = 0;
        for (int cropTotal: totals.values()){
            total += cropTotal;
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
