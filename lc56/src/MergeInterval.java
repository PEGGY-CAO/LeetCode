import java.util.List;
import java.util.ArrayList;


public class MergeInterval {

    public static int[][] mergeInterval(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        List<int[]> result = new ArrayList<>();
        int[] currentInterval = intervals[0];
        result.add(currentInterval);
        for (int i = 0; i < intervals.length - 1; i++) {
            int[] nextInterval = intervals[i + 1];
            if (nextInterval[0] <= currentInterval[1]) {
                currentInterval[1] = nextInterval[1];
            } else {
                currentInterval = nextInterval;
                result.add(currentInterval);
            }

        }

        return result.toArray(new int[result.size()][]);
    }


    public static void main(String[] args)  {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] result = mergeInterval(intervals);
        for (int[] intervalMerged : result) {
            System.out.println(intervalMerged[0] + " " + intervalMerged[1]);
        }
    }
}
