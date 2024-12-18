import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Puzzle2Day18 {
    public static void main(String[] args){
        int size = 71;
        boolean[][] map = new boolean[size][size];
        ArrayList<node> bytesList = new ArrayList<>();
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day18/InputDay18.txt"));
            String line;
            while ((line = file.readLine()) != null){
                int x = Integer.parseInt(line.split(",")[0]);
                int y = Integer.parseInt(line.split(",")[1]);
                bytesList.add(new node(x,y));
            }
        } catch (Exception e){
            System.out.println("File Error");
        }
        int i = 0;
        while (i < 1024){
            map[bytesList.get(i).y][bytesList.get(i).x] = true;
            i++;
        }
        while (true) {
            boolean[][] visited = new boolean[size][size];
            node[][] closestNode = new node[size][size];
            Queue<node> queue = new LinkedList<>();
            visited[0][0] = true;
            queue.add(new node(0, 0));
            while (!queue.isEmpty()) {
                node currentNode = queue.poll();
                if (currentNode.y - 1 > -1 && !map[currentNode.y - 1][currentNode.x] && !visited[currentNode.y - 1][currentNode.x]) {
                    visited[currentNode.y - 1][currentNode.x] = true;
                    closestNode[currentNode.y - 1][currentNode.x] = currentNode;
                    queue.add(new node(currentNode.x, currentNode.y - 1));
                }
                if (currentNode.x + 1 < size && !map[currentNode.y][currentNode.x + 1] && !visited[currentNode.y][currentNode.x + 1]) {
                    visited[currentNode.y][currentNode.x + 1] = true;
                    closestNode[currentNode.y][currentNode.x + 1] = currentNode;
                    queue.add(new node(currentNode.x + 1, currentNode.y));
                }
                if (currentNode.y + 1 < size && !map[currentNode.y + 1][currentNode.x] && !visited[currentNode.y + 1][currentNode.x]) {
                    visited[currentNode.y + 1][currentNode.x] = true;
                    closestNode[currentNode.y + 1][currentNode.x] = currentNode;
                    queue.add(new node(currentNode.x, currentNode.y + 1));
                }
                if (currentNode.x - 1 > -1 && !map[currentNode.y][currentNode.x - 1] && !visited[currentNode.y][currentNode.x - 1]) {
                    visited[currentNode.y][currentNode.x - 1] = true;
                    closestNode[currentNode.y][currentNode.x - 1] = currentNode;
                    queue.add(new node(currentNode.x - 1, currentNode.y));
                }
            }
            if (closestNode[size - 1][size - 1] == null) {
                break;
            } else {
                i++;
                map[bytesList.get(i).y][bytesList.get(i).x] = true;
            }
        }
        System.out.printf("%d,%d\n", bytesList.get(i).x, bytesList.get(i).y);
    }
    public static class node {
        int x;
        int y;
        public node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
