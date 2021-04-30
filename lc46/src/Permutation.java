import java.util.ArrayList;
import java.util.List;

public class Permutation {

    public static List<List<Integer>> permute(int[] input) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        backtrack(result, temp, input);
//        for (int i = 0; i < result.size(); i++) {
//            System.out.println(result.get(i));
//        }
        return result;
    }

    public static void backtrack(List<List<Integer>> result, List<Integer> temp, int[] input) {
        if(temp.size() == input.length) {
//            System.out.println(temp);
            //Note: It has to be new ArrayList<>(temp) here,
            //Otherwise, an empty list will be added
            result.add(new ArrayList<>(temp));
        } else {
            for (int i = 0; i < input.length; i++) {
                if (temp.contains(input[i])) {
                    //the number is already in the component
                    continue;
                } else {
                    temp.add(input[i]);
                    backtrack(result, temp, input);
                    temp.remove(temp.size() - 1);
                }
            }

        }
        return;
    }

    public static void main(String[] args) {
        int[] inputArr = {1, 2, 3};
        List<List<Integer>> answer = permute(inputArr);
        System.out.println(answer.size());

        for (int i = 0; i < answer.size(); i++) {
            System.out.println(answer.get(i));
        }
//        for(List<Integer> eachPermu : result) {
//            for (int i = 0; i < eachPermu.size(); i++) {
//                System.out.print(eachPermu.get(i) + " ");
//            }
//            System.out.println(" ");
//        }
    }
}
