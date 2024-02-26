import java.util.*;

public class MinSemesterParallelCourses {
    //LC 1136
    public static int minSemesterTakingCourses(int n, int[][] coursesRelations)  {
        //build a graph then do topological sort

        int[] inDegrees = new int[n + 1];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < coursesRelations.length; i++) {
            int pre = coursesRelations[i][0];
            int next = coursesRelations[i][1];
            inDegrees[next]++;
            List<Integer> adjacentList = graph.get(pre);
            adjacentList.add(next);
            graph.put(pre, adjacentList);
        }

        //prepare for topological sort
        Queue<Integer> zeroInDegreeQueue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegrees[i] == 0) {
                zeroInDegreeQueue.add(i);
            }
        }
        int semester = 0;
        int coursesTaken = 0;
        while (!zeroInDegreeQueue.isEmpty()) {
            int size = zeroInDegreeQueue.size();
            for (int i = 0; i < size; i++) {
                int currentCourse = zeroInDegreeQueue.poll();
                coursesTaken++;
                List<Integer> adjacentList = graph.get(currentCourse);
                for (int course : adjacentList) {
                    inDegrees[course]--;
                    if (inDegrees[course] == 0) {
                        zeroInDegreeQueue.add(course);
                    }
                }

            }
            semester++;
        }

        return coursesTaken == n ? semester : -1;

    }

    public static void main(String[] args) {
        int test1 = 3;
        int[][] relations1 = {{1, 3}, {2, 3}};
        int testCase1 = minSemesterTakingCourses(test1, relations1);
        System.out.println(testCase1);//supposed to be 2

        int test2 = 3;
        int[][] relations2 = {{1, 2}, {2, 3}, {3, 1}};
        int testCase2 = minSemesterTakingCourses(test2, relations2);
        System.out.println(testCase2);//supposed to be -1
    }
}
