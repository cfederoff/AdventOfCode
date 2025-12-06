#include <iostream>
#include <fstream>
int main(){
    std::ifstream inFile;
    inFile.open("input2.txt");
    int num;
    std::string input;
    std::string numbers[4];
    for (int i = 0; i < 4; i++) {
        std::getline(inFile,numbers[i]);
    }
    std::getline(inFile,input);
    long sum = 0;
    int numberCount = 0;
    input += "   ";
    for (int i = 0; i < input.length(); i++){
        if (input[i] == '+') {
            int spots = 0;
            for (int j = i+1; j < input.length(); j++) {
                if (input[j] != ' ') {
                    break;
                }
                spots++;
            }
            long count = 0;
            for (int j = 0; j < spots; j++) {
                std::string number;
                for (int k = 0; k < 4; k++) {
                    if (numbers[k][j+i] != ' ') {
                        number += numbers[k][j+i];
                    }
                }
                if (!number.empty()) {
                    count += std::stol(number);
                }
            }
            sum += count;
            numberCount++;
        }
        if (input[i] == '*') {
            int spots = 0;
            for (int j = i+1; j < input.length(); j++) {
                if (input[j] != ' ') {
                    break;
                }
                spots++;
            }
            long count = 1;
            for (int j = 0; j < spots; j++) {
                std::string number;
                for (int k = 0; k < 4; k++) {
                    if (numbers[k][j+i] != ' ') {
                        number += numbers[k][j+i];
                    }
                }
                if (!number.empty()) {
                    count *= std::stol(number);
                }
            }
            sum += count;
            numberCount++;
        }
    }
    std::cout << sum;
    inFile.close();

}