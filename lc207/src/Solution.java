import java.util.Queue;
import java.util.LinkedList;

public class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] edge = new int[numCourses][numCourses]; // i -> j
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int ready = prerequisites[i][0]; //0
            int pre = prerequisites[i][1]; //1
            if (edge[pre][ready] == 0)
                inDegree[ready]++; //duplicate case
            edge[pre][ready] = 1;
        }
        //inDegree = {1, 0}
        //edge = {{0, 0},
        //        {1, 0}};
        int count = 0;
        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }
        //queue = {1}
        while (!queue.isEmpty()) {
            int course = queue.poll();//1
            count++;//1
            for (int i=0; i<numCourses; i++) {
                if (edge[course][i] != 0) {
//                    System.out.println(i);
                    if (--inDegree[i] == 0) {
//                        System.out.println(inDegree[i]);
//                        System.out.println(--inDegree[i]);
                        queue.offer(i);
                    }
                }
            }
        }
        return count == numCourses;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] test1 = {{0, 1}};
        boolean ans1 = s.canFinish(2, test1);
        System.out.println(ans1);
    }
}
