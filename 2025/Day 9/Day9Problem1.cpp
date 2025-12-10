#include <iostream>
#include <set>

int main(){
    int inputs = 496;
    std::vector<std::pair<long,long>> coords;
    for (int i = 0; i < inputs; i++){
        std::string input;
        std::cin >> input;
        std::string num1str;
        std::string num2str;
        int num = 1;
        for (int j = 0; j < input.size(); j++) {
            if (input[j] == ',') {
                num = 2;
            }
            else if (num == 1) {
                num1str += input[j];
            }
            else {
                num2str += input[j];
            }
        }
        coords.emplace_back(std::stol(num1str),std::stol(num2str));
    }
    long max = 0;
    for (int i = 0; i < inputs; i++) {
        for (int j = 0; j < inputs; j++) {
            if ((std::abs(coords[i].first-coords[j].first)+1)*(std::abs(coords[i].second-coords[j].second)+1) > max) {
                max = (std::abs(coords[i].first-coords[j].first)+1)*(std::abs(coords[i].second-coords[j].second)+1);
            }
        }
    }
    std::cout << max;
}

