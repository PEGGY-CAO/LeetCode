import java.io.*;
import java.util.*;

public class Solution {
    // If you need extra classes, you can define them privately here within class Solution

    // Prints to standard output.
    static String findMidpointCourse(String[][] pairs) {
        // Your code here.
        if (pairs == null || pairs.length == 0) {
            return null;
        }
        if (pairs.length == 1) {
            return pairs[0][0];
        }
        int numOfCourse = pairs.length + 1;

        Map<String, String> dict = new HashMap<>();
        for (int i = 0; i < pairs.length; i++) {
            String pre = pairs[i][0];
            String course = pairs[i][1];
            dict.put(pre, course);
        }
        //find head course
        String head = new String("");
        for (String course: dict.keySet()) {
            if (!dict.containsValue(course)) {
                head = course;
            }
        }

        int target = numOfCourse % 2 == 0 ? numOfCourse / 2 - 1 : numOfCourse / 2;
        if (target == 0) {
            return head;
        }
        String result = new String("");
        while (target > 0) {
            result = dict.get(head);
            target--;
            head = result;
        }
        return result;
    }

    // DO NOT MODIFY MAIN()
    public static void main(String[] args) {
        List<String[]> arg1 = new ArrayList<String[]>();

        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }

                arg1.add(line.split(" "));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String[][] pairs = arg1.toArray(new String[arg1.size()][]);

        System.out.println(findMidpointCourse(pairs));
    }
}
