import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class CombineGivenNumber {

    public static int combineGivenNumber(int[] arrs, int target) {
//        Map<Integer, String> mapping = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Set<String> complement = new HashSet<>();

        String tar = String.valueOf(target);
        int result = 0;

        for (int i = 0; i < arrs.length; i++) {
            String current = String.valueOf(arrs[i]);
            System.out.println("current: " + current);

            if (tar.indexOf(current) == -1) {
                continue;
            }
            if (complement.contains(current)) {
                //it's a complement latter pair with existing string
                result ++;
                if (tar.indexOf(current) == 0 && visited.contains(tar.substring(current.length()))) {
                    result ++;
                }

            } else {
                complement.add(tar.substring(current.length()));
            }
            
            visited.add(current);

        }
        return result;
    }

    public static void main(String[] args) {
        int[] arrs = {12, 121, 2, 12};
        int target = 1212;
        System.out.println(combineGivenNumber(arrs, target));
    }
}
