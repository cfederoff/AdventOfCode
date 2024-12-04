import java.io.BufferedReader;
import java.io.FileReader;

public class Puzzle2Day4 {
    public static void main(String[] args){
        int length = 140;
        char[][] wordSearch = new char[length][length];
        int row = 0;
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day4/InputDay4.txt"));
            String line;
            while ((line = file.readLine()) != null){
                for (int i = 0; i < length; i++) {
                    wordSearch[row][i] = line.charAt(i);
                }
                row++;
            }
        } catch (Exception e){
            System.out.println("File issue");
        }
        int count = 0;
        for (int i = 0; i < length; i++){
            for (int j = 0; j < length; j++){
                if (wordSearch[i][j] == 'A'){
                    if ((i-1 > -1 && i+1<length) && (j-1 > -1 && j+1<length)){
                        if (wordSearch[i-1][j+1] == 'M'){
                            if (wordSearch[i-1][j-1] == 'M'){
                                if (wordSearch[i+1][j+1] == 'S' && wordSearch[i+1][j-1] == 'S'){
                                    count++;
                                }
                            }
                            else if (wordSearch[i+1][j+1] == 'M'){
                                if (wordSearch[i-1][j-1] == 'S' && wordSearch[i+1][j-1] == 'S'){
                                    count++;
                                }
                            }
                        }
                        if (wordSearch[i-1][j+1] == 'S'){
                            if (wordSearch[i-1][j-1] == 'S'){
                                if (wordSearch[i+1][j+1] == 'M' && wordSearch[i+1][j-1] == 'M'){
                                    count++;
                                }
                            }
                            else if (wordSearch[i+1][j+1] == 'S'){
                                if (wordSearch[i-1][j-1] == 'M' && wordSearch[i+1][j-1] == 'M'){
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}
