#include <iostream>

int main() {
    char map[136][136];
    constexpr int rowCount = 136;
    constexpr int columnCount = 136;
    for (int i = 0; i < rowCount; i++) {
        std::string input;
        std::cin >> input;
        for (int j = 0; j < columnCount; j++) {
            map[i][j] = input[j];
        }
    }
    int totalCount = 0;
    for (int i = 0; i < rowCount; i++) {
        for (int j = 0; j < columnCount; j++) {
            if (map[i][j] != '.') {
                int count = 0;
                if (i - 1 >= 0) {
                    if (j-1 >= 0) {
                        if (map[i-1][j-1] != '.') {
                            count++;
                        }
                    }
                    if (map[i-1][j] != '.') {
                        count++;
                    }
                    if (j+1 < columnCount) {
                        if (map[i-1][j+1] != '.') {
                            count++;
                        }
                    }
                }
                if (j-1 >= 0) {
                    if (map[i][j-1] != '.') {
                        count++;
                    }
                }
                if (j+1 < columnCount) {
                    if (map[i][j+1] != '.') {
                        count++;
                    }
                }
                if (i+1 < rowCount) {
                    if (j-1 >= 0) {
                        if (map[i+1][j-1] != '.') {
                            count++;
                        }
                    }
                    if (map[i+1][j] != '.') {
                        count++;
                    }
                    if (j+1 < columnCount) {
                        if (map[i+1][j+1] != '.') {
                            count++;
                        }
                    }
                }
                if (count < 4) {
                    map[i][j] = 'x';
                    totalCount++;
                }
            }
        }
    }
    std::cout << totalCount;
}