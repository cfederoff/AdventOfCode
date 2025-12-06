#include <iostream>

int main(){
    int inputs = 187;
    long rangeArray[inputs][2];
    for (int i = 0; i < inputs; i++) {
        std::string input;
        std::cin >> input;
        std::string num1str;
        std::string num2str;
        int num = 1;
        for (int j = 0; j < input.length(); j++) {
            if (input[j] == '-') {
                num = 2;
            }
            else if (num == 1) {
                num1str += input[j];
            }
            else {
                num2str += input[j];
            }
        }
        rangeArray[i][0] = std::stol(num1str);
        rangeArray[i][1] = std::stol(num2str);
    }
    std::cin;
    int count = 0;
    for (int i = 0; i < 1000; i++) {
        long input;
        std::cin >> input;
        for (int j = 0; j < inputs; j++) {
            if (input >= rangeArray[j][0] && input <= rangeArray[j][1]) {
                count++;
                break;
            }
        }
    }
    std::cout << count;
}
