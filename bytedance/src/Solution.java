import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Solution {

    public static int[] findIntersect(int[] nums1, int[] nums2){

        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> mapping = new HashMap<>();

        for (int i : nums1) {

            if (mapping.containsKey(i)) {
                mapping.put(i, mapping.get(i) + 1);
            } else {
                mapping.put(i, 1);
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int i : nums2) {

            if (mapping.containsKey(i)) {
                int counting = mapping.get(i);
                counting--;
                if (counting == 0) {
                    mapping.remove(i);
                }
                result.add(i);
            }
        }

        Collections.sort(result);
        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 7, 3, 4, 9};
        int[] nums2 = {3, 4};
        int[] test1 = findIntersect(nums1, nums2);
        for (int i : test1) {
            System.out.println(i);
        }

    }
}
