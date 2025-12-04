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
                if (number.length() > 1) {
                    bool same = true;
                    for (int j = 1; j < number.length(); j++) {
                        if (number[j] != number[0]) {
                            same = false;
                        }
                    }
                    if (same) {
                        count+= i;
                        continue;
                    }
                    if (number.length() % 2 == 0 && number.length() > 2) {
                        std::string sub = number.substr(0,2);
                        same = true;
                        for (int j = 2; j < number.length(); j+=2) {
                            if (sub != number.substr(j,2)) {
                                same = false;
                            }
                        }
                        if (same) {
                            count+= i;
                            continue;
                        }
                    }
                    if (number.length() % 3 == 0 && number.length() > 3) {
                        std::string sub = number.substr(0,3);
                        same = true;
                        for (int j = 3; j < number.length(); j+=3) {
                            if (sub != number.substr(j,3)) {
                                same = false;
                            }
                        }
                        if (same) {
                            count+= i;
                            continue;
                        }
                    }
                    if (number.length() % 4 == 0 && number.length() > 4) {
                        std::string sub = number.substr(0,4);
                        same = true;
                        for (int j = 4; j < number.length(); j+=4) {
                            if (sub != number.substr(j,4)) {
                                same = false;
                            }
                        }
                        if (same) {
                            count+= i;
                            continue;
                        }
                    }
                    if (number.length() % 5 == 0 && number.length() > 5) {
                        std::string sub = number.substr(0,5);
                        same = true;
                        for (int j = 5; j < number.length(); j+=5) {
                            if (sub != number.substr(j,5)) {
                                same = false;
                            }
                        }
                        if (same) {
                            count+= i;
                        }
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