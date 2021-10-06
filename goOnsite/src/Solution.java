import java.util.ArrayList;
import java.util.List;

public class Solution {

    //Hi I'm Peggy

    static class Replacement {
        private int start;
        private String before;
        private String after;
        public Replacement(int inputStart, String inputBefore, String inputAfter) {
            start = inputStart;
            before = inputBefore;
            after = inputAfter;
        }
    }

// input: “num foo;”
// {start: 0, before: “num”, after: “String”} output:"String foo;"
// {start: 4, before: “foo”, after: “barpppppp”} output:"num bar;"
//Actual output:"String barpppppp;"


    public static String replaceRanges(String text, List<Replacement> replacements) {
        //corner cases
        if (text == null || text.length() == 0) return "";

        if (replacements == null || replacements.size() == 0) {
            return text;
        }

        //go through the replacements list
        StringBuilder sb = new StringBuilder();

        if (replacements.get(0).start > 0) {
            sb.append(text.substring(0, replacements.get(0).start));
        }

        int lastEndIndex = replacements.get(0).start;

        for (int i = 0; i < replacements.size(); i++) {
            int currentStartIndex = replacements.get(i).start;
            String curBefore = replacements.get(i).before;
            int endIndexSubString = currentStartIndex + curBefore.length();
            String subString = text.substring(currentStartIndex, endIndexSubString);
            if (!subString.equals(curBefore)) {
                throw new IllegalArgumentException("Invalid Input.");
            }

            sb.append(text.substring(lastEndIndex, currentStartIndex));

            //if they're matching
            sb.append(replacements.get(i).after);
            System.out.println("round i = " + i + " : " + sb.toString());

            lastEndIndex = endIndexSubString;

        }

        sb.append(text.substring(lastEndIndex));
        return sb.toString();

    }

    public static void main(String[] args) {

        Replacement r1 = new Replacement(0, "num", "String");
        Replacement r2 = new Replacement(4, "foo", "barpppppp");
        List<Replacement> listOfReplacements = new ArrayList<>();
        listOfReplacements.add(r1);
        listOfReplacements.add(r2);
        System.out.println(listOfReplacements.size());
        System.out.println(replaceRanges("num foo", listOfReplacements));
    }
}
