public class StringCompression {

    public static int compress(char[] example) {

        //corner case
        if (example == null || example.length == 0) {
            throw new IllegalArgumentException("Input array's size should be greater than 0");
        }

        if (example.length == 1) return 1;

        //initialization
        int newLength = 0;
        int index = 0;

        while (index < example.length) {
            char currentC = example[index];
            int count = 0;
            while (index < example.length && currentC == example[index]) {
                index++;
                count++;
            }

            if (count == 1) {
                example[newLength] = currentC;
            } else {
                example[newLength] = currentC;

                String num = Integer.toString(count);
                for (int i = 0; i < num.length(); i++) {
                    newLength++;
                    example[newLength] = num.charAt(i);

                }
            }
            newLength++;

        }

        return newLength;
    }


    public static void main(String[] args) {
        char[] eg1 = {'a'};
        char[] eg2 = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};

        System.out.println(compress(eg2));
    }
}
