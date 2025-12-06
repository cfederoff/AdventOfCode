#include <iostream>

int main(){
    int inputs = 187;
    std::vector<std::pair<long,long>> ranges;
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
        ranges.push_back({std::stol(num1str),std::stol(num2str)});
    }
    std::sort(ranges.begin(),ranges.end());
    std::vector<std::pair<long,long>> fixedRanges;
    fixedRanges.push_back({ranges[0].first,ranges[0].second});
    for (int i = 1; i < inputs; i++) {
        if (fixedRanges.back().first <= ranges[i].first && fixedRanges.back().second >= ranges[i].first ) {
            if (fixedRanges.back().second < ranges[i].second) {
                fixedRanges.back().second = ranges[i].second;
            }
        }
        else {
            fixedRanges.push_back({ranges[i].first, ranges[i].second});
        }
    }
    long count = 0;
    for (int i = 0; i < fixedRanges.size(); i++) {
        count += fixedRanges[i].second - fixedRanges[i].first + 1;
    }
    std::cout << count;
}