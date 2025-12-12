#include <iostream>
int main(){
    int count = 0;
    for (int i = 0; i < 6; i++) {
        std::string trash;
        std::cin >> trash;
        for (int j = 0; j < 3; j++) {
            std::string input;
            std::cin >> input;
        }
    }
    std::string trash;
    std::string input;
    std::cin >> trash;
    int inputs = 1000;
    for (int i = 0; i < inputs; i++) {
        std::string lengthStr;
        std::string widthStr;
        int num = 1;
        std::getline(std::cin, input);
        if (!trash.empty()) {
            trash += input;
            input = trash;
            trash.clear();
        }
        int length;
        int width;
        std::string number;
        std::vector<int> amountOfPresents;
        for (int j = 0; j < input.size(); j++) {
            if (input[j] == 'x') {
                length = std::stoi(lengthStr);
                num = 2;
            }
            else if (input[j] == ':') {
                width = std::stoi(widthStr);
                num = 3;
                j++;
            }
            else if (num == 1) {
                lengthStr += input[j];
            }
            else if (num == 2) {
                widthStr += input[j];
            }
            else if (num == 3 && input[j] == ' ') {
                amountOfPresents.push_back(std::stoi(number));
                number.clear();
            }
            else if (num == 3 && input[j] != ' ') {
                number += input[j];
            }
        }
        amountOfPresents.push_back(std::stoi(number));
        int sum = 0;
        for (int j = 0; j < 6; j++) {
            sum += amountOfPresents[j] * 8;
        }
        if (sum < length * width) {
            count++;
        }
    }
    std::cout << count;
}
