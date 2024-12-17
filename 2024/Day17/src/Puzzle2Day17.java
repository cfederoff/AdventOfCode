public class Puzzle2Day17 {
    public static void main(String[] args){
        long low = 164540884001221L;
        long high = 164540903001221L;
        while (low < high){
            if (exec(low).equals("2,4,1,1,7,5,1,5,4,5,0,3,5,5,3,0")) {
                System.out.println(low);
                break;
            }
            low++;
        }
    }
    public static String exec(long n){
        String out = "";
        while(true){
            long b = (n % 8) ^ 1;
            long c = n / (long) Math.pow(2,b);
            out = out + (b ^ c ^ 5) % 8;
            n /= 8;
            if (n == 0){
                break;
            }
            out = out + ',';
        }
        return out;
    }
}
