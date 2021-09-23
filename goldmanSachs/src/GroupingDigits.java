public class GroupingDigits {

    public static int minSwap(int[] input) {

        int leftMark = 0;

        int left = input[leftMark];

        int minSwapCount = 0;

        if (input.length <= 2) return minSwapCount;

        //skip first n digits that are the same
        for (int i = 1; i < input.length; i++) {
            if (input[i] == left) {
                leftMark++;
            } else break;
        }

        //whole array is 0 or 1, Or the last digit is different from others don't need swap
        if (leftMark == input.length - 1 || leftMark == input.length - 2) return minSwapCount;

        //find the next left then swap it accordingly
        int pointer = leftMark + 2;
        while (pointer < input.length) {
            if (input[pointer] == left) {
                //count the swap
                leftMark++;
                minSwapCount += pointer - leftMark;
                pointer++;
            } else {
                pointer++;
            }
        }

        return minSwapCount;

    }

    public static void main(String[] args) {
        int[] inputArr = {0, 0, 0, 0, 1, 0};
        int result = minSwap(inputArr);
        System.out.println(result);
    }
}
