import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Puzzle1Day23 {
    public static void main(String[] args){
        Graph graph = new Graph();
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day23/InputDay23.txt"));
            String line;
            while((line = file.readLine()) != null){
                String str1 = line.substring(0,2);
                String str2 = line.substring(3,5);
                graph.connectNodes(str1,str2);
            }
        } catch (IOException e){
            System.out.println("File Error");
        }
        ArrayList<String[]> connections = new ArrayList<>();
        for (int i = 0; i < graph.nodes.size(); i++){
            for (int j = 0; j < graph.nodes.get(i).connectedNodes.size(); j++){
                node currentNode = graph.getNode(graph.nodes.get(i).connectedNodes.get(j));
                for (int k = 0; k < currentNode.connectedNodes.size(); k++){
                    node currentNode2 = graph.getNode(currentNode.connectedNodes.get(k));
                    for (int l = 0; l < currentNode2.connectedNodes.size(); l++){
                        if (currentNode2.connectedNodes.get(l).equals(graph.nodes.get(i).name)){
                            String currentConnect =
                                    graph.nodes.get(i).name + "," + currentNode.name + "," +currentNode2.name;
                            String[] currentConnections = currentConnect.split(",");
                            Arrays.sort(currentConnections);
                            boolean inList = false;
                            for (int idk = 0; idk < connections.size(); idk++){
                                if (currentConnections[0].equals(connections.get(idk)[0]) && currentConnections[1].equals(connections.get(idk)[1]) && currentConnections[2].equals(connections.get(idk)[2])){
                                    inList = true;
                                    break;
                                }
                            }
                            if (!inList){
                                connections.add(currentConnections);
                            }
                        }
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < connections.size(); i++){
            if (connections.get(i)[0].charAt(0) == 't'|| connections.get(i)[1].charAt(0) == 't' || connections.get(i)[2].charAt(0) == 't'){
                count++;
            }
        }
        System.out.println(count);
    }
    public static class node {
        String name;
        ArrayList<String> connectedNodes;
        public node(String name){
            this.name = name;
            connectedNodes = new ArrayList<>();
        }
    }
    public static class Graph {
        ArrayList<node> nodes;
        public Graph(){
            nodes = new ArrayList<>();
        }
        public node getNode(String name){
            for (int i = 0; i < nodes.size(); i++){
                if (nodes.get(i).name.equals(name)){
                    return nodes.get(i);
                }
            }
            return null;
        }
        public void connectNodes(String name1, String name2){
            boolean firstNode = false;
            int firstLocation = 0;
            boolean secondNode = false;
            int secondLocation = 0;
            for (int i = 0; i < nodes.size(); i++){
                if (!firstNode && nodes.get(i).name.equals(name1)) {
                    firstNode = true;
                    firstLocation = i;
                }
                else if (!secondNode && nodes.get(i).name.equals(name2)){
                    secondNode = true;
                    secondLocation = i;
                }
            }
            if (!firstNode){
                firstLocation = nodes.size();
                nodes.add(new node(name1));
            }
            if (!secondNode){
                secondLocation = nodes.size();
                nodes.add(new node(name2));
            }
            nodes.get(firstLocation).connectedNodes.add(name2);
            nodes.get(secondLocation).connectedNodes.add(name1);
        }
    }

}
