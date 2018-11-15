import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;


public class FinalDiscountedPrice {
    public static void finalPrice(List<Integer> prices) {
        // Write your code here
        int length = prices.size();
        long total = 0;
        //Iterator<Integer> ite = prices.iterator();
        //int i = 0;
        List<Integer> originalPrice = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = length - 1; i >= 0; i--) {
            //pop out those that are irrelevant to the current one
            while (!stack.empty() && stack.peek() > prices.get(i)) {
                stack.pop();
            }

            if (stack.empty()) {
                total += prices.get(i);
                originalPrice.add(0, i);
            } else {
                total += ((prices.get(i) - stack.peek()));
            }

            stack.push(prices.get(i));

        }
//        while (ite.hasNext()) {
//            boolean discount = false;
//            int current = ite.next();
//            //find the first lower or equal
//            if (i < length - 1) {
//                for (int j = i + 1; j < length; j++) {
//                    int nextPrice = prices.get(j);
//                    if (nextPrice <= current) {
//                        total += (current - nextPrice);
//                        discount = true;
//                        break;
//                    }
//
//                }
//            }
//            if (!discount) {
//                total += current;
//                originalPrice.add(i);
//            }
//            i++;
//        }
        System.out.println(total);
        int newList = originalPrice.size();
        for (int x = 0; x < newList; x++) {
            if (x < newList - 1) {
                System.out.print(originalPrice.get(x) + " ");
            } else {
                System.out.print(originalPrice.get(x));
            }
        }

    }


    public static void main(String[] args) {
        FinalDiscountedPrice result = new FinalDiscountedPrice();
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(10);
        a.add(6);
        a.add(6);
        a.add(3);
        result.finalPrice(a);
    }
}
