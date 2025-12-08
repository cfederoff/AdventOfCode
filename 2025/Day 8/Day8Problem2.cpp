#include <iostream>

class UnionFind {
    public:
    std::vector<int> list;
    std::vector<long> size;
    int Find(int x) {
        if (x != list[x]) {
            list[x] = Find(list[x]);
            return list[x];
        }
        return x;
    }
    void Union(int x, int y) {
        x = Find(x);
        y = Find(y);
        if (x == y) {
            return;
        }
        if (size[x] < size[y]) {
            int hold = x;
            x = y;
            y = hold;
        }
        list[y] = x;
        size[x] += size[y];
    }
};

int main(){
    int inputs = 1000;
    std::vector<std::tuple<int,int,int>> coordinates;
    for (int i = 0; i < inputs; i++) {
        std::string input;
        std::cin >> input;
        std::string num1str;
        std::string num2str;
        std::string num3str;
        int number = 1;
        for (int j = 0; j < input.length(); j++) {
            if (input[j] == ',') {
                if (number == 1) {
                    number = 2;
                }
                else {
                    number = 3;
                }
            }
            else {
                if (number == 1) {
                    num1str += input[j];
                }
                else if (number == 2) {
                    num2str += input[j];
                }
                else {
                    num3str += input[j];
                }
            }
        }
        coordinates.emplace_back(std::stoi(num1str),std::stoi(num2str), std::stoi(num3str));
    }
    std::priority_queue<std::pair<double,std::pair<int,int>>,std::vector<std::pair<double,std::pair<int,int>>>,std::greater<>> distances;
    for (int i = 0; i < inputs; i++) {
        for (int j = i+1; j < inputs; j++) {
            double output = std::sqrt(std::pow(std::get<0>(coordinates[i])-std::get<0>(coordinates[j]),2)+
                                      std::pow(std::get<1>(coordinates[i])-std::get<1>(coordinates[j]),2)+
                                      std::pow(std::get<2>(coordinates[i])-std::get<2>(coordinates[j]),2));
            distances.push({output,{i,j}});
        }
    }
    UnionFind uf;
    for (int i = 0; i < inputs; i++) {
        uf.list.push_back(i);
        uf.size.push_back(1);
    }
    while (true){
        uf.Union(distances.top().second.first,distances.top().second.second);
        if (uf.size[uf.Find(distances.top().second.first)] == inputs) {
            break;
        }
        distances.pop();
    }
    std::cout << std::get<0>(coordinates[distances.top().second.first])*std::get<0>(coordinates[distances.top().second.second]);
}