#include <complex>
#include <iostream>
int main() {
    int rotationCount = 4136;
    int position = 50;
    int count = 0;
    std::vector<std::string> rotations;
    for (int i = 0; i < rotationCount; i++) {
        std::string rotation;
        std::cin >> rotation;
        if (rotation[0] == 'L') {
            position = (position - std::stoi(rotation.substr(1,rotation.length()-1)))%100;
            if (position < 0) {
                position += 100;
            }
        }
        else {
            position = (position+std::stoi(rotation.substr(1,rotation.length()-1)))%100;
        }
        if (position == 0) {
            count++;
        }
    }
    std::cout << count;
    return 0;
}