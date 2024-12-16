import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

public class Puzzle2Day15 {
    static int robotX = 0;
    static int robotY = 0;
    public static void main(String[] args) {
        int size = 50;
        char[][] map = new char[size][size*2];
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day15/InputDay15.txt"));
            String line;
            int row = 0;
            for (int i = 0; i < size; i++) {
                line = file.readLine();
                int position = 0;
                for (int j = 0; j < size; j++) {
                    if (line.charAt(j) == '@') {
                        robotX = j*2;
                        robotY = i;
                        map[i][position] = line.charAt(j);
                        map[i][position+1] = '.';
                    }
                    else if (line.charAt(j) == '#'){
                        map[i][position] = line.charAt(j);
                        map[i][position+1] = line.charAt(j);
                    }
                    else if (line.charAt(j) == 'O'){
                        map[i][position] = '[';
                        map[i][position+1] = ']';
                    }
                    else {
                        map[i][position] = line.charAt(j);
                        map[i][position+1] = line.charAt(j);
                    }
                    position += 2;
                }
                row++;
            }
            while ((line = file.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    switch (line.charAt(i)) {
                        case '^':
                            move(map, robotX, robotY, 1);
                            break;
                        case '>':
                            move(map, robotX, robotY, 2);
                            break;
                        case 'v':
                            move(map, robotX, robotY, 3);
                            break;
                        default:
                            move(map, robotX, robotY, 4);
                    }
                }
            }
            int total = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size*2; j++) {
                    if (map[i][j] == '[') {
                        total += i * 100 + j;
                    }
                }
            }
            for (int j = 0; j < size; j++){
                for (int k = 0; k < size*2; k++){
                    System.out.print(map[j][k]);
                }
                System.out.print('\n');
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //1:North, 2:East, 3:South, 4:West
    public static void move(char[][] map, int x, int y, int direction){
        switch (direction){
            case 1:
                if (map[y][x] == '@'){
                    if (map[y-1][x] == '.'){
                        robotY--;
                        map[y-1][x] = '@';
                        map[y][x] = '.';
                    }
                    else if (map[y-1][x] == ']' || map[y-1][x] == '['){
                        if (checkMovement(map,x,y-1,direction)){
                            moveBox(map,x,y-1,direction);
                            robotY--;
                            map[y-1][x] = '@';
                            map[y][x] = '.';
                        }
                    }
                }
                break;
            case 2:
                if (map[y][x] == '@'){
                    if (map[y][x+1] == '.'){
                        robotX++;
                        map[y][x+1] = '@';
                        map[y][x] = '.';
                    } else if (map[y][x+1] == '['){
                        move(map,x+1,y,direction);
                        if (map[y][x+1] == '.'){
                            robotX++;
                            map[y][x+1] = '@';
                            map[y][x] = '.';
                        }
                    }
                }
                else {
                    if (map[y][x+2] == '.'){
                        map[y][x+2] = ']';
                        map[y][x+1] = '[';
                        map[y][x] = '.';
                    } else if (map[y][x+2] == '['){
                        move(map,x+2,y,direction);
                        if (map[y][x+2] == '.'){
                            map[y][x+2] = ']';
                            map[y][x+1] = '[';
                            map[y][x] = '.';
                        }
                    }
                }
                break;
            case 3:
                if (map[y][x] == '@'){
                    if (map[y+1][x] == '.'){
                        robotY++;
                        map[y+1][x] = '@';
                        map[y][x] = '.';
                    }
                    else if (map[y+1][x] == ']' || map[y+1][x] == '['){
                        if (checkMovement(map,x,y+1,direction)){
                            moveBox(map,x,y+1,direction);
                            robotY++;
                            map[y+1][x] = '@';
                            map[y][x] = '.';
                        }
                    }
                }
                break;
            default:
                if (map[y][x] == '@'){
                    if (map[y][x-1] == '.'){
                        robotX--;
                        map[y][x-1] = '@';
                        map[y][x] = '.';
                    } else if (map[y][x-1] == ']'){
                        move(map,x-1,y,direction);
                        if (map[y][x-1] == '.'){
                            robotX--;
                            map[y][x-1] = '@';
                            map[y][x] = '.';
                        }
                    }
                }
                else {
                    if (map[y][x-2] == '.'){
                        map[y][x-2] = '[';
                        map[y][x-1] = ']';
                        map[y][x] = '.';
                    } else if (map[y][x-2] == ']'){
                        move(map,x-2,y,direction);
                        if (map[y][x-2] == '.'){
                            map[y][x-2] = '[';
                            map[y][x-1] = ']';
                            map[y][x] = '.';
                        }
                    }
                }
        }
    }
    public static boolean checkMovement(char[][] map, int x, int y, int direction){
        int leftBoxX = 0;
        int leftBoxY = 0;
        int rightBoxX = 0;
        int rightBoxY = 0;
        if (map[y][x] == '['){
            leftBoxX = x;
            leftBoxY = y;
            rightBoxX = x+1;
            rightBoxY = y;
        }
        else {
            leftBoxX = x-1;
            leftBoxY = y;
            rightBoxX = x;
            rightBoxY = y;
        }
        if (direction == 1){
            if (map[leftBoxY-1][leftBoxX] == '.' && map[rightBoxY-1][rightBoxX] == '.'){
                return true;
            }
            else if (map[leftBoxY-1][leftBoxX] == '[' && map[rightBoxY-1][rightBoxX] == ']'){
                return checkMovement(map, leftBoxX, leftBoxY-1, direction);
            }
            else if (map[leftBoxY-1][leftBoxX] == ']' && map[rightBoxY-1][rightBoxX] == '['){
                return checkMovement(map, leftBoxX, leftBoxY-1, direction) && checkMovement(map, rightBoxX, rightBoxY-1,
                        direction);
            }
            else if (map[leftBoxY-1][leftBoxX] == ']' && map[rightBoxY-1][rightBoxX] != '#'){
                return checkMovement(map, leftBoxX, leftBoxY-1, direction);
            } else if (map[rightBoxY-1][rightBoxX] == '[' && map[leftBoxY-1][leftBoxX] != '#'){
                return checkMovement(map, rightBoxX, rightBoxY-1, direction);
            }
        }
        else {
            if (map[leftBoxY+1][leftBoxX] == '.' && map[rightBoxY+1][rightBoxX] == '.'){
                return true;
            }
            else if (map[leftBoxY+1][leftBoxX] == '[' && map[rightBoxY+1][rightBoxX] == ']'){
                return checkMovement(map, leftBoxX, leftBoxY+1, direction);
            }
            else if (map[leftBoxY+1][leftBoxX] == ']' && map[rightBoxY+1][rightBoxX] == '['){
                return checkMovement(map, leftBoxX, leftBoxY+1, direction) && checkMovement(map, rightBoxX, rightBoxY+1,
                        direction);
            }
            else if(map[leftBoxY+1][leftBoxX] == ']' && map[rightBoxY+1][rightBoxX] != '#') {
                return checkMovement(map, leftBoxX, leftBoxY+1, direction);
            }
            else if (map[rightBoxY+1][rightBoxX] == '[' && map[leftBoxY+1][leftBoxX] != '#'){
                return checkMovement(map, rightBoxX, rightBoxY+1, direction);
            }
        }
        return false;
    }
    public static void moveBox (char[][] map, int x, int y, int direction){
        int leftBoxX = 0;
        int leftBoxY = 0;
        int rightBoxX = 0;
        int rightBoxY = 0;
        if (map[y][x] == '['){
            leftBoxX = x;
            leftBoxY = y;
            rightBoxX = x+1;
            rightBoxY = y;
        }
        else {
            leftBoxX = x-1;
            leftBoxY = y;
            rightBoxX = x;
            rightBoxY = y;
        }
        if (direction == 1){
            if (map[leftBoxY-1][leftBoxX] == '.' && map[rightBoxY-1][rightBoxX] == '.'){
                map[leftBoxY-1][leftBoxX]  = '[';
                map[rightBoxY-1][rightBoxX]  = ']';
                map[leftBoxY][leftBoxX]  = '.';
                map[rightBoxY][rightBoxX]  = '.';
            }
            else if (map[leftBoxY-1][leftBoxX] == '[' && map[rightBoxY-1][rightBoxX] == ']'){
                moveBox(map, leftBoxX, leftBoxY-1, direction);
                map[leftBoxY-1][leftBoxX]  = '[';
                map[rightBoxY-1][rightBoxX]  = ']';
                map[leftBoxY][leftBoxX]  = '.';
                map[rightBoxY][rightBoxX]  = '.';
            }
            else if (map[leftBoxY-1][leftBoxX] == ']' && map[rightBoxY-1][rightBoxX] == '['){
                moveBox(map, leftBoxX, leftBoxY-1, direction);
                moveBox(map,rightBoxX, rightBoxY-1, direction);
                map[leftBoxY-1][leftBoxX]  = '[';
                map[rightBoxY-1][rightBoxX]  = ']';
                map[leftBoxY][leftBoxX]  = '.';
                map[rightBoxY][rightBoxX]  = '.';
            }
            else if (map[leftBoxY-1][leftBoxX] == ']') {
                moveBox(map, leftBoxX, leftBoxY-1, direction);
                map[leftBoxY-1][leftBoxX]  = '[';
                map[rightBoxY-1][rightBoxX]  = ']';
                map[leftBoxY][leftBoxX]  = '.';
                map[rightBoxY][rightBoxX]  = '.';
            }
            else if (map[rightBoxY-1][rightBoxX] == '['){
                moveBox(map,rightBoxX, rightBoxY-1, direction);
                map[leftBoxY-1][leftBoxX]  = '[';
                map[rightBoxY-1][rightBoxX]  = ']';
                map[leftBoxY][leftBoxX]  = '.';
                map[rightBoxY][rightBoxX]  = '.';
            }
        }
        else {
            if (map[leftBoxY+1][leftBoxX] == '.' && map[rightBoxY+1][rightBoxX] == '.'){
                map[leftBoxY+1][leftBoxX]  = '[';
                map[rightBoxY+1][rightBoxX]  = ']';
                map[leftBoxY][leftBoxX]  = '.';
                map[rightBoxY][rightBoxX]  = '.';
            }
            else if (map[leftBoxY+1][leftBoxX] == '[' && map[rightBoxY+1][rightBoxX] == ']'){
                moveBox(map, leftBoxX, leftBoxY+1, direction);
                map[leftBoxY+1][leftBoxX]  = '[';
                map[rightBoxY+1][rightBoxX]  = ']';
                map[leftBoxY][leftBoxX]  = '.';
                map[rightBoxY][rightBoxX]  = '.';
            }
            else if (map[leftBoxY+1][leftBoxX] == ']' && map[rightBoxY+1][rightBoxX] == '['){
                moveBox(map, leftBoxX, leftBoxY+1, direction);
                moveBox(map,rightBoxX, rightBoxY+1, direction);
                map[leftBoxY+1][leftBoxX]  = '[';
                map[rightBoxY+1][rightBoxX]  = ']';
                map[leftBoxY][leftBoxX]  = '.';
                map[rightBoxY][rightBoxX]  = '.';
            }
            else if (map[leftBoxY+1][leftBoxX] == ']'){
                moveBox(map, leftBoxX, leftBoxY+1, direction);
                map[leftBoxY+1][leftBoxX]  = '[';
                map[rightBoxY+1][rightBoxX]  = ']';
                map[leftBoxY][leftBoxX]  = '.';
                map[rightBoxY][rightBoxX]  = '.';
            }
            else if (map[rightBoxY+1][rightBoxX] == '['){
                moveBox(map,rightBoxX, rightBoxY+1, direction);
                map[leftBoxY+1][leftBoxX]  = '[';
                map[rightBoxY+1][rightBoxX]  = ']';
                map[leftBoxY][leftBoxX]  = '.';
                map[rightBoxY][rightBoxX]  = '.';
            }
        }
    }
}
