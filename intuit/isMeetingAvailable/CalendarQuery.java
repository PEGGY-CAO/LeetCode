import java.util.*;

public class CalendarQuery {

    public static boolean isAvailable(int[][] meeting, int startTime, int endTime) {

        for (int i = 0; i < meeting.length; i++) {
            int existingStartTime = meeting[i][0];
            int existingEndTime = meeting[i][1];
            if (startTime <= existingStartTime && endTime > existingStartTime) return false;
            if (startTime < existingEndTime && endTime > existingStartTime) return false;
        }
        return true;
    }


    public static void main(String[] args) {

        int[][] meeting = {
                {800, 900},
                {1340, 1600},
                {915, 1020}
        };
        System.out.println(isAvailable(meeting, 915, 950));//expect false
        System.out.println(isAvailable(meeting, 1020, 1150));//expect true
        System.out.println(isAvailable(meeting, 820, 850));//expect false
    }
}