import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle1Day17 {
    public static void main(String[] args){
        long registerA = 0;
        long registerB = 0;
        long registerC = 0;
        String line = "";
        try {
            BufferedReader file = new BufferedReader(new FileReader("2024/Day17/InputDay17.txt"));
            line = file.readLine();
            registerA = Long.parseLong(line.substring(12));
            line = file.readLine();
            registerB = Long.parseLong(line.substring(12));
            line = file.readLine();
            registerC = Long.parseLong(line.substring(12));
            line = file.readLine();
            line = file.readLine();
        } catch (IOException e){
            System.out.println("File not Found");
        }
        String out = "";
        for (int i = 9; i < line.length(); i+=4){
            long opcode = Long.parseLong(line.substring(i,i+1));
            long operand = Long.parseLong(line.substring(i+2,i+3));
            switch ((int) opcode){
                case 0:
                    if (operand == 0 || operand == 1 || operand == 2 || operand == 3){
                        registerA = (long) (registerA / Math.pow(2,operand));
                    }
                    else if (operand == 4){
                        registerA = (long) (registerA / Math.pow(2,registerA));
                    }
                    else if (operand == 5){
                        registerA = (long) (registerA / Math.pow(2,registerB));
                    }
                    else if (operand == 6){
                        registerA = (long) (registerA / Math.pow(2,registerC));
                    }
                    break;
                case 1:
                    registerB = registerB ^ operand;
                    break;
                case 2:
                    if (operand == 0 || operand == 1 || operand == 2 || operand == 3){
                        registerB = operand % 8;
                    }
                    else if (operand == 4){
                        registerB = registerA % 8;
                    }
                    else if (operand == 5){
                        registerB = registerB % 8;
                    }
                    else if (operand == 6){
                        registerB = registerC % 8;
                    }
                    break;
                case 3:
                    if (registerA != 0){
                        i = (int) (9 - 4 + (operand*4));
                    }
                    break;
                case 4:
                    registerB = registerB ^ registerC;
                    break;
                case 5:
                    if (operand == 0 || operand == 1 || operand == 2 || operand == 3){
                        out = out + operand % 8 + ',';
                    }
                    else if (operand == 4){
                        out = out + registerA % 8 + ',';
                    }
                    else if (operand == 5){
                        out = out + registerB % 8 + ',';
                    }
                    else if (operand == 6){
                        out = out + registerC % 8 + ',';
                    }
                    break;
                case 6:
                    if (operand == 0 || operand == 1 || operand == 2 || operand == 3){
                        registerB = (long) (registerA / Math.pow(2,operand));
                    }
                    else if (operand == 4){
                        registerB = (long) (registerA / Math.pow(2,registerA));
                    }
                    else if (operand == 5){
                        registerB = (long) (registerA / Math.pow(2,registerB));
                    }
                    else if (operand == 6){
                        registerB = (long) (registerA / Math.pow(2,registerC));
                    }
                    break;
                case 7:
                    if (operand == 0 || operand == 1 || operand == 2 || operand == 3){
                        registerC = (long) (registerA / Math.pow(2,operand));
                    }
                    else if (operand == 4){
                        registerC = (long) (registerA / Math.pow(2,registerA));
                    }
                    else if (operand == 5){
                        registerC = (long) (registerA / Math.pow(2,registerB));
                    }
                    else if (operand == 6){
                        registerC = (long) (registerA / Math.pow(2,registerC));
                    }
                    break;
                default:
                    throw new RuntimeException();
            }
        }
        out = out.substring(0,out.length()-1);
        System.out.println(out);
    }
}
