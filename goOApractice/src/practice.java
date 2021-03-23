
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;

class Practice {

    static int solution(Integer[] A) {
        // Your solution goes here.
        int length = A.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return 1;
        }

        List<Integer> smallestInEachRow = new ArrayList<>();
        smallestInEachRow.add(A[0]);
        int count = 1;

        for (int i = 1; i < length; i++) {
            int current = A[i];
            int j = 0;
            for (j = 0; j < smallestInEachRow.size(); j++) {
                int smallest = smallestInEachRow.get(j);
                if (current < smallest) {
                    smallestInEachRow.set(j, current);
                    break;
                }
            }
            if (j == smallestInEachRow.size()) {
                smallestInEachRow.add(current);
            }
        }

        return smallestInEachRow.size();
    }

    public static void main(String[] args) {
        // Read from stdin, solve the problem, write answer to stdout.
        Scanner in = new Scanner(System.in);
        Integer[] A = getIntegerArray(in.next());

        System.out.print(solution(A));
    }

    private static Integer[] getIntegerArray(String str) {
        return Stream.of(str.split("\\,"))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
    }
}