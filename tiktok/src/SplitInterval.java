import java.util.*;

public class SplitInterval {

    public List<int[]> parseIntervals(List<int[]> arr, int index) {
        List<int[]> ans = new ArrayList<>();

//        boolean found = false;
        for (int[] interval : arr) {
            int elements = interval[1] - interval[0] + 1;

            if (index >= 0 && index < elements) {
//                found = true;
                if (elements == 1) {
                    index -= elements;
                    continue;
                } else {
                    if (index > 0) {
                        ans.add(new int[]{interval[0], interval[0] + index - 1});
                    }

                    if (index < elements - 1) {
                        ans.add(new int[]{interval[0] + index + 1, interval[1]});
                    }
                    index -= elements;
                }

            } else {
                ans.add(interval);
                index -= elements;
            }

        }



        return ans;

    }

    public static void main(String[] args) {
        int[][] testArr= {{4, 7}, {10, 13}, {17, 19}};
        List<int[]> testList = Arrays.asList(testArr);
        SplitInterval test = new SplitInterval();
        List<int[]> results = test.parseIntervals(testList, 2);
        for (int[] result : results) {
            System.out.println(result[0] + " " + result[1]);
        }

        List<int[]> results2 = test.parseIntervals(results, 2);
        for (int[] result : results2) {
            System.out.println(result[0] + " " + result[1]);
        }
    }
}
