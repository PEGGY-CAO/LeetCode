
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class Solution {

    public int solution(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        //int result = 0;

        //TreeMap<Integer, Integer> trmap = new TreeMap<>();

        ConcurrentMap<Integer, Integer> trmap = new ConcurrentHashMap<>();

        //the treemap stores data
        //each entry is the smallest number for each row
        //if array[i] is smaller then entry[k], then put array[i] instead of entry[k], aka remove(entry[k])

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
            Set<Integer> intSet = trmap.keySet();


            //Iterator it = trmap.entrySet().iterator();

            if (intSet.size() == 0) {
                trmap.put(array[i], 1);
            } else {
                int minDis = Integer.MAX_VALUE;
                int minK = -1;
                for (int k : intSet) {

                    if (array[i] < k) {
                        if ((k - array[i]) < minDis) {
                            minDis = k - array[i];
                            minK = k;
                        }

                    }
                }
                System.out.println("minK: " + minK);
                if (minK != -1) {
                    trmap.put(array[i], trmap.get(minK) + 1);
                    trmap.remove(minK);

                } else {
                    trmap.put(array[i], 1);
                    System.out.println("size" + trmap.size());
                }
            }
        }
        return trmap.size();
    }

    public static void main(String[] arg) {

        int[] example = {5, 3, 1, 4, 6};
        int[] ex2 = {5, 4, 3, 6, 1};

        Solution num = new Solution();

        System.out.println("result: " + num.solution(example));
    }

}