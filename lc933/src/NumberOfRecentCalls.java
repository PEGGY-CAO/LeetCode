import java.util.TreeMap;

class RecentCounter {

    TreeMap<Integer, Integer> tr;

    public RecentCounter() {
        tr = new TreeMap<>();
    }

    public int ping(int t) {
        tr.put(t, tr.size() + 1);
        return tr.tailMap(t - 3000).size();
    }
}

public class NumberOfRecentCalls {


    public static void main(String[] args) {
        RecentCounter rc = new RecentCounter();
        System.out.println(rc.ping(1));
        System.out.println(rc.ping(100));
        System.out.println(rc.ping(3001));
        System.out.println(rc.ping(3002));
    }
}
