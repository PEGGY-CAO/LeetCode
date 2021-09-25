public class BalancedArray {

    public static int balancedSum(int[] arr) {
        //corner case
        if (arr.length == 0 || arr.length <= 2) return -1;

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int sumLeft = 0;
        int sumRight = 0;

        for (int pivotIndex = 1; pivotIndex < arr.length - 1; pivotIndex++) {
            sumLeft += arr[pivotIndex - 1];
            sumRight = sum - sumLeft - arr[pivotIndex];
            if (sumLeft == sumRight) {
                return pivotIndex;
            }

        }
        return -1;

    }



    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 6};
        int pivotIndex = balancedSum(arr);
        System.out.println(pivotIndex);
    }
}
