import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Puzzle2Day21 {
    static char[][] keypadVisual = {{'7','8','9'},{'4','5','6'},{'1','2','3'},{'.','0','A'}};
    static char[][] directionKeypadVisual = {{'.','^','A'},{'<','v','>'}};
    static ArrayList<String> currentPath = new ArrayList<>();
    static ArrayList<String>[][] keyPadPaths = new ArrayList[11][11];

    static ArrayList<String>[][] directionalKeyPadPaths = new ArrayList[5][5];
    static int depth = 25;

    public static void main(String[] args){
        Puzzle1Day21.node[] keyPad = new Puzzle1Day21.node[11];
        keyPad[0] = new Puzzle1Day21.node(1,3);
        keyPad[1] = new Puzzle1Day21.node(0,2);
        keyPad[2] = new Puzzle1Day21.node(1,2);
        keyPad[3] = new Puzzle1Day21.node(2, 2);
        keyPad[4] = new Puzzle1Day21.node(0,1);
        keyPad[5] = new Puzzle1Day21.node(1,1);
        keyPad[6] = new Puzzle1Day21.node(2,1);
        keyPad[7] = new Puzzle1Day21.node(0,0);
        keyPad[8] = new Puzzle1Day21.node(1,0);
        keyPad[9] = new Puzzle1Day21.node(2,0);
        keyPad[10] = new Puzzle1Day21.node(2,3);
        for (int i = 0; i < keyPad.length; i++){
            for (int j = 0; j < keyPad.length; j++){
                if (i == j){
                    keyPadPaths[i][j] = new ArrayList<>();
                    keyPadPaths[i][j].add("");
                }
                else {
                    dfs(keyPad[i].x,keyPad[i].y,keyPad[j].x,keyPad[j].y,"", new boolean[4][3]);
                    long minimumLength = Long.MAX_VALUE;
                    for (int k = 0; k < currentPath.size(); k++){
                        if (currentPath.get(k).length() < minimumLength){
                            minimumLength = currentPath.get(k).length();
                        }
                    }
                    for (int k = 0; k < currentPath.size(); k++){
                        if (currentPath.get(k).length() > minimumLength){
                            currentPath.remove(currentPath.get(k));
                        }
                    }
                    keyPadPaths[i][j] = new ArrayList<>(currentPath);
                    currentPath.clear();
                }
            }
        }
        Puzzle1Day21.node[] directionalKeypad = new Puzzle1Day21.node[5];
        directionalKeypad[0] = new Puzzle1Day21.node(0,1); // <
        directionalKeypad[1] = new Puzzle1Day21.node(1,1); // v
        directionalKeypad[2] = new Puzzle1Day21.node(2,1); // >
        directionalKeypad[3] = new Puzzle1Day21.node(1,0); // ^
        directionalKeypad[4] = new Puzzle1Day21.node(2,0); // A
        for (int i = 0; i < directionalKeyPadPaths.length; i++){
            for (int j = 0; j < directionalKeyPadPaths.length; j++){
                if (i == j){
                    directionalKeyPadPaths[i][j] = new ArrayList<>();
                    directionalKeyPadPaths[i][j].add("");
                }
                else {
                    dfsDirectional(directionalKeypad[i].x, directionalKeypad[i].y, directionalKeypad[j].x,
                            directionalKeypad[j].y,"", new boolean[2][3]);
                    long minimumLength = Long.MAX_VALUE;
                    for (int k = 0; k < currentPath.size(); k++){
                        if (currentPath.get(k).length() < minimumLength){
                            minimumLength = currentPath.get(k).length();
                        }
                    }
                    for (int k = 0; k < currentPath.size(); k++){
                        if (currentPath.get(k).length() > minimumLength){
                            currentPath.remove(currentPath.get(k));
                            k--;
                        }
                    }
                    directionalKeyPadPaths[i][j] = new ArrayList<>(currentPath);
                    currentPath.clear();
                }
            }
        }
        long total = 0;
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day21/InputDay21.txt"));
            String line;
            while ((line = file.readLine()) != null){
                ArrayList<String> sequence = new ArrayList<>();
                buildKeypadSequence(line,0,'A',"", sequence);
                HashMap<hashInfo, Long> hashMap = new HashMap<>();
                long minimum = Long.MAX_VALUE;
                for (int i = 0; i < sequence.size(); i++){
                    long amount = shortestSequence(sequence.get(i),depth, hashMap);
                    if (amount < minimum){
                        minimum = amount;
                    }
                }
                total += minimum * Long.parseLong(line.substring(0,line.length()-1));
            }
        } catch (IOException e){
            System.out.println("File Not Found");
        }
        System.out.println(total);
    }
    public static void dfs(int x, int y, int endX, int endY, String path, boolean[][] visited){
        if (x == endX && y == endY){
            currentPath.add(path);
            return;
        }
        visited[y][x] = true;
        if (x-1 > -1 && !visited[y][x-1] && keypadVisual[y][x-1] != '.'){
            boolean[][] newVisited = new boolean[4][3];
            for (int i = 0; i < 4; i++){
                System.arraycopy(visited[i], 0, newVisited[i], 0, 3);
            }
            dfs(x-1,y,endX,endY, path + "<", newVisited);
        }
        if (y-1 > -1 && !visited[y-1][x] && keypadVisual[y-1][x] != '.'){
            boolean[][] newVisited = new boolean[4][3];
            for (int i = 0; i < 4; i++){
                System.arraycopy(visited[i], 0, newVisited[i], 0, 3);
            }
            dfs(x,y-1,endX,endY, path + "^", newVisited);
        }
        if (y+1 < 4 && !visited[y+1][x] && keypadVisual[y+1][x] != '.'){
            boolean[][] newVisited = new boolean[4][3];
            for (int i = 0; i < 4; i++){
                System.arraycopy(visited[i], 0, newVisited[i], 0, 3);
            }
            dfs(x,y+1,endX,endY, path + "v", newVisited);
        }
        if (x+1 < 3 && !visited[y][x+1] && keypadVisual[y][x+1] != '.'){
            boolean[][] newVisited = new boolean[4][3];
            for (int i = 0; i < 4; i++){
                System.arraycopy(visited[i], 0, newVisited[i], 0, 3);
            }
            dfs(x+1,y,endX,endY, path + ">", newVisited);
        }
    }
    public static void dfsDirectional(int x, int y, int endX, int endY, String path, boolean[][] visited){
        if (x == endX && y == endY){
            currentPath.add(path);
            return;
        }
        visited[y][x] = true;
        if (x-1 > -1 && !visited[y][x-1] && directionKeypadVisual[y][x-1] != '.'){
            boolean[][] newVisited = new boolean[2][3];
            for (int i = 0; i < 2; i++){
                System.arraycopy(visited[i], 0, newVisited[i], 0, 3);
            }
            dfsDirectional(x-1,y,endX,endY, path + "<", newVisited);
        }
        if (y-1 > -1 && !visited[y-1][x] && directionKeypadVisual[y-1][x] != '.'){
            boolean[][] newVisited = new boolean[2][3];
            for (int i = 0; i < 2; i++){
                System.arraycopy(visited[i], 0, newVisited[i], 0, 3);
            }
            dfsDirectional(x,y-1,endX,endY, path + "^", newVisited);
        }
        if (y+1 < 2 && !visited[y+1][x] && directionKeypadVisual[y+1][x] != '.'){
            boolean[][] newVisited = new boolean[2][3];
            for (int i = 0; i < 2; i++){
                System.arraycopy(visited[i], 0, newVisited[i], 0, 3);
            }
            dfsDirectional(x,y+1,endX,endY, path + "v", newVisited);
        }
        if (x+1 < 3 && !visited[y][x+1] && directionKeypadVisual[y][x+1] != '.'){
            boolean[][] newVisited = new boolean[2][3];
            for (int i = 0; i < 2; i++){
                System.arraycopy(visited[i], 0, newVisited[i], 0, 3);
            }
            dfsDirectional(x+1,y,endX,endY, path + ">", newVisited);
        }
    }
    public static void buildSequence(String keys, int index, char prevKey,String currPath, ArrayList<String> result){
        if (index == keys.length()){
            result.add(currPath);
            return;
        }
        int previousIndex = 0;
        switch (prevKey){
            case '<':
                previousIndex = 0;
                break;
            case 'v':
                previousIndex = 1;
                break;
            case '>':
                previousIndex = 2;
                break;
            case '^':
                previousIndex = 3;
                break;
            case 'A':
                previousIndex = 4;
                break;
        }
        char currentChar = keys.charAt(index);
        int currentIndex = 0;
        switch (currentChar){
            case '<':
                currentIndex = 0;
                break;
            case 'v':
                currentIndex = 1;
                break;
            case '>':
                currentIndex = 2;
                break;
            case '^':
                currentIndex = 3;
                break;
            case 'A':
                currentIndex = 4;
                break;
        }
        for (int i = 0; i < directionalKeyPadPaths[previousIndex][currentIndex].size(); i++){
            buildSequence(keys,index+1, currentChar,
                    currPath + directionalKeyPadPaths[previousIndex][currentIndex].get(i) + "A", result);
        }

    }
    public static void buildKeypadSequence(String keys, int index, char prevKey,String currPath, ArrayList<String> result){
        if (index == keys.length()){
            result.add(currPath);
            return;
        }
        char currentKey = keys.charAt(index);
        int currentIndex = 0;
        if (currentKey == 'A'){
            currentIndex = 10;
        }
        else {
            currentIndex = currentKey - '0';
        }
        int previousIndex = 0;
        if (prevKey == 'A'){
            previousIndex = 10;
        }
        else {
            previousIndex = prevKey - '0';
        }
        for (int i = 0; i < keyPadPaths[previousIndex][currentIndex].size(); i++){
            buildKeypadSequence(keys,index+1, currentKey,
                    currPath + keyPadPaths[previousIndex][currentIndex].get(i) + 'A', result);
        }
    }

    public static long shortestSequence(String keys, long depth, HashMap<hashInfo,Long> cache){
        if (depth == 0){
            return keys.length();
        }
        if (cache.containsKey(new hashInfo(keys,depth))){
            return cache.get(new hashInfo(keys,depth));
        }
        String[] subKeys = keys.split("A");
        if (subKeys.length == 0){
            subKeys = new String[]{"A"};
        }
        else {
            for (int i = 0; i < subKeys.length; i++) {
                subKeys[i] = subKeys[i] + 'A';
            }
        }
        long total = 0;
        for (int i = 0; i < subKeys.length; i++){
            ArrayList<String> sequenceList = new ArrayList<>();
            buildSequence(subKeys[i],0,'A',"",sequenceList);
            long minimum = Long.MAX_VALUE;
            for (int j = 0; j < sequenceList.size(); j++){
                long num = shortestSequence(sequenceList.get(j),depth-1, cache);
                if (num < minimum){
                    minimum = num;
                }
            }
            total += minimum;
        }
        cache.put(new hashInfo(keys,depth),total);
        return total;
    }
    public static class hashInfo {
        String keys;
        Long total;
        int hashcode;
        hashInfo(String keys, Long total){
            this.keys = keys;
            this.total = total;
            this.hashcode = Objects.hash(keys,total);
        }
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof hashInfo HashInfo){
                return this.keys.equals(HashInfo.keys) && Objects.equals(this.total, HashInfo.total);
            }
            return false;
        }
        @Override
        public int hashCode() {
            return this.hashcode;
        }
    }
}
