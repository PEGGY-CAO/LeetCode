import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static List<Integer> closest(String s, List<Integer> queries) {
        // Write your code here
        List<Integer> result = new ArrayList<>(queries.size());
        if (queries.size() < 1) {
            result.add(-1);
            return result;
        }
        Map<Character, List<Integer>> mapping = new HashMap<>();
        int[] indexDict = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (mapping.containsKey(c)) {
                List<Integer> indicesList = mapping.get(c);
                indicesList.add(i);
                mapping.put(c, indicesList);
                indexDict[i] = indicesList.size() - 1;

            } else {
                List<Integer> indices = new ArrayList<>();
                indices.add(i);
                mapping.put(c, indices);
                indexDict[i] = 0;
            }

        }

        for (int i = 0; i < queries.size(); i++) {
            int index = queries.get(i);
            if (index >= s.length() || index < 0) {
                result.add(-1);
                continue;
            }
            char c = s.charAt(index);
            List<Integer> mappingList = mapping.get(c);
            if (mappingList.size() == 1) {
                result.add(-1);
                continue;
            }
            int indexInList = indexDict[index];
            //we need to know indexInList - 1 and indexInList + 1 if exist
            int former = indexInList - 1 >= 0 ? mappingList.get(indexInList - 1) : Integer.MAX_VALUE;
            int latter = indexInList + 1 < mappingList.size() ? mappingList.get(indexInList + 1) : Integer.MAX_VALUE;
            int res = Math.abs(former - index) > (latter - index) ? latter : former;
            result.add(res);


        }


        return result;

    }
}
