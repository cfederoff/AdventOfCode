#include <iostream>

std::unordered_map<std::string, std::vector<std::string>> nodes;
std::unordered_map<std::string, long > counts;

long paths(const std::string& node) {
    if (node == "out") {
        counts.insert({node,1});
        return 1;
    }
    std::vector<std::string> edges = nodes.at(node);
    long sum = 0;
    for (std::string x : edges) {
        if (counts.find(x) == counts.end()) {
            long count = paths(x);
            counts.insert({x,count});
            sum += count;
        }
        else {
            sum += counts.at(x);
        }
    }
    counts.insert({node,sum});
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
    std::cout << paths("you");
}