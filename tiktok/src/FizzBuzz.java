public class FizzBuzz {

    public static void fizzBuzz(int n) {
        if (n < 1) return;
        int count3 = 0;
        int count5 = 0;

        for (int i = 1; i <= n; i++) {
            count3 ++;
            count5 ++;
            if (count3 == 3 && count5 == 5) {
                System.out.println("FizzBuzz");
                count3 = 0;
                count5 = 0;
            } else if (count3 == 3) {
                System.out.println("Fizz");
                count3 = 0;
            } else if (count5 == 5) {
                System.out.println("Buzz");
                count5 = 0;
            } else {
                System.out.println(i);
            }
        }
    }
    public static void main(String[] args) {
        int x = 15;
        fizzBuzz(x);
    }
}
