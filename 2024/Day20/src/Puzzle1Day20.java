import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Puzzle1Day20 {
    static int size = 15;
    public static void main(String[] args){
        char[][] map = new char[size][size];
        node startNode = new node();
        node endNode = new node();
        ArrayList<node> cheatNodes = new ArrayList<>();
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
        for (int i = 0; i < size; i++) {
            for (int j =0 ; j < size; j++){
                if (map[j][i] == '#'){
                    if (i > 0 && i < size-1 && j > 0 && j < size-1){
                        int count = 0;
                        if (map[j-1][i] != '#'){
                            count++;
                        }
                        if (map[j][i-1] != '#'){
                            count++;
                        }
                        if (map[j+1][i] != '#'){
                            count++;
                        }
                        if (map[j][i+1] != '#'){
                            count++;
                        }
                        if (count >= 2){
                            cheatNodes.add(new node(i,j));
                        }
                    }
                }
            }
        }
        int fastestTime = bfs(map,startNode,endNode);
        int count = 0;
        for (int i = 0; i < cheatNodes.size(); i++){
            map[cheatNodes.get(i).y][cheatNodes.get(i).x] = '.';
            if (fastestTime- bfs(map,startNode,endNode) >= 100){
                count++;
            }
            map[cheatNodes.get(i).y][cheatNodes.get(i).x] = '#';
        }
        System.out.println(count);
    }
    public static class node {
        int x;
        int y;
        public node(int x, int y){
            this.x = x;
            this.y = y;
        }
        public node(){

        }
    }
    public static int bfs(char[][] map, node startNode, node endNode){
        Queue<node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[size][size];
        node[][] previousNode = new node[size][size];
        visited[startNode.y][startNode.x] = true;
        queue.add(new node(startNode.x, startNode.y));
        while(!queue.isEmpty()){
            node currentNode = queue.poll();
            if (map[currentNode.y-1][currentNode.x] != '#' && !visited[currentNode.y-1][currentNode.x]){
                visited[currentNode.y-1][currentNode.x] = true;
                previousNode[currentNode.y-1][currentNode.x] = currentNode;
                queue.add(new node(currentNode.x,currentNode.y-1));
            }
            if (map[currentNode.y][currentNode.x+1] != '#'  && !visited[currentNode.y][currentNode.x+1]){
                visited[currentNode.y][currentNode.x+1] = true;
                previousNode[currentNode.y][currentNode.x+1] = currentNode;
                queue.add(new node(currentNode.x+1,currentNode.y));
            }
            if (map[currentNode.y+1][currentNode.x] != '#' && !visited[currentNode.y+1][currentNode.x]){
                visited[currentNode.y+1][currentNode.x] = true;
                previousNode[currentNode.y+1][currentNode.x] = currentNode;
                queue.add(new node(currentNode.x,currentNode.y+1));
            }
            if (map[currentNode.y][currentNode.x-1] != '#' && !visited[currentNode.y][currentNode.x-1]){
                visited[currentNode.y][currentNode.x-1] = true;
                previousNode[currentNode.y][currentNode.x-1] = currentNode;
                queue.add(new node(currentNode.x-1,currentNode.y));
            }

        }
        node node = endNode;
        int length = 0;
        if (previousNode[node.y][node.x] == null){
            throw new RuntimeException("Exit not found");
        }
        else {
            while (previousNode[node.y][node.x] != null){
                length++;
                node = previousNode[node.y][node.x];
            }
        }
        return length;
    }
}
