import java.io.BufferedReader;
import java.io.FileReader;

public class Puzzle2Day6 {

    public static void main(String[] args) {
        int size = 130;
        char[][] map = new char[size][size];
        int guardX = 0;
        int guardY = 0;
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day6/InputDay6.txt"));
            String line;
            int row = 0;
            while ((line = file.readLine()) != null){
                for (int i = 0; i < size; i++){
                    map[row][i] = line.charAt(i);
                    if (line.charAt(i) == '^'){
                        guardX = i;
                        guardY = row;
                    }
                }
                row++;
            }
        } catch (Exception e){
            System.out.println("File Issue");
        }
        int guardStartX = guardX;
        int guardStartY = guardY;
        int loops = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (i == guardStartY && j == guardStartX){
                    continue;
                }
                char[][] newMap = new char[size][size];
                for (int k =0; k < size; k++){
                    for (int l = 0; l < size; l++){
                        newMap[k][l] = map[k][l];
                    }
                }
                newMap[i][j] = 'O';
                guardX = guardStartX;
                guardY = guardStartY;
                int direction = 1; //1:Up, 2:Right, 3:Down, 4:Left
                guardState[][] guardStates = new guardState[size][size];
                while (true){
                    if (guardStates[guardY][guardX] == null){
                        guardStates[guardY][guardX] = new guardState(guardX, guardY, direction);
                    }
                    else if (guardStates[guardY][guardX].x == guardX && guardStates[guardY][guardX].y == guardY && guardStates[guardY][guardX].direction == direction){
                        loops++;
                        break;
                    }
                    if ((direction == 1 || direction == 3) && newMap[guardY][guardX] == '.'){
                        newMap[guardY][guardX] = '|';
                    }
                    else if ((direction == 1 || direction == 3) && newMap[guardY][guardX] == '-') {
                        newMap[guardY][guardX] = '+';
                    }

                    if ((direction == 2 || direction == 4) && newMap[guardY][guardX] == '.'){
                        newMap[guardY][guardX] = '-';
                    }

                    else if ((direction == 2 || direction == 4) && newMap[guardY][guardX] == '|'){
                        newMap[guardY][guardX] = '+';
                    }
                    if (direction == 1){
                        if (guardY-1 < 0){
                            break;
                        }
                        else if (newMap[guardY-1][guardX] == '#'){
                            direction = 2;
                        }
                        else if (newMap[guardY-1][guardX] == 'O'){
                            direction = 2;
                        }
                        else {
                            guardY--;
                        }
                    }
                    else if (direction == 2){
                        if (guardX+1 >= size){
                            break;
                        }
                        else if (newMap[guardY][guardX+1] == '#'){
                            direction = 3;
                        }
                        else if (newMap[guardY][guardX+1] == 'O'){
                            direction = 3;
                        }
                        else {
                            guardX++;
                        }
                    }
                    else if (direction == 3){
                        if (guardY+1 >= size){
                            break;
                        }
                        else if (newMap[guardY+1][guardX] == '#'){
                            direction = 4;
                        }
                        else if (newMap[guardY+1][guardX] == 'O'){
                            direction = 4;
                        }
                        else {
                            guardY++;
                        }
                    }
                    else {
                        if (guardX-1 < 0){
                            break;
                        }
                        else if (newMap[guardY][guardX-1] == '#'){
                            direction = 1;
                        }
                        else if (newMap[guardY][guardX-1] == 'O'){
                            direction = 1;
                        }
                        else {
                            guardX--;
                        }
                    }
                }
            }
        }
        System.out.println(loops);
    }
    public static class guardState {
        public int x;
        public int y;
        public int direction;
        guardState(int x, int y, int direction){
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

}
