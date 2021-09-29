public class NumOfIds {


    public static int numOfIds(String pool) {
        // Write your code here

        //corner case
        if (pool == null || pool.length() < 11) {
            return 0;
        }

        int countEight = 0;
        for (int i = 0; i < pool.length(); i++) {
            int num = Character.getNumericValue(pool.charAt(i));
            if (num == 8) {
                countEight++;
            }
        }

        int possibleTIDs = pool.length() / 11;

        return Math.min(countEight, possibleTIDs);
    }

    public static void main(String[] args) {
        String pool = "82370185994080580";
        System.out.println(numOfIds(pool));
    }
}
