import java.util.ArrayList;
import java.util.List;

public class CountAnalogousArr {

    public static int countAnalogousArrays(List<Integer> consecutiveDifference, int lowerBound, int upperBound) {

        if (consecutiveDifference == null || consecutiveDifference.isEmpty() || lowerBound > upperBound) {
            return 0;
        }

        int min = 0;
        int max = 0;

        int dummy = 0;
        for (int diff : consecutiveDifference) {
            dummy -= diff;
            min = Math.min(min, dummy);
            max = Math.max(max, dummy);
        }
        int maxDiff = max - min;
        if (maxDiff > upperBound - lowerBound) return 0;
        return (upperBound - lowerBound - maxDiff + 1);

    }


    public static void main(String[] args) {

        int[] diffs = {-2, -1, -2, 5};
        List<Integer> consecutiveDiffs = new ArrayList<>();
        for (int d : diffs) {
            consecutiveDiffs.add(d);
        }

        int lower = 3;
        int upper = 10;
        System.out.println(countAnalogousArrays(consecutiveDiffs, lower, upper));
    }
}
