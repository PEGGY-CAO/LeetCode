import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.*;

public class PrimeSubtree {
    /*
     * Complete the 'primeQuery' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY first
     *  3. INTEGER_ARRAY second
     *  4. INTEGER_ARRAY values
     *  5. INTEGER_ARRAY queries
     */

    public static List<Integer> primeQuery(int n, List<Integer> first, List<Integer> second, List<Integer> values, List<Integer> queries) {
        // Write your code here
        ConcurrentMap<Integer, Set<Integer>> map = new ConcurrentHashMap<>();
        //Map<Integer, Set<Integer>> map = new HashMap<>();
        int size = first.size();
        for (int i = 0; i < size; i++) {
            int indexParent = first.get(i);
            int indexChild = second.get(i);
            if (map.containsKey(indexParent)) {
                Set<Integer> chiList = map.get(indexParent);
                chiList.add(indexChild);
                map.put(indexParent, chiList);
            } else {
                Set<Integer> childList = Collections.newSetFromMap(new ConcurrentHashMap<>());
                childList.add(indexChild);
                map.put(indexParent, childList);
            }
            if (map.containsKey(indexChild)) {
                Set<Integer> chiList = map.get(indexChild);
                chiList.add(indexParent);
                map.put(indexChild, chiList);
            } else {

                Set<Integer> cList = Collections.newSetFromMap(new ConcurrentHashMap<>());
                cList.add(indexParent);
                map.put(indexChild, cList);
            }
        }

//        Iterator x = map.entrySet().iterator();
//        while (x.hasNext()) {
//            Map.Entry pair = (Map.Entry) x.next();
//            Set<Integer> nums = (Set<Integer>) pair.getValue();
//            System.out.println("pair.getKey()" + ": " + pair.getKey());
//            for (int num : nums) {
//                System.out.println(num);
//            }
//        }

        //construct the tree in level order BFS
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        Set<Integer> visited = new HashSet<>();

//        Iterator<Integer> mapKeyIt = ((ConcurrentHashMap<Integer, Set<Integer>>) map).keySet().iterator();
//        while (mapKeyIt.hasNext()) {
//            int key = mapKeyIt.next();
//            visited.add(key);
//            Set<Integer> childvalues = map.get(key);
//            //map.remove(key);
//            for (int value : childvalues) {
//                if (!visited.contains(value)) {
//                    visited.add(value);
//                } else {
//                    childvalues.remove(value);
//                }
//            }
//            map.put(key, childvalues);
//        }

        while (q.peek() != null) {
            int head = q.poll();
            visited.add(head);
            Set<Integer> cList = map.get(head);


            for (Iterator<Integer> ite = cList.iterator(); ite.hasNext();) {
                int child = ite.next();
                //if the child node hasn't been visited, we add the child to the queue and add it to the visited set
                if (!visited.contains(child)) {
                    visited.add(child);
                    q.add(child);
                } else {
                    // if the child node has already been visited, which means it's actually a parent
                    //so we remove it from the current child list and put the new mapping parent-childlist in the map
                    cList.remove(child);
                    map.put(head, cList);
                }
            }
        }

        System.out.println("After constructing tree: ");
        Iterator<Integer> mapKeyIt = ((ConcurrentHashMap<Integer, Set<Integer>>) map).keySet().iterator();
        while (mapKeyIt.hasNext()) {
            int key = mapKeyIt.next();
            Set<Integer> nums = map.get(key);
            System.out.println("pair.getKey()" + ": " + key);
            for (int num : nums) {
                System.out.println(num);
            }
        }
        System.out.println("finish printing tree");


        List<Integer> result = new ArrayList<>();
        for (int query : queries) {
            int res = dfs(query, values, map);
            result.add(res);
        }
        return result;

    }

    private static int dfs(int node, List<Integer> values, ConcurrentMap<Integer, Set<Integer>> map) {
        //System.out.println("res: " + res + " node: " + node);
        int res = 0;
        if (isPrime(values.get(node - 1))) {
            res++;
        }
        Set<Integer> childList = map.get(node);
        //base case
        if (childList.size() == 0) {

            return res;
        }
        Iterator<Integer> ite = childList.iterator();
        while (ite.hasNext()) {
            int child = ite.next();
            System.out.println("child: " + child);
            res += dfs(child, values, map);
            System.out.println("    " + res);
        }
        return res;

    }

    private static boolean isPrime(int x) {
        if (x <= 1) {
            return false;
        }
        if (x == 2 || x == 3) {
            return true;
        }
        for (int i = 2; i <= x / 2; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        PrimeSubtree ps = new PrimeSubtree();
        int[] f = {6, 8, 3, 6, 4, 1, 8, 5, 1};
        List<Integer> first = new ArrayList<>();
        for (int i : f) {
            first.add(i);
        }
        int[] s = {9, 9, 5, 7, 8, 8, 10, 8, 2};
        List<Integer> second = new ArrayList<>();
        for (int i : s) {
            second.add(i);
        }
        int[] v = {17, 29, 3, 20, 11, 8, 3, 23, 5, 15};
        List<Integer> value = new ArrayList<>();
        for (int i : v) {
            value.add(i);
        }
        int[] q = {1, 8, 9, 6, 4, 3};
        List<Integer> queries = new ArrayList<>();
        for (int i : q) {
            queries.add(i);
        }

        List<Integer> result = ps.primeQuery(10, first, second, value, queries);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
