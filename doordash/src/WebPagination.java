import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class WebPagination {
    public static List<String> fetchItemsToDisplay(List<List<String>> items, int sortParameter, int sortOrder, int itemsPerPage, int pageNumber) {
        // Write your code here
        List<String> ans = new ArrayList<>();
        Collections.sort(items, (a, b)->{
            if (sortOrder == 0) return sortParameter != 0 ? Integer.parseInt(a.get(sortParameter)) - Integer.parseInt(b.get(sortParameter)) : a.get(sortParameter).compareTo(b.get(sortParameter));
            else return sortParameter != 0 ? Integer.parseInt(b.get(sortParameter)) - Integer.parseInt(a.get(sortParameter)) : b.get(sortParameter).compareTo(a.get(sortParameter));
        });

        int pre = itemsPerPage * pageNumber;
        if (pre >= items.size()) return ans;
        for (int i = pre; i < items.size() && i < pre + itemsPerPage; i++) {
            ans.add(items.get(i).get(0));
        }

        return ans;
    }

    public static void main(String[] args) {
        String[] e1 = {"item1", "10", "15"};
        String[] e2 = {"item2", "3", "4"};
        String[] e3 = {"item3", "17", "8"};
        List<String> entry1 = new ArrayList<>();
        List<String> entry2 = new ArrayList<>();
        List<String> entry3 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            entry1.add(e1[i]);
            entry2.add(e2[i]);
            entry3.add(e3[i]);
        }
        List<List<String>> test1 = new ArrayList<>();
        test1.add(entry1);
        test1.add(entry2);
        test1.add(entry3);

        List<String> ans = fetchItemsToDisplay(test1, 2, 0, 2, 0);
        for (String name : ans) {
            System.out.println(name);
        }

    }
}
