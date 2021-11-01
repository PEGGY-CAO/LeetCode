import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateFormat {

    public static List<String> transferDateFormat(List<String> dates) {

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Map<String, String> lookup = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            StringBuilder sb = new StringBuilder();
            if (i < 10) {
                sb.append('0').append(i);
            } else {
                sb.append(i);
            }
            lookup.put(months[i - 1], sb.toString());
        }

        List<String> result = new ArrayList<>();
        for (String date : dates) {
            result.add(dateFormatHelper(date, lookup));
        }

        return result;
    }

    public static String dateFormatHelper(String date, Map lookup) {
        String[] splits = date.split(" ");
        StringBuilder sb = new StringBuilder();
        //append year
        sb.append(splits[2]);
        sb.append('-');
        //append months
        sb.append(lookup.get(splits[1]));
        sb.append('-');
        //append date
        Character firstDigit = splits[0].charAt(0);
        Character secondDigit = splits[0].charAt(1);
        if (secondDigit - '0' < 10 && secondDigit - '0' >= 0) {
            sb.append(firstDigit);
            sb.append(secondDigit);
        } else {
            sb.append('0');
            sb.append(firstDigit);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        List<String> test = new ArrayList<>();
        test.add("8th Oct 2017");
        test.add("10th Aug 2009");
        List<String> result = transferDateFormat(test);
        for (String date : result) {
            System.out.println(date);
        }
    }
}
