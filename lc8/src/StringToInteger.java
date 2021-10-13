public class StringToInteger {

    public static int myAtoi(String eg) {
        if (eg == null || eg.length() == 0) {
            return 0;
        }

        int index = 0;
        int result = 0;
        //skip space
        while (index < eg.length()) {
            if (eg.charAt(index) == ' ') {
                index++;
            } else {
                break;
            }
        }
        //coner case
        if (index == eg.length()) return 0;
        //decide sign
        int sign = 1;
        if (eg.charAt(index) == '-') {
            sign = -1;
            index ++;
        } else if (eg.charAt(index) == '+') {
            index ++;
        }

        while (index < eg.length()) {
            char cur = eg.charAt(index);
            if (cur - '0' < 0 || cur - '0' > 9) break;
            long temp = (long)result * 10 + cur - '0';
            if (temp * sign > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (temp * sign < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            } else {
                result = (int) temp;
                index ++;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        String eg = "     9274683";
        String empty = "       ";
        String overflow = "      -2873528746825916585264592654862986475";
        System.out.println(myAtoi(overflow));
    }
}
