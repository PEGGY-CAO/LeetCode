import java.util.Scanner;
import java.io.*;

public class MemoryLeak {

    static void printResult(long m1, long m2) {
        int counting = 1;
        while (m1 > 0 && m2 > 0) {
            if (m1 >= m2) {
                // deducting from m1
                if (m1 - counting < 0) {
                    break;
                } else {
                    m1 -= counting;
                    counting++;
                }
            } else {
                // deducting from m2
                if (m2 - counting < 0) {
                    break;
                } else {
                    m2 -= counting;
                    counting++;
                }
            }
        }

        System.out.println(counting + " " + m1 + " " + m2);
    }


    public static void main(String[] args) {
        Scanner scan1 = new Scanner(System.in);
        int numOfTestCases = Integer.parseInt(scan1.nextLine());
        long[][] memoryCases = new long[numOfTestCases][2];

        for (int i = 0; i < numOfTestCases; i++) {
            String testCase = scan1.nextLine();
            String[] memories = testCase.split(" ");
            memoryCases[i][0] = Long.parseLong(memories[0]);
            memoryCases[i][1] = Long.parseLong(memories[1]);
            i++;
            System.out.println();
        }

//        for (int i = 0; i < numOfTestCases; i++) {
//            printResult(memoryCases[i][0], memoryCases[i][1]);
//        }
    }
}
