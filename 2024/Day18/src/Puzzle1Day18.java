import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;

public class Puzzle1Day18 {
    public static void main(String[] args){
        int size = 71;
        boolean[][] map = new boolean[size][size];
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day18/InputDay18.txt"));
            String line;
            int bytes = 1;
            int maxBytes = 1024;
            while ((line = file.readLine()) != null){
                int x = Integer.parseInt(line.split(",")[0]);
                int y = Integer.parseInt(line.split(",")[1]);
                map[y][x] = true;
                if (bytes == maxBytes){
                    break;
                }
                bytes++;
            }
        } catch (Exception e){
            System.out.println("File Error");
        }
        boolean[][] visited = new boolean[size][size];
        node[][] closestNode = new node[size][size];
        Queue<node> queue = new LinkedList<>();
        visited[0][0] = true;
        queue.add(new node(0,0));
        while(!queue.isEmpty()){
            node currentNode = queue.poll();
            if (currentNode.y-1 > -1 && !map[currentNode.y-1][currentNode.x] && !visited[currentNode.y-1][currentNode.x]){
                visited[currentNode.y-1][currentNode.x] = true;
                closestNode[currentNode.y-1][currentNode.x] = currentNode;
                queue.add(new node(currentNode.x,currentNode.y-1));
            }
            if (currentNode.x+1 < size && !map[currentNode.y][currentNode.x+1] && !visited[currentNode.y][currentNode.x+1]){
                visited[currentNode.y][currentNode.x+1] = true;
                closestNode[currentNode.y][currentNode.x+1] = currentNode;
                queue.add(new node(currentNode.x+1,currentNode.y));
            }
            if (currentNode.y+1 < size && !map[currentNode.y+1][currentNode.x] && !visited[currentNode.y+1][currentNode.x]){
                visited[currentNode.y+1][currentNode.x] = true;
                closestNode[currentNode.y+1][currentNode.x] = currentNode;
                queue.add(new node(currentNode.x,currentNode.y+1));
            }
            if (currentNode.x-1 > -1 && !map[currentNode.y][currentNode.x-1] && !visited[currentNode.y][currentNode.x-1]){
                visited[currentNode.y][currentNode.x-1] = true;
                closestNode[currentNode.y][currentNode.x-1] = currentNode;
                queue.add(new node(currentNode.x-1,currentNode.y));
            }
        }
        node node = new node(size-1,size-1);
        int length = 0;
        if (closestNode[node.y][node.x] == null){
            throw new RuntimeException("Exit not found");
        }
        else {
            while (closestNode[node.y][node.x] != null){
                length++;
                node = closestNode[node.y][node.x];
            }
        }
        System.out.println(length);
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
