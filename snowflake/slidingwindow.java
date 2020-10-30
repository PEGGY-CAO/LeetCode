class Result {
 
    /*
     * Complete the 'segment' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER x
     *  2. INTEGER_ARRAY space
     */
 
    public static int segment(int x, List<Integer> space) {
    // Write your code here
        if (space == null || x <= 0) {
            return -1;
        }
        int result = Integer.MIN_VALUE;
        int length = space.size();
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < length; i++) {
            //remove numbers out of range x
            while (!q.isEmpty() && q.peek() < i - x + 1) {
                q.poll();
            }
            // remove larger numbers in x range as they are useless
            while (!q.isEmpty() && space.get(q.peekLast()) > space.get(i)) {
                q.pollLast();
            }
            q.offer(i);
            if (i >= x - 1) {
                result = result > space.get(q.peek()) ? result : space.get(q.peek());
            }                      
        }        
        return result;
    }
 
}
