import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class Puzzle2Day22 {
    public static void main(String[] args) {
        final int length = 2008;
        HashMap<tuple,Long>[] changes = new HashMap[length];
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day22/InputDay22.txt"));
            String line;
            int lineCount = 0;
            while ((line = file.readLine()) != null){
                changes[lineCount] = new HashMap();
                LinkedList<Long> list = new LinkedList<>();
                long num = Long.parseLong(line);
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
                if (!changes[lineCount].containsKey(new tuple(list.get(0),list.get(1),list.get(2),list.get(3)))){
                    changes[lineCount].put(new tuple(list.get(0),list.get(1),list.get(2),list.get(3)),num%10);
                }
                list.removeFirst();
                for (int i = 4; i <= 2000; i++) {
                    num = secretNumber(num);
                    num2 = num;
                    dif = num2 % 10 - num1 % 10;
                    list.addLast(dif);
                    if (!changes[lineCount].containsKey(new tuple(list.get(0),list.get(1),list.get(2),list.get(3)))){
                        changes[lineCount].put(new tuple(list.get(0),list.get(1),list.get(2),list.get(3)),num%10);
                    }
                    num1 = num2;
                    list.removeFirst();
                }
                lineCount++;
            }
        } catch (Exception e){
            System.out.println("File Error");
        }
        long highest = 0;
        for (long one = -9; one < 10; one++){
            for (long two = -9; two < 10; two++){
                for (long three = -9; three < 10; three++){
                    for (long four = -9; four < 10; four++){
                        long total = 0;
                        for (int i = 0; i < length; i++){
                            if (changes[i].containsKey(new tuple(one,two,three,four))){
                                total += changes[i].get(new tuple(one,two,three,four));
                            }
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
    public static class tuple{
        long one;
        long two;
        long three;
        long four;
        int hashCode;
        tuple(long one, long two, long three, long four){
            this.one = one;
            this.two = two;
            this.three = three;
            this.four = four;
            this.hashCode = Objects.hash(one,two,three,four);
        }
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof tuple Tuple){
                return this.one == Tuple.one && this.two == Tuple.two && this.three == Tuple.three && this.four == Tuple.four;
            }
            return false;
        }
        @Override
        public int hashCode() {
            return this.hashCode;
        }

    }
    public static long secretNumber(long num){
        num = num ^ (num * 64) % 16777216;
        num = num ^ (num / 32) % 16777216;
        num = num ^ (num * 2048) % 16777216;
        return num;
    }
}