public class ParseInt {

    public static int parseInt(int target) {

        int num = target % 10;
        int counts = 1;

        int digit = 1;

        int result = 0;

        target = target / 10;

        while (target > 0) {

            if (target % 10 == num)  {
                counts ++;
            } else {

                int temp = counts * 10 + num;
                result += temp * digit;

                digit *= 100;
                num = target % 10;
                counts = 1;

            }

            target = target / 10;
        }

        int temp = counts * 10 + num;
        result += temp * digit;

        return result;
    }

    public static void main(String[] args) {
        int result = parseInt(1111110111);
        System.out.println(result);
//        System.out.println(Integer.MAX_VALUE);
    }
}
