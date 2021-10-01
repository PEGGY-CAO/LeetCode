public class EfficientHarvest {

    public static int maxHarvest(int[] arr, int k) {

        //time complexity: O(n)
        //space complexity: O(1)

        //corner cases
        if (k < 0 || k > arr.length / 2) {
            throw new IllegalArgumentException("input is invalid");
        }
        if (k == 0) return 0;
        if (k == arr.length / 2) {
            int count = 0;
            for (int i : arr) {
                count += i;
            }
            return count;
        }

        //add an extended array for calculation
        int[] extendedArr = new int[arr.length * 2];
        //initialize the extended array and a sum of subset with size k
        int subsum = 0;
//        for (int i = 0; i < arr.length; i++) {
//            extendedArr[i] = arr[i];
//            extendedArr[arr.length + i] = arr[i];
//            if (i < k) {
//                subsum += arr[i];
//                subsum += arr[arr.length / 2 + i];
//            }
//        }
        for (int i = 0; i < k; i++) {
            subsum += arr[i];
            subsum += arr[arr.length / 2 + i];
        }

        int maxResult = subsum;
        for (int i = 1; i < arr.length / 2; i++) {
            subsum -= arr[i - 1];
            subsum -= arr[i - 1 + arr.length / 2];
            subsum += arr[(i + k) % arr.length];
            subsum += arr[(i + k + arr.length / 2) % arr.length];
            maxResult = subsum > maxResult ? subsum : maxResult;
        }
        return maxResult;
    }



    public static void main(String[] args) {

        int[] arr = {1, 5, 1, 3, 7, -3};

        int result = maxHarvest(arr, 2);
        System.out.println(result);

    }
}
