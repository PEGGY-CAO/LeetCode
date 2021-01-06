import java.util.*;

public class ParsingStringArray {

    public static List<Integer> parsingCharArr(char[][] chars) {

        int length = chars.length;
        //an arraylist used to store the result of indices
        List<Integer> result = new ArrayList<>();

        //corner case
        if (chars == null || chars.length == 0) {
            return result;
        }

        if (chars.length == 1) {
            result.add(1);
            return result;
        }
        //hashmap to record string and its index
        Map<String, List<Integer>> dict = new HashMap<>();
        //String list
        Set<String> allStrings = new HashSet<>();

        //traverse the lower part of chars array to build up allStrings and dict
        for (int row = length - 1; row >= 0; row--) {
            System.out.println("current row: " + row);
            StringBuilder sb = new StringBuilder();
            int startCol = 0;
            int startRow = row;
            while (startRow < length) {
                sb.append(chars[startRow][startCol]);
                startRow++;
                startCol++;
            }

            String str = padding(sb, length);

            allStrings.add(str);
            if (dict.keySet().contains(str)) {
                List<Integer> indices = dict.get(str);
                indices.add(length - row);
                dict.put(str, indices);
            } else {
                List<Integer> indices = new ArrayList<>();
                indices.add(length - row);
                dict.put(str, indices);
            }

        }
        //traverse the upper part of chars array to build up allStrings and dict
        for (int col = 1; col < length; col++) {
            StringBuilder sb = new StringBuilder();
            int startRow = 0;
            int startCol = col;
            while (startCol < length) {
                sb.append(chars[startRow][startCol]);
                startRow++;
                startCol++;
            }
            String str = padding(sb, length);

            allStrings.add(str);
            if (dict.keySet().contains(str)) {
                List<Integer> indices = dict.get(str);
                indices.add(length + col);
                dict.put(str, indices);
            } else {
                List<Integer> indices = new ArrayList<>();
                indices.add(length + col);
                dict.put(str, indices);
            }
        }

        List<String> noDuplicate = new ArrayList<>(allStrings);
        Collections.sort(noDuplicate);
        //build up result list
        for (int i = 0; i < allStrings.size(); i++) {
            String key = noDuplicate.get(i);
            List<Integer> indices = dict.get(key);
            for (int index : indices) {
                result.add(index);
            }
        }
      return result;
    }

    public static String padding(StringBuilder sb, int length)  {
        if (sb.length() == length) {
            return sb.toString();
        }

        int currentCharIndex = 0;

        while (sb.length() < length) {
            char curChar = sb.charAt(currentCharIndex);
            sb.append(curChar);
            currentCharIndex++;
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        char[][] chars = {
                {'a', 'c', 'a', 'b', 'b'},
                {'c', 'b', 'a', 'c', 'b'},
                {'a', 'a', 'e', 'c', 'b'},
                {'b', 'b', 'd', 'a', 'g'},
                {'a', 'b', 'e', 'b', 'a'}
        };
        List<Integer> res = parsingCharArr(chars);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
