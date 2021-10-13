public class LongestUniformSubstring {

    public static int[] findLongestSub(String eg) {
        if (eg == null || eg.length() == 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int[] result = new int[2];
        int startIndex = 0;
        int length = 1;
        result[1] = 1;
        for (int i = 0; i < eg.length() - 1; i++) {
            if (eg.charAt(i) == eg.charAt(i + 1)) {
                length ++;
            } else {
                if (length > result[1]) {
                    result[0] = startIndex;
                    result[1] = length;
                }
                length = 1;
                startIndex = i + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String eg = "aabbbbbCdAA";
        int[] answer = findLongestSub(eg);
        System.out.println(answer[0] + " " + answer[1]);
    }
}
