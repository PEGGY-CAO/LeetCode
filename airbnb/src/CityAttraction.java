import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;




public class CityAttraction {

    //nested class Road to construct the graph
    static class Road {
        int start;
        int end;
        int time;
        public Road(int start, int end, int time){
            this.start = start;
            this.end = end;
            this.time = time;
        }

    }
    /*
     * Complete the 'findBestPath' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     *  3. INTEGER max_t
     *  4. INTEGER_ARRAY beauty
     *  5. INTEGER_ARRAY u
     *  6. INTEGER_ARRAY v
     *  7. INTEGER_ARRAY t
     */


    public static int findBestPath(int n, int m, int max_t, List<Integer> beauty, List<Integer> u, List<Integer> v, List<Integer> t) {
        // Write your code here
        //construct a lookup map to store road, in which Key will be the start, Value will be a list of road
        Map<Integer, List<Road>> lookup = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int s = u.get(i);
            int e = v.get(i);
            int time = t.get(i);
            Road r;
            if (s < e) {
                r = new Road(s, e, time);
            } else {
                int temp = s;
                s = e;
                e = temp;
                r = new Road(s, e, time);
            }
            if (lookup.containsKey(s)) {
                List<Road> listOfR = lookup.get(s);
                listOfR.add(r);
                lookup.put(s, listOfR);
            } else {
                List<Road> newList = new ArrayList<>();
                newList.add(r);
                lookup.put(s, newList);
            }
        }

        int currentBeauty = beauty.get(0);
        List<Integer> beautyList = new ArrayList<>();
        int currentTime = 0;
        List<Integer> timeList = new ArrayList<>();
        //use Depth first search to calculate each road to the end and built possible time-beauty list
        dfs(currentBeauty, beauty, lookup, 0, beautyList, max_t, currentTime, timeList);

        //calculate maximum time possible when less than max_t
        int numOfPossible = timeList.size();
        int maxBeauty = 0;
        for (int i = 0; i < numOfPossible; i++) {
            System.out.println("beautyList: " + beautyList.get(i));
            System.out.println("    timeList: " + timeList.get(i));
            if (beautyList.get(i) > maxBeauty) {
                maxBeauty = beautyList.get(i);
            }
        }


        return maxBeauty;
    }

    public static void dfs(int currentBeauty, List<Integer> beauty, Map<Integer, List<Road>> lookup, int currentSite, List<Integer> beautyList, int max_t, int currentTime, List<Integer> timeList) {
        //prune the graph to improve efficiency
        if (currentTime > ((max_t + 1) / 2)) {
            return;
        }

        //base case: when the road hits end, and the total current time is <= max time
        if (!lookup.containsKey(currentSite)) {
            System.out.println("current site: " + currentSite);
            if (currentTime <= ((max_t + 1) / 2)) {
                currentBeauty += beauty.get(currentSite);
                timeList.add(currentTime);
                beautyList.add(currentBeauty);
                currentBeauty -= beauty.get(currentSite);
            }
            return;
        } else {
            List<Road> currentRoads = lookup.get(currentSite);
            for (Road r : currentRoads) {
                int newStart = r.end;
                int currentT = r.time;
                System.out.println("r.end: " + newStart);

                currentTime += currentT;
                currentBeauty += beauty.get(currentSite);
                System.out.println("current time: " + currentTime);
                dfs(currentBeauty, beauty, lookup, newStart, beautyList, max_t, currentTime, timeList);
                currentTime -= currentT;
                //currentBeauty -= beauty.get(newStart);
            }

            return;
        }
    }


    public static void main(String[] args) {
        CityAttraction result = new CityAttraction();
        int n = 4;
        int m = 3;
        int maxt = 49;
        int[] b = {0, 32, 10, 43};
        List<Integer> beauty = new ArrayList<>();
        for (int i = 0; i < b.length; i++) {
            beauty.add(b[i]);
        }
        int[] uarr = {0, 2, 0};
        int[] varr = {1, 0, 3};
        int[] tarr = {10, 13, 17};
        List<Integer> u = new ArrayList<>();
        List<Integer> v = new ArrayList<>();
        List<Integer> t = new ArrayList<>();
        for (int j = 0; j < uarr.length; j++) {
            u.add(uarr[j]);
            v.add(varr[j]);
            t.add(tarr[j]);
        }
        int max = result.findBestPath(n, m, maxt, beauty, u, v, t);
        System.out.println(max);


/**     test case two
        n = 5
        m = 6
        max_t = 70
        beauty = {30, 80, 100, 50, 50}
        u = {4, 1, 0, 4, 2, 2}
        v = {3, 4, 3, 0, 3, 0}
        t = {20, 15, 40, 10, 100, 10}
 **/
    }
}
