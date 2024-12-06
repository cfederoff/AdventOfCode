import java.io.BufferedReader;
import java.io.FileReader;

public class Puzzle1Day6 {
    public static void main(String[] args){
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
        int direction = 1; //1:Up, 2:Right, 3:Down, 4:Left
        int uniqueSpots = 0;
        while (true){
            if (map[guardY][guardX] != 'X'){
                map[guardY][guardX] = 'X';
                uniqueSpots++;
            }
            if (direction == 1){
                if (guardY-1 < 0){
                    break;
                }
                else if (map[guardY-1][guardX] == '#'){
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
                else if (map[guardY][guardX+1] == '#'){
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
                else if (map[guardY+1][guardX] == '#'){
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
                else if (map[guardY][guardX-1] == '#'){
                    direction = 1;
                }
                else {
                    guardX--;
                }
            }
        }
        System.out.println(uniqueSpots);
    }
}
