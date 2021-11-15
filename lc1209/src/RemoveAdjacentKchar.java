public class RemoveAdjacentKchar {

    public static String removeDuplicate(String s, int k) {
        char[] str = s.toCharArray();
        int i = 0;//indicate the target output string's char index
        int[] countFreq = new int[s.length()];

        for (int j = 0; j < s.length(); j++, i++) {
            str[i] = str[j];
            if (i == 0) {
                countFreq[i] = 1;
            } else {
                countFreq[i] = str[i] == str[i - 1] ? countFreq[i - 1] + 1 : 1;
            }

            if (countFreq[i] >= k) {
                i -= k;
            }
        }
        return new String(str, 0, i);
    }

    public static void main(String[] args) {
        String test1 = "abcd";
        int k1 = 2;
        String test2 = "deeedbbcccbdaa";
        int k2 = 3;
        String test3 = "pbbcggttciiippooaais";
        int k3 = 2;

        System.out.println(removeDuplicate(test1, k1));
        System.out.println(removeDuplicate(test2, k2));
        System.out.println(removeDuplicate(test3, k3));

    }
}
