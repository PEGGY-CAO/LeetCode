public class Solution {


    public int[] solution(int[] stores, int[] houses) {
//        int[] st = {3, 1, 2, 5};
//        int[] h = {2, 4, 7};
        int[] result = new int[houses.length];
        //sort stores according to the distance
        sort(stores);
        //stores = [1,2,3,5]
        for (int i = 0; i < houses.length; i++) {
            System.out.println(houses[i]);
            int minDis = Integer.MAX_VALUE;
            boolean storesRepeat = false;
            int storesR = -1;
            for (int j = 0; j < stores.length; j++) {
                System.out.println(stores[j]);

                int temp = Math.abs(houses[i] - stores[j]);
                System.out.println("temp: " + temp + ", minDis: " + minDis);
                if (temp < minDis) {
                    minDis = temp;
                } else if (temp > minDis){
                    result[i] = storesRepeat? storesR : stores[j - 1];
                    System.out.println("result[i]: " + result[i]);
                    break;
                } else {
                    //when several stores have same distance
                    if (!storesRepeat) {
                        storesRepeat = true;
                        storesR = stores[j - 1];

                    } else {
                        continue;
                    }


                }
                if (j == stores.length - 1) {
                    result[i] = stores[j];
                }
            }
        }
        return result;
    }

    private void sort(int[] stores) {
        int n = stores.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (stores[j] > stores[j + 1]) {
                    int temp = stores[j];
                    stores[j] = stores[j + 1];
                    stores[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] arg) {
        int[] st = {5, 5, 6, 6, 9, 0, 1};
        int[] h = {3, 3, 7, 7, 10};
        Solution s = new Solution();
        int[] res = s.solution(st, h);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
