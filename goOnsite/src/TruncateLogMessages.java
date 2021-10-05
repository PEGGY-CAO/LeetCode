import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


//google onsite round 3. May 20th, 2021
class LogMessage {

    public String sourceFile;
    public String message;

    public LogMessage(String sourceFile, String message) {
        this.sourceFile = sourceFile;
        this.message = message;
    }
}

public class TruncateLogMessages {
//    Given a list of log files containing messages from various producers
//    Truncate the list of files to the given size.
//    Maintain the same (and as high as possible) log message limit across the producers.

//Our goal is to truncate the list down to size max_log_messages.
//That is, we want to return a subset of the original list.
//However, we want to perform the truncation in a “fair” manner.
//Intuitively, we can imagine that it is more fair to truncate “noisy” source files more than “quiet” source files.
//
//For the sake of this problem, our “fair” truncation algorithm is as follows:
//Let X be the max log messages maintained per source file.
//For each source file:
//If the source file has emitted > X messages, truncate the log messages for that source file to X messages.
//If the source file has emitted <= X messages, do not truncate the log messages for that source file.
//
//The goal of this problem is twofold:
//Figure out the maximum value of X which causes the total number of messages retained across all source files to be <= max_log_messages.
//Perform the truncation, returning the truncated list.
 //    eg. maxLogMessages=3
//    [{"file1.txt", "hello"},
//    {"file1.txt", "world"},
//    {"file2.txt", "foo"}]
//
//    X=2
//    keep everything
//
//    if maxLogMessages=2
//    X=1

    private static List<LogMessage> truncateLogMessages(List<LogMessage> messages, int maxLogMessages) {
        //corner cases
        if (messages == null || messages.size() == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> dict = new HashMap<>();

        for (int i = 0; i < messages.size(); i++) {
            String producer = messages.get(i).sourceFile;
            List<String> currentMessages = dict.getOrDefault(producer, new ArrayList<String>());
            currentMessages.add(messages.get(i).message);
            dict.put(producer, currentMessages);
        }

        int curTotalMessages = 0;
        List<LogMessage> result = new ArrayList<>();
        int X = 0;

        //brute-force
        while (curTotalMessages < maxLogMessages) {
            for (String s : dict.keySet()) {
                if (!dict.get(s).isEmpty()) {
                    List<String> availableMessages = dict.get(s);
                    String addToResult = availableMessages.remove(0);
                    LogMessage lm = new LogMessage(s, addToResult);
                    result.add(lm);
                    curTotalMessages++;
                }
            }
            X++;
        }

        System.out.println(X);

        return result;

    }

    public static void main(String[] args) {
        List<LogMessage> eg1 = new ArrayList<>();
        eg1.add(new LogMessage("file1.txt", "hello"));
        eg1.add(new LogMessage("file1.txt", "world"));
        eg1.add(new LogMessage("file2.txt", "foo"));

        List<LogMessage> result = TruncateLogMessages.truncateLogMessages(eg1, 3);
        for (LogMessage lm : result) {
            System.out.println(lm.sourceFile + ", " + lm.message);
        }
    }


}
