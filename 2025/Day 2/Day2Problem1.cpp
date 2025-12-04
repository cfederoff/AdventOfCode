#include <complex>
#include <iostream>

int main() {
    std::string input;
    std::cin >> input;
    input+=',';
    long count = 0;
    std::string word1 = "";
    std::string word2 = "";
    int word = 1;
    for (int i = 0; i < input.length(); i++) {
        if (input[i] == '-') {
            word = 2;
        }
        else if (input[i] == ',' ) {
            for (long i = stol(word1); i <= stol(word2); i++) {
                std::string number = std::to_string(i);
                if (number.length() % 2 == 0) {
                    if (number.substr(0,number.length()/2) == number.substr(number.length()/2,number.length()/2)) {
                        count += i;
                    }
                }
            }
            word1.clear();
            word2.clear();
            word = 1;
        }
        else {
            if (word == 1) {
                word1 += input[i];
            }
            else {
                word2 += input[i];
            }
        }
    }
    std::cout << count;
}