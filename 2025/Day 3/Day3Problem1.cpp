//Go from left to right checking if the next number if bigger than the first number
//and then bigger than then second number. If it's bigger than the first and its not the
//last number replace it. If it's bigger than the second number replace __int128_t
#include<iostream>
int main() {
    int inputs = 200;
    int count = 0;
    for (int i = 0 ; i < inputs; i++) {
        std::string input;
        std::cin >> input;
        char largestFirstNumber = input[0];
        char largestSecondNumber = input[1];
        for (int j = 1; j < input.length()-1; j++) {
            if (input[j] > largestFirstNumber) {
                largestFirstNumber = input[j];
                largestSecondNumber = input[j+1];
            }
            else if (input[j]> largestSecondNumber ) {
                largestSecondNumber = input[j];
            }
        }
        if (input[input.length()-1] > largestSecondNumber) {
            largestSecondNumber = input[input.length()-1];
        }
        std::string highest;
        highest += largestFirstNumber;
        highest += largestSecondNumber;
        count += stoi(highest);
    }
    std::cout << count;
}