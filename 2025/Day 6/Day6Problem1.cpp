#include <iostream>
#include <fstream>
int main(){
    std::ifstream inFile;
    inFile.open("input1.txt");
    int num;
    std::string input;
    int numbers[4][1000];
    for (int i = 0; i < 4; i++) {
        std::getline(inFile,input);
        std::string number;
        int numberCount = 0;
        for (int j = 0; j < input.length(); j++) {
            if (input[j] == ' ') {
                if (!number.empty()) {
                    numbers[i][numberCount] = std::stol(number);
                    numberCount++;
                    number.clear();
                }
            }
            else {
                number+= input[j];
            }
        }
        numbers[i][numberCount] = std::stol(number);
    }
    std::getline(inFile,input);
    long sum = 0;
    int numberCount = 0;
    for (int i = 0; i < input.length(); i++) {
        if (input[i] == '+') {
            long count = 0;
            for (int j = 0; j < 4; j++) {
                count += numbers[j][numberCount];
            }
            sum += count;
            numberCount++;
        }
        if (input[i] == '*') {
            long count = 1;
            for (int j = 0; j < 4; j++) {
                count *= numbers[j][numberCount];
            }
            sum += count;
            numberCount++;
        }
    }
    std::cout << sum;
    inFile.close();

}