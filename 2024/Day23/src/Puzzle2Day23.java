import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Puzzle2Day23 {

    public static HashMap<node, Boolean> visitied = new HashMap<>();
    public static ArrayList<ArrayList<node>> maximalClique = new ArrayList<>();
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
        BronKerbosch(graph.nodes, new ArrayList<node>(), new ArrayList<node>());
        int maximum = 0;
        int maximumLocation = 0;
        for (int i = 0; i < maximalClique.size(); i++){
            if (maximalClique.get(i).size() > maximum){
                maximum = maximalClique.get(i).size();
                maximumLocation = i;
            }
        }
        String[] password = new String[maximalClique.get(maximumLocation).size()];
        for (int i = 0; i < maximalClique.get(maximumLocation).size(); i++){
            password[i] = maximalClique.get(maximumLocation).get(i).name;
        }
        Arrays.sort(password);
        String finalPassword = "";
        for (int i = 0; i < password.length; i++){
            finalPassword = finalPassword + password[i] + ',';
        }
        finalPassword = finalPassword.substring(0,finalPassword.length()-1);
        System.out.println(finalPassword);
    }
    public static void BronKerbosch(ArrayList<node> P, ArrayList<node> R, ArrayList<node> X){
        if (P.isEmpty() && X.isEmpty()){
            maximalClique.add(R);
        }
        ArrayList<node> PDup = new ArrayList<>(P);
        if (!P.isEmpty()){
            int maxDegree = 0;
            int maxLocation = 0;
            for (int i = 0; i < P.size(); i++){
                if (P.get(i).connectedNodes.size() > maxDegree){
                    maxDegree = P.get(i).connectedNodes.size();
                maxLocation = i;
                }
            }
            for (int i = 0; i < P.get(maxLocation).connectedNodes.size(); i++){
                  for (int j = 0; j < P.size(); j++){
                   if (P.get(j).name.equals(P.get(maxLocation).connectedNodes.get(i))){
                          PDup.remove(P.get(j));
                    }
                 }
             }
        }
        for (int i = 0; i < PDup.size(); i++){
            ArrayList<node> PAndNeighbors = new ArrayList<>();
            ArrayList<node> newR = new ArrayList<>(R);
            ArrayList<node> xAndNeighbors = new ArrayList<>();
            node currentNode= PDup.get(i);
            for (int j = 0; j < P.size(); j++){
                for (int k = 0; k < currentNode.connectedNodes.size(); k++){
                    if (P.get(j).name.equals(currentNode.connectedNodes.get(k))){
                        PAndNeighbors.add(P.get(j));
                    }
                }
            }
            newR.add(currentNode);
            for (int j = 0; j < X.size(); j++){
                for (int k = 0; k < currentNode.connectedNodes.size(); k++){
                    if (X.get(j).name.equals(currentNode.connectedNodes.get(k))){
                        PAndNeighbors.add(X.get(j));
                    }
                }
            }
            BronKerbosch(PAndNeighbors,newR,xAndNeighbors);
            X.add(currentNode);
            P.remove(currentNode);
        }
    }
    public static class node {
        String name;
        ArrayList<String> connectedNodes;
        int hashCode;
        public node(String name){
            this.name = name;
            connectedNodes = new ArrayList<>();
            hashCode = Objects.hash(name);
        }
        public boolean equals(Object obj) {
            if (obj instanceof node Node){
                return this.name.equals(Node.name);
            }
            return false;
        }
        @Override
        public int hashCode() {
            return this.hashCode;
        }
    }
    public static class Graph {
        ArrayList<node> nodes;
        public Graph(){
            nodes = new ArrayList<>();
        }
        public void addNode(String name){
            nodes.add(new node(name));
            visitied.put(new node(name), false);
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
