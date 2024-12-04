import java.io.BufferedReader;
import java.io.FileReader;

public class Puzzle1Day4 {
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
                if (wordSearch[i][j] == 'X'){
                    if (i-1 > -1 && wordSearch[i-1][j] == 'M'){
                        if (i-2 > -1 && wordSearch[i-2][j] == 'A'){
                            if (i-3 > -1 && wordSearch[i-3][j] == 'S'){
                                count++;
                            }
                        }
                    }
                    if ((i-1 > -1 && j+1 < length) && wordSearch[i-1][j+1] == 'M'){
                        if ((i-2 > -1 && j+2 < length) && wordSearch[i-2][j+2] == 'A'){
                            if ((i-3 > -1 && j+3 < length) && wordSearch[i-3][j+3] == 'S'){
                                count++;
                            }
                        }
                    }
                    if (j+1 < length && wordSearch[i][j+1] == 'M'){
                        if (j+2 < length && wordSearch[i][j+2] == 'A'){
                            if (j+3 < length && wordSearch[i][j+3] == 'S'){
                                count++;
                            }
                        }
                    }
                    if ((i+1 < length && j+1 < length) && wordSearch[i+1][j+1] == 'M'){
                        if ((i+2 < length && j+2 < length) && wordSearch[i+2][j+2] == 'A'){
                            if ((i+3 < length && j+3 < length) && wordSearch[i+3][j+3] == 'S'){
                                count++;
                            }
                        }
                    }
                    if (i+1 < length && wordSearch[i+1][j] == 'M'){
                        if (i+2 < length && wordSearch[i+2][j] == 'A'){
                            if (i+3 < length && wordSearch[i+3][j] == 'S'){
                                count++;
                            }
                        }
                    }
                    if ((i+1 < length && j-1 > -1) && wordSearch[i+1][j-1] == 'M'){
                        if ((i+2 < length && j-2 > -1) && wordSearch[i+2][j-2] == 'A'){
                            if ((i+3 < length && j-3 > -1) && wordSearch[i+3][j-3] == 'S'){
                                count++;
                            }
                        }
                    }
                    if (j-1 > -1 && wordSearch[i][j-1] == 'M'){
                        if (j-2 > -1 && wordSearch[i][j-2] == 'A'){
                            if (j-3 > -1 && wordSearch[i][j-3] == 'S'){
                                count++;
                            }
                        }
                    }
                    if ((i-1 > -1 && j-1 > -1) && wordSearch[i-1][j-1] == 'M'){
                        if ((i-2 > -1 && j-2 > -1) && wordSearch[i-2][j-2] == 'A'){
                            if ((i-3 > -1 && j-3 > -1) && wordSearch[i-3][j-3] == 'S'){
                                count++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}
