import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Puzzle1Day14 {
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
        int moves = 100;
        for (int i = 0; i < moves; i++){
            for (int j = 0; j < robotList.size(); j++){
                new robot().moveRobot(robotList.get(j), map);
            }
        }
        int quadrant1 = 0;
        for (int i = 0; i < Math.floor((double)(rows)/2); i++){
            for (int j = 0; j < Math.floor((double)(columns-1)/2); j++){
                quadrant1 += map[i][j];
            }
        }
        int quadrant2 = 0;
        for (int i = 0; i < Math.floor((double)(rows)/2); i++){
            for (int j = (int) Math.ceil((double)(columns)/2); j < columns; j++){
                quadrant2 += map[i][j];
            }
        }
        int quadrant3 = 0;
        for (int i = (int) Math.ceil((double)(rows)/2); i < rows; i++){
            for (int j = 0; j < Math.floor((double)(columns)/2); j++){
                quadrant3 += map[i][j];
            }
        }
        int quadrant4 = 0;
        for (int i = (int) Math.ceil((double)(rows)/2); i < rows; i++){
            for (int j = (int) Math.ceil((double)(columns)/2); j < columns; j++){
                quadrant4 += map[i][j];
            }
        }
        int safetyScore = quadrant1 * quadrant2 * quadrant3 * quadrant4;
        System.out.println(safetyScore);
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
