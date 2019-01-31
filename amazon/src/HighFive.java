import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;



public class HighFive {

    //nested class for products
    static class Productscore {
        int id;
        int value;
        public Productscore(int id, int value) {
            this.id = id;
            this.value = value;
        }
    }

    public static Map<Integer, Double> getHighFive(Productscore[] results) {
        //build a pscores map in order to map each ID with its list of scores
        Map<Integer, List<Integer>> pscores = new HashMap<>();
        for (Productscore p : results) {
            int pID = p.id;
            if (!pscores.containsKey(pID)) {
                List<Integer> scores = new ArrayList<>();
                scores.add(p.value);
                pscores.put(pID, scores);
            } else {
                List<Integer> scores = pscores.get(pID);
                scores.add(p.value);
                pscores.replace(pID, scores);
            }
        }

        //build the returned map
        Map<Integer, Double> res = new HashMap<>();
        for (Integer p : pscores.keySet()) {
            List<Integer> scores = pscores.get(p);
            Collections.sort(scores);
            Collections.reverse(scores);
            double r = 0;
            for (int i = 0; i < 5; i++) {
                r += scores.get(i);
            }
            r = r / 5.0;
            res.put(p, r);
        }
        return res;

    }

    public static void main(String[] args) {
        Productscore r1 = new Productscore(1, 95);
        Productscore r2 = new Productscore(1, 95);
        Productscore r3 = new Productscore(1, 91);
        Productscore r4 = new Productscore(1, 91);
        Productscore r5 = new Productscore(1, 105);
        Productscore r6 = new Productscore(2, 6);
        Productscore r7 = new Productscore(2, 5);
        Productscore r8 = new Productscore(2, 9);
        Productscore r9 = new Productscore(2, 7);
        Productscore r10 = new Productscore(2, 7);
        Productscore[] arr = {r1, r2, r3, r4, r5, r6, r7, r8, r9, r10};

        //HighFive ex = new HighFive();
        Map<Integer, Double> result = getHighFive(arr);

        for (int i : result.keySet()) {
            System.out.println(i);
            System.out.println("    " + result.get(i));
        }


    }




}
