import java.util.*;

public class ParseDomain {
    public static String[] counts = {
            "900,google.com",
            "60,mail.yahoo.com",
            "10,mobile.sports.yahoo.com",
            "40,sports.yahoo.com",
            "300,yahoo.com",
            "10,stackoverflow.com",
            "2,en.wikipedia.org",
            "1,es.wikipedia.org",
            "1,mobile.sports"
    };

    public static Map<String, Integer> parseStrings (String[] input) {
        int length = input.length;
        Map<String, Integer> mapping = new HashMap<>();

        for (int i = 0; i < length; i++) {
            String x = input[i];
            String[] parseX = x.split(",");
            int numOfHit = Integer.parseInt(parseX[0]);
            //deal with parseX[1]
            List<String> afterParse = parseDomain(parseX[1]);
            for (String afP : afterParse) {
                if (afP.length() != 0) {
                    if (mapping.containsKey(afP)) {
                        mapping.put(afP, mapping.get(afP) + numOfHit);
                    } else {
                        mapping.put(afP, numOfHit);
                    }
                }
            }

        }

        return mapping;

    }

    private static List<String> parseDomain(String original) {
        StringBuilder temp = new StringBuilder(original);
        StringTokenizer parse = new StringTokenizer(original, ".");
        List<String> answer = new ArrayList<>();
        answer.add(original);
        while (parse.hasMoreTokens()) {
            String t = parse.nextToken();
            temp.delete(0, t.length() + 1);
            if (temp.length() != 0) {
                answer.add(temp.toString());
            }
        }
        return answer;
    }


    public static void main(String[] args) {

        Map<String, Integer> test = parseStrings(counts);
        for (Map.Entry<String, Integer> entry : test.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue());
        }
        // String xxx = "google.com.A.B";
        // List<String> answer = parseDomain(xxx);
        // for (String x : answer) {
        //     System.out.println(x);
        // }
    }
}