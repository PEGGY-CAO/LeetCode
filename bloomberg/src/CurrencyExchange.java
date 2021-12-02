import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;


public class CurrencyExchange {

    public static double getForexRate(String src_country, String dest_country, Map<String, Double> forexMap) {

        //corner cases
        if (forexMap == null || forexMap.size() == 0 || src_country.length() == 0 || dest_country.length() == 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        //construct a hashMap for BFS
        Map<String, List<String>> graph = new HashMap<>();
        for (String srcToDest : forexMap.keySet()) {
            String src = srcToDest.substring(0, 3);
            String dest = srcToDest.substring(3, 6);
            List<String> neighbors = graph.getOrDefault(src, new ArrayList<>());
            neighbors.add(dest);
            graph.put(src, neighbors);
        }

        //bfs
        Queue<String> q = new LinkedList<>();
        q.offer(src_country);
        boolean foundIt = false;

        Map <String, Double> costToCountry = new HashMap<>();
        double prevcost = 1;
        costToCountry.put(src_country, prevcost);

        while (!q.isEmpty()) {
            String src = q.poll();
            List<String> neighbors = graph.get(src);
            System.out.println(src + " has neighbors: " + neighbors.size());
            prevcost = costToCountry.get(src);
//            costToCountry.put(src) = prevcost * CurrentFromgGraph;
            for (int i = 0; i < neighbors.size(); i++) {
                String neighbor = neighbors.get(i);
                q.offer(neighbor);
//                costToCountry.put(neighbor) = prevcost * CurrentFromgGraph;
                double currentFromGraph = forexMap.get(src + neighbor);
                System.out.println(neighbor + " " + currentFromGraph);
                costToCountry.put(neighbor, prevcost * currentFromGraph);

                if (neighbor.equals(dest_country)) {
                    return prevcost * currentFromGraph;

                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        Map<String, Double> forexMap = new HashMap<>();
        String[] countries = {"USAIND",
                "INDJPN",
                "INDPAK",
                "INDKOR",
                "INDCHN",
                "KORCHN",
                "CHNBRZ",
                "USAJPN",
                "JPNPAK",
                "PAKKOR"
        };
        double[] exchanges = {78,
                23,
                12323,
                12321321,
                1.5,
                123,
                10,
                100,
                1231,
                12312323
        };
        for (int i = 0; i < countries.length; i++) {
            forexMap.put(countries[i], exchanges[i]);
        }
        System.out.println(getForexRate("USA", "CHN", forexMap));

    }
}
