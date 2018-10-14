import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class MissingWord {
    public static List<String> missingWords(String s, String t) {
        // Write your code here
        //     String[] strings = s.split(" ");
        //     String[] texts = t.split(" ");
        StringTokenizer st1 = new StringTokenizer(s, " ");
        StringTokenizer st2 = new StringTokenizer(t, " ");

        List<String> res = new ArrayList<>();

        while (st2.hasMoreTokens()) {

            String strj = st2.nextToken();
            while (st1.hasMoreTokens()) {
                String stri = st1.nextToken();
                if (stri.equals(strj)) {
                    // if (i + 1 < strings.length && strings[i + 1].equals(texts[j])) {
                    //     i++;
                    //     continue;
                    // }
                    break;

                } else {

                    res.add(stri);
                    continue;
                }

            }
        }
        while (st1.hasMoreTokens()) {
            res.add(st1.nextToken());
        }
        return res;

    }
}
