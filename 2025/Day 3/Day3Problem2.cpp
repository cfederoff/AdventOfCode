#include<iostream>
int main() {
    int inputs = 200;
    long count = 0;
    for (int i = 0 ; i < inputs; i++) {
        std::string input;
        std::cin >> input;
        long dp[100];
        dp[11] = std::stol(input.substr(0,12));
        for (int j = 12; j < input.length(); j++) {
            long maxNum = dp[j-1];
            std::string maxNumString = std::to_string(maxNum);
            char x =  input[j];
            char hold = maxNumString[11];
            maxNumString[11] = x;
            if (maxNum < std::stol(maxNumString)) {
                maxNum = std::stol(maxNumString);
            }
            for (int k = 10; k >= 0; k--) {
                char swap = maxNumString[k];
                maxNumString[k] = hold;
                hold = swap;
                if (maxNum < std::stol(maxNumString)) {
                    maxNum = std::stol(maxNumString);
                }
            }
            dp[j] = maxNum;
        }
        count += dp[99];
    }
    std::cout << count;
}
