import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Puzzle2Day14 {
    static int rows = 103;
    static int columns = 101;
    public static void main(String[] args) {
        int[][] map = new int[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                map[i][j] = 0;
            }
        }
        List<robot> robotList = new ArrayList<>();
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day14/InputDay14.txt"));
            String line;
            while ((line = file.readLine()) != null){
                String[] information = line.split("[pv]=|,| ");
                robotList.add(new robot(Integer.parseInt(information[1]),Integer.parseInt(information[2]),
                        Integer.parseInt(information[4]), Integer.parseInt(information[5])));
            }
        } catch (Exception e){
            System.out.println(e);
        }
        for (int i = 0; i < robotList.size(); i++){
            new robot().insertRobot(robotList.get(i), map);
        }
        int moves = 10000;
        int easterEggMove = 0;
        for (int i = 0; i < moves; i++){
            for (int j = 0; j < robotList.size(); j++){
                new robot().moveRobot(robotList.get(j), map);
            }
            for (int j = 0; j < rows; j++){
                for(int k =0 ; k< columns; k++){
                    if (map[j][k] != 0){
                        for (int robotCheck = k; robotCheck < k+8; robotCheck++){
                            if ( robotCheck >= columns || map[j][robotCheck] == 0){
                                k += robotCheck-1;
                                break;
                            }
                            if (robotCheck + 1 == k+8){
                                easterEggMove = i;
                                k = columns;
                                j = rows;
                                i = moves;
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < rows; i++){
            for (int j = 0; j <columns; j++){
                if (map[i][j] == 0){
                    System.out.print(" ");
                }
                else {
                    System.out.print("X");
                }
            }
            System.out.print("\n");
        }
        System.out.printf("Easter Egg on Move:%d\n", easterEggMove+1);
    }
    public static class robot {
        int x;
        int y;
        int xSpeed;
        int ySpeed;
        public robot(){

        }
        public robot(int x, int y, int xSpeed, int ySpeed){
            this.x = x;
            this.y = y;
            this.xSpeed = xSpeed;
            this.ySpeed = ySpeed;
        }
        void insertRobot(robot robot, int[][] map){
            map[robot.y][robot.x]++;
        }
        void moveRobot(robot robot, int[][] map){
            map[robot.y][robot.x]--;
            if (robot.x+robot.xSpeed > -1 && robot.x+robot.xSpeed < columns) {
                robot.x += robot.xSpeed;
                if (robot.y + robot.ySpeed > -1 && robot.y + robot.ySpeed < rows){
                    robot.y += robot.ySpeed;
                }
                else {
                    if (robot.ySpeed < 0){
                        robot.y = robot.ySpeed + robot.y + rows;
                    } else {
                        robot.y = robot.ySpeed + robot.y - rows;
                    }
                }
                insertRobot(robot,map);
            }
            else {
                if (robot.xSpeed < 0){
                    robot.x = robot.xSpeed + robot.x + columns;
                }
                else {
                    robot.x = robot.xSpeed + robot.x- columns;
                }
                if (robot.y + robot.ySpeed > -1 && robot.y + robot.ySpeed < rows){
                    robot.y += robot.ySpeed;
                }
                else {
                    if (robot.ySpeed < 0){
                        robot.y = robot.ySpeed + robot.y + rows;
                    } else {
                        robot.y = robot.ySpeed + robot.y - rows;
                    }
                }
                insertRobot(robot,map);
            }
        }
    }
}
