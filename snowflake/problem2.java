class Result {
 
    /*
     * Complete the 'getSafePaths' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY journeys as parameter.
     */
 
    public static List<String> getSafePaths(List<String> journeys) {
    // Write your code here
 
        if (journeys == null || journeys.size() == 0) {
            return new ArrayList<String>();
        }
        int length = journeys.size();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            String[] question = journeys.get(i).split(" ");
            
            int x = Integer.parseInt(question[0]);
            int y = Integer.parseInt(question[1]);
            int key = Integer.parseInt(question[2]);
            String answer = findSafe(x, y, key);
            result.add(answer);
        }
    
        return result;
    }
    
    private static String findSafe(int m, int n, int key) {
        StringBuilder sb = new StringBuilder();
        System.out.println("m: " + m + " n: " + n + " key: " + key);
        while (m > 0 && n > 0) {
            long combination = factorial(m + n - 1) / (factorial(n) * factorial(m - 1));
            System.out.println("combination: " + combination);
            if (key >= combination) {
                System.out.println("V");
                sb.append('V');
                key -= combination;
                n = n - 1;
            } else {
                System.out.println("H");
                sb.append('H');
                m = m - 1;
            }
            
        }
        while (m != 0) {
            sb.append('H');
            m--;
        }
        while (n != 0) {
            sb.append('V');
            n--;
        }
        
        return sb.toString();
    }
 
    private static long factorial(int n) 
    { 
        if (n == 0) 
            return 1; 
          
        return n*factorial(n-1); 
    } 
}
