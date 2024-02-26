import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;

class RequestsProcess {
    /**
     * AWS has lots of servers. One of the servers is assigned to serve customer requests. There are n requests placed
     * in a queue, where the ith request has a maximum waiting time denoted by wait[i]. That is, if the ith request is
     * not served within wait[i] seconds, the request'll expired and will be removed from the queue. The server
     * processes the request following FIFO rule. At each second, the first request in the queue is processed.
     * At the next second, the processed request and any expired requests are removed from the queue
     *
     * Given the max waiting time of each request denoted by wait[], find the number of requests represent in the queue
     * at every second until it's empty
     *
     * Eg.
     * wait=[2,2,3,1]
     *
     * time = 0: [2,2,3,1]  => 4 requests
     * time  = 1: [2,3] => 2 requests
     * time = 2: [3] => 1 requests
     * time = 3: [] => 0 request
     *
     * Thus answer should be [4,2,1,0]
     *
     * Eg.
     * wait=[3,1,2,1]
     *
     * time=0: [3,1,2,1] => 4
     * time=1: [2] => 1
     * time=2: [] => 0
     *
     * Thus answer should be [4,1,0]
     *
     * Complete the 'findRequestsInQueue' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY wait as parameter.
     * @param List<Integer> wait
     */


    public static List<Integer> findRequestsInQueue(List<Integer> wait) {
        // Write your code here
        int currentTimeCollapsed = 0;

        List<Integer> result = new ArrayList<>();

        int n = wait.size();
        //corner case
        if (n == 1) {
            result.add(1);
            result.add(0);
            return result;
        }

        //build up tree map, key: wait time; value: index

        TreeMap<Integer, List<Integer>> waitTime2index = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            Integer currentWaitTime = wait.get(i);
            List<Integer> indexList = waitTime2index.getOrDefault(currentWaitTime, new ArrayList<>());
            indexList.add(i);
            waitTime2index.put(currentWaitTime, indexList);
        }

        boolean[] isRemoved = new boolean[n];
        int requestInQ = n;

        processedAllRequest:
        for (Integer currentRequest = 0; currentRequest < n; currentRequest++) {
            if (!isRemoved[currentRequest]) {
                //update result
                result.add(requestInQ);

                //update time
                currentTimeCollapsed++;

                //process current request
                isRemoved[currentRequest] = true;
                int currentRequestWaitTime = wait.get(currentRequest);
                requestInQ--;
                //update tree map
                List<Integer> indexList = waitTime2index.get(currentRequestWaitTime);
                indexList.remove(currentRequest);
                if (indexList.size() == 0) {
                    waitTime2index.remove(currentRequestWaitTime);
                }
                if (waitTime2index.size() == 0) {
                    break;
                }
                //update other request
                while (waitTime2index.firstKey() <= currentTimeCollapsed) {
                    List<Integer> expiredRequests = waitTime2index.get(waitTime2index.firstKey());
                    requestInQ-=expiredRequests.size();
                    for (int expiredRequest : expiredRequests) {
                        isRemoved[expiredRequest] = true;
                    }
                    waitTime2index.remove(waitTime2index.firstKey());
                    if (waitTime2index.size() == 0) {
                        break;
                    }
                    if (requestInQ == 0) {
                        break processedAllRequest;
                    }
                }

            }


        }
        result.add(0);
        return result;
    }
    public static void main(String[] args) {

        List<Integer> wait1 = Arrays.asList(2,2,3,1);
        List<Integer> result1 = findRequestsInQueue(wait1);
        System.out.println("Test 1");
        for (Integer i : result1) {
            System.out.println(i);
        }
        System.out.println("Test 2");
        List<Integer> wait2 = Arrays.asList(3,1,2,1);
        List<Integer> result2 = findRequestsInQueue(wait2);
        for (Integer i : result2) {
            System.out.println(i);
        }
        System.out.println("Test 3");
        List<Integer> wait3 = Arrays.asList(100);
        List<Integer> result3 = findRequestsInQueue(wait3);
        for (Integer i : result3) {
            System.out.println(i);
        }
        System.out.println("Test 4");
        List<Integer> wait4 = Arrays.asList(100,1,1,1,1,100);
        List<Integer> result4 = findRequestsInQueue(wait4);
        for (Integer i : result4) {
            System.out.println(i);
        }
    }
}
