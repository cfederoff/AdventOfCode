import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Puzzle2Day22 {
    public static void main(String[] args) {

        long highest = 0;
        for (int one = -9; one < 10; one++){
     for (int two = -9; two < 10; two++){
                for (int three = -9; three < 10; three++){
                    for (int four = -9; four < 10; four++){
                        long total = 0;
                        try {
                            BufferedReader file = new BufferedReader(new FileReader("2024/Day22/InputDay22.txt"));
                            String line;
                            while ((line = file.readLine()) != null) {
                                LinkedList<Long> list = new LinkedList<>();
                                long num = Long.parseLong(line);
                                boolean found = false;
                                long num1 = num;
                                long num2 = 0;
                                long dif = 0;
                                for (int i = 1; i <= 4; i++) {
                                    num = secretNumber(num);
                                    num2 = num;
                                    dif = num2 % 10 - num1 % 10;
                                    list.addLast(dif);
                                    num1 = num2;
                                }
                                if (list.get(0) != one || list.get(1) != two || list.get(2) != three || list.get(3) != four) {
                                    list.removeFirst();
                                    for (int i = 4; i <= 2000; i++) {
                                        num = secretNumber(num);
                                        num2 = num;
                                        dif = num2 % 10 - num1 % 10;
                                        list.addLast(dif);
                                        num1 = num2;
                                        if (list.get(0) == one && list.get(1) == two && list.get(2) == three && list.get(3) == four) {
                                            found = true;
                                            break;
                                        } else {
                                            list.removeFirst();
                                        }
                                    }
                                    if (!found) {
                                        num = 0;
                                    }
                                }
                                total += num%10;
                            }
                        } catch (IOException e){
                            System.out.println("File issue");
                        }
                        if (total > highest){
                            highest = total;
                        }
                    }
                }
            }
        }
        System.out.println(highest);

    }
    public static long secretNumber(long num){
        num = num ^ (num * 64) % 16777216;
        num = num ^ (num / 32) % 16777216;
        num = num ^ (num * 2048) % 16777216;
        return num;
    }
}
