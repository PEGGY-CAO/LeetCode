// package whatever; // don't place package name!

import java.io.*;
import java.util.Map;
import java.util.HashMap;

class MyCode {

    public int findUnique(int[] input) {

        Map<Integer, Integer> dict = new HashMap<>();
        int length = input.length;
        for (int i = 0; i < length; i++) {
            int element = input[i];
            if (!dict.keySet().contains(element)) {
                dict.put(element, 1);

            } else {
                int amount = dict.get(element);
                amount++;
                dict.put(element, amount);
                // if (amount > 0) {
                //   amount--;
                // }


            }
        }

        for (Integer e: dict.keySet()) {
            int amount = dict.get(e);
            if (amount % 2 != 0) {
                return e;
            }
        }

        return -1;

    }


    public int findUniSortedArr(int[] sortedArr) {
        int lenth = sortedArr.length;
        int l = 0;
        int r = lenth - 1;
        while (l < r) {
            int midIndex = l + (r - l) / 2;
            int mid = sortedArr[midIndex];
            int latter = sortedArr[midIndex + 1];
            if (mid == latter) {
                if (mid % 2 == 0) {
                    l = midIndex;
                }
            } else if (mid == sortedArr[midIndex - 1]) {
                r = midIndex;
            } else {
                return mid;
            }

        }
        return -1;

    }

    public static void main (String[] args) {
        System.out.println("Hello Java");

        int array[] = {2, 3, 2, 4, 3, 5, 4};
        MyCode test = new MyCode();
        int result = test.findUnique(array);
        System.out.println(result);


        //q2 sorted
        int arraySorted[] = {1, 1, 3, 4, 4, 5, 5,  6, 6};
        MyCode q2 = new MyCode();
        System.out.println(q2.findUniSortedArr(arraySorted));

    }
}