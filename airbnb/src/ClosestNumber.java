import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ClosestNumber {
    public static void closestNumbers(List<Integer> numbers) {
        // Write your code here
        if (numbers == null || numbers.size() == 0 || numbers.size() == 1) {
            return;
        }
        Collections.sort(numbers);
        List<List<Integer>> result = new ArrayList<>();

        Iterator<Integer> it = numbers.iterator();
        int min = Integer.MAX_VALUE;

        int former = it.next();
        while(it.hasNext()) {
            int latter = it.next();
            int diff = latter - former;
            if (diff < min) {
                min = diff;
                List<Integer> temp = new ArrayList<>();
                temp.add(former);
                temp.add(latter);

                result.clear();
                result.add(temp);

            } else if (diff == min) {
                List<Integer> temp = new ArrayList<>();
                temp.add(former);
                temp.add(latter);

                result.add(temp);
            }

            former = latter;


        }

        int length = result.size();
        Iterator<List<Integer>> ite = result.iterator();
        while (ite.hasNext()) {
            List<Integer> cur = ite.next();
            for (int i : cur) {
                System.out.print(i + " ");
            }
            System.out.println();
        }


    }
}
