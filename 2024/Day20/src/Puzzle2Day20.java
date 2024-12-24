import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Puzzle2Day20 {
    static int size = 141;
    static int cheatLength = 20;
    static int[][] distance = new int[size][size];
    static int[][] steps = new int[size][size];
    static char[][] map = new char[size][size];


    public static void main(String[] args){
        Puzzle1Day20.node startNode = new Puzzle1Day20.node();
        Puzzle1Day20.node endNode = new Puzzle1Day20.node();
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day20/InputDay20.txt"));
            String line;
            int row = 0;
            while ((line = file.readLine()) != null){
                for (int i = 0; i < size; i++){
                    map[row][i] = line.charAt(i);
                    if (line.charAt(i) == 'S'){
                        startNode.y = row;
                        startNode.x = i;
                    }
                    else if (line.charAt(i) == 'E'){
                        endNode.y = row;
                        endNode.x = i;
                    }
                }
                row++;
            }
        } catch (IOException e){
            System.out.println("File Issue");
        }
        System.out.println(cheatCount(map,startNode,endNode));
    }
    public static int cheatCount(char[][] map, Puzzle1Day20.node startNode, Puzzle1Day20.node endNode){
        Queue<Puzzle1Day20.node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[size][size];
        Puzzle1Day20.node[][] previousNode = new Puzzle1Day20.node[size][size];
        visited[startNode.y][startNode.x] = true;
        queue.add(new Puzzle1Day20.node(startNode.x, startNode.y));
        while(!queue.isEmpty()){
            Puzzle1Day20.node currentNode = queue.poll();
            if (map[currentNode.y-1][currentNode.x] != '#' && !visited[currentNode.y-1][currentNode.x]){
                visited[currentNode.y-1][currentNode.x] = true;
                previousNode[currentNode.y-1][currentNode.x] = currentNode;
                queue.add(new Puzzle1Day20.node(currentNode.x,currentNode.y-1));
            }
            if (map[currentNode.y][currentNode.x+1] != '#'  && !visited[currentNode.y][currentNode.x+1]){
                visited[currentNode.y][currentNode.x+1] = true;
                previousNode[currentNode.y][currentNode.x+1] = currentNode;
                queue.add(new Puzzle1Day20.node(currentNode.x+1,currentNode.y));
            }
            if (map[currentNode.y+1][currentNode.x] != '#' && !visited[currentNode.y+1][currentNode.x]){
                visited[currentNode.y+1][currentNode.x] = true;
                previousNode[currentNode.y+1][currentNode.x] = currentNode;
                queue.add(new Puzzle1Day20.node(currentNode.x,currentNode.y+1));
            }
            if (map[currentNode.y][currentNode.x-1] != '#' && !visited[currentNode.y][currentNode.x-1]){
                visited[currentNode.y][currentNode.x-1] = true;
                previousNode[currentNode.y][currentNode.x-1] = currentNode;
                queue.add(new Puzzle1Day20.node(currentNode.x-1,currentNode.y));
            }

        }
        Puzzle1Day20.node node = endNode;
        int length = 0;
        if (previousNode[node.y][node.x] == null){
            throw new RuntimeException("Exit not found");
        }
        else {
            while (previousNode[node.y][node.x] != null){
                distance[node.y][node.x] = length;
                length++;
                node = previousNode[node.y][node.x];
            }
            distance[startNode.y][startNode.x] = length;

        }
        int l = 0;
        node = endNode;
        int lengthHold = length;
        while (previousNode[node.y][node.x] != null){
            steps[node.y][node.x] = lengthHold;
            node = previousNode[node.y][node.x];
            lengthHold--;
        }
        int count = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (map[i][j] != '#') {
                    for (int x = 0; x < size; x++) {
                        for (int y = 0; y < size; y++) {
                            if (map[x][y] != '#') {
                                int diff = Math.abs(i-x) + Math.abs(j-y);
                                if (diff <= cheatLength) {
                                    if ((length - (steps[i][j] + distance[x][y] + diff)) >= 100){
                                        count++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}