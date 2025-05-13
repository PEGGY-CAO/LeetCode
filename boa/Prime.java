// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;

class Main {

    //Q1. is Prime
    public boolean isPrime(int target) {
        if (target <= 1) {
            return false;
        }
        if (target == 2) {
            return true;
        }

        for (int i = 2; i <= (int) Math.sqrt(target); i++) {
            if (target % i == 0) {
                return false;
            }
        }
        return true;
    }

    //Q2 M(n) = 2^n - 1 first 5
    public List<Integer> findM_n(int n) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            if (isPrime(i)) {
                int potential = (int)Math.pow(2, i) - 1;
                if (isPrime(potential)) {
                    result.add(potential);
                }
                if (result.size() == 5) {
                    return result;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Try programiz.pro");
        Main test = new Main();
        // for (int i = 0; i <= 20; i++) {
        //     if (test.isPrime(i)) {
        //         System.out.println(i);
        //     }
        // }

        List<Integer> result = test.findM_n(20);
//        System.out.println("result.size " + result.size());
        for (Integer res : result) {
            System.out.println(res);
        }
    }
}