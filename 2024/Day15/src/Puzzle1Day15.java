import java.io.BufferedReader;
import java.io.FileReader;

public class Puzzle1Day15 {
    static int robotX = 0;
    static int robotY = 0;
    public static void main(String[] args){
        int size = 50;
        char[][] map = new char[size][size];
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day15/InputDay15.txt"));
            String line;
            int row = 0;
            for (int i = 0; i < size; i++){
                line = file.readLine();
                for (int j = 0; j < size; j++){
                    map[i][j] = line.charAt(j);
                    if (line.charAt(j) == '@'){
                        robotX = j;
                        robotY = i;
                    }
                }
                row++;
            }
            while ((line = file.readLine()) != null){
                for (int i = 0; i < line.length(); i++){
                    switch (line.charAt(i)){
                        case '^':
                            move(map,robotX,robotY,1);
                            break;
                        case '>':
                            move(map,robotX,robotY,2);
                            break;
                        case 'v':
                            move(map,robotX,robotY,3);
                            break;
                        default:
                            move(map,robotX,robotY,4);
                    }
                }
            }
            int total = 0;
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size; j++){
                    if (map[i][j] == 'O'){
                      total += i*100 + j;
                    }
                }
            }
            System.out.println(total);
        } catch (Exception e){
            System.out.println(e);
        }
    }
    //1:North, 2:East, 3:South, 4:West
    public static void move(char[][] map, int x, int y, int direction){
        switch (direction){
            case 1:
                if (map[y-1][x] == '#'){
                    return;
                }
                else if (map[y-1][x] == '.'){
                    if (map[y][x] == '@'){
                        robotY--;
                    }
                    map[y-1][x] = map[y][x];
                    map[y][x] = '.';
                    return;
                } else {
                    move(map,x,y-1,direction);
                    if (map[y-1][x] == '.'){
                        if (map[y][x] == '@'){
                            robotY--;
                        }
                        map[y-1][x] = map[y][x];
                        map[y][x] = '.';
                    }
                }
                break;
            case 2:
                if (map[y][x+1] == '#'){
                    return;
                }
                else if (map[y][x+1] == '.'){
                    if (map[y][x] == '@'){
                        robotX++;
                    }
                    map[y][x+1] = map[y][x];
                    map[y][x] = '.';
                    return;
                } else {
                    move(map,x+1,y,direction);
                    if (map[y][x+1] == '.'){
                        if (map[y][x] == '@'){
                            robotX++;
                        }
                        map[y][x+1] = map[y][x];
                        map[y][x] = '.';
                    }
                }
                break;
            case 3:
                if (map[y+1][x] == '#'){
                    return;
                }
                else if (map[y+1][x] == '.'){
                    if (map[y][x] == '@'){
                        robotY++;
                    }
                    map[y+1][x] = map[y][x];
                    map[y][x] = '.';
                    return;
                } else {
                    move(map,x,y+1,direction);
                    if (map[y+1][x] == '.'){
                        if (map[y][x] == '@'){
                            robotY++;
                        }
                        map[y+1][x] = map[y][x];
                        map[y][x] = '.';
                    }
                }
                break;
            default:
                if (map[y][x-1] == '#'){
                    return;
                }
                else if (map[y][x-1] == '.'){
                    if (map[y][x] == '@'){
                        robotX--;
                    }
                    map[y][x-1] = map[y][x];
                    map[y][x] = '.';
                    return;
                } else {
                    move(map,x-1,y,direction);
                    if (map[y][x-1] == '.'){
                        if (map[y][x] == '@'){
                            robotX--;
                        }
                        map[y][x-1] = map[y][x];
                        map[y][x] = '.';
                    }
                }
        }

    }
}
