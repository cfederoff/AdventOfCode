#include <iostream>
#include <set>

bool compareElements(std::pair<long,long> a, std::pair<long,long> b) {
    if (a.first == b.first) {
        return a.second > b.second;
    }
    return a.first < b.first;
}

std::vector<std::pair<long,long>> fixRanges(int inputs, std::vector<std::pair<long,long>> ranges){
    std::sort(ranges.begin(),ranges.end(), compareElements);
    std::vector<std::pair<long,long>> fixedRanges;
    fixedRanges.emplace_back(ranges[0].first,ranges[0].second);
    for (int i = 1; i < inputs; i++) {
        if (fixedRanges.back().first <= ranges[i].first && fixedRanges.back().second >= ranges[i].first ) {
            if (fixedRanges.back().second < ranges[i].second) {
                fixedRanges.back().second = ranges[i].second;
            }
        }
        else {
            fixedRanges.emplace_back(ranges[i].first, ranges[i].second);
        }
    }
    return fixedRanges;
}

int main(){
    int inputs = 496;
    std::vector<std::pair<long,long>> coords;
    for (int i = 0; i < inputs; i++){
        std::string input;
        std::cin >> input;
        std::string num1str;
        std::string num2str;
        int num = 1;
        for (int j = 0; j < input.size(); j++) {
            if (input[j] == ',') {
                num = 2;
            }
            else if (num == 1) {
                num1str += input[j];
            }
            else {
                num2str += input[j];
            }
        }
        coords.emplace_back(std::stol(num1str),std::stol(num2str));
    }
    long max = 0;
    for (int i = 0; i < inputs; i++) {
        for (int j = i+1; j < inputs; j++) {
            std::pair<int,int> point1;
            std::pair<int,int> point2;
            if (coords[i].first <= coords[j].first) {
                point1 = coords[i];
                point2 = coords[j];
            }
            else {
                point1 = coords[j];
                point2 = coords[i];
            }
            std::pair<std::pair<int,int>,std::pair<int,int>> range1;
            std::pair<std::pair<int,int>,std::pair<int,int>> range2;
            std::pair<std::pair<int,int>,std::pair<int,int>> range3;
            std::pair<std::pair<int,int>,std::pair<int,int>> range4;
            if (point1.second <= point2.second) {
                range1.first = {point1.first,point2.first};
                range1.second = {point1.second,point1.second};
                range2.first = {point2.first,point2.first};
                range2.second = {point1.second,point2.second};
                range3.first = {point1.first,point2.first};
                range3.second  = {point2.second,point2.second};
                range4.first = {point1.first,point1.first};
                range4.second = {point1.second,point2.second};
            }
            else {
                range1.first = {point1.first,point2.first};
                range1.second = {point2.second,point2.second};
                range2.first = {point2.first,point2.first};
                range2.second = {point2.second,point1.second};
                range3.first = {point1.first,point2.first};
                range3.second = {point1.second,point1.second};
                range4.first = {point1.first, point1.first};
                range4.second = {point2.second,point1.second};
            }
            std::vector<std::pair<long,long>> checkRange1;
            std::vector<std::pair<long,long>> checkRange2;
            std::vector<std::pair<long,long>> checkRange3;
            std::vector<std::pair<long,long>> checkRange4;

            for (int k = 1; k < inputs; k++) {
                if (coords[k-1].first == coords[k].first) {
                    if (coords[k-1].second <= coords[k].second && coords[k-1].first >= range2.first.first) {
                        if (!(coords[k].second < range2.second.first) && !(coords[k-1].second > range2.second.second)) {
                                checkRange2.emplace_back(std::max(range2.second.first,(int) coords[k-1].second),
                                                         std::min(range2.second.second, (int) coords[k].second));
                        }
                    }
                    else if (coords[k-1].second > coords[k].second && coords[k-1].first <= range4.first.first) {
                        if (!(coords[k-1].second < range2.second.first) && !(coords[k].second > range2.second.second)) {
                            checkRange4.emplace_back(std::max(range2.second.first, (int) coords[k].second),
                                                     std::min(range2.second.second,(int) coords[k-1].second));
                        }
                    }
                }
                else {
                    if (coords[k-1].first <= coords[k].first && coords[k-1].second <= range1.second.second) {
                        if (!(coords[k].first < range1.first.first) && !(coords[k-1].first > range1.first.second)) {
                            checkRange1.emplace_back(std::max(range1.first.first, (int) coords[k-1].first),
                         std::min(range1.first.second, (int) coords[k].first));
                        }
                    }
                    else if (coords[k-1].first > coords[k].first && coords[k-1].second >= range3.second.second) {
                        if (!(coords[k-1].first < range1.first.first) && !(coords[k].first > range1.first.second)) {
                            checkRange3.emplace_back(std::max(range1.first.first, (int) coords[k].first),
                         std::min(range1.first.second, (int)coords[k-1].first));
                        }
                    }
                }
            }
            if (coords[inputs-1].first == coords[0].first) {
                    if (coords[inputs-1].second <= coords[0].second && coords[inputs-1].first >= range2.first.first) {
                        if (!(coords[0].second < range2.second.first) && !(coords[inputs-1].second > range2.second.second)) {
                                checkRange2.emplace_back(std::max(range2.second.first,(int) coords[inputs-1].second),
                                                         std::min(range2.second.second, (int) coords[0].second));
                        }
                    }
                    else if (coords[inputs-1].second > coords[0].second && coords[inputs-1].first <= range4.first.first) {
                        if (!(coords[inputs-1].second < range2.second.first) && !(coords[0].second > range2.second.second)) {
                            checkRange4.emplace_back(std::max(range2.second.first, (int) coords[0].second),
                                                     std::min(range2.second.second,(int) coords[inputs-1].second));
                        }
                    }
            }
            else {
                if (coords[inputs-1].first <= coords[0].first && coords[inputs-1].second <= range1.second.second) {
                    if (!(coords[0].first < range1.first.first) && !(coords[inputs-1].first > range1.first.second)) {
                            checkRange1.emplace_back(std::max(range1.first.first, (int) coords[inputs-1].first),
                         std::min(range1.first.second, (int) coords[0].first));
                        }
                    }
                    else if (coords[inputs-1].first > coords[0].first && coords[inputs-1].second >= range3.second.second) {
                        if (!(coords[inputs-1].first < range1.first.first) && !(coords[0].first > range1.first.second)) {
                            checkRange3.emplace_back(std::max(range1.first.first, (int) coords[0].first),
                         std::min(range1.first.second, (int)coords[inputs-1].first));
                        }
                    }
                }
            std::pair<int,int> finalRange1;
            std::pair<int,int> finalRange2;
            std::pair<int,int> finalRange3;
            std::pair<int,int> finalRange4;
            if (fixRanges(checkRange1.size(),checkRange1).size() == 1) {
                finalRange1 = fixRanges(inputs,checkRange1)[0];
            }
            else {
                continue;
            }
            if (fixRanges(checkRange2.size(),checkRange2).size() == 1) {
                finalRange2 = fixRanges(inputs,checkRange2)[0];
            }
            else {
                continue;
            }
            if (fixRanges(checkRange3.size(),checkRange3).size() == 1) {
                finalRange3 = fixRanges(inputs,checkRange3)[0];
            }
            else {
                continue;
            }
            if (fixRanges(checkRange4.size(),checkRange4).size() == 1) {
                finalRange4 = fixRanges(inputs,checkRange4)[0];
            }
            else {
                continue;
            }
            if (range1.first.first >= finalRange1.first && range1.first.second <= finalRange1.second &&
                range2.second.first >= finalRange2.first && range2.second.second <= finalRange2.second &&
                range3.first.first >= finalRange3.first && range3.first.second <= finalRange3.second &&
                range4.second.first >= finalRange4.first && range4.second.second <= finalRange4.second) {
                if ((std::abs(coords[i].first-coords[j].first)+1)*(std::abs(coords[i].second-coords[j].second)+1) > max) {
                    max = (std::abs(coords[i].first-coords[j].first)+1)*(std::abs(coords[i].second-coords[j].second)+1);
                }
            }
        }
    }
    std::cout << max;
}

