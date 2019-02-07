import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


public class MST {

    //nested class
    static class Connection {
        String node1;
        String node2;
        int cost;
        public Connection(String a, String b, int c) {
            node1 = a;
            node2 = b;
            cost = c;
        }
    }

    private static int unionNum;

    //code start here
    public static ArrayList<Connection> getLowCost(ArrayList<Connection> connections) {
        //corner case
        if (connections == null || connections.size() == 0) {
            return new ArrayList<>();
        }

        ArrayList<Connection> result = new ArrayList<>();
        Collections.sort(connections, new Comparator<Connection>() {
            @Override
            public int compare(Connection c1, Connection c2) {
                return c1.cost - c2.cost;
            }
        });

        //idea: Union Find + Kruskal
        unionNum = 0;
        //use a hashmap to record each node and its union ID
        HashMap<String, Integer> map = new HashMap<>();
        for (Connection c : connections) {
            String a = c.node1;
            String b = c.node2;
            if (union(map, a, b)) {
                result.add(c);
            }
        }

        //这里要检查一下,是不是所有的城市都属于同一个union, 若为离散的图--MST不存在，返回空表
        String find = connections.get(0).node1;
        int union = map.get(find);
        for (String str : map.keySet()){
            // 如果我们中出了一个叛徒，返回空表
            if (map.get(str) != union){
                return new ArrayList<>();
            }
        }

        Collections.sort(result, new Comparator<Connection>() {
            @Override
            public int compare(Connection o1, Connection o2) {
                if (o1.node1.equals(o2.node1)) {
                    return o1.node2.compareTo(o2.node2);
                }
                return o1.node1.compareTo(o2.node1);
            }
        });

        return result;
    }

    private static boolean union(HashMap<String, Integer> map, String node1, String node2) {

        if (!map.keySet().contains(node1) && !map.keySet().contains(node2)) {
            map.put(node1, unionNum);
            map.put(node2, unionNum);
            unionNum++;
            return true;
        }
        if (!map.keySet().contains(node1) && map.keySet().contains(node2)) {
            int parent2 = map.get(node2);
            map.put(node1, parent2);
            return true;
        }
        if (!map.keySet().contains(node2) && map.keySet().contains(node1)) {
            int parent1 = map.get(node1);
            map.put(node2, parent1);
            return true;
        }

        int parent1 = map.get(node1);
        int parent2 = map.get(node2);
        if (parent1 == parent2) {
            return false;
        }

        for (String s : map.keySet()) {
            if (map.get(s) == parent2) {
                map.put(s, parent1);
            }
        }
        return true;

    }



    public static void main(String[] args) {

        ArrayList<Connection> connections = new ArrayList<>();
        //下面的图是个苯环，中间加上了几根，如果想验证空表，去掉几根线就行。
        connections.add(new Connection("A","B",6));
        connections.add(new Connection("B","C",4));
        connections.add(new Connection("C","D",5));
        connections.add(new Connection("D","E",8));
        connections.add(new Connection("E","F",2));
        connections.add(new Connection("B","F",10));
        connections.add(new Connection("E","C",9));
        connections.add(new Connection("F","C",7));
        connections.add(new Connection("B","E",3));
        connections.add(new Connection("A","F",16));
        //这里就输出一下看看结果。
        ArrayList<Connection> res = getLowCost(connections);
        for (Connection c : res){
            System.out.println(c.node1 + " -> " + c.node2 + " " + c.cost);
        }
    }
}
