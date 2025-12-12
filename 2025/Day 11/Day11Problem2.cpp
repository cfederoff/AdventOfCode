#include <iostream>

std::unordered_map<std::string, std::vector<std::string>> nodes;
std::unordered_map<std::string, long > counts;

long paths(const std::string& node, bool found1, bool found2) {
    if (node == "out") {
        if (found1 && found2){
            std::string result = "TT";
            result += node;
            counts.insert({result,1});
            return 1;
        }
        std::string result;
        if (found1 == true) {
            result += 'T';
        }
        else {
            result += 'F';
        }
        if (found2 == true) {
            result += 'T';
        }
        else {
            result += 'F';
        }
        counts.insert({result,0});
        return 0;
    }
    if (node == "dac"){
        found1 = true;
    }
    if (node == "fft"){
        found2 = true;
    }
    std::vector<std::string> edges = nodes.at(node);
    long sum = 0;
    for (std::string x : edges) {
        std::string result;
        if (found1 == true) {
            result += 'T';
        }
        else {
            result += 'F';
        }
        if (found2 == true) {
            result += 'T';
        }
        else {
            result += 'F';
        }
        result += x;
        if (counts.find(result) != counts.end()) {
            sum += counts.at(result);
        }
        else {
            long otherSum = paths(x, found1, found2);
            counts.insert({result,otherSum});
            sum += otherSum;
        }
    }
    std::string result;
    if (found1 == true) {
        result += 'T';
    }
    else {
        result += 'F';
    }
    if (found2 == true) {
        result += 'T';
    }
    else {
        result += 'F';
    }
    result += node;
    counts.insert({result,sum});
    return sum;
}

int main(){
    int inputs = 586;
    for (int i = 0; i < inputs; i++) {
        std::string input;
        std::getline(std::cin, input);
        std::string node;
        std::string edge;
        std::vector<std::string> edges;
        int num = 1;
        for (int j = 0; j < input.size(); j++) {
            if (input[j] == ':') {
                num = 2;
                j++;
            }
            else if (input[j] == ' ') {
                edges.push_back(edge);
                edge.clear();
            }
            else if (num == 1) {
                node += input[j];
            }
            else if (num == 2) {
                edge += input[j];
            }
        }
        edges.push_back(edge);
        nodes.insert({node,edges});
    }
    std::cout << paths("svr", false, false);
}