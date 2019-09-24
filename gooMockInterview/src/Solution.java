import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;

public class Solution {
//    Good morning
//
//2 lists of nums int
//    each in sorted ascending
//    combine
//    kth largest
//    input: int[] a, int[] b, int k
//return int
//
//    Eg. a = {2, 3, 4, 5}; b = {3, 4, 5, 6}; k = 3 //expected answer : 3
//    Eg2. a = {2, 3}; b = {4, 5, 6}; k = 4 //expected answer : 5
//    Eg3. a = {5, 6, 7}; b = {4}; k =3 // expected answer: 6
//
//    A = {5}, B = {6} k=2

    public int findKth(List<Integer> a, List<Integer> b, int k) throws IllegalArgumentException{
        if (a == null || b == null || k == 0 || a.size() == 0 || b.size() == 0) {
            throw new IllegalArgumentException("invalid input");
        }
        int lengthA = a.size(); //4
        int lengthB = b.size(); //4
        if (k > lengthA + lengthB) {
            throw new IllegalArgumentException("invalid k");
        }
        if (k == lengthA + lengthB) {
            return a.get(lengthA - 1) > b.get(lengthB - 1) ? a.get(lengthA - 1) : b.get(lengthB - 1);
        }
        int countForTotal = 0;
        int pA = 0;
        int pB = 0;
        int result = 0;
        while (countForTotal < k && pA < lengthA && pB < lengthB) {
            int intA = a.get(pA);//2; 3; 4
            int intB = b.get(pB);//3
            if (intA <= intB) { //2 < 3; 3 <= 3; 4 > 3
                result = intA;
                pA++; //1; 2
            } else {
                result = intB;
                pB++; //0; 1
            }
            countForTotal++; //1; 2; 3
        }
        if (countForTotal == k) {
            return result;
        } else {
            //countForTotal < k, we’ve gone through a list already, we still need to increment
            if (pA < lengthA) {
                pA = pA + (k - countForTotal);//0+ (3-1) = 2
                return a.get(pA -1);//6
            } else {
                pB = pB +  (k - countForTotal);//0 + (4 - 2) = 2
                return b.get(pB - 1);
            }
        }
    }

    public static void main(String[] arg) throws IllegalArgumentException{
        Solution test = new Solution();
        //testcase1
        int[] arrayA = {2, 3, 4, 5};
        List<Integer> a = Arrays.stream(arrayA).boxed().collect(Collectors.toList());
        int[] arrayB = {3, 4, 5, 6};
        List<Integer> b = Arrays.stream(arrayB).boxed().collect(Collectors.toList());
        System.out.println(test.findKth(a, b, 3));
        //testcase2
        int[] arrA = {2, 3};
        List<Integer> testA = Arrays.stream(arrA).boxed().collect(Collectors.toList());
        int[] arrB = {4, 5, 6};
        List<Integer> testB = Arrays.stream(arrB).boxed().collect(Collectors.toList());
        System.out.println(test.findKth(testA, testB, 4));
        //testcase3
        int[] aa = {5, 6, 7};
        List<Integer> testA1 = Arrays.stream(aa).boxed().collect(Collectors.toList());
        int[] bb = {4};
        List<Integer> testB1 = Arrays.stream(bb).boxed().collect(Collectors.toList());
        System.out.println(test.findKth(testA1, testB1, 3));
        //testcase4
        List<Integer> testA2 = new ArrayList<>();
        testA2.add(6);
        List<Integer> testB2 = new ArrayList<>();
        testB2.add(5);
        System.out.println(test.findKth(testA2, testB2, 2));
    }

}
