#include <iostream>

int main(){
    int inputs = 189;
    long sum = 0;
    for (int i = 0; i < inputs; i++) {
        std::string input;
        std::getline(std::cin, input);
        std::vector<int> panel;
        std::vector<int> schematic;
        std::vector<std::vector<int>> schematics;
        int checking = 1;
        for (int j = 1; j < input.size(); j++) {
            if (input[j] == ']') {
                checking = 2;
            }
            else if (input[j] == '{') {
                break;
            }
            else if (checking == 1) {
                if (input[j] == '.') {
                    panel.push_back(0);
                }
                else {
                    panel.push_back(1);
                }
            }
            else if (input[j] == ')') {
                schematics.push_back(schematic);
                schematic.clear();
            }
            else if (input[j] != ' ' && input[j] != '(' && input[j] != ',') {
                schematic.push_back(input[j]-48);
            }
        }
        int min = INT32_MAX;
        for (int j = 1; j < std::pow(2,schematics.size()); j++) {
            std::vector<int> currentCheck(panel.size(),0);
            int on = 0;
            for (int k = 0; k < schematics.size(); k++) {
                if (j >> k & 1){
                    on++;
                    for (int h = 0; h < schematics[k].size(); h++) {
                        if (currentCheck[schematics[k][h]] == 0) {
                            currentCheck[schematics[k][h]] = 1;
                        }
                        else {
                            currentCheck[schematics[k][h]] = 0;
                        }
                    }
                }
            }
            bool good = true;
            for (int k = 0; k < panel.size(); k++) {
                if (panel[k] != currentCheck[k]) {
                    good = false;
                    break;
                }
            }
            if (good) {
                if (min > on) {
                    min = on;
                }
            }
        }
        sum += min;
    }
    std::cout << sum;
}