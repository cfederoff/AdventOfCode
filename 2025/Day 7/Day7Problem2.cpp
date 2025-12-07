#include <iostream>

int main(){
    int size = 142;
    char grid[142][142];
    std::pair<int,int> start;
    for (int i = 0; i < size; i++) {
        std::string input;
        std::cin >> input;
        for (int j = 0; j < size; j++) {
            grid[i][j] = input[j];
            if (input[j] == 'S') {
                start.first = i;
                start.second = j;
            }
        }
    }
    std::queue<std::pair<int,int>> beams;
    beams.emplace(start.first,start.second);
    while (!beams.empty()) {
        std::pair<int,int> currentBeams = beams.front();
        if (currentBeams.first+1 < size) {
            if (grid[currentBeams.first+1][currentBeams.second] == '^') {
                if (currentBeams.second-1 >= 0) {
                    if (grid[currentBeams.first+1][currentBeams.second-1] != '|') {
                        grid[currentBeams.first+1][currentBeams.second-1] = '|';
                        beams.emplace(currentBeams.first+1,currentBeams.second-1);
                    }
                    if (grid[currentBeams.first+1][currentBeams.second+1] != '|') {
                        grid[currentBeams.first+1][currentBeams.second+1] = '|';
                        beams.emplace(currentBeams.first+1,currentBeams.second+1);
                    }
                }
            }
            else {
                grid[currentBeams.first+1][currentBeams.second] = '|';
                beams.emplace(currentBeams.first+1,currentBeams.second);
            }
        }
        beams.pop();
    }
    long paths[142][142] = {};
    for (int i = 0; i < size; i++) {
        if (grid[size-1][i] == '|') {
            int y = size-1;
            while (grid[y][i] == '|') {
                paths[y][i] = 1;
                y++;
            }
        }
    }
    for (int i = size-2; i >= 1; i--) {
        for (int j = 0; j < size; j++) {
            if (grid[i][j] == '|' && paths[i][j] == 0) {
                if (grid[i+1][j] == '|') {
                    paths[i][j] = paths[i+1][j];
                }
                if (grid[i+1][j] == '^') {
                    if (j-1 >= 0) {
                        paths[i][j] += paths[i+1][j-1];
                    }
                    if (j+1 < size) {
                        paths[i][j] += paths[i+1][j+1];
                    }
                }
            }
        }
    }
    std::cout << paths[start.first+1][start.second];
}